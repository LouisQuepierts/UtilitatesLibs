package net.quepierts.libs.math.calculate.function;

import net.quepierts.libs.math.calculate.expression.MathematicalExpression;

import java.util.Map;

public class FunctionLg extends MathematicalFunction {
    public FunctionLg(MathematicalExpression[] expressions) {
        super(expressions);
    }

    @Override
    protected double calculate(Map<Character, Double> parameters) {
        return Math.log(inputParameters[0].result(parameters));
    }
}
