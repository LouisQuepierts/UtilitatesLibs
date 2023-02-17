package net.quepierts.libs.math.compare;

public class Equals extends MathematicalComparator {
    private final Number value;

    public Equals(Number number) {
        value = number;
    }

    @Override
    public boolean compare(Number number) {
        return number.doubleValue() == value.doubleValue();
    }
}
