package seedu.stockpal.storage.transaction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import seedu.stockpal.commands.Command;
import seedu.stockpal.commands.HistoryCommand;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.data.Transaction;
import seedu.stockpal.data.TransactionList;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.storage.exception.InvalidStorageFilePathException;
import seedu.stockpal.storage.exception.StorageIOException;

import static seedu.stockpal.common.Messages.ERROR_MESSAGE_GENERAL;
import static seedu.stockpal.common.Messages.WARNING_DATA_ERROR;
import static seedu.stockpal.common.Messages.WARNING_INVALID_FILEPATH_JSON;

/**
 * Represents the transaction file storage of the StockPal application.
 * Responsible for loading/saving of the transaction data from/to the JSON file.
 */
public class TransactionStorage {

    private static final String DEFAULT_STORAGE_FILEPATH = "data/transactions.json";
    private static final String JSON_EXTENSION = ".json";
    private static final String PID_HEADER = "PID";
    private static final String CHANGE_HEADER = "Change In Quantity";
    private static final String TIME_HEADER = "Time";

    private final String path;

    /**
     * Constructs a new TransactionStorage object with the default storage filepath.
     *
     * @throws StockPalException If the file path is invalid,
     *     or if there is an error with the format of the data file.
     */
    public TransactionStorage() throws StockPalException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * Constructs a new Storage object with the specified filepath.
     *
     * @param filePath The filepath of the saved JSON file containing the transactions.
     * @throws StockPalException If the file path is invalid,
     *     or if there is an error with the format of the data file.
     */
    public TransactionStorage(String filePath) throws StockPalException {
        if (!isValidPath(filePath)) {
            throw new InvalidStorageFilePathException(WARNING_INVALID_FILEPATH_JSON);
        }
        this.path = filePath;
    }

    /**
     * Checks if the given file path is of the valid format.
     *
     * @param filePath The given file path string.
     * @return true if the file path is of the correct extension, else false.
     */
    private boolean isValidPath(String filePath) {
        return filePath.endsWith(JSON_EXTENSION);
    }

    /**
     * Creates the new save file with the specified file path.
     *
     * @throws StockPalException If an error occurred while creating the new save file.
     */
    private void createSaveFile() throws StockPalException {
        File f = new File(path);
        f.getParentFile().mkdirs();
        try {
            f.createNewFile();
        } catch (IOException ioe) {
            throw new StockPalException(ERROR_MESSAGE_GENERAL + ioe.getMessage());
        }
    }

    /**
     * Parses the data object by object and converts each JSONObject into a Transaction.
     *
     * @param transactionObj The object containing the details of the saved Transaction.
     * @return The converted Transaction.
     * @throws JSONException If any of the data contains erroneous input.
     * @throws DateTimeParseException If the String cannot be parsed into LocalDateTime.
     */
    private Transaction parseTransactionFromJsonObject(JSONObject transactionObj)
            throws JSONException, DateTimeParseException {
        Pid pid = new Pid(transactionObj.getInt(PID_HEADER));
        Integer changeToQuantity = transactionObj.getInt(CHANGE_HEADER);
        LocalDateTime time = LocalDateTime.parse(transactionObj.getString(TIME_HEADER));
        return new Transaction(pid, changeToQuantity, time);
    }

    /**
     * Parses the entire transaction data file and returns the data as a TransactionList.
     *
     * @param jsonArray The JSONArray containing the transaction data.
     * @return Loaded TransactionList with the previously saved transactions.
     * @throws StorageIOException If the data file contains any erroneous input.
     */
    private TransactionList parseTransactions(JSONArray jsonArray) throws StorageIOException {
        TransactionList transactionList = new TransactionList();
        int numTransactions = jsonArray.length();
        for (int i = 0; i < numTransactions; i++) {
            try {
                JSONObject transactionObj = jsonArray.getJSONObject(i);
                transactionList.addTransaction(parseTransactionFromJsonObject(transactionObj));
            } catch (JSONException | DateTimeParseException e) {
                throw new StorageIOException(WARNING_DATA_ERROR);
            }
        }
        return transactionList;
    }

    /**
     * Loads the product list from the saved data file and returns the loaded product list.
     *
     * @return Loaded product list with the previously saved products.
     * @throws StockPalException If an error occurred while creating the new save file.
     * @throws StorageIOException If the data file contains any erroneous input.
     */
    public TransactionList load() throws StockPalException, StorageIOException {
        Path filepath = Path.of(this.path);
        if (!Files.exists(filepath) || !Files.isRegularFile(filepath)) {
            createSaveFile();
            TransactionList transactionList = new TransactionList();
            JsonWriter jsonWriter = new JsonWriter(this.path);
            jsonWriter.saveTransactions(transactionList);
            return transactionList;
        }
        JsonReader jsonReader = new JsonReader();
        return parseTransactions(jsonReader.readTransactions(filepath));
    }

    /**
     * Saves the current TransactionList to the transaction data file.
     *
     * @param command The current Command being executed.
     * @param transactionList The updated TransactionList.
     * @throws StockPalException If there is an error saving the data.
     */
    public void save(Command command, TransactionList transactionList)
            throws StockPalException {
        if (command instanceof HistoryCommand) {
            return;
        }
        JsonWriter jsonWriter = new JsonWriter(this.path);
        jsonWriter.saveTransactions(transactionList);
    }

    /**
     * Gets the String representation of the filepath for testing.
     *
     * @return String representation of the filepath.
     */
    protected String getPath() {
        return path;
    }
}
