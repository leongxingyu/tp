package seedu.stockpal.commands;

import seedu.stockpal.common.FormatUtils;

import static seedu.stockpal.StockPal.exit;

public class ExitCommand extends Command {
    public static final String COMMAND_KEYWORD = "exit";
    public static final String COMMAND_DESCRIPTION = "Exits the program.";
    public static final String COMMAND_USAGE = "exit";
    public static final String[] COMMAND_FLAGS = {};
    public static final String[] COMMAND_FLAG_DESCRIPTIONS = {};

    @Override
    public void execute() {
        exit();
    }

    public static String commandDetails() {
        String formattedDetails = FormatUtils.formatCommandDetails(COMMAND_KEYWORD, COMMAND_DESCRIPTION
                , COMMAND_USAGE, COMMAND_FLAGS, COMMAND_FLAG_DESCRIPTIONS);
        return formattedDetails;
    }
}
