package net.quepierts.libs.math.calculate.function;

import net.quepierts.libs.math.calculate.expression.MathematicalExpression;

import java.util.Map;

public class FunctionCeil extends MathematicalFunction {
    public FunctionCeil(MathematicalExpression[] expressions) {
        super(expressions);
    }

    @Override
    protected double calculate(Map<Character, Double> parameters) {
        return Math.ceil(inputParameters[0].result(parameters));
    }
}
