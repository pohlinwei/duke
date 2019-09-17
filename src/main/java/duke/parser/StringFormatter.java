package duke.parser;

/**
 * String formatter that removes a specified string and returns the string that begins after the specified string.
 */
class StringFormatter {
    private StringFormatter() {

    }

    /**
     * Removes a specified string and returns the string that begins after the specified string.
     *
     * @param regex specified string to be excluded
     * @param originalStr original string from which the specified string is to be removed
     * @return the string that begins after the specified string
     */
    static String excludeRegex(String regex, String originalStr) {
        int regexLen = regex.length();
        int regexStartIndex = originalStr.indexOf(regex);
        String editedStr = originalStr.substring(regexLen + regexStartIndex).trim();
        return editedStr;
    }
}
