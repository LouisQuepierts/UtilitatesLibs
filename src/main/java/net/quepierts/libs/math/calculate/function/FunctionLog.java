package net.quepierts.libs.math.calculate.function;

import net.quepierts.libs.math.calculate.expression.MathematicalExpression;
import utils.MathUtils;

import java.util.Map;

public class FunctionLog extends MathematicalFunction {
    public FunctionLog(MathematicalExpression[] expressions) {
        super(expressions);
    }

    @Override
    protected double calculate(Map<Character, Double> parameters) {
        return MathUtils.log(inputParameters[0].result(parameters), inputParameters[1].result(parameters));
    }
}
