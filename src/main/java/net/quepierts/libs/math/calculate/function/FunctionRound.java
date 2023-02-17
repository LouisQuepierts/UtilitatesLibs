package net.quepierts.libs.math.calculate.function;

import net.quepierts.libs.math.calculate.expression.MathematicalExpression;

import java.util.Map;

public class FunctionRound extends MathematicalFunction {
    public FunctionRound(MathematicalExpression[] expressions) {
        super(expressions);
    }

    @Override
    protected double calculate(Map<Character, Double> parameters) {
        return Math.round(inputParameters[0].result(parameters));
    }
}
