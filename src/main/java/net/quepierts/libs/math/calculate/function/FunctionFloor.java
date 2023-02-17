package net.quepierts.libs.math.calculate.function;

import net.quepierts.libs.math.calculate.expression.MathematicalExpression;

import java.util.Map;

public class FunctionFloor extends MathematicalFunction {
    public FunctionFloor(MathematicalExpression[] expressions) {
        super(expressions);
    }

    @Override
    protected double calculate(Map<Character, Double> parameters) {
        return Math.floor(inputParameters[0].result(parameters));
    }
}
