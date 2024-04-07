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

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;
import static seedu.stockpal.common.Messages.MESSAGE_ERROR_INPUT_INTEGER_EXCEEDED;
import static seedu.stockpal.common.Messages.MESSAGE_ERROR_INVALID_AMOUNT_FORMAT;
import static seedu.stockpal.common.Messages.MESSAGE_ERROR_INVALID_COMMAND;
import static seedu.stockpal.common.Messages.MESSAGE_ERROR_INVALID_DELETE_USAGE;
import static seedu.stockpal.common.Messages.MESSAGE_ERROR_INVALID_DESCRIPTION_LENGTH;
import static seedu.stockpal.common.Messages.MESSAGE_ERROR_INVALID_EXIT_USAGE;
import static seedu.stockpal.common.Messages.MESSAGE_ERROR_INVALID_INFLOW_USAGE;
import static seedu.stockpal.common.Messages.MESSAGE_ERROR_INVALID_NAME_FORMAT;
import static seedu.stockpal.common.Messages.MESSAGE_ERROR_INVALID_NAME_LENGTH;
import static seedu.stockpal.common.Messages.MESSAGE_ERROR_INVALID_OUTFLOW_USAGE;
import static seedu.stockpal.common.Messages.MESSAGE_ERROR_INVALID_PID_FORMAT;
import static seedu.stockpal.common.Messages.MESSAGE_ERROR_INVALID_PRICE_FORMAT;
import static seedu.stockpal.common.Messages.MESSAGE_ERROR_INVALID_QUANTITY_FORMAT;

/**
 * The Parser class is responsible for parsing user input into its respective command's relevant fields.
 */
public class Parser {
    public static final String DIVIDER = " ";
    public static final Pattern PID_PATTERN = Pattern.compile("^(\\d+)(.*)?");
    public static final Pattern COMPULSORY_NAME_PATTERN =
            Pattern.compile("^n/([a-zA-Z0-9 ()\\[\\],.\\-_]+) q/(.*)");
    public static final Pattern OPTIONAL_NAME_PATTERN =
            Pattern.compile("^n/([a-zA-Z0-9 ()\\[\\],.\\-_]+) (q/|p/|d/|.*)?");
    public static final Pattern QUANTITY_PATTERN = Pattern.compile("^q/(\\d+)(.*)?");
    public static final Pattern AMOUNT_PATTERN = Pattern.compile("^a/(\\d+)(.*)?");
    public static final Pattern PRICE_PATTERN = Pattern.compile("^p/(\\d+)\\.(\\d{2})(.*)?");
    public static final Pattern DESCRIPTION_PATTERN = Pattern.compile("^d/([a-zA-Z0-9 ()\\[\\],.\\-_]+)");
    public static final Pattern LIST_FLAG_PATTERN = Pattern.compile("(-sn|-sq)?");
    public static final Pattern KEYWORD_PATTERN = Pattern.compile("([a-zA-Z0-9 ()\\[\\],.\\-_]+)");
    public static final Pattern EXIT_COMMAND_PATTERN = Pattern.compile("exit(.*)?");
    public static final int FLAG_LENGTH = 2;
    public static final int START_INDEX = 0;

    private static final Logger LOGGER = Logger.getLogger(Parser.class.getName());

    private static String getCommandFromInput(String input) {
        if (!input.contains(DIVIDER)) {
            return input;
        }

        return input.substring(START_INDEX, input.indexOf(DIVIDER));
    }

    private Integer parseInteger(String intString) throws UnsignedIntegerExceededException {
        try {
            return Integer.parseUnsignedInt(intString);
        } catch (NumberFormatException nfe) {
            throw new UnsignedIntegerExceededException(MESSAGE_ERROR_INPUT_INTEGER_EXCEEDED);
        }
    }
    private Double parsePrice(String priceString) throws UnsignedIntegerExceededException {
        String dollarsString = priceString.substring(0, priceString.indexOf("."));
        String centsString = "0" + priceString.substring(priceString.indexOf("."));
        try {
            Integer dollars = parseInteger(dollarsString);
            Double cents = Double.parseDouble(centsString);
            return dollars + cents;
        } catch (NumberFormatException nfe) {
            throw new UnsignedIntegerExceededException(MESSAGE_ERROR_INVALID_PRICE_FORMAT);
        }
    }

    private String validatePrice(String input) {
        Matcher priceMatcher = PRICE_PATTERN.matcher(input);
        if (priceMatcher.matches()) {
            String dollarString = priceMatcher.group(1);
            String centsString = priceMatcher.group(2);

            return dollarString + "." + centsString;
        }
        return null;
    }
    private String validatePid(String input) throws InvalidFormatException {
        Matcher pidMatcher = PID_PATTERN.matcher(input);
        if (!pidMatcher.matches()) {
            throw new InvalidFormatException(MESSAGE_ERROR_INVALID_PID_FORMAT);
        }
        return pidMatcher.group(1);
    }

    private String validateAmount(String input) throws InvalidFormatException {
        Matcher amountMatcher = AMOUNT_PATTERN.matcher(input);
        if (!amountMatcher.matches()) {
            throw new InvalidFormatException(MESSAGE_ERROR_INVALID_AMOUNT_FORMAT);
        }
        return amountMatcher.group(1);
    }

    private String validateQuantity(String input) {
        Matcher quantityMatcher = QUANTITY_PATTERN.matcher(input);
        if (quantityMatcher.matches()) {
            return quantityMatcher.group(1);
        }

        return null;
    }

    private String validateString(Pattern pattern, String input) {
        if (input.isEmpty()) {
            return null;
        }

        Matcher stringMatcher = pattern.matcher(input);
        if (stringMatcher.matches()) {
            return stringMatcher.group(1);
        }
        return null;
    }

    private ExitCommand createExitCommand(String input) throws InvalidFormatException {
        input = input.strip();
        Matcher matcher = EXIT_COMMAND_PATTERN.matcher(input.strip());
        if (matcher.matches() && matcher.group(1).isBlank()) {
            return new ExitCommand();
        }
        throw new InvalidFormatException(MESSAGE_ERROR_INVALID_EXIT_USAGE);
    }

    private ListCommand createListCommand(String input) {
        String flag = null;

        input = input.substring(ListCommand.COMMAND_KEYWORD.length()).stripLeading();
        Matcher listFlagMatcher = LIST_FLAG_PATTERN.matcher(input);

        if (listFlagMatcher.matches()) {
            flag = listFlagMatcher.group(1);
        }

        return new ListCommand(flag);
    }

    private OutflowCommand createOutflowCommand(String input)
            throws InvalidFormatException, UnsignedIntegerExceededException {
        Integer pid;
        Integer decreaseBy;

        input = input.substring(OutflowCommand.COMMAND_KEYWORD.length()).stripLeading();

        String pidString = validatePid(input);
        pid = parseInteger(pidString);
        input = input.substring(pidString.length()).stripLeading();

        String amountString = validateAmount(input);
        decreaseBy = parseInteger(amountString);

        input = input.substring(amountString.length() + FLAG_LENGTH).stripLeading();
        if (!input.isEmpty()) {
            throw new InvalidFormatException(MESSAGE_ERROR_INVALID_OUTFLOW_USAGE);
        }

        return new OutflowCommand(pid, decreaseBy);
    }

    private InflowCommand createInflowCommand(String input)
            throws InvalidFormatException, UnsignedIntegerExceededException {
        Integer pid;
        Integer increaseBy;

        input = input.substring(InflowCommand.COMMAND_KEYWORD.length()).stripLeading();

        String pidString = validatePid(input);
        pid = parseInteger(pidString);
        input = input.substring(pidString.length()).stripLeading();

        String amountString = validateAmount(input);
        increaseBy = parseInteger(amountString);

        input = input.substring(amountString.length() + FLAG_LENGTH).stripLeading();

        if (!input.isEmpty()) {
            throw new InvalidFormatException(MESSAGE_ERROR_INVALID_INFLOW_USAGE);
        }

        return new InflowCommand(pid, increaseBy);
    }


    private DeleteCommand createDeleteCommand(String input)
            throws InvalidFormatException, UnsignedIntegerExceededException {
        Integer pid;

        input = input.substring(DeleteCommand.COMMAND_KEYWORD.length()).stripLeading();

        String pidString = validatePid(input);
        pid = parseInteger(pidString);

        input = input.substring(pidString.length()).stripLeading();
        if (!input.isEmpty()) {
            throw new InvalidFormatException(MESSAGE_ERROR_INVALID_DELETE_USAGE);
        }

        return new DeleteCommand(pid);
    }

    private EditCommand createEditCommand(String input)
            throws InvalidFormatException, UnsignedIntegerExceededException {
        Integer pid;
        String name = null;
        Integer quantity = null;
        Double price = null;
        String description = null;

        input = input.substring(EditCommand.COMMAND_KEYWORD.length()).stripLeading();

        String pidString = validatePid(input);
        pid = parseInteger(pidString);

        input = input.substring(pidString.length()).stripLeading();

        String nameString = validateString(OPTIONAL_NAME_PATTERN, input);
        if (!isNull(nameString)) {
            if (nameString.strip().length() > 50) {
                throw new InvalidFormatException(MESSAGE_ERROR_INVALID_NAME_LENGTH);
            }
            if (!nameString.isBlank()) {
                name = nameString.strip();
            }
            input = input.substring(nameString.length() + FLAG_LENGTH).stripLeading();
        }

        String quantityString = validateQuantity(input);
        if (!isNull(quantityString)) {
            quantity = parseInteger(quantityString);
            input = input.substring(quantityString.length() + FLAG_LENGTH).stripLeading();
        }

        String priceString = validatePrice(input);
        if (!isNull(priceString)) {
            price = parsePrice(priceString);
            input = input.substring(priceString.length() + FLAG_LENGTH).stripLeading();
        }

        String descriptionString = validateString(DESCRIPTION_PATTERN, input);
        if (!isNull(descriptionString)) {
            if (descriptionString.strip().length() > 100) {
                throw new InvalidFormatException(MESSAGE_ERROR_INVALID_DESCRIPTION_LENGTH);
            }
            if (!descriptionString.isBlank()) {
                description = descriptionString.strip();
            }
        }

        return new EditCommand(pid, name, quantity, price, description);
    }

    private NewCommand createNewCommand(String input)
            throws InvalidFormatException, UnsignedIntegerExceededException {
        String name;
        Integer quantity;
        Double price = null;
        String description = null;

        input = input.substring(NewCommand.COMMAND_KEYWORD.length()).stripLeading();

        String nameString = validateString(COMPULSORY_NAME_PATTERN, input);
        if (isNull(nameString)) {
            throw new InvalidFormatException(MESSAGE_ERROR_INVALID_NAME_FORMAT);
        }
        if (nameString.isBlank()) {
            throw new InvalidFormatException(MESSAGE_ERROR_INVALID_NAME_FORMAT);
        }
        if (nameString.strip().length() > 50) {
            throw new InvalidFormatException(MESSAGE_ERROR_INVALID_NAME_LENGTH);
        }
        name = nameString.strip();
        input = input.substring(nameString.length() + FLAG_LENGTH).stripLeading();

        String quantityString = validateQuantity(input);
        if (isNull(quantityString)) {
            throw new InvalidFormatException(MESSAGE_ERROR_INVALID_QUANTITY_FORMAT);
        }
        quantity = parseInteger(quantityString);
        input = input.substring(quantityString.length() + FLAG_LENGTH).stripLeading();

        String priceString = validatePrice(input);
        if (!isNull(priceString)) {
            price = parsePrice(priceString);
            input = input.substring(priceString.length() + FLAG_LENGTH).stripLeading();
        }

        String descriptionString = validateString(DESCRIPTION_PATTERN, input);
        if (!isNull(descriptionString)) {
            if (descriptionString.strip().length() > 100) {
                throw new InvalidFormatException(MESSAGE_ERROR_INVALID_DESCRIPTION_LENGTH);
            }
            description = descriptionString.strip();
        }

        return new NewCommand(name, quantity, price, description);
    }

    private FindCommand createFindCommand(String input) throws InvalidFormatException {
        input = input.substring(FindCommand.COMMAND_KEYWORD.length()).stripLeading();
        if (input.isEmpty()) {
            throw new InvalidFormatException("Find Command takes in 1 keyword!");
        }

        Matcher keywordMatcher = KEYWORD_PATTERN.matcher(input);
        if (!keywordMatcher.matches()) {
            throw new InvalidFormatException("Please use only permitted characters! The following are permitted:" +
                    "a-z, A-Z, 0-9, (), [], -, _, comma and dot");
        }
        String keyword = keywordMatcher.group(1);

        return new FindCommand(keyword);
    }

    private HistoryCommand createHistoryCommand(String input)
            throws InvalidFormatException, UnsignedIntegerExceededException {
        Integer pid;

        input = input.substring(HistoryCommand.COMMAND_KEYWORD.length()).stripLeading();

        Matcher pidMatcher = PID_PATTERN.matcher(input);
        if (!pidMatcher.matches()) {
            throw new InvalidFormatException("PID is in wrong format!");
        }
        try {
            pid = Integer.parseUnsignedInt(pidMatcher.group(1));
            input = input.substring(pidMatcher.group(1).length()).stripLeading();
        } catch (NumberFormatException nfe) {
            throw new UnsignedIntegerExceededException(MESSAGE_ERROR_INPUT_INTEGER_EXCEEDED);
        }

        if (!input.isEmpty()) {
            throw new InvalidFormatException("History Command does not take in any extra arguments other than PID!");
        }

        return new HistoryCommand(pid);
    }

    private HelpCommand createHelpCommand(String input) throws InvalidFormatException {
        input = input.substring(HelpCommand.COMMAND_KEYWORD.length()).stripLeading();
        if (input.isEmpty()) {
            return new HelpCommand();
        }
        return new HelpCommand(input);
    }

    /**
     * Parses the input string supplied by the user to the UI of StockPal into its respective command fields.
     *
     * @param input The input string to be parsed
     * @return A specific Command object containing parsed components of input
     * @throws InvalidFormatException If input string is not matched with its respective argument's regexes.
     * @throws UnsignedIntegerExceededException If parsed PID, quantity or dollars in price exceeds Integer.MAX_VALUE
     * @throws InvalidCommandException If input's first word (which is command word) is not a legal command word
     */
    public Command parseInput(String input)
            throws InvalidCommandException, InvalidFormatException, UnsignedIntegerExceededException {
        input = input.replaceAll("\\s", " ");
        input = input.stripLeading();
        String command = getCommandFromInput(input);

        switch (command) {
        case HelpCommand.COMMAND_KEYWORD:
            return createHelpCommand(input);

        case ListCommand.COMMAND_KEYWORD:
            return createListCommand(input);

        case ExitCommand.COMMAND_KEYWORD:
            return createExitCommand(input);

        case NewCommand.COMMAND_KEYWORD:
            return createNewCommand(input);

        case EditCommand.COMMAND_KEYWORD:
            return createEditCommand(input);

        case DeleteCommand.COMMAND_KEYWORD:
            return createDeleteCommand(input);

        case InflowCommand.COMMAND_KEYWORD:
            return createInflowCommand(input);

        case OutflowCommand.COMMAND_KEYWORD:
            return createOutflowCommand(input);

        case FindCommand.COMMAND_KEYWORD:
            return createFindCommand(input);

        case HistoryCommand.COMMAND_KEYWORD:
            return createHistoryCommand(input);

        default:
            LOGGER.log(Level.WARNING, MESSAGE_ERROR_INVALID_COMMAND);
            throw new InvalidCommandException(MESSAGE_ERROR_INVALID_COMMAND);
        }
    }
}
