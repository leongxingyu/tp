package seedu.stockpal.parser;

import seedu.stockpal.commands.HelpCommand;
import seedu.stockpal.commands.ListCommand;
import seedu.stockpal.commands.ExitCommand;
import seedu.stockpal.commands.NewCommand;
import seedu.stockpal.commands.EditCommand;
import seedu.stockpal.commands.DeleteCommand;
import seedu.stockpal.commands.InflowCommand;
import seedu.stockpal.commands.OutflowCommand;
import seedu.stockpal.commands.FindCommand;
import seedu.stockpal.commands.HistoryCommand;
import seedu.stockpal.commands.Command;

import seedu.stockpal.exceptions.InvalidCommandException;
import seedu.stockpal.exceptions.InvalidFormatException;
import seedu.stockpal.exceptions.UnsignedIntegerExceededException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

import static seedu.stockpal.common.Messages.MESSAGE_ERROR_INPUT_INTEGER_EXCEEDED;
import static seedu.stockpal.common.Messages.MESSAGE_ERROR_INVALID_COMMAND;
import static seedu.stockpal.common.Messages.MESSAGE_ERROR_INVALID_FORMAT;
import static seedu.stockpal.common.Messages.MESSAGE_ERROR_NAME_ONLY_SPACES;

/**
 * The Parser class is responsible for parsing user input into its respective command's relevant fields.
 */
public class Parser {
    public static final String DIVIDER = " ";
    public static final String BLANK_SPACING_REGEX = "(?:\\s*)?";
    public static final Pattern NEW_COMMAND_PATTERN =
            Pattern.compile("new " + BLANK_SPACING_REGEX +
                    "n/([a-zA-Z0-9 ()\\[\\],.\\-_]{1,50})" + BLANK_SPACING_REGEX +
                    " q/(\\d+)" + BLANK_SPACING_REGEX +
                    "(?: p/(\\d+\\.\\d{2}))?" + BLANK_SPACING_REGEX +
                    "(?: d/([a-zA-Z0-9 ()\\[\\],.\\-_]+))?");
    public static final Pattern EDIT_COMMAND_PATTERN =
            Pattern.compile("edit " + BLANK_SPACING_REGEX +
                    "(\\d+)" + BLANK_SPACING_REGEX +
                    "(?: n/([a-zA-Z0-9 ()\\[\\],.\\-_]{1,50}))?" + BLANK_SPACING_REGEX +
                    "(?: q/(\\d+))?" + BLANK_SPACING_REGEX +
                    "(?: p/(\\d+\\.\\d{2}))?" + BLANK_SPACING_REGEX +
                    "(?: d/([a-zA-Z0-9 ()\\[\\],.\\-_]+))?");
    public static final Pattern LIST_COMMAND_PATTERN = Pattern.compile("list( -sn| -sq)?" + BLANK_SPACING_REGEX);
    public static final Pattern DELETE_COMMAND_PATTERN =
            Pattern.compile("delete" + BLANK_SPACING_REGEX +
                    "(\\d+)" + BLANK_SPACING_REGEX);
    public static final Pattern INFLOW_COMMAND_PATTERN =
            Pattern.compile("inflow" + BLANK_SPACING_REGEX +
                    "(\\d+)" + BLANK_SPACING_REGEX +
                    "a/(\\d+)" + BLANK_SPACING_REGEX);
    public static final Pattern OUTFLOW_COMMAND_PATTERN =
            Pattern.compile("outflow" + BLANK_SPACING_REGEX +
                    "(\\d+)" + BLANK_SPACING_REGEX +
                    "a/(\\d+)" + BLANK_SPACING_REGEX);
    public static final Pattern FIND_COMMAND_PATTERN =
            Pattern.compile("find ([a-zA-Z0-9 `~!@#$%^&*()\\[\\]{}<>\\-_+=,.?\"':;]+)");
    public static final Pattern HISTORY_COMMAND_PATTERN =
            Pattern.compile("history" + BLANK_SPACING_REGEX +
                    "(\\d+)" + BLANK_SPACING_REGEX);
    public static final Pattern HELP_COMMAND_PATTERN =
            Pattern.compile("help" + BLANK_SPACING_REGEX +
                    "(?: ([a-z]+))?" + BLANK_SPACING_REGEX);
    public static final Pattern EXIT_COMMAND_PATTERN = Pattern.compile("exit(.*)?");
    public static final int NUM_OF_LIST_COMMAND_ARGUMENTS = 1;
    public static final int NUM_OF_NEW_COMMAND_ARGUMENTS = 4;
    public static final int NUM_OF_EDIT_COMMAND_ARGUMENTS = 5;
    public static final int NUM_OF_DELETE_COMMAND_ARGUMENTS = 1;
    public static final int NUM_OF_INFLOW_COMMAND_ARGUMENTS = 2;
    public static final int NUM_OF_OUTFLOW_COMMAND_ARGUMENTS = 2;
    public static final int NUM_OF_FIND_COMMAND_ARGUMENTS = 1;
    public static final int NUM_OF_HISTORY_COMMAND_ARGUMENTS = 1;
    public static final int NUM_OF_HELP_COMMAND_ARGUMENTS = 1;
    public static final int START_INDEX = 0;
    private static final Logger LOGGER = Logger.getLogger(Parser.class.getName());

    private static String getCommandFromInput(String input) {
        if (!input.contains(DIVIDER)) {
            return input;
        }

        return input.substring(START_INDEX, input.indexOf(DIVIDER));
    }
  
    private String validateStringInput(String parsedString) {
        if (isNull(parsedString)) { // conditional branch only applicable to optional description input
            return null;
        }

        String strippedString = parsedString.strip();
        if (strippedString.isEmpty()) {
            return null;
        }
        return strippedString;
    }

    private Integer validateIntegerInput(String parsedInt) throws UnsignedIntegerExceededException {
        if (isNull(parsedInt)) {
            return null;
        }

        try {
            return Integer.parseUnsignedInt(parsedInt);
        } catch (NumberFormatException nfe) {
            throw new UnsignedIntegerExceededException(MESSAGE_ERROR_INPUT_INTEGER_EXCEEDED);
        }
    }

    private Double validateDoubleInput(String parsedDouble) {
        if (isNull(parsedDouble)) {
            return null;
        }
        return Double.parseDouble(parsedDouble);
    }

    private ExitCommand validateAndCreateExitCommand(String input) throws InvalidFormatException {
        input = input.strip();
        Matcher matcher = EXIT_COMMAND_PATTERN.matcher(input.strip());
        if (matcher.matches() && matcher.group(1).isBlank()) {
            return new ExitCommand();
        }
        throw new InvalidFormatException("Exit Command does not take in any extra arguments!");
    }

    private ListCommand validateAndCreateListCommand(ArrayList<String> parsed) {
        String flag = parsed.get(0);

        return new ListCommand(flag);
    }

    private OutflowCommand validateAndCreateOutflowCommand(ArrayList<String> parsed)
            throws UnsignedIntegerExceededException {
        Integer pid = validateIntegerInput(parsed.get(0));
        Integer decreaseBy = validateIntegerInput(parsed.get(1));

        return new OutflowCommand(pid, decreaseBy);
    }

    private InflowCommand validateAndCreateInflowCommand(ArrayList<String> parsed)
            throws UnsignedIntegerExceededException {
        Integer pid = validateIntegerInput(parsed.get(0));
        Integer increaseBy = validateIntegerInput(parsed.get(1));

        return new InflowCommand(pid, increaseBy);
    }

    private DeleteCommand validateAndCreateDeleteCommand(ArrayList<String> parsed)
            throws UnsignedIntegerExceededException {
        Integer pid = validateIntegerInput(parsed.get(0));

        return new DeleteCommand(pid);
    }

    private EditCommand validateAndCreateEditCommand(ArrayList<String> parsed) throws UnsignedIntegerExceededException {
        Integer pid = validateIntegerInput(parsed.get(0));
        String name = validateStringInput(parsed.get(1));
        Integer quantity = validateIntegerInput(parsed.get(2));
        Double price = validateDoubleInput(parsed.get(3));
        String description = validateStringInput(parsed.get(4));

        return new EditCommand(pid, name, quantity, price, description);
    }

    private NewCommand validateAndCreateNewCommand(ArrayList<String> parsed)
            throws UnsignedIntegerExceededException, InvalidFormatException {
        String name = validateStringInput(parsed.get(0));
        if (isNull(name)) {
            throw new InvalidFormatException(MESSAGE_ERROR_NAME_ONLY_SPACES);
        }

        Integer quantity = validateIntegerInput(parsed.get(1));
        Double price = validateDoubleInput(parsed.get(2));
        String description = validateStringInput(parsed.get(3));

        return new NewCommand(name, quantity, price, description);
    }

    private FindCommand validateAndCreateFindCommand(ArrayList<String> parsed) throws InvalidFormatException {
        String name = validateStringInput(parsed.get(0));
        if (isNull(name)) {
            throw new InvalidFormatException(MESSAGE_ERROR_NAME_ONLY_SPACES);
        }

        return new FindCommand(name);
    }

    private HistoryCommand validateAndCreateHistoryCommand(ArrayList<String> parsed)
            throws UnsignedIntegerExceededException {
        Integer pid = validateIntegerInput(parsed.get(0));
        return new HistoryCommand(pid);
    }
            
    private HelpCommand validateAndCreateHelpCommand(ArrayList<String> parsed) throws InvalidFormatException {
        String command = parsed.get(0);
        if (isNull(command)) {
            return new HelpCommand();
        }
        return new HelpCommand(command);
    }

    private static ArrayList<String> matchAndParseInput(String input, Pattern pattern, int numOfArgs)
            throws InvalidFormatException {
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            LOGGER.log(Level.WARNING, MESSAGE_ERROR_INVALID_FORMAT);
            throw new InvalidFormatException(MESSAGE_ERROR_INVALID_FORMAT);
        }

        ArrayList<String> parsed = new ArrayList<>();
        for (int i = 1; i < numOfArgs + 1; i++) {
            String argument = matcher.group(i);
            parsed.add(argument);
        }

        return parsed;
    }

    /**
     * Parses the input string supplied by the user to the UI of StockPal into its respective command fields.
     *
     * @param input The input string to be parsed
     * @return A specific Command object containing parsed components of input
     * @throws InvalidFormatException If input string is not matched with its respective command regex pattern.
     * @throws UnsignedIntegerExceededException If parsed PID or quantity exceeds Integer.MAX_VALUE
     * @throws InvalidCommandException If input's first word (which is command word) is not a legal command word
     */
    public Command parseInput(String input)
            throws InvalidCommandException, InvalidFormatException, UnsignedIntegerExceededException {
        ArrayList<String> parsed;
        input = input.replaceAll("\\s", " ");
        input = input.stripLeading();
        String command = getCommandFromInput(input);

        switch (command) {
        case HelpCommand.COMMAND_KEYWORD:
            parsed = matchAndParseInput(input, HELP_COMMAND_PATTERN, NUM_OF_HELP_COMMAND_ARGUMENTS);
            return validateAndCreateHelpCommand(parsed);

        case ListCommand.COMMAND_KEYWORD:
            parsed = matchAndParseInput(input, LIST_COMMAND_PATTERN, NUM_OF_LIST_COMMAND_ARGUMENTS);
            return validateAndCreateListCommand(parsed);

        case ExitCommand.COMMAND_KEYWORD:
            return validateAndCreateExitCommand(input);

        case NewCommand.COMMAND_KEYWORD:
            parsed = matchAndParseInput(input, NEW_COMMAND_PATTERN, NUM_OF_NEW_COMMAND_ARGUMENTS);
            assert(parsed.get(0) != null); // name group is captured but may be made up only of spaces
            assert(parsed.get(1) != null);
            return validateAndCreateNewCommand(parsed);

        case EditCommand.COMMAND_KEYWORD:
            parsed = matchAndParseInput(input, EDIT_COMMAND_PATTERN, NUM_OF_EDIT_COMMAND_ARGUMENTS);
            assert(parsed.get(0) != null); // pid is captured but may not be found in inventory yet
            return validateAndCreateEditCommand(parsed);

        case DeleteCommand.COMMAND_KEYWORD:
            parsed = matchAndParseInput(input, DELETE_COMMAND_PATTERN, NUM_OF_DELETE_COMMAND_ARGUMENTS);
            assert(parsed.get(0) != null); // pid is captured but may not be found in inventory yet
            return validateAndCreateDeleteCommand(parsed);

        case InflowCommand.COMMAND_KEYWORD:
            parsed = matchAndParseInput(input, INFLOW_COMMAND_PATTERN, NUM_OF_INFLOW_COMMAND_ARGUMENTS);
            assert(parsed.get(0) != null); // pid is captured but may not be found in inventory yet
            assert(parsed.get(1) != null);
            return validateAndCreateInflowCommand(parsed);

        case OutflowCommand.COMMAND_KEYWORD:
            parsed = matchAndParseInput(input, OUTFLOW_COMMAND_PATTERN, NUM_OF_OUTFLOW_COMMAND_ARGUMENTS);
            assert(parsed.get(0) != null); // pid is captured but may not be found in inventory yet
            assert(parsed.get(1) != null);
            return validateAndCreateOutflowCommand(parsed);

        case FindCommand.COMMAND_KEYWORD:
            parsed = matchAndParseInput(input, FIND_COMMAND_PATTERN, NUM_OF_FIND_COMMAND_ARGUMENTS);
            assert(parsed.get(0) != null); // keyword group is captured but may be made up only of spaces
            return validateAndCreateFindCommand(parsed);

        case HistoryCommand.COMMAND_KEYWORD:
            parsed = matchAndParseInput(input, HISTORY_COMMAND_PATTERN, NUM_OF_HISTORY_COMMAND_ARGUMENTS);
            assert(parsed.get(0) != null); // pid is captured but may not be found in inventory yet
            return validateAndCreateHistoryCommand(parsed);

        default:
            LOGGER.log(Level.WARNING, MESSAGE_ERROR_INVALID_COMMAND);
            throw new InvalidCommandException(MESSAGE_ERROR_INVALID_COMMAND);
        }
    }
}
