package net.quepierts.libs.math.number;

import org.jetbrains.annotations.NotNull;

public class UlMutableInteger extends IMutableNumber<Integer> {
    private int value;

    public UlMutableInteger(int number) {
        this.value = number;
    }

    public UlMutableInteger(Number number) {
        this.value = number.intValue();
    }

    @Override
    public AUlNumber<Integer> plus(Number number) {
        value += number.intValue();
        return this;
    }

    @Override
    public AUlNumber<Integer> subtract(Number number) {
        value -= number.intValue();
        return this;
    }

    @Override
    public AUlNumber<Integer> multiply(Number number) {
        value *= number.intValue();
        return this;
    }

    @Override
    public AUlNumber<Integer> divide(Number number) {
        value /= number.intValue();
        return this;
    }

    @Override
    public AUlNumber<Integer> pow(Number number) {
        value = (int) Math.pow(value, number.doubleValue());
        return this;
    }

    @Override
    public AUlNumber<Integer> clone() {
        return new UlMutableInteger(value);
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
        value = number.intValue();
    }

    @Override
    public Integer result() {
        return value;
    }

    @Override
    public int compareTo(@NotNull Number o) {
        int other = o.intValue();
        return Integer.compare(value, other);
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public long longValue() {
        return value;
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
        if (obj instanceof Integer) {
            return value == (Integer) obj;
        } else if (obj instanceof Number) {
            return value == ((Number) obj).intValue();
        }
        return false;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
