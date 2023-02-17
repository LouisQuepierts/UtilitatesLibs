package net.quepierts.libs.math.calculate.function;

import net.quepierts.libs.math.calculate.expression.MathematicalExpression;

import java.util.Map;

public class FunctionPow extends MathematicalFunction {
    private final MathematicalExpression value;
    private final MathematicalExpression power;

    public FunctionPow(MathematicalExpression value, MathematicalExpression power) {
        super(new MathematicalExpression[]{value, power});
        this.value = value;
        this.power = power;
    }

    @Override
    protected double calculate(Map<Character, Double> parameters) {
        return Math.pow(value.result(parameters), power.result(parameters));
    }
}
