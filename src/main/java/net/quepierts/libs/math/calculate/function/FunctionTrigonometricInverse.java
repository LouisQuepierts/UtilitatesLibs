package net.quepierts.libs.math.calculate.function;

import net.quepierts.libs.math.calculate.expression.MathematicalExpression;
import utils.MathUtils;

import java.util.Map;

public abstract class FunctionTrigonometricInverse extends MathematicalFunction {
    public FunctionTrigonometricInverse(MathematicalExpression[] expressions) {
        super(new MathematicalExpression[]{MathUtils.toRadian(expressions[0])});
    }

    public static final class Arcsin extends FunctionTrigonometricInverse {
        public Arcsin(MathematicalExpression[] expressions) {
            super(expressions);
        }

        @Override
        protected double calculate(Map<Character, Double> parameters) {
            return Math.asin(inputParameters[0].result(parameters));
        }
    }

    public static final class Arccos extends FunctionTrigonometricInverse {
        public Arccos(MathematicalExpression[] expressions) {
            super(expressions);
        }

        @Override
        protected double calculate(Map<Character, Double> parameters) {
            return Math.acos(inputParameters[0].result(parameters));
        }
    }

    public static final class Arctan extends FunctionTrigonometricInverse {
        public Arctan(MathematicalExpression[] expressions) {
            super(expressions);
        }

        @Override
        protected double calculate(Map<Character, Double> parameters) {
            return Math.atan(inputParameters[0].result(parameters));
        }
    }

    public static final class Arccsc extends FunctionTrigonometricInverse {
        public Arccsc(MathematicalExpression[] expressions) {
            super(expressions);
        }

        @Override
        protected double calculate(Map<Character, Double> parameters) {
            return Math.asin(1 / inputParameters[0].result(parameters));
        }
    }

    public static final class Arcsec extends FunctionTrigonometricInverse {
        public Arcsec(MathematicalExpression[] expressions) {
            super(expressions);
        }

        @Override
        protected double calculate(Map<Character, Double> parameters) {
            return Math.acos(1 / inputParameters[0].result(parameters));
        }
    }

    public static final class Arccot extends FunctionTrigonometricInverse {
        public Arccot(MathematicalExpression[] expressions) {
            super(expressions);
        }

        @Override
        protected double calculate(Map<Character, Double> parameters) {
            return Math.atan(1 / inputParameters[0].result(parameters));
        }
    }

    public static final class Arsinh extends FunctionTrigonometricInverse {
        public Arsinh(MathematicalExpression[] expressions) {
            super(expressions);
        }

        @Override
        protected double calculate(Map<Character, Double> parameters) {
            double x = inputParameters[0].result(parameters);
            return Math.log(x + Math.sqrt(x * x + 1));
        }
    }

    public static final class Arcosh extends FunctionTrigonometricInverse {
        public Arcosh(MathematicalExpression[] expressions) {
            super(expressions);
        }

        @Override
        protected double calculate(Map<Character, Double> parameters) {
            double x = inputParameters[0].result(parameters);
            return Math.log(x + Math.sqrt(x * x - 1));
        }
    }

    public static final class Artanh extends FunctionTrigonometricInverse {
        public Artanh(MathematicalExpression[] expressions) {
            super(expressions);
        }

        @Override
        protected double calculate(Map<Character, Double> parameters) {
            double x = inputParameters[0].result(parameters);
            return Math.log((1 + x) / (1 - x)) / 2;
        }
    }

    public static final class Arcsch extends FunctionTrigonometricInverse {
        public Arcsch(MathematicalExpression[] expressions) {
            super(expressions);
        }

        @Override
        protected double calculate(Map<Character, Double> parameters) {
            double x = inputParameters[0].result(parameters);
            return Math.log((x + 1) / (x - 1)) / 2;
        }
    }

    public static final class Arsech extends FunctionTrigonometricInverse {
        public Arsech(MathematicalExpression[] expressions) {
            super(expressions);
        }

        @Override
        protected double calculate(Map<Character, Double> parameters) {
            double x = inputParameters[0].result(parameters);
            return Math.log((1 + Math.sqrt(-x * x + 1)) / x);
        }
    }

    public static final class Arcoth extends FunctionTrigonometricInverse {
        public Arcoth(MathematicalExpression[] expressions) {
            super(expressions);
        }

        @Override
        protected double calculate(Map<Character, Double> parameters) {
            double x = inputParameters[0].result(parameters);
            return Math.log((1 / x) + (Math.sqrt(x * x + 1) / Math.abs(x)));
        }
    }
}
