package net.quepierts.libs.math.number;

public class UlMutableShort extends IMutableNumber<Short> {
    private short value;

    public UlMutableShort(short number) {
        value = number;
    }

    public UlMutableShort(Number number) {
        value = number.shortValue();
    }

    @Override
    public Short result() {
        return value;
    }

    @Override
    public AUlNumber<Short> plus(Number number) {
        value += number.shortValue();
        return this;
    }

    @Override
    public AUlNumber<Short> subtract(Number number) {
        value -= number.shortValue();
        return this;
    }

    @Override
    public AUlNumber<Short> multiply(Number number) {
        value *= number.shortValue();
        return this;
    }

    @Override
    public AUlNumber<Short> divide(Number number) {
        value /= number.shortValue();
        return this;
    }

    @Override
    public AUlNumber<Short> pow(Number number) {
        value = (short) Math.pow(value, number.doubleValue());
        return this;
    }

    @Override
    public UlMutableShort clone() {
        return new UlMutableShort(value);
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
    public void increment() {
        ++ value;
    }

    @Override
    public void decrement() {
        -- value;
    }

    @Override
    public void setValue(Number number) {
        value = number.shortValue();
    }
}
