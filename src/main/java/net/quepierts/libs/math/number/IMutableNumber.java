package net.quepierts.libs.math.number;

public abstract class IMutableNumber<T extends Number> extends AUlNumber<T>  {
    public abstract void increment();

    public abstract void decrement();

    public abstract void setValue(Number number);
}
