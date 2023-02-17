package net.quepierts.libs.math.calculate.expression;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class Polynomial extends MathematicalExpression {
    private MathematicalExpression[] expressions;

    public Polynomial(MathematicalExpression[] expressions) {
        this.expressions = expressions;
    }

    @Override
    public double calculate(Map<Character, Double> parameters) {
        double result = 0;

        for (MathematicalExpression expression : expressions) {
            result += expression.result(parameters);
        }

        return result;
    }

    @Override
    public boolean contains(@NotNull Class<? extends MathematicalExpression> type) {
        if (type.equals(Polynomial.class)) {
            return true;
        }

        for (MathematicalExpression expression : this.expressions) {
            if (expression.contains(type)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsConstant(@NotNull MathematicalExpression constant) {
        for (MathematicalExpression expression : this.expressions) {
            if (expression == constant || expression.containsConstant(constant)) {
                return true;
            }
        }

        return false;
    }
}
