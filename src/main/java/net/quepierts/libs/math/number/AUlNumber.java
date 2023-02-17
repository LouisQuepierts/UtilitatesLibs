package net.quepierts.libs.math.number;

import net.quepierts.libs.configuration.field.FieldNumber;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.regex.Pattern;

public abstract class AUlNumber<T extends Number> extends Number implements FieldNumber<T>, Calculable<AUlNumber<T>>, Comparable<Number>, Cloneable {
    private static final Pattern patternNumber = Pattern.compile("-?[0-9]+.?[0-9]*");
    public static boolean isNumber(@NotNull String arg) {
        char type = arg.toLowerCase().charAt(arg.length() - 1);
        String number = arg;

        if ('0' >= type || '9' <= type) {
            number = arg.substring(0, arg.length() - 1);
        }

        return patternNumber.matcher(number).matches();
    }

    public static boolean trueType(@NotNull String number, char type) {
        switch (type) {
            case 'd': case 'f':
                return number.contains(".");
            case 'i': case 'l':
                return !number.contains(".");
        }

        return false;
    }

    @Nullable
    public static AUlNumber<?> getMutable(String arg) {
        try {
            char type = arg.toLowerCase().charAt(arg.length() - 1);
            String number = arg.substring(0, arg.length() - 1);

            if ('0' <= type && '9' >= type) {
                if (arg.contains(".")) {
                    return new UlMutableDouble(Double.parseDouble(arg));
                } else {
                    return new UlMutableInteger(Integer.parseInt(arg));
                }
            } else {
                switch (type) {
                    case 'd':
                        return new UlMutableDouble(Double.parseDouble(number));
                    case 'f':
                        return new UlMutableFloat(Float.parseFloat(number));
                    case 'i':
                        return new UlMutableInteger(Integer.parseInt(number));
                    case 'l':
                        return new UlMutableLong(Long.parseLong(number));
                }
            }
        } catch (Exception ignored) {

        }

        return null;
    }

    public static AUlNumber<?> cast(@NotNull Object o, Class<? extends Number> type) throws Exception {
        if (o instanceof AUlNumber) {
            AUlNumber<?> number = (AUlNumber<?>) o;

            if (!number.getTypeClass().equals(type)) {

            }

            return number;
        }

        throw new Exception();
    }

    @SafeVarargs
    public static @NotNull AUlNumber<?> cast(@NotNull Object o, Class<? extends Number>... types) {
        if (o instanceof AUlNumber) {
            AUlNumber<?> number = (AUlNumber<?>) o;

            if (!(types.length == 0 || number.result() instanceof AUlNumber || Arrays.asList(types).contains(number.getTypeClass()))) {
                StringBuffer builder = new StringBuffer("Suggest Types: [");
                for (Class<?> type : types) {
                    builder.append(type.getSimpleName()).append(", ");
                }

                builder.append("], Uses Type: ").append(number.getTypeClass().getSimpleName());

            }

            return number;
        }

        return new UlMutableInteger(0);
    }

    @Nullable
    public static Number getNumber(@NotNull String arg) {
        char type = arg.toLowerCase().charAt(arg.length() - 1);
        String number = arg.substring(0, arg.length() - 1);

        try {
            if ('0' <= type && '9' >= type) {
                if (arg.contains(".")) {
                    return Double.parseDouble(arg);
                } else {
                    return Integer.parseInt(arg);
                }
            } else {
                switch (type) {
                    case 'd':
                        return Double.parseDouble(number);
                    case 'f':
                        return Float.parseFloat(number);
                    case 'i':
                        return Integer.parseInt(number);
                    case 'l':
                        return Long.parseLong(number);
                }
            }
        } catch (Exception ignored) {

        }

        return null;
    }

    public static AUlNumber<?> getMutable(@NotNull Number number) {
        if (number instanceof Double) {
            return new UlMutableDouble(number);
        } else if (number instanceof Float) {
            return new UlMutableFloat(number);
        } else if (number instanceof Integer || number instanceof Byte) {
            return new UlMutableInteger(number);
        } else if (number instanceof Long) {
            return new UlMutableLong(number);
        }

        return new UlMutableInteger(0);
    }

    public static String parseString(@NotNull Number number) {
        char type = 'd';

        if (number instanceof Float) {
            type = 'f';
        } else if (number instanceof Integer) {
            type = 'i';
        } else if (number instanceof Long) {
            type = 'l';
        }

        return number.toString() + type;
    }

    public final Class<? extends Number> getTypeClass() {
        return result().getClass();
    }

    @Override
    public void update(Player player) {

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Number) {
            return obj.equals(this.result());
        }

        return false;
    }

    @Override
    public int compareTo(@NotNull Number o) {
        return Double.compare(this.doubleValue(), o.doubleValue());
    }

    @Override
    public AUlNumber<T> plus(Calculable<?> number) {
        return plus((Number) number);
    }

    @Override
    public AUlNumber<T> subtract(Calculable<?> number) {
        return subtract((Number) number);
    }

    @Override
    public AUlNumber<T> multiply(Calculable<?> number) {
        return multiply((Number) number);
    }

    @Override
    public AUlNumber<T> divide(Calculable<?> number) {
        return divide((Number) number);
    }

    public abstract AUlNumber<T> plus(Number number);

    public abstract AUlNumber<T> subtract(Number number);

    public abstract AUlNumber<T> multiply(Number number);

    public abstract AUlNumber<T> divide(Number number);

    public abstract AUlNumber<T> pow(Number number);

    public abstract AUlNumber<T> clone();
}
