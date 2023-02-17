package net.quepierts.libs.math.compare;

public class Range extends MathematicalComparator {
    private final Number min;
    private final Number max;
    private boolean includedMin;
    private boolean includedMax;

    public Range(Number min, Number max, boolean includedMin, boolean includedMax) {
        this.min = min;
        this.max = max;
        this.includedMin = includedMin;
        this.includedMax = includedMax;
    }

    public boolean compare(Number number) {
        double value = number.doubleValue();
        return includedMin ? value >= min.doubleValue() : value > min.doubleValue()
                && includedMax ? value <= max.doubleValue() : value < max.doubleValue();
    }
}
