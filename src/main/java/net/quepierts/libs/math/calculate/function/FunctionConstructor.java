package net.quepierts.libs.math.calculate.function;

import net.quepierts.libs.math.calculate.exception.FunctionException;
import net.quepierts.libs.math.calculate.expression.MathematicalExpression;

@FunctionalInterface
public interface FunctionConstructor {
    MathematicalFunction construct(MathematicalExpression[] inputParameters) throws FunctionException;
}
