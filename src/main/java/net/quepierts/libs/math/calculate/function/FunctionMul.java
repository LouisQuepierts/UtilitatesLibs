package net.quepierts.libs.math.calculate.function;

import net.quepierts.libs.math.calculate.expression.MathematicalExpression;

import java.util.Map;

public class FunctionMul extends MathematicalFunction {
    public FunctionMul(MathematicalExpression... inputParameters) {
        super(inputParameters);
    }

    @Override
    protected double calculate(Map<Character, Double> parameters) {
        double result = inputParameters[0].result(parameters);

        if (result == 0) {
            return 0;
        }

        double v;

        for (int i = 1; i < inputParameters.length; i++) {
            v = inputParameters[i].result(parameters);

            if (v == 0) {
                return 0;
            }

            result *= v;
        }

        return result;
    }
}
