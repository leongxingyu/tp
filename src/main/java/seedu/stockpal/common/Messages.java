package seedu.stockpal.common;

public class Messages {
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String EMPTY_STRING = "";
    public static final String SINGLE_SPACE = " ";
    public static final String ALLOWED_CHARACTERS = "a-z, A-Z, 0-9, (), [], -, _, comma and dot.";
    public static final String HORIZONTAL_LINE =
            "====================================================================================";
    public static final String BEFORE_USER_UNPUT =
            ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
    public static final String AFTER_USER_UNPUT =
            "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";
    public static final String MESSAGE_WELCOME = "Welcome to StockPal!";
    public static final String MESSAGE_GOODBYE = "Exiting program, goodbye!";
    public static final String MESSAGE_ADDED = "Product has been added.";
    public static final String MESSAGE_REFER_TO_HELP = "For more information on the commands, use `help`.";
    public static final String MESSAGE_INFO_QUANTITY_FORMAT =
            "Ensure that Quantity entered is an integer and is at most " + Integer.MAX_VALUE;
    public static final String MESSAGE_INFO_PRICE_FORMAT =
            "Ensure that price is in one of the allowed format: 10, 1, 1.2, 1.23";
    public static final String MESSAGE_INFO_AMOUNT_FORMAT =
            "Ensure that Amount entered is an integer and is at most " + Integer.MAX_VALUE;

    // ------------------------Start of Parser errors---------------------------------------------------
    public static final String MESSAGE_ERROR_INVALID_COMMAND = "Invalid command. " + MESSAGE_REFER_TO_HELP;

    // Invalid Format
    public static final String MESSAGE_ERROR_INVALID_FORMAT =
            "Formatting issue found!\n" +
            "Ensure no extra arguments and characters are supplied within and after the command.\n";
    public static final String MESSAGE_ERROR_INVALID_NEW_FORMAT = MESSAGE_ERROR_INVALID_FORMAT +
            "New Command takes in only Name, Quantity, Price and Description.";
    public static final String MESSAGE_ERROR_INVALID_EDIT_FORMAT = MESSAGE_ERROR_INVALID_FORMAT +
            "Edit Command takes in only PID, Name, Quantity, Price and Description.";
    public static final String MESSAGE_ERROR_INVALID_INFLOW_FORMAT = MESSAGE_ERROR_INVALID_FORMAT +
            "Inflow Command takes in only PID and Amount.";
    public static final String MESSAGE_ERROR_INVALID_OUTFLOW_FORMAT = MESSAGE_ERROR_INVALID_FORMAT +
            "Outflow Command takes in only PID and Amount.";
    public static final String MESSAGE_ERROR_INVALID_DELETE_FORMAT = MESSAGE_ERROR_INVALID_FORMAT +
            "Delete Command takes in only PID.";
    public static final String MESSAGE_ERROR_INVALID_HISTORY_FORMAT = MESSAGE_ERROR_INVALID_FORMAT +
            "History Command takes in only PID.";
    public static final String MESSAGE_ERROR_INVALID_EXIT_USAGE = MESSAGE_ERROR_INVALID_FORMAT +
            "Exit Command does not take in any extra arguments!";
    public static final String MESSAGE_ERROR_INVALID_LIST_SPECIFIER = "No such format specifier for List Command!";


    // Missing compulsory flags
    public static final String MESSAGE_ERROR_MISSING_NAME_FLAG =
            "n/ flag cannot be detected!\n" +
            "Ensure n/ flag is entered immediately after 'new', using n/<Name>";
    public static final String MESSAGE_ERROR_MISSING_QUANTITY_FLAG =
            "q/ flag cannot be detected!\n" +
            "Ensure that q/ flag is entered immediately after NAME, using q/<Quantity>";

    public static final String MESSAGE_ERROR_MISSING_AMOUNT_FLAG =
            "a/ flag cannot be detected!\n" +
            "Ensure that amount is entered immediately after PID, using a/<amount>";


    // Flag present and input is only whitespaces
    public static final String MESSAGE_ERROR_EMPTY_NAME =
            "Name is empty!\n" +
            " nsure that Name entered is at most 50 characters long and\n" +
            "only contains the following characters: " + ALLOWED_CHARACTERS;
    public static final String MESSAGE_ERROR_EMPTY_QUANTITY =
            "Quantity is empty!\n" + MESSAGE_INFO_QUANTITY_FORMAT;
    public static final String MESSAGE_ERROR_EMPTY_PRICE =
            "Price is empty!\n" + MESSAGE_INFO_PRICE_FORMAT;
    public static final String MESSAGE_ERROR_EMPTY_DESCRIPTION =
            "Description is empty!\n" +
            "Ensure that Description entered is at most 100 characters long and\n" +
            "only contains the following characters: " + ALLOWED_CHARACTERS;

    public static final String MESSAGE_ERROR_EMPTY_AMOUNT =
            "Amount is empty!\n" + MESSAGE_INFO_AMOUNT_FORMAT;

    // Illegal characters used
    public static final String MESSAGE_ERROR_ILLEGAL_CHAR =
            "string contains prohibited characters!\n" +
            "Ensure that string entered only contains the following characters: " + ALLOWED_CHARACTERS;
    public static final String MESSAGE_ERROR_NAME_ILLEGAL_CHAR = "Name " + MESSAGE_ERROR_ILLEGAL_CHAR;
    public static final String MESSAGE_ERROR_DESCRIPTION_ILLEGAL_CHAR = "Description " + MESSAGE_ERROR_ILLEGAL_CHAR;
    public static final String MESSAGE_ERROR_KEYWORD_ILLEGAL_CHAR = "Keyword " + MESSAGE_ERROR_ILLEGAL_CHAR;

    // Invalid length
    public static final String MESSAGE_ERROR_INVALID_NAME_LENGTH =
            "Length of Name is more than 50 characters!\n" +
            "Ensure that Name entered is only at most 50 characters long.";
    public static final String MESSAGE_ERROR_INVALID_DESCRIPTION_LENGTH =
            "Length of description is more than 100!\n" +
            "Ensure that name entered is only at most 100 characters long.";

    // No input at all (with or without flags)
    public static final String MESSAGE_ERROR_MISSING_QUANTITY =
            "Quantity is missing!\n" + MESSAGE_INFO_QUANTITY_FORMAT;
    public static final String MESSAGE_ERROR_MISSING_PRICE =
            "Price is missing!\n" + MESSAGE_INFO_PRICE_FORMAT;
    public static final String MESSAGE_ERROR_MISSING_AMOUNT =
            "Amount is missing!\n" + MESSAGE_INFO_AMOUNT_FORMAT;
    public static final String MESSAGE_ERROR_INVALID_FIND_FORMAT =
            "Keyword is empty! Ensure that a keyword is supplied and\n" +
            "contains only the allowed characters: " + ALLOWED_CHARACTERS;

    // Integer entered contains non-integer characters
    public static final String MESSAGE_ERROR_INT_PARSE_FAIL =
            "Unable to detect integer!\n" +
            "Ensure that there are no extra / illegal characters other than numbers\n" +
            "Ensure that integer is a positive integer no larger than " + Integer.MAX_VALUE;

    public static final String MESSAGE_ERROR_INVALID_PID_FORMAT =
            "Unable to detect PID!\n" +
            "Ensure that there are no extra / illegal characters other than numbers\n" +
            "Ensure that PID is a positive integer no larger than " + Integer.MAX_VALUE;
    public static final String MESSAGE_ERROR_INVALID_PRICE_FORMAT =
            "Price is in wrong format!\n" + MESSAGE_INFO_PRICE_FORMAT;

    // Integer entered too large
    public static final String MESSAGE_ERROR_DOLLAR_EXCEED_MAX_INT =
            "Dollars in Price argument exceeds largest integer allowed! Max integer is " + Integer.MAX_VALUE;
    public static final String MESSAGE_ERROR_INPUT_INTEGER_EXCEEDED =
            "Integer input exceeds largest integer allowed. Max integer is " + Integer.MAX_VALUE;
    public static final String MESSAGE_ERROR_CENTS_EXCEED_LENGTH =
            "Cents in Price argument cannot be more than 2 decimal places!";

    // Amount is zero
    public static final String MESSAGE_ERROR_ZERO_AMOUNT = "Amount cannot be 0!";

    // ---------------------------------End of parser errors-----------------------------------------------

    public static final String MESSAGE_ERROR_MISSING_PARAMETERS = "Please ensure that there is " +
            "at least 1 parameter provided.";
    public static final String MESSAGE_ERROR_PID_NOT_FOUND = "Product with the following PID is not found: ";
    public static final String MESSAGE_EDIT_SUCCESS = "Product details have been updated.";

    public static final String ERROR_MESSAGE_GENERAL = "OOPS!!! Error Occurred: ";
    public static final String WARNING_INVALID_FILEPATH = "OOPS!!! Storage file should end with '.csv'";
    public static final String WARNING_DATA_ERROR = "OOPS!!! Data file contains erroneous input!";
    public static final String WARNING_READ_DATA_ERROR = "OOPS!!! Error reading data file";
    public static final String WARNING_DATA_FILE_FORMAT_ERROR = "OOPS!! Error in data file format!";
    public static final String WARNING_CLOSE_WRITER_ERROR = "OOPS!! Error in closing save file writer!";
    public static final String MESSAGE_FILE_ALR_CREATED = "A non-existent file scenario is already handled earlier.";
  
    public static final String MESSAGE_DELETE_SUCCESS = "Product has been deleted.";
    public static final String MESSAGE_INFLOW_SUCCESS = "Product quantity has been increased successfully.";
    public static final String MESSAGE_OUTFLOW_SUCCESS = "Product quantity has been decreased successfully.";
    public static final String MESSAGE_LIST_SUCCESS = "Products in the product list have been printed.";
    public static final String MESSAGE_EMPTY_LIST = "ProductList is empty";

    public static final String ALERT_LOW_QUANTITY = "These products have low quantity! Please top up!";
    public static final String ALERT_FIRST_LOW_QUANTITY_OCCURRENCE = "Warning! This product is low in quantity.";
    public static final String MESSAGE_NO_LOW_QUANTITY_PRODUCTS = "No products with low quantity";
    public static final String MESSAGE_EMPTY_FIND_LIST = "No matches found.";
    public static final String MESSAGE_EMPTY_TRANSACTION_LIST = "No inflow or outflow found.";
    public static final String LOG_PRINT_HELP_PAGE = "Help page has been printed.";
    public static final String MESSAGE_HISTORY = "History command has ran.";
    public static final String MESSAGE_FIND = "Find command has ran.";

    public static final String DUPLICATE_MESSAGE = "Product name already exists. Please use a different product name";
    public static final String ERROR_KEYBOARD_INTERRUPT = "Error: Keyboard interrupt detected. Exiting StockPal.";
}
