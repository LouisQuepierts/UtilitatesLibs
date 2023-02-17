package net.quepierts.libs.math.number;

import org.jetbrains.annotations.NotNull;

public class UlMutableDouble extends IMutableNumber<Double> {
    private double value;

    public UlMutableDouble(double number) {
        value = number;
    }

    public UlMutableDouble(Number number) {
        value = number.doubleValue();
    }

    @Override
    public AUlNumber<Double> plus(Number number) {
        value += number.doubleValue();
        return this;
    }

    @Override
    public AUlNumber<Double> subtract(Number number) {
        value -= number.doubleValue();
        return this;
    }

    @Override
    public AUlNumber<Double> multiply(Number number) {
        value *= number.doubleValue();
        return this;
    }

    @Override
    public AUlNumber<Double> divide(Number number) {
        value /= number.doubleValue();
        return this;
    }

    @Override
    public AUlNumber<Double> pow(Number number) {
        value = Math.pow(value, number.doubleValue());
        return this;
    }

    @Override
    public AUlNumber<Double> clone() {
        return new UlMutableDouble(this.value);
    }

    @Override
    public void increment() {
        ++ value;
    }

    @Override
    public void decrement() {
        -- value;
    }

    @Override
    public void setValue(Number number) {
        value = number.doubleValue();
    }

    @Override
    public Double result() {
        return value;
    }

    @Override
    public int compareTo(@NotNull Number o) {
        double other = o.doubleValue();
        return Double.compare(value, other);
    }

    @Override
    public int intValue() {
        return (int) value;
    }

    @Override
    public long longValue() {
        return (long) value;
    }

    @Override
    public float floatValue() {
        return (float) value;
    }

    @Override
    public double doubleValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Double) {
            return value == (Double) obj;
        } else if (obj instanceof Number) {
            return value == ((Number) obj).doubleValue();
        }

        return false;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
