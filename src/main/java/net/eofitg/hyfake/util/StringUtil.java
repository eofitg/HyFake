package net.eofitg.hyfake.util;

import java.util.regex.Pattern;

public class StringUtil {

    private static final Pattern STRIP_COLOR_PATTERN = Pattern.compile("(?i)" + '§' + "[0-9A-FK-OR]");

    public static String stripColor(String input) {
        if (input == null) {
            return null;
        }
        return STRIP_COLOR_PATTERN.matcher(input).replaceAll("");
    }

    public static String removeFormattingCodes(String text) {
        if (text == null || text.length() < 2) return text;
        final int len = text.length();
        final char[] chars = text.toCharArray();
        final char[] newChars = new char[len];
        int count = 0;
        for (int i = 0; i < len; i++) {
            final char c = chars[i];
            if (c == '§' && i + 1 < len && "0123456789abcdefklmnorABCDEFKLMNOR".indexOf(chars[i + 1]) != -1) {
                i++;
                continue;
            }
            newChars[count] = c;
            count++;
        }
        return new String(newChars, 0, count);
    }

}
