package seedu.stockpal.commands;

import seedu.stockpal.common.FormatUtils;
import seedu.stockpal.common.Messages;
import seedu.stockpal.exceptions.InvalidFormatException;
import seedu.stockpal.exceptions.StockPalException;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.stockpal.common.Messages.HORIZONTAL_LINE;
import static seedu.stockpal.common.Messages.LINE_SEPARATOR;
import static seedu.stockpal.ui.Ui.printToScreenWithoutNewlineAtEnd;

public class HelpCommand extends Command {
    public static final String COMMAND_KEYWORD = "help";
    public static final String COMMAND_DESCRIPTION = "Provides command details for all or specific commands.";
    public static final String COMMAND_USAGE = "help [COMMAND_KEYWORD]";
    public static final String[] COMMAND_FLAGS = {"COMMAND_KEYWORD"};
    public static final String[] COMMAND_FLAG_DESCRIPTIONS = {
        "The command for which you wish to display the help page.\n" +
            "Leaving this option empty will display the help page for\n" +
            "all commands.\n" +
            "Commands available are: help, new, list, edit, delete,\n" +
            "inflow, outflow, history, find, exit"
    };

    protected static Logger logger = Logger.getLogger(seedu.stockpal.commands.HelpCommand.class.getName());
    private static final String CASE_PRINT_ALL_COMMANDS = "all";
    private static final Set<String> COMMAND_KEYWORDS = Set.of(NewCommand.COMMAND_KEYWORD
            , ListCommand.COMMAND_KEYWORD, EditCommand.COMMAND_KEYWORD , DeleteCommand.COMMAND_KEYWORD
            , InflowCommand.COMMAND_KEYWORD , OutflowCommand.COMMAND_KEYWORD , HistoryCommand.COMMAND_KEYWORD
            , FindCommand.COMMAND_KEYWORD, ExitCommand.COMMAND_KEYWORD, HelpCommand.COMMAND_KEYWORD);
    private final String command;

    public HelpCommand() {
        this.command = CASE_PRINT_ALL_COMMANDS;
    }

    public HelpCommand(String command) throws InvalidFormatException {
        if (!COMMAND_KEYWORDS.contains(command)) {
            throw new InvalidFormatException(Messages.MESSAGE_ERROR_INVALID_COMMAND);
        }
        this.command = command;
    }

    @Override
    public void execute() throws StockPalException {
        String formattedText = HORIZONTAL_LINE + LINE_SEPARATOR;

        if (this.command.equals(CASE_PRINT_ALL_COMMANDS)) {
            formattedText += getAllCommandsDetails();
        } else {
            assert this.command != null;
            formattedText += getSpecificCommandDetails();
        }

        printToScreenWithoutNewlineAtEnd(formattedText);
        logger.log(Level.INFO, Messages.LOG_PRINT_HELP_PAGE);
    }

    /**
     * Combines help page for all commands.
     *
     * @return Help page for all commands
     */
    private String getAllCommandsDetails() {
        return NewCommand.commandDetails() + EditCommand.commandDetails() + DeleteCommand.commandDetails()
                + InflowCommand.commandDetails() + OutflowCommand.commandDetails() + FindCommand.commandDetails()
                + ListCommand.commandDetails() + ExitCommand.commandDetails() + HelpCommand.commandDetails();
    }

    /**
     * Obtains the help page of a specific command.
     *
     * @return Help page of specified command.
     */
    private String getSpecificCommandDetails() {
        switch(this.command) {
        case NewCommand.COMMAND_KEYWORD:
            return NewCommand.commandDetails();
        case EditCommand.COMMAND_KEYWORD:
            return EditCommand.commandDetails();
        case DeleteCommand.COMMAND_KEYWORD:
            return DeleteCommand.commandDetails();
        case InflowCommand.COMMAND_KEYWORD:
            return InflowCommand.commandDetails();
        case OutflowCommand.COMMAND_KEYWORD:
            return OutflowCommand.commandDetails();
        case FindCommand.COMMAND_KEYWORD:
            return FindCommand.commandDetails();
        case ListCommand.COMMAND_KEYWORD:
            return ListCommand.commandDetails();
        case ExitCommand.COMMAND_KEYWORD:
            return ExitCommand.commandDetails();
        case HelpCommand.COMMAND_KEYWORD:
            return HelpCommand.commandDetails();
        case HistoryCommand.COMMAND_KEYWORD:
            return HistoryCommand.commandDetails();
        default:
            return Messages.EMPTY_STRING;
        }
    }

    public static String commandDetails() {
        String formattedDetails = FormatUtils.formatCommandDetails(COMMAND_KEYWORD, COMMAND_DESCRIPTION
                , COMMAND_USAGE, COMMAND_FLAGS, COMMAND_FLAG_DESCRIPTIONS);
        return formattedDetails;
    }
}
