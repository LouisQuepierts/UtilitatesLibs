package net.quepierts.libs.math.calculate.expression;

import net.quepierts.libs.math.calculate.exception.FunctionException;
import net.quepierts.libs.math.number.AUlNumber;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import utils.Patterns;
import utils.StringUtils;

import java.util.*;
import java.util.regex.Matcher;

public class MathematicalFormula extends AUlNumber<Double> {
    private static final List<MathematicalFormula> STATIC_FORMULAS = new ArrayList<>();
    private static final List<String> AVALIABLE_NAMES = new ArrayList<>();
    private final Map<Character, Double> parameters = new HashMap<>();
    private final MathematicalExpression expression;

    private final String name;

    public static MathematicalFormula compile(String expression, boolean static_) throws FunctionException {
        expression = analyzeSyntax(expression);

//        System.out.println(expression);

        Matcher matcher = Patterns.FUNCTION_ENTRY.matcher(expression);
        if (matcher.find()) {
            String name = matcher.group();

            expression = expression.replace(name, "");
            name = name.substring(0, name.length() - 1);

            MathematicalFormula result = new MathematicalFormula(compile(expression), name.split("\\(")[0]);

            for (String parameter : StringUtils.extractParameters(name)) {
                if (parameter.isEmpty()) {
                    continue;
                }

                result.trySetParameter(parameter);
            }

            if (static_) {
                STATIC_FORMULAS.add(result);
                AVALIABLE_NAMES.add(name);
            }

            return result;
        } else {
            if (static_) {
                throw new FunctionException("A Formula Without Entry Cannot Be Constant");
            }
            return new MathematicalFormula(compile(expression), "");
        }
    }

    private static MathematicalExpression compile(String expression) throws FunctionException {
        char c;
        LinkedList<StringUtils.IndexedChar> stack = new LinkedList<>();
        LinkedList<Info> cache = new LinkedList<>();

        String value;
        for (int i = 0; i < expression.length(); i++) {
            c = expression.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.addLast(new StringUtils.IndexedChar(i + 1, c));
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
//                    System.out.println(ReportUtils.getIndexError(expression, i, "error_0"));
                    throw new FunctionException("");

                } else if (StringUtils.getRightBracket(stack.getLast().c) == c) {
                    value = expression.substring(stack.removeLast().index, i);

//                    System.out.println("Add To Cache: " + value + ", Cache Size = " + cache.size());
                    addCache(cache, value);
                } else {
//                    System.out.println(ReportUtils.getIndexError(expression, i, "error_1"));
                    throw new FunctionException("");
                }
            }
        }

        addCache(cache, expression);
        return cache.getLast().expression;
    }

    private static void addCache(LinkedList<Info> cache, String value) throws FunctionException {
        String[] splitValue = StringUtils.splitValue(value);

        Info last;
        String replace;
        int i;

        List<MathematicalExpression> values = new ArrayList<>();
        Info[] result = new Info[splitValue.length];

        for (int j = 0; j < splitValue.length; j++) {
            value = splitValue[j];
            replace = value;
            i = 0;
            while (!cache.isEmpty() && value.contains(cache.getLast().value)) {
                last = cache.getLast();
//                System.out.println("Value = " + value + ", Replacement = " + last.value);
                replace = StringUtils.replaceLastValue(replace, last.value, "$v_" + i++, "$v_");

                if (replace.equals(value)) {
                    break;
                }

                values.add(last.expression);
                cache.removeLast();
            }

            result[j] = new Info(value, replace, values);
            values.clear();
        }

        Collections.addAll(cache, result);
    }

    public static String analyzeSyntax(String expression) {
        expression = expression.replace(" ", "");

        StringBuffer buffer = new StringBuffer();

        int indexLast = 0;

        char[] chars = expression.toCharArray();
        char c;
        for (int i = 0; i < chars.length; i++) {
            c = chars[i];

            if (Character.isDigit(c)) {
                if (i - 1 > 0) {
                    if (Character.isAlphabetic(chars[i - 1])) {
                        buffer.append(expression, indexLast, i).append("*");
                        indexLast = i;
                    }
                }

                if (i + 1 < chars.length) {
                    if (Character.isAlphabetic(chars[i + 1]) || chars[i + 1] == '(') {
                        buffer.append(expression, indexLast, i + 1).append("*");
                        indexLast = ++i;
                    }
                }
            } else {
                switch (c) {
                    case '-':
                        if (i > 0 && chars[i - 1] != '(') {
                            buffer.append(expression, indexLast, i);
                            if (i + 2 < chars.length
                                    && chars[i + 1] == '('
                                    && chars[i + 2] == '-') {
                                buffer.append("+(");
                                indexLast = i += 3;
                            } else {
                                buffer.append("+-");
                                indexLast = ++i;
                            }
                        }
                        break;
                    case ')':
                        if (i + 1 < chars.length
                                && (chars[i + 1] == '(' || Character.isLetterOrDigit(chars[i + 1]))) {
                            buffer.append(expression, indexLast, i).append(")*");
                            indexLast = ++i;
                        }
                        break;
                }
            }
        }

        buffer.append(expression, indexLast, chars.length);

        return buffer.toString();
    }

    @Nullable
    public static MathematicalFormula getStatic(String name, int parameterAmount) {
        if (!AVALIABLE_NAMES.contains(name)) {
            return null;
        }
        
        for (MathematicalFormula constant : STATIC_FORMULAS) {
            if (!constant.name.equals(name)) {
                continue;
            }

            if (constant.parameters.size() != parameterAmount) {
                continue;
            }

            return constant;
        }

        return null;
    }

    public static boolean containsStatic(String name) {
        return AVALIABLE_NAMES.contains(name);
    }

    private MathematicalFormula(MathematicalExpression expression, String name) {
        this.expression = expression;
        this.name = name;
    }

    public void setParameter(char alphabet, double amount) {
        this.parameters.put(alphabet, amount);
    }

    public Set<Character> listParameterNames() {
        return this.parameters.keySet();
    }

    private void trySetParameter(String parameter) throws FunctionException {
        if (parameter.contains("=")) {
            String[] split = parameter.split("=");

            if (split[0].length() != 1) {
                throw new FunctionException("Parameter Name Only Can Contains One Alphabet: " + split[0]);
            }

            if (split[0].equalsIgnoreCase("E")) {
                throw new FunctionException("Parameter Name Cannot Be A Mathematical Constant: " + split[0]);
            }

            if (split[0].equals(split[1])) {
                throw new FunctionException("Default Parameter Value Cannot Same With Parameter Name: " + split[1]);
            }

            MathematicalExpression value = MathematicalExpression.valueOf(split[1], new MathematicalExpression[0]);

            if (value.contains(Alphabet.class)) {
                throw new FunctionException("Default Parameter Value Cannot Be Other Unknown Value: " + split[1]);
            }

            this.parameters.put(split[0].charAt(0), value.result(null));
        } else {
            if (parameter.length() != 1) {
                throw new FunctionException("Parameter Name Only Can Contains One Alphabet: " + parameter);
            }

            this.parameters.put(parameter.charAt(0), 1.0);
        }
    }

    public boolean contains(@NotNull Class<? extends MathematicalExpression> type) {
        return expression.contains(type);
    }

    public boolean containConstant(@NotNull MathematicalExpression constant) {
        return expression.containsConstant(constant);
    }

    public Double result(final Map<Character, Double> parameters) {
        return expression.result(parameters);
    }

    @Override
    public Double result() {
        return expression.result(parameters);
    }

    @Override
    public AUlNumber<Double> plus(Number number) throws UnsupportedOperationException {
        return null;
    }

    @Override
    public AUlNumber<Double> subtract(Number number) throws UnsupportedOperationException {
        return null;
    }

    @Override
    public AUlNumber<Double> multiply(Number number) throws UnsupportedOperationException {
        return null;
    }

    @Override
    public AUlNumber<Double> divide(Number number) throws UnsupportedOperationException {
        return null;
    }

    @Override
    public AUlNumber<Double> pow(Number number) throws UnsupportedOperationException {
        return null;
    }

    @Override
    public AUlNumber<Double> clone() {
        return null;
    }

    @Override
    public int intValue() {
        return result().intValue();
    }

    @Override
    public long longValue() {
        return result().longValue();
    }

    @Override
    public float floatValue() {
        return result().floatValue();
    }

    @Override
    public double doubleValue() {
        return result();
    }

    private static class Info {
        private final String value;
        private final MathematicalExpression expression;

        private Info(String string, String replace, List<MathematicalExpression> cache) throws FunctionException {
            this.value = string;
            this.expression = MathematicalExpression.valueOf(replace, cache.toArray(new MathematicalExpression[0]));
        }
    }
}
