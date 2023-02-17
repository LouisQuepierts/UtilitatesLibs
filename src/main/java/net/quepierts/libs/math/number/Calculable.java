package net.quepierts.libs.math.number;

public interface Calculable<T extends Calculable<?>> extends Cloneable {
    T plus(Calculable<?> number);

    T subtract(Calculable<?> number);

    T multiply(Calculable<?> number);

    T divide(Calculable<?> number);

    T clone();
}
