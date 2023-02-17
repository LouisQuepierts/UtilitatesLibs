package net.quepierts.libs.math.calculate.function;

import net.quepierts.libs.math.calculate.expression.MathematicalExpression;

import java.util.Map;

public class FunctionAve extends MathematicalFunction {
    public FunctionAve(MathematicalExpression[] expressions) {
        super(expressions);
    }

    @Override
    protected double calculate(Map<Character, Double> parameters) {
        double sum = 0;

        for (MathematicalExpression parameter : inputParameters) {
            sum += parameter.result(parameters);
        }

        return sum / inputParameters.length;
    }
}
