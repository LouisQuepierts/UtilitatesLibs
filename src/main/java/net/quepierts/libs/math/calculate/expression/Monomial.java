package net.quepierts.libs.math.calculate.expression;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class Monomial extends MathematicalExpression {
    private MathematicalExpression coefficient;
    private MathematicalExpression value;
    private MathematicalExpression exponent;

    public Monomial(MathematicalExpression coefficient, MathematicalExpression value, MathematicalExpression exponent) {
        this.coefficient = coefficient;
        this.value = value;
        this.exponent = exponent;
    }

    public Monomial(Number coefficient, Number value, Number exponent) {
        this.coefficient = new SingleNumber(coefficient);
        this.value = new SingleNumber(value);
        this.exponent = new SingleNumber(exponent);
    }

    @Override
    public double calculate(Map<Character, Double> parameters) {
        double exponent = this.exponent.result(parameters);

        if (exponent == 0) {
            return coefficient.result(parameters);
        } else if (exponent == 1) {
            return coefficient.result(parameters) * value.result(parameters);
        } else {
            return coefficient.result(parameters) * Math.pow(value.result(parameters), exponent);
        }

    }

    @Override
    public boolean contains(@NotNull Class<? extends MathematicalExpression> type) {
        return type.equals(Monomial.class) || coefficient.contains(type) || value.contains(type) || exponent.contains(type);
    }

    @Override
    public boolean containsConstant(@NotNull MathematicalExpression constant) {
        return this == constant || constant.containsConstant(constant) || value.containsConstant(constant) || exponent.containsConstant(constant);
    }
}
