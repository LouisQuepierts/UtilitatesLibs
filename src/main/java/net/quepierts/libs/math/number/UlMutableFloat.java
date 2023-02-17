package net.quepierts.libs.math.number;

import org.jetbrains.annotations.NotNull;

public class UlMutableFloat extends IMutableNumber<Float> {
    private float value;
    
    public UlMutableFloat(float number) {
        value = number;
    }
    
    public UlMutableFloat(Number number) {
        value = number.floatValue();
    }

    @Override
    public AUlNumber<Float> plus(Number number) {
        value += number.floatValue();
        return this;
    }

    @Override
    public AUlNumber<Float> subtract(Number number) {
        value -= number.floatValue();
        return this;
    }

    @Override
    public AUlNumber<Float> multiply(Number number) {
        value *= number.floatValue();
        return this;
    }

    @Override
    public AUlNumber<Float> divide(Number number) {
        value /= number.floatValue();
        return this;
    }

    @Override
    public AUlNumber<Float> pow(Number number) {
        value = (float) Math.pow(value, number.doubleValue());
        return this;
    }

    @Override
    public AUlNumber<Float> clone() {
        return new UlMutableFloat(value);
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
        value = number.floatValue();
    }

    @Override
    public Float result() {
        return value;
    }

    @Override
    public int compareTo(@NotNull Number o) {
        float other = o.floatValue();
        return Float.compare(value, other);
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
        return value;
    }

    @Override
    public double doubleValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Float) {
            return value == (Float) obj;
        } else if (obj instanceof Number) {
            return value == ((Number) obj).floatValue();
        }
        return false;
    }

    @Override
    public String toString() {
        return Float.toString(value);
    }
}
