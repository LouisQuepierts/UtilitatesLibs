package net.quepierts.libs.math.calculate.function;

import net.quepierts.libs.math.calculate.expression.MathematicalExpression;
import net.quepierts.libs.math.calculate.expression.SingleNumber;
import utils.MathUtils;

import java.util.Map;

public class FunctionSqrt extends MathematicalFunction {
    private static final SingleNumber DEFAULT = new SingleNumber(2);

    private final MathematicalExpression value;
    private final MathematicalExpression power;

    public FunctionSqrt(MathematicalExpression value) {
        super(new MathematicalExpression[]{value, DEFAULT});
        this.value = value;
        this.power = DEFAULT;
    }

    public FunctionSqrt(MathematicalExpression power, MathematicalExpression value) {
        super(new MathematicalExpression[]{power, value});
        this.power = power;
        this.value = value;
    }

    @Override
    protected double calculate(Map<Character, Double> parameters) {
        return MathUtils.sqrt(this.value.result(parameters), this.power.result(parameters));
    }
}
