package net.quepierts.libs.math.calculate.expression;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class Assignment extends MathematicalExpression {
    private final Alphabet alphabet;
    private final MathematicalExpression expression;

    public Assignment(Alphabet alphabet, MathematicalExpression expression) {
        this.alphabet = alphabet;
        this.expression = expression;
    }

    @Override
    protected double calculate(Map<Character, Double> parameters) {
        parameters.put(alphabet.get(), expression.result(parameters));
        return 0;
    }

    @Override
    public boolean contains(@NotNull Class<? extends MathematicalExpression> type) {
        return type.equals(Assignment.class) || type.equals(Alphabet.class) || expression.contains(type);
    }

    @Override
    public boolean containsConstant(@NotNull MathematicalExpression constant) {
        return expression.containsConstant(constant);
    }

    public Alphabet getAlphabet() {
        return alphabet;
    }
}
