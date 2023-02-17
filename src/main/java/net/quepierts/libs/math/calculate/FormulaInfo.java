package net.quepierts.libs.math.calculate;

import utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class FormulaInfo {
    private static final Pattern FUNCTION_ENTRY = Pattern.compile("^[\\da-z_]+\\(.*\\)=");

    public static FormulaInfo analyze(String input) {
        input = input.replaceAll("-", "+-").replaceAll(" ", "");
        Matcher matcher = FUNCTION_ENTRY.matcher(input);

        boolean hasEntry = matcher.find();
        if (hasEntry) {
            String entry = matcher.group();
            input = input.replace(entry, "");

            entry = entry.substring(0, entry.length() - 1);

            return new FormulaInfo(entry, valueOf(input), true);
        } else {
            return valueOf(input);
        }
    }

    private static FormulaInfo valueOf(String expression) {
        String[] values = StringUtils.stringsInBrackets(expression);

        LinkedList<FormulaInfo> stack = new LinkedList<>();
        List<FormulaInfo> cache = new ArrayList<>();

        for (String value : values) {
            while (!stack.isEmpty() && stack.getLast().containsBy(value)) {
                //System.out.println("Add To Cache -> " + stack.getLast().rawInfo);
                cache.add(stack.removeLast());
            }

            stack.addLast(new FormulaInfo(value, cache.toArray(new FormulaInfo[0])));

            cache.clear();
        }

        return new FormulaInfo(expression, stack.toArray(new FormulaInfo[0]));
    }

    static final FormulaInfo[] STATIC_EMPTY_INFO = new FormulaInfo[0];

    private final String info;
    private final String rawInfo;
    private final FormulaInfo[] value;

    private final boolean entry;

    private FormulaInfo(String info, FormulaInfo value, boolean input) {
        this.info = info;
        this.rawInfo = info;
        this.value = new FormulaInfo[]{value};

        this.entry = input;
    }

    private FormulaInfo(String info, FormulaInfo[] value) {
        this.value = value == null || value.length == 0 ? STATIC_EMPTY_INFO : value;
        this.rawInfo = info;

        this.entry = false;
        int count = 0;

        for (FormulaInfo formulaInfo : value) {
            if (info.contains(formulaInfo.rawInfo)) {
                info = info.replace(formulaInfo.rawInfo, "$v_" + count++);
                //System.out.println("value = " + formulaInfo.rawInfo + ", coded = " + info);
            }
        }

        this.info = info;
    }

    public boolean empty() {
        return this.value == STATIC_EMPTY_INFO;
    }

    public boolean containsBy(String parent) {
        return parent.contains(rawInfo);
    }

    @Override
    public String toString() {
        return "(" + info + (value.length > 0 ? ", values = " + Arrays.toString(value) : "") + ")";
    }

    public String getInfo() {
        return info;
    }

    public String getRawInfo() {
        return rawInfo;
    }

    public FormulaInfo[] getValue() {
        return value;
    }

    public boolean isEntry() {
        return entry;
    }
}
