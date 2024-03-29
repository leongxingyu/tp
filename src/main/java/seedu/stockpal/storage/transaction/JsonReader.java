package seedu.stockpal.storage.transaction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import seedu.stockpal.exceptions.StockPalException;

import static seedu.stockpal.common.Messages.WARNING_READ_DATA_ERROR;

/**
 * Represents the JsonReader object for the StockPal application.
 * Responsible for reading in data from the saved transaction data file.
 */
public class JsonReader {

    private static final String STRING_DELIMITER = "";
    private static final String JSON_ARRAY_HEADER = "Transactions";

    /**
     * Reads in all transaction data at once from the saved transaction data file.
     *
     * @param filePath Path to the saved transaction data file.
     * @return The JSONArray containing the transaction data.
     * @throws StockPalException If there is an error reading data
     *     from the saved transaction data file.
     */
    public JSONArray readTransactions(Path filePath) throws StockPalException {
        List<String> jsonLines;
        try {
            jsonLines = Files.readAllLines(filePath);
            String jsonLine = String.join(STRING_DELIMITER, jsonLines);
            JSONObject jsonObject = new JSONObject(jsonLine);
            return jsonObject.getJSONArray(JSON_ARRAY_HEADER);
        } catch (IOException | JSONException e) {
            throw new StockPalException(WARNING_READ_DATA_ERROR);
        }
    }
}
