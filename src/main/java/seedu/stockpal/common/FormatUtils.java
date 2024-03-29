package seedu.stockpal.common;

import seedu.stockpal.ui.Ui;

import static seedu.stockpal.common.Messages.HORIZONTAL_LINE;
import static seedu.stockpal.common.Messages.LINE_SEPARATOR;
import static seedu.stockpal.common.Messages.SINGLE_SPACE;

public class FormatUtils {
    private static final String COMMAND_KEYWORD_PREFIX = "Command: ";
    private static final String DESCRIPTION_PREFIX = "Description: ";
    private static final String USAGE_PREFIX = "Usage: ";
    private static final String OPTIONS_PREFIX = "Options:" + LINE_SEPARATOR;
    private static final int FLAG_COL_WIDTH = 22;

    /**
     * Pads text with spaces until a specified length.
     *
     * @param text The text.
     * @param width The length of the text after adding padding.
     * @return The text padded with spaces until a specified length.
     */
    public static String padUntilWidth(String text, int width) {
        String paddedText = text;
        int amountToPad = width - text.length();
        if (amountToPad > 0) {
            paddedText += SINGLE_SPACE.repeat(amountToPad);
        }
        assert paddedText.length() == width;
        return paddedText;
    }

    /**
     * Formats the flag options and flag descriptions for display.
     *
     * @param flags List of flags for the command.
     * @param flagDescriptions List of description for the flags of the command.
     * @return Formatted string of command options and flag descriptions.
     */
    public static String formatCommandOptions(String[] flags, String[] flagDescriptions) {
        if (flags.length == 0) {
            return Messages.EMPTY_STRING;
        }

        String commandOptions = OPTIONS_PREFIX;

        for (int i = 0; i < flags.length; i++) {
            commandOptions += padUntilWidth(flags[i], FLAG_COL_WIDTH);
            commandOptions += Ui.wrapTextWithIndentation(flagDescriptions[i], FLAG_COL_WIDTH);
            commandOptions += LINE_SEPARATOR;
        }

        return commandOptions;
    }

    /**
     * Formats all details of the command.
     *
     * @param keyword Command keyword.
     * @param description Description of the command.
     * @param usage Format of the command.
     * @param flags List of flags that the command accepts.
     * @param flagDescriptions List of description for the flags of the command.
     * @return Formatted string of command details.
     */
    public static String formatCommandDetails(String keyword, String description, String usage
            , String[] flags, String[] flagDescriptions) {
        String command = COMMAND_KEYWORD_PREFIX + keyword + LINE_SEPARATOR;
        String commandDescription = DESCRIPTION_PREFIX
                + Ui.wrapTextWithIndentation(description, DESCRIPTION_PREFIX.length()) + LINE_SEPARATOR;
        String commandUsage = USAGE_PREFIX + usage + LINE_SEPARATOR;
        String commandOptions = formatCommandOptions(flags, flagDescriptions);

        String commandDetails = command + LINE_SEPARATOR
                + commandDescription + LINE_SEPARATOR
                + commandUsage + LINE_SEPARATOR
                + commandOptions
                + HORIZONTAL_LINE + LINE_SEPARATOR;

        assert !commandDetails.isEmpty();
        return commandDetails;
    }

}
