package net.quepierts.libs.math.calculate.function;

import net.quepierts.libs.math.calculate.expression.MathematicalExpression;
import utils.MathUtils;

import java.util.Map;

public abstract class FunctionTrigonometric extends MathematicalFunction {
    public FunctionTrigonometric(MathematicalExpression[] expressions) {
        super(new MathematicalExpression[]{MathUtils.toRadian(expressions[0])});
    }

    public static final class Sin extends FunctionTrigonometric {
        public Sin(MathematicalExpression[] expressions) {
            super(expressions);
        }

        @Override
        protected double calculate(Map<Character, Double> parameters) {
            return Math.sin(inputParameters[0].result(parameters));
        }
    }

    public static final class Cos extends FunctionTrigonometric {
        public Cos(MathematicalExpression[] expressions) {
            super(expressions);
        }

        @Override
        protected double calculate(Map<Character, Double> parameters) {
            return Math.cos(inputParameters[0].result(parameters));
        }
    }

    public static final class Tan extends FunctionTrigonometric {
        public Tan(MathematicalExpression[] expressions) {
            super(expressions);
        }

        @Override
        protected double calculate(Map<Character, Double> parameters) {
            return Math.tan(inputParameters[0].result(parameters));
        }
    }

    public static final class Csc extends FunctionTrigonometric {
        public Csc(MathematicalExpression[] expressions) {
            super(expressions);
        }

        @Override
        protected double calculate(Map<Character, Double> parameters) {
            return 1 / Math.sin(this.inputParameters[0].result(parameters));
        }
    }

    public static final class Sec extends FunctionTrigonometric {
        public Sec(MathematicalExpression[] expressions) {
            super(expressions);
        }

        @Override
        protected double calculate(Map<Character, Double> parameters) {
            return 1 / Math.cos(this.inputParameters[0].result(parameters));
        }
    }

    public static final class Cot extends FunctionTrigonometric {
        public Cot(MathematicalExpression[] expressions) {
            super(expressions);
        }

        @Override
        protected double calculate(Map<Character, Double> parameters) {
            return 1 / Math.tan(this.inputParameters[0].result(parameters));
        }
    }


    public static final class Sinh extends FunctionTrigonometric {
        public Sinh(MathematicalExpression[] expressions) {
            super(expressions);
        }

        @Override
        protected double calculate(Map<Character, Double> parameters) {
            return Math.sinh(this.inputParameters[0].result(parameters));
        }
    }

    public static final class Cosh extends FunctionTrigonometric {
        public Cosh(MathematicalExpression[] expressions) {
            super(expressions);
        }

        @Override
        protected double calculate(Map<Character, Double> parameters) {
            return Math.cosh(this.inputParameters[0].result(parameters));
        }
    }

    public static final class Tanh extends FunctionTrigonometric {
        public Tanh(MathematicalExpression[] expressions) {
            super(expressions);
        }

        @Override
        protected double calculate(Map<Character, Double> parameters) {
            return Math.tanh(this.inputParameters[0].result(parameters));
        }
    }

    public static final class Csch extends FunctionTrigonometric {
        public Csch(MathematicalExpression[] expressions) {
            super(expressions);
        }

        @Override
        protected double calculate(Map<Character, Double> parameters) {
            return 1 / Math.sinh(this.inputParameters[0].result(parameters));
        }
    }

    public static final class Sech extends FunctionTrigonometric {
        public Sech(MathematicalExpression[] expressions) {
            super(expressions);
        }

        @Override
        protected double calculate(Map<Character, Double> parameters) {
            return 1 / Math.cosh(this.inputParameters[0].result(parameters));
        }
    }

    public static final class Coth extends FunctionTrigonometric {
        public Coth(MathematicalExpression[] expressions) {
            super(expressions);
        }

        @Override
        protected double calculate(Map<Character, Double> parameters) {
            return 1 / Math.tanh(this.inputParameters[0].result(parameters));
        }
    }
}
