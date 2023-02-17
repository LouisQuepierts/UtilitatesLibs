package net.quepierts.libs.utils;

import net.quepierts.libs.math.calculate.expression.Fraction;
import net.quepierts.libs.math.calculate.expression.MathematicalExpression;
import net.quepierts.libs.math.calculate.expression.SingleNumber;
import net.quepierts.libs.math.calculate.function.FunctionMul;

public class MathUtils {
    public static int commonFactor(int a, int b) {
        int c = 1;

        while (a % b != 0) {
            c = a % b;
            a = b;
            b = c;
        }

        return c;
    }

    public static int commonMultiple(int a, int b) {
        int commonFactor = commonFactor(a, b);

        return a * b / commonFactor;
    }

    public static double sqrt(double value, double power) {
        if (value <= 0) {
            return 0;
        } else if (value == 1) {
            return 1;
        }

        if (power > 0) {
            return Math.pow(value, 1 / power);
        } else {
            return 0;
        }
    }

    public static double log(double base, double value) {
        return Math.log(value) / Math.log(base);
    }

    public static MathematicalExpression toRadian(MathematicalExpression degree) {
        if (!degree.containsConstant(SingleNumber.STATIC_PI)) {
            return new FunctionMul(SingleNumber.STATIC_PI, new Fraction(degree, SingleNumber.STATIC_180));
        }

        return degree;
    }

    public static MathematicalExpression toDegrees(MathematicalExpression radian) {
        if (radian.containsConstant(SingleNumber.STATIC_PI)) {
            return new FunctionMul(SingleNumber.STATIC_180, new Fraction(radian, SingleNumber.STATIC_PI));
        }

        return radian;
    }
}
