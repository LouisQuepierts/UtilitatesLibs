package net.quepierts.libs.math.calculate.function;

import net.quepierts.libs.math.calculate.expression.MathematicalExpression;
import net.quepierts.libs.math.calculate.expression.MathematicalFormula;

import java.util.Map;

public class FunctionPointer extends MathematicalFunction {
    private final MathematicalFormula formula;

    public FunctionPointer(MathematicalExpression[] inputParameters, MathematicalFormula formula) {
        super(inputParameters);
        this.formula = formula;
    }

    @Override
    protected double calculate(Map<Character, Double> parameters) {
        int index = 0;
        for (Character name : formula.listParameterNames()) {
            parameters.put(name, inputParameters[index].result(parameters));

            index ++;
        }
        return formula.result(parameters);
    }
}
