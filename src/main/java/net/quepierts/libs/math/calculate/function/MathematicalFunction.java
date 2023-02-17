package net.quepierts.libs.math.calculate.function;


import net.quepierts.libs.dao.ListRegistry;
import net.quepierts.libs.math.calculate.exception.FunctionException;
import net.quepierts.libs.math.calculate.expression.MathematicalExpression;
import net.quepierts.libs.math.calculate.expression.MathematicalFormula;
import net.quepierts.libs.math.compare.Equals;
import net.quepierts.libs.math.compare.MathematicalComparator;
import net.quepierts.libs.math.compare.Threshold;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class MathematicalFunction extends MathematicalExpression {
    public static final Equals SINGLE_PARAMETER = new Equals(1);

    private static final ListRegistry<Info> registry = new ListRegistry<>();

    public static void init() {
        register("plus", false, new Threshold(2, true, true), FunctionPlus::new);
        register("mul", false, new Threshold(2, true, true), FunctionMul::new);
        register("max", false, new Threshold(2, true, true), FunctionMax::new);
        register("min", false, new Threshold(2, true, true), FunctionMin::new);

        register("round", false, SINGLE_PARAMETER, FunctionRound::new);
        register("floor", false, SINGLE_PARAMETER, FunctionFloor::new);
        register("ceil", false, SINGLE_PARAMETER, FunctionCeil::new);

        register("abs", false, SINGLE_PARAMETER, FunctionAbs::new);
        register("ave", false, SINGLE_PARAMETER, FunctionAve::new);

        register("pow", false, new Equals(2), (inputParameters -> new FunctionPow(inputParameters[0], inputParameters[1])));
        register("sqrt", true, new Equals(2), (inputParameters) -> new FunctionSqrt(inputParameters[0], inputParameters[1]));
        register("sqrt", false, SINGLE_PARAMETER, ((inputParameters) -> new FunctionSqrt(inputParameters[0])));

        register("sin", false, SINGLE_PARAMETER, FunctionTrigonometric.Sin::new);
        register("cos", false, SINGLE_PARAMETER, FunctionTrigonometric.Cos::new);
        register("tan", false, SINGLE_PARAMETER, FunctionTrigonometric.Tan::new);
        register("csc", false, SINGLE_PARAMETER, FunctionTrigonometric.Csc::new);
        register("sec", false, SINGLE_PARAMETER, FunctionTrigonometric.Sec::new);
        register("cot", false, SINGLE_PARAMETER, FunctionTrigonometric.Cot::new);

        register("arcsin", false, SINGLE_PARAMETER, FunctionTrigonometricInverse.Arcsin::new);
        register("arccos", false, SINGLE_PARAMETER, FunctionTrigonometricInverse.Arccos::new);
        register("arctan", false, SINGLE_PARAMETER, FunctionTrigonometricInverse.Arctan::new);
        register("arccsc", false, SINGLE_PARAMETER, FunctionTrigonometricInverse.Arccsc::new);
        register("arcsec", false, SINGLE_PARAMETER, FunctionTrigonometricInverse.Arcsec::new);
        register("arccot", false, SINGLE_PARAMETER, FunctionTrigonometricInverse.Arccot::new);

        register("sinh", false, SINGLE_PARAMETER, FunctionTrigonometric.Sinh::new);
        register("cosh", false, SINGLE_PARAMETER, FunctionTrigonometric.Cosh::new);
        register("tanh", false, SINGLE_PARAMETER, FunctionTrigonometric.Tanh::new);
        register("csch", false, SINGLE_PARAMETER, FunctionTrigonometric.Csch::new);
        register("sech", false, SINGLE_PARAMETER, FunctionTrigonometric.Sech::new);
        register("coth", false, SINGLE_PARAMETER, FunctionTrigonometric.Coth::new);

        register("arsinh", false, SINGLE_PARAMETER, FunctionTrigonometricInverse.Arsinh::new);
        register("arcosh", false, SINGLE_PARAMETER, FunctionTrigonometricInverse.Arcosh::new);
        register("artanh", false, SINGLE_PARAMETER, FunctionTrigonometricInverse.Artanh::new);
        register("arcsch", false, SINGLE_PARAMETER, FunctionTrigonometricInverse.Arcsch::new);
        register("arsech", false, SINGLE_PARAMETER, FunctionTrigonometricInverse.Arsech::new);
        register("arcoth", false, SINGLE_PARAMETER, FunctionTrigonometricInverse.Arcoth::new);

        register("log", true, new Equals(2), FunctionLog::new);
        register("lg", false, SINGLE_PARAMETER, FunctionLg::new);

        register("sum", true, new Equals(3), FunctionSum::new);
        register("prod", true, new Equals(3), FunctionProd::new);
    }

    public static void register(String name, boolean hasFunctionalParameters, MathematicalComparator inputParameterAmount, FunctionConstructor constructor) {
        registry.register(name, new Info(hasFunctionalParameters, inputParameterAmount, constructor));
    }

    public static boolean isAvaliableFunction(String name) {
        return registry.containsKey(name) || MathematicalFormula.containsStatic(name);
    }

    public static MathematicalFunction construct(String name, boolean hasFunctionalParameters, MathematicalExpression[] inputParameters) throws FunctionException {
        List<Info> infoList = registry.get(name);

        if (infoList == null) {
            FunctionPointer pointer = constructPointer(name, inputParameters);

            if (pointer != null) {
                return pointer;
            }

            throw new FunctionException("Invaluable Function Name: " + name);
        }

        for (Info info : infoList) {
            if (info.hasFunctionalParameters != hasFunctionalParameters) {
                continue;
            }

            if (info.inputParameterAmount.compare(inputParameters.length)) {
                return info.constructor.construct(inputParameters);
            }
        }

        throw new FunctionException("Invaluable Function Parameters Amount: " + inputParameters.length);
    }

    private static FunctionPointer constructPointer(String name, MathematicalExpression[] inputParameters) {
        MathematicalFormula static_ = MathematicalFormula.getStatic(name, inputParameters.length);

        if (static_ == null) {
            return null;
        }

        return new FunctionPointer(inputParameters, static_);
    }

    protected final MathematicalExpression[] inputParameters;

    public MathematicalFunction(MathematicalExpression[] expressions) {
        this.inputParameters = expressions;
    }

    @Override
    public boolean contains(@NotNull Class<? extends MathematicalExpression> type) {
        if (this.getClass().isAssignableFrom(type)) {
            return true;
        }

        for (MathematicalExpression expression : inputParameters) {
            if (expression.getClass().isAssignableFrom(type)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsConstant(@NotNull MathematicalExpression constant) {
        for (MathematicalExpression inputParameter : inputParameters) {
            if (inputParameter == constant || inputParameter.containsConstant(constant)) {
                return true;
            }
        }

        return false;
    }

    private static class Info {
        private final boolean hasFunctionalParameters;

        private final MathematicalComparator inputParameterAmount;
        private final FunctionConstructor constructor;

        private Info(boolean hasFunctionalParameters, MathematicalComparator inputParameterAmount, FunctionConstructor constructor) {
            this.hasFunctionalParameters = hasFunctionalParameters;
            this.inputParameterAmount = inputParameterAmount;

            this.constructor = constructor;
        }
    }
}
