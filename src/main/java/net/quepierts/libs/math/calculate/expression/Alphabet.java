package net.quepierts.libs.math.calculate.expression;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class Alphabet extends MathematicalExpression {
    private final char alphabet;

    public Alphabet(char alphabet) {
        this.alphabet = alphabet;
    }

    @Override
    public double calculate(Map<Character, Double> parameters) {
        return parameters.get(alphabet);
    }

    @Override
    public boolean contains(@NotNull Class<? extends MathematicalExpression> type) {
        return type.equals(Alphabet.class);
    }

    @Override
    public boolean containsConstant(@NotNull MathematicalExpression constant) {
        return this == constant;
    }

    public final char get() {
        return this.alphabet;
    }
}
