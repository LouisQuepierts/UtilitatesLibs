package net.quepierts.libs.math.compare;

public class Threshold extends MathematicalComparator {
    private final Number threshold;
    private boolean bigger;
    private boolean equal;

    public Threshold(Number threshold, boolean bigger, boolean equal) {
        this.threshold = threshold;
        this.bigger = bigger;
        this.equal = equal;
    }

    public boolean compare(Number number) {
        double a = number.doubleValue();
        double b = threshold.doubleValue();

        if (!bigger) {
            a = threshold.doubleValue();
            b = number.doubleValue();
        }

        return equal ? a >= b : a > b;
    }
}
