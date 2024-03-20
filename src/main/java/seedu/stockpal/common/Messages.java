package seedu.stockpal.common;

public class Messages {
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String HORIZONTAL_LINE =
            "====================================================================================";
    public static final String MESSAGE_WELCOME = "Welcome to StockPal!"
            + LINE_SEPARATOR
            + "Enter a command to begin.";
    public static final String MESSAGE_GOODBYE = "Exiting program, goodbye!";
    public static final String MESSAGE_ADDED = "Product added!";
    public static final String MESSAGE_REFER_TO_HELP = "For more information on the commands, use `help`.";
    public static final String MESSAGE_ERROR_INVALID_COMMAND = "Invalid command. " + MESSAGE_REFER_TO_HELP;
    public static final String MESSAGE_ERROR_INVALID_FORMAT = "Invalid format. " + MESSAGE_REFER_TO_HELP;
    public static final String MESSAGE_ERROR_MISSING_PARAMETERS = "Please ensure that there is " +
            "at least 1 parameter provided.";
    public static final String MESSAGE_ERROR_INPUT_INTEGER_EXCEEDED = "Integer input exceeds largest integer " +
            "allowed. Max integer is " + Integer.MAX_VALUE;

    public static final String MESSAGE_ERROR_INVALID_PID = "Invalid Product ID.";
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
}
