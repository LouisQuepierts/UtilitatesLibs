package net.quepierts.libs.utils;

import com.google.common.collect.ImmutableMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class StringUtils {
    static final Map<Character, Character> map =
            new ImmutableMap.Builder<Character, Character>()
                    .put('(', ')').put('{', '}').put('[', ']').build();

    public static char getRightBracket(char left) {
        return map.get(left);
    }

    public static boolean isValid(CharSequence input) {
        char c;
        LinkedList<Character> stack = new LinkedList<>();

        for (short i = 0; i < input.length(); i++) {
            c = input.charAt(i);

            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    return false;
                } else if (map.get(stack.getLast()) == c) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static String[] stringsInBrackets(String input, final int deep, final boolean include) {
        char c;
        char last = 0;
        int deep_ = 0;
        int index = 0;

        List<String> result = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                deep_ ++;
                last = c;

                if (deep_ == deep) {
                    index = i + 1;
                }
            } else if (c == ')' || c == '}' || c == ']') {
                if (map.get(last) == c) {
                    deep_ --;

                    if (deep_ + 1 == deep) {
                        result.add(input.substring(include ? index - 1 : index, include ? i + 1 : i));
                    }
                } else {
                    System.out.println(ReportUtils.getIndexError(input, i, "error_1"));
                    return new String[0];
                }
            }
        }

        return result.toArray(new String[0]);
    }

    public static String[] stringsInBrackets(String input) {
        char c;
        LinkedList<IndexedChar> stack = new LinkedList<>();

        List<String> result = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.addLast(new IndexedChar(i + 1, c));
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    System.out.println(ReportUtils.getIndexError(input, i, "error_0"));
                    return new String[0];
                } else if (map.get(stack.getLast().c) == c) {
                    result.add(input.substring(stack.removeLast().index, i));
                } else {
                    System.out.println(ReportUtils.getIndexError(input, i, "error_1"));
                    return new String[0];
                }
            }
        }

        return result.toArray(new String[0]);
    }

    public static String[] stringsIn(String input, char left, char right) {
        char c;
        int index = 0;

        List<String> result = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);

            if (c == left) {
                index = i + 1;
            } else if (c == right) {
                result.add(input.substring(index, i));
            }
        }

        return result.toArray(new String[0]);
    }

    public static String[] stringsIn(String input, char bet) {
        char c;
        int index = -1;

        List<String> result = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);

            if (c == bet) {
                if (index == -1) {
                    index = i + 1;
                } else {
                    result.add(input.substring(index, i));
                    index = -1;
                }
            }
        }

        return result.toArray(new String[0]);
    }

    /*
    * Split the parameters with ","
    * */
    public static String[] splitValue(String input) {
        if (!input.contains(",")) {
            return new String[]{input};
        }

        char c;
        int deep = 0;
        int indexFrom = 0;

        List<String> result = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);

            if (c == '(' || c == '{' || c == '[') {
                ++ deep;
            } else if (c == ')' || c == '}' || c == ']') {
                -- deep;
            } else if (c == ',') {
                if (deep == 0) {
                    String substring = input.substring(indexFrom, i);
                    result.add(substring);
                    indexFrom = i + 1;
                }
            }
        }

        result.add(input.substring(indexFrom));

        return result.toArray(new String[0]);
    }

    public static String[] splitAt(String input, int index) {
        return new String[]{input.substring(0, index), input.substring(index)};
    }

    public static String[] extractParameters(String input) {
        String values = stringsIn(input, '(', ')')[0];
        return values.split(",");
    }

    public static String replaceFirst(@NotNull String input,
                                      @NotNull String target,
                                      @NotNull String replacement) {
        int index = input.indexOf(target);

        if (index == -1) {
            return new StringBuffer(input.substring(0, index)).append(replacement).append(input.substring(index + target.length())).toString();
        }

        return input;
    }

    public static String replaceFirst(@NotNull String input,
                                      @NotNull String target,
                                      @NotNull String replacement,
                                      @Nullable String ignoredPrefix) {
        int index = input.indexOf(target);

        if (index == -1) {
            return input;
        }

        if (ignoredPrefix != null && !ignoredPrefix.isEmpty()) {
            int i = input.indexOf(ignoredPrefix);

            if (input.contains(ignoredPrefix)) {
                if (i < index) {
                    while (input.charAt(index - ignoredPrefix.length()) == ignoredPrefix.charAt(0)) {
                        index = input.indexOf(target, index + 1);
                    }
                }
            }
        }

        return new StringBuffer(input.substring(0, index)).append(replacement).append(input.substring(index + target.length())).toString();
    }

    public static String replaceFirst(@NotNull String input,
                                      @NotNull String target,
                                      @NotNull String replacement,
                                      @Nullable String ignoredPrefix,
                                      @NotNull int indexFrom) {
        int index = 0;

        do {
            index = input.indexOf(target, index);
        } while (index != -1 && indexFrom > index);

        if (index == -1) {
            return input;
        }

        if (!(ignoredPrefix == null || ignoredPrefix.isEmpty())) {
            int i = input.indexOf(ignoredPrefix);

            if (input.contains(ignoredPrefix)) {
                if (i < index) {
                    while (input.charAt(index - ignoredPrefix.length()) == ignoredPrefix.charAt(0)) {
                        index = input.indexOf(target, index + 1);
                    }
                }
            }
        }

        return new StringBuffer(input.substring(0, index)).append(replacement).append(input.substring(index + target.length())).toString();
    }

    public static String replaceLast(String input, String target, String replacement) {
        int index = input.lastIndexOf(target);

        if (index != -1) {
            return input.substring(0, index) + replacement + input.substring(index + target.length());
        }

        return input;
    }

    public static String replaceLast(@NotNull String input,
                                     @NotNull String target,
                                     @NotNull String replacement,
                                     @Nullable String ignoredPrefix) {
        int index = input.lastIndexOf(target);

        if (index == -1) {
            return input;
        }

        if (ignoredPrefix != null) {
            int i = input.lastIndexOf(ignoredPrefix);

            if (input.contains(ignoredPrefix)) {
                if (i < index) {
                    while (charAt(input, index - ignoredPrefix.length()) == ignoredPrefix.charAt(0)) {
                        index = input.substring(0, index).lastIndexOf(target);
                    }
                }
            }
        }

        return input.substring(0, index) + replacement + input.substring(index + target.length());
    }

    public static String replaceFirstValue(@NotNull String input,
                                           @NotNull String target,
                                           @NotNull String replacement,
                                           @Nullable String ignoredPrefix) {
        String[] split = stringsInBrackets(input, 1, true);
        String replaced;

        for (String value : split) {
            replaced = replaceFirst(value, target, replacement, ignoredPrefix);

            if (!replaced.equals(value)) {
                return input.replace(value, replaced);
            }
        }

        return input;
    }

    public static String replaceLastValue(@NotNull String input,
                                          @NotNull String target,
                                          @NotNull String replacement,
                                          @Nullable String ignoredPrefix) {
        String[] split = stringsInBrackets(input, 1, true);

        String replace;
        String value;

        for (int i = split.length - 1; i > -1; i--) {
            value = split[i];

            replace = replaceLast(value, target, replacement, ignoredPrefix);

            if (!replace.equals(value)) {
                return input.replace(value, replace);
            }
        }

        return input;
    }

    public static char charAt(String input, int index) {
        int length = input.length();

        if (index > length || index < 0) {
            return 0;
        }

        return input.charAt(index);
    }

    public static String[] stringsBetween(String input, char front, char back) {
        char c;
        LinkedList<Integer> stack = new LinkedList<>();
        List<String> result = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            if (c == front) {
                stack.push(i + 1);
            } else if (c == back) {
                if (stack.isEmpty()) {
                    return new String[0];
                } else {
                    result.add(input.substring(stack.getLast(), i));
                    stack.pop();
                }
            }
        }

        return result.toArray(new String[0]);
    }

    public static boolean containsAll(CharSequence charSequence, CharSequence... chars) {
        String arg = charSequence.toString();
        for (CharSequence c : chars) {
            if (!arg.contains(c)) {
                return false;
            }
        }

        return true;
    }

    public static boolean containsAny(CharSequence charSequence, CharSequence... chars) {
        String arg = charSequence.toString();
        for (CharSequence c : chars) {
            if (arg.contains(c)) {
                return true;
            }
        }

        return false;
    }

    public static boolean containsValue(String expression, String value) {
        List<String> values = new ArrayList<>();
        for (String fullValue : stringsInBrackets(expression, 1, true)) {
            System.out.println("FullValue = " + fullValue);
            Collections.addAll(values, splitValue(fullValue));
        }

        return values.contains(value);
    }

    public static boolean isDigital(CharSequence charSequence) {
        char c;
        for (int i = 0; i < charSequence.length(); i++) {
            c = charSequence.charAt(i);

            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    public static class IndexedChar {
        public final int index;
        public final char c;

        public IndexedChar(int index, char c) {
            this.index = index;
            this.c = c;
        }
    }
}
