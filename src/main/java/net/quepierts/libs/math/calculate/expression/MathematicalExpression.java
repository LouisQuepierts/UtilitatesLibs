package net.quepierts.libs.math.calculate.expression;

import net.quepierts.libs.math.calculate.exception.FunctionException;
import net.quepierts.libs.math.calculate.function.FunctionMul;
import net.quepierts.libs.math.calculate.function.FunctionPlus;
import net.quepierts.libs.math.calculate.function.FunctionPow;
import net.quepierts.libs.math.calculate.function.MathematicalFunction;
import net.quepierts.libs.math.number.AUlNumber;
import org.jetbrains.annotations.NotNull;
import utils.Patterns;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

public abstract class MathematicalExpression {
    protected boolean negative = false;

    public final double result(final Map<Character, Double> parameter) {
        return negative ? -calculate(parameter) : calculate(parameter);
    }

    protected abstract double calculate(final Map<Character, Double> parameters);

    public abstract boolean contains(@NotNull Class<? extends MathematicalExpression> type);

    public abstract boolean containsConstant(@NotNull MathematicalExpression constant);

    public static MathematicalExpression valueOf(String expression, @NotNull MathematicalExpression[] values) throws FunctionException {
        boolean negative = expression.startsWith("-");

//        System.out.println("Start Construct: " + expression + ", Parameters Amount = " + values.length + ", Negative = " + negative);

        if (negative) {
            expression = expression.substring(1);
        }

        String signal = getSignal(expression);
        String[] parts = expression.split(signal);

        List<MathematicalExpression> expressions = new ArrayList<>();

        if (parts.length > 1) {
            for (String part : parts) {
                if (part.isEmpty()) continue;
                expressions.add(valueOf(part, values));
            }
        } else {
            Matcher matcher = Patterns.PLACEHOLDER.matcher(expression);

            while (matcher.find()) {
                expressions.add(values[Integer.parseInt(matcher.group().substring(3))]);
            }
        }

//        System.out.println("Expression = " + expression + ", signal = " + signal + ", parameters = " + expressions.size());

        return construct(expression, negative, signal, expressions.toArray(new MathematicalExpression[0]));
    }

    private static String getSignal(String expression) {
        String signal = " ";

        if (expression.contains("+")) {
            signal = "\\+";
        } else if (expression.contains("/")) {
            signal = "/";
        } else if (expression.contains("\u002a")) {
            signal = "\\*"; // *
        } else if (expression.contains("\u005e")) {
            signal = "\\^"; // ^
        } else if (Patterns.FUNCTION.matcher(expression).matches()) {
            signal = "function";
        } else if (expression.contains("=")) {
            signal = "=";
        }

        return signal;
    }

    private static MathematicalExpression construct(String expression, boolean negative, String signal, MathematicalExpression... expressions) throws FunctionException {
//        System.out.println("signal = " + signal + ", expression = " + expression);

        MathematicalExpression result;

        switch (signal) {
            case "\\+":
                result = new FunctionPlus(expressions);
                break;
            case "/":
                result = new Fraction(expressions[0], expressions[1]);
                break;
            case "\\*":
                result = new FunctionMul(expressions);
                break;
            case "\\^":
                result = new FunctionPow(expressions[0], expressions[1]);
                break;
            case "function":
                result = getFunction(expression, expressions);
                break;
            case "=":
                result = getAssignment(expressions);
                break;
            default:
                if (expressions.length == 1) {
                    return expressions[0];
                } else {
                    result = getSingle(expression);
                }
        }

        result.negative = negative;
        return result;
    }

    private static MathematicalExpression getAssignment(MathematicalExpression[] expressions) throws FunctionException {
        if (!(expressions[0] instanceof Alphabet)) {
            throw new FunctionException("");
        }

        return new Assignment((Alphabet) expressions[0], expressions[1]);
    }

    private static MathematicalExpression getFunction(String expression, MathematicalExpression[] parameters) throws FunctionException {
        boolean advanced = expression.contains("{");
        String name = expression.split(advanced ? "\\{" : "\\(")[0];

        //System.out.println("Construct Function " + name + ", By Expression " + expression);

        return MathematicalFunction.construct(name, advanced, parameters);
    }

    private static MathematicalExpression getSingle(String expression) {
        MathematicalExpression result;

//        System.out.println("Expression = " + expression + ", length = " + expression.length());

        if (expression.equalsIgnoreCase("pi")) {
            result = SingleNumber.STATIC_PI;
        } else if (expression.equals("e")) {
            result = SingleNumber.STATIC_E;
        } else if (expression.length() == 1) {
            char c = expression.charAt(0);

            if (Character.isDigit(c)) {
                result = new SingleNumber(Character.getNumericValue(c));
            } else {
                result = new Alphabet(c);
            }
        } else {
            result = new SingleNumber(AUlNumber.getNumber(expression));
        }

        return result;
    }
}
