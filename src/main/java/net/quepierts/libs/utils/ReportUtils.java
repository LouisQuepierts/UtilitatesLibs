package net.quepierts.libs.utils;

public class ReportUtils {
    public static String getIndexError(CharSequence info, int index, CharSequence print) {
        StringBuilder builder = new StringBuilder(print);
        builder.append(": \"").append(info).append("\"\n").append("at:");

        for (int i = 0; i < index + print.length(); i++) {
            builder.append(" ");
        }

        builder.append("'");
        return builder.toString();
    }
}
