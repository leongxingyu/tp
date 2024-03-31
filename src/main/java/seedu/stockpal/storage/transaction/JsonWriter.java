package seedu.stockpal.storage.transaction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import seedu.stockpal.data.Transaction;
import seedu.stockpal.data.TransactionList;
import seedu.stockpal.exceptions.StockPalException;

import static seedu.stockpal.common.Messages.ERROR_MESSAGE_GENERAL;
import static seedu.stockpal.common.Messages.WARNING_DATA_FILE_FORMAT_ERROR;

/**
 * Represents the JsonWriter object for the StockPal application.
 * Responsible for writing data to the saved transaction data file.
 */
public class JsonWriter {

    private static final String TRANSACTIONS_HEADER = "Transactions";
    private static final String PID_HEADER = "PID";
    private static final String CHANGE_HEADER = "Change In Quantity";
    private static final String TIME_HEADER = "Time";
    private static final int INDENT_FACTOR = 4;

    private final JSONObject transactionsData;
    private final JSONArray transactions;
    private final BufferedWriter bw;

    /**
     * Constructs a new JsonWriter object and creates a writer to write data
     * into the saved transaction data file in filePath.
     *
     * @param filePath Path to the saved transaction data file.
     * @throws StockPalException If there is an error with the format of the data file.
     */
    public JsonWriter(String filePath) throws StockPalException {
        transactionsData = new JSONObject();
        transactions = new JSONArray();
        try {
            bw = new BufferedWriter(new FileWriter(filePath));
        } catch (IOException ioe) {
            throw new StockPalException(WARNING_DATA_FILE_FORMAT_ERROR);
        }
    }

    /**
     * Converts Transaction into the formatted JSONObject to be saved.
     *
     * @param transaction The Transaction to be converted.
     * @return The formatted JSONObject to be saved in the transaction data file.
     * @throws JSONException If the transaction data is of the wrong format to be saved.
     */
    private JSONObject convertTransactionToJson(Transaction transaction) throws JSONException {
        JSONObject transactionObj = new JSONObject();
        transactionObj.put(PID_HEADER, transaction.getPid());
        transactionObj.put(CHANGE_HEADER, transaction.getChangeInQuantity());
        transactionObj.put(TIME_HEADER, transaction.getTime());
        return transactionObj;
    }

    /**
     * Edits and saves all transaction data at once into the data file.
     *
     * @param transactionList The TransactionList containing all the transactions to be saved.
     * @throws StockPalException If there is an error saving the data.
     */
    public void saveTransactions(TransactionList transactionList) throws StockPalException {
        int numTransactions = transactionList.getSize();
        try {
            for (int i = 0; i < numTransactions; i++) {
                Transaction currTransaction = transactionList.get(i);
                transactions.put(convertTransactionToJson(currTransaction));
            }
            transactionsData.put(TRANSACTIONS_HEADER, transactions);
            bw.write(transactionsData.toString(INDENT_FACTOR));
            bw.flush();
            bw.close();
        } catch (IOException | JSONException e) {
            throw new StockPalException(ERROR_MESSAGE_GENERAL + e.getMessage());
        }
    }
}
