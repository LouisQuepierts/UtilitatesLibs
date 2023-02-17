package net.quepierts.libs.utils;

import java.util.regex.Pattern;

public class Patterns {
    public static final Pattern PLACEHOLDER = Pattern.compile("\\$v_\\d+");

    public static final Pattern FUNCTION = Pattern.compile("^[a-z_\\d]+(\\{.+})?\\(.+\\)$");

    public static final Pattern FUNCTION_ENTRY = Pattern.compile("^[a-zA-Z_]+\\(.*\\)=");
}
