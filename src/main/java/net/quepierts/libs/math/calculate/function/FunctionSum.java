package net.quepierts.libs.math.calculate.function;

import net.quepierts.libs.math.calculate.exception.FunctionException;
import net.quepierts.libs.math.calculate.expression.Assignment;
import net.quepierts.libs.math.calculate.expression.MathematicalExpression;

import java.util.Map;

public class FunctionSum extends MathematicalFunction {
    private final Assignment lower;
    private final MathematicalExpression upper;
    private final MathematicalExpression calculate;

    public FunctionSum(MathematicalExpression[] inputParameters) throws FunctionException {
        super(inputParameters);

        if (!(inputParameters[0] instanceof Assignment)) {
            throw new FunctionException("");
        }

        this.lower = (Assignment) inputParameters[0];
        this.upper = inputParameters[1];
        this.calculate = inputParameters[2];
    }

    @Override
    protected double calculate(Map<Character, Double> parameters) {
        this.lower.result(parameters);

        char alphabet = this.lower.getAlphabet().get();
        double result = 0;

        for (int i = (int) Math.round(parameters.get(alphabet)); i < upper.result(parameters) + 1; i++) {
            result += this.calculate.result(parameters);

            parameters.put(alphabet, parameters.get(alphabet) + 1);
        }

        parameters.remove(alphabet);

        return result;
    }
}
