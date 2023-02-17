package net.quepierts.libs.math.calculate.expression;

import org.jetbrains.annotations.NotNull;
import utils.MathUtils;

import java.util.Map;

public class Fraction extends MathematicalExpression {
    private MathematicalExpression numerator;
    private MathematicalExpression denominator;

    public Fraction(MathematicalExpression numerator, MathematicalExpression denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction(Number numerator, Number denominator) {
        this.numerator = new SingleNumber(numerator);
        this.denominator = new SingleNumber(denominator);
    }

    public static Fraction valueOf(Number number) {
        int numerator = 1;
        int denominator = number.intValue();

        if (number instanceof Double || number instanceof Float) {
            double value = number.doubleValue();
            denominator = 1;

            while (value % 1 != 0) {
                value *= 10;
                denominator *= 10;
            }

            numerator = (int) value;
        }

        int commonFactor = MathUtils.commonFactor(numerator, denominator);

        return new Fraction(numerator / commonFactor, denominator / commonFactor);
    }

    @Override
    public double calculate(Map<Character, Double> parameters) {
        return numerator.result(parameters) / denominator.result(parameters);
    }

    @Override
    public boolean contains(@NotNull Class<? extends MathematicalExpression> type) {
        return type.equals(Fraction.class);
    }

    @Override
    public boolean containsConstant(@NotNull MathematicalExpression constant) {
        return numerator == constant || denominator == constant;
    }
}
