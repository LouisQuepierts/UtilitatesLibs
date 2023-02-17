package net.quepierts.libs.math.calculate.function;

import net.quepierts.libs.math.calculate.expression.MathematicalExpression;

import java.util.Map;

public class FunctionPlus extends MathematicalFunction {
    public FunctionPlus(MathematicalExpression[] inputParameters) {
        super(inputParameters);
    }

    @Override
    protected double calculate(Map<Character, Double> parameters) {
        double result = inputParameters[0].result(parameters);

        for (int i = 1; i < inputParameters.length; i++) {
            result += inputParameters[i].result(parameters);
        }

        return result;
    }
}
