package net.quepierts.libs.math.calculate.expression;

import net.quepierts.libs.math.number.AUlNumber;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class SingleNumber extends MathematicalExpression {
    public static final SingleNumber STATIC_ONE = new SingleNumber(1);
    public static final SingleNumber STATIC_ZERO = new SingleNumber(0);
    public static final SingleNumber STATIC_180 = new SingleNumber(180);

    public static final SingleNumber STATIC_PI = new SingleNumber(Math.PI);
    public static final SingleNumber STATIC_E = new SingleNumber(Math.E);

    private final AUlNumber<?> number;

    public SingleNumber(AUlNumber<?> number) {
        this.number = number;
    }

    public SingleNumber(Number number) {
        this.number = AUlNumber.getMutable(number);
    }

    @Override
    public double calculate(Map<Character, Double> parameters) {
        return number.doubleValue();
    }

    @Override
    public boolean contains(@NotNull Class<? extends MathematicalExpression> type) {
        return type.equals(SingleNumber.class);
    }

    @Override
    public boolean containsConstant(@NotNull MathematicalExpression constant) {
        return this == constant;
    }
}
