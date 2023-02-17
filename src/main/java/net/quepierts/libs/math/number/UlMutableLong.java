package net.quepierts.libs.math.number;

import org.jetbrains.annotations.NotNull;

public class UlMutableLong extends IMutableNumber<Long> {
    private long value;

    public UlMutableLong(long number) {
        value = number;
    }

    public UlMutableLong(Number number) {
        value = number.longValue();
    }

    @Override
    public AUlNumber<Long> plus(Number number) {
        value += number.longValue();
        return this;
    }

    @Override
    public AUlNumber<Long> subtract(Number number) {
        value -= number.longValue();
        return this;
    }

    @Override
    public AUlNumber<Long> multiply(Number number) {
        value *= number.longValue();
        return this;
    }

    @Override
    public AUlNumber<Long> divide(Number number) {
        value /= number.longValue();
        return this;
    }

    @Override
    public AUlNumber<Long> pow(Number number) {
        value = (long) Math.pow(value, number.doubleValue());
        return this;
    }

    @Override
    public AUlNumber<Long> clone() {
        return new UlMutableLong(value);
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
        value = number.longValue();
    }

    @Override
    public Long result() {
        return value;
    }

    @Override
    public int compareTo(@NotNull Number o) {
        long other = o.longValue();
        return Long.compare(value, other);
    }

    @Override
    public int intValue() {
        return (int) value;
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
        if (obj instanceof Long) {
            return value == (Long) obj;
        } else if (obj instanceof Number) {
            return value == ((Number) obj).longValue();
        }

        return false;
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }
}
