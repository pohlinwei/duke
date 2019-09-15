package duke.parser;

public class StringFormatter {
    private StringFormatter() {}

    public static String excludeRegex(String regex, String originalStr) {
        int regexLen = regex.length();
        int regexStartIndex = originalStr.indexOf(regex);
        String editedStr = originalStr.substring(regexLen + regexStartIndex).trim();
        return editedStr;
    }
}
