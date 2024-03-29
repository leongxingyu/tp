package seedu.stockpal.storage.transaction;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import seedu.stockpal.commands.HistoryCommand;
import seedu.stockpal.commands.InflowCommand;
import seedu.stockpal.data.Transaction;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.storage.exception.InvalidStorageFilePathException;
import seedu.stockpal.data.TransactionList;

public class TransactionStorageTest {
    @TempDir
    private static Path testDir;

    private static final String DEFAULT_STORAGE_FILEPATH = "data/transactions.json";
    private static final String TEST_STORAGE_DIR = "src/test/data/TransactionStorageTest/";
    private static final String VALID_DATA_FILE_NAME = "ValidData.json";
    private static final String INVALID_DATA_FILE_NAME = "InvalidData.json";
    private static final String NON_EXISTENT_FILE_NAME = "NonExistentFile.json";
    private static final String TEMP_FILE_NAME = "temp.json";
    private static final String INVALID_FILE_NAME = "InvalidFileName";
    private static final String NULL_STRING = "";
    private static final Integer TEST_AMT = 0;
    private static final Integer TEST_PID = 6;

    private static final Pid TRANS1_PID = new Pid(1);
    private static final Integer TRANS1_CHANGE = 45;
    private static final LocalDateTime TRANS1_TIME = LocalDateTime.parse("2024-04-29T00:31:18.620338900");

    private static final Pid TRANS2_PID = new Pid(2);
    private static final Integer TRANS2_CHANGE = -10;
    private static final LocalDateTime TRANS2_TIME = LocalDateTime.parse("2024-02-29T04:31:25.690748900");

    private static final Pid TRANS3_PID = new Pid(3);
    private static final Integer TRANS3_CHANGE = -400;
    private static final LocalDateTime TRANS3_TIME = LocalDateTime.parse("2024-03-29T01:55:15.112919900");

    private static final Pid TRANS4_PID = new Pid(4);
    private static final Integer TRANS4_CHANGE = 200;
    private static final LocalDateTime TRANS4_TIME = LocalDateTime.parse("2024-03-21T03:55:15.112919900");

    /**
     * Tests if the constructor sets the path with the correct filepath.
     *
     * @throws Exception If the file path is invalid.
     */
    @Test
    public void constructor_correctFilepath() throws Exception {
        TransactionStorage transactionStorage = new TransactionStorage();
        assertEquals(transactionStorage.getPath(), DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * Tests if the constructor throws a NullPointerException if null is passed in as the file path.
     */
    @Test
    public void constructor_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TransactionStorage(null));
    }

    /**
     * Tests if the constructor throws an InvalidStorageFilePathException
     * if an invalid file name format is passed in as the file path.
     */
    @Test
    public void constructor_noCsvExtension_throwsInvalidStorageFilePathException() {
        assertThrows(InvalidStorageFilePathException.class, () ->
                new TransactionStorage(TEST_STORAGE_DIR  + INVALID_FILE_NAME));
    }

    /**
     * Tests if the load method throws a StockPalException when an empty data file is passed in.
     *
     * @throws Exception If the data file name is of an invalid format.
     */
    @Test
    public void load_invalidJson_throwsStockPalException() throws Exception {
        TransactionStorage transactionStorage = getTransactionStorage(INVALID_DATA_FILE_NAME);
        assertThrows(StockPalException.class, transactionStorage::load);
    }

    /**
     * Asserts that the Transaction in the loaded TransactionList
     * is the same as the Transaction in the expected TransactionList.
     *
     * @param actual Transaction in the loaded TransactionList.
     * @param expected Transaction in the expected TransactionList.
     */
    private void assertTransactionEquals(Transaction actual, Transaction expected) {
        assertEquals(actual.getPid(), expected.getPid());
        assertEquals(actual.getChangeInQuantity(), expected.getChangeInQuantity());
        assertEquals(actual.getTime(), expected.getTime());
    }


    /**
     * Asserts that the loaded TransactionList is the same as the expected TransactionList.
     *
     * @param tl Loaded TransactionList.
     * @param testTL Expected TransactionList.
     */
    private void assertListsEquals(TransactionList tl, TransactionList testTL) {
        int tlSize = tl.getSize();
        int testTLSize = testTL.getSize();
        assertEquals(tlSize, testTLSize);
        for (int i = 0; i < tlSize; i++) {
            assertTransactionEquals(tl.get(i), testTL.get(i));
        }
    }

    /**
     * Tests if the load method loads a data file with valid data formats properly,
     * and checks if the loaded TransactionList matches the expected TransactionList.
     *
     * @throws Exception If the data file name is of an invalid format.
     */
    @Test
    public void load_validProductList() throws Exception {
        TransactionStorage testStorage = getTransactionStorage(VALID_DATA_FILE_NAME);
        TransactionList tl = testStorage.load();
        TransactionList testTL = getTestTransactionList();
        assertListsEquals(tl, testTL);
    }

    /**
     * Asserts that the created file exists in the desired file path.
     *
     * @param filePath The desired file path that the file should be at.
     */
    private void assertFileExist(String filePath) {
        assertTrue(Files.exists(Paths.get(filePath)));
    }

    /**
     * Tests if the loaded method creates a new data file of the given file path,
     * and returns a new empty ProductList.
     *
     * @throws Exception If the data file name is of the invalid format.
     */
    @Test
    public void load_nonExistentFile_createsNewFileAndReturnsEmptyTransactionList() throws Exception {
        TransactionList actualTl = getTransactionStorage(NON_EXISTENT_FILE_NAME).load();
        TransactionList expectedTl = new TransactionList();

        assertEquals(actualTl.isEmpty(), expectedTl.isEmpty());

        // verify that loading non-existent file results in the file being created
        assertFileExist(TEST_STORAGE_DIR + NON_EXISTENT_FILE_NAME);
    }

    /**
     * Asserts that the text in the two given files are the same. Ignores any
     * differences in line endings.
     *
     * @param path1 Path of the first Storage file to compare.
     * @param path2 Path of the second Storage file to compare.
     * @throws IOException If there is error reading from the files.
     */
    public static void assertJsonFilesEqual(Path path1, Path path2) throws IOException {
        List<String> list1 = Files.readAllLines(path1, Charset.defaultCharset());
        List<String> list2 = Files.readAllLines(path2, Charset.defaultCharset());
        assertEquals(String.join(NULL_STRING, list1), String.join(NULL_STRING, list2));
    }

    /**
     * Asserts that the contents of two Transaction storage files are the same.
     *
     * @param s1 First TransactionStorage file to compare.
     * @param s2 Second TransactionStorage file to compare.
     * @throws Exception If there is error reading from the files.
     */
    private void assertStorageFilesEqual(TransactionStorage s1, TransactionStorage s2) throws Exception {
        assertJsonFilesEqual(Paths.get(s1.getPath()), Paths.get(s2.getPath()));
    }

    /**
     * Tests if a new TransactionList gets saved in the same format as the ValidData.json file.
     *
     * @throws Exception If there is error reading from the file.
     */
    @Test
    public void saveTransactions_validTransactionList() throws Exception {
        TransactionList tl = getTestTransactionList();
        TransactionStorage tempStorage = getTempStorage();
        tempStorage.saveTransactions(new InflowCommand(TEST_PID, TEST_AMT), tl);

        assertStorageFilesEqual(tempStorage, getTransactionStorage(VALID_DATA_FILE_NAME));
    }

    /**
     * Tests if the updated TransactionList does not get saved
     * if HistoryCommand is passed into the saveTransactions method.
     *
     * @throws Exception If there is error reading from the file.
     */
    @Test
    public void saveTransactions_historyCommand_returnNull() throws Exception {
        TransactionList tl = getTestTransactionList();
        TransactionStorage tempStorage = getTempStorage();
        tempStorage.saveTransactions(new InflowCommand(TEST_PID, TEST_AMT), tl);
        tl.addTransaction(new Transaction(TRANS4_PID, TRANS4_CHANGE, TRANS4_TIME));
        tempStorage.saveTransactions(new HistoryCommand(TEST_PID), tl);

        assertStorageFilesEqual(tempStorage, getTransactionStorage(VALID_DATA_FILE_NAME));
    }

    private TransactionStorage getTransactionStorage(String fileName) throws Exception {
        return new TransactionStorage(TEST_STORAGE_DIR + fileName);
    }

    private TransactionStorage getTempStorage() throws Exception {
        return new TransactionStorage(testDir.resolve(TEMP_FILE_NAME).toString());
    }

    private TransactionList getTestTransactionList() {
        TransactionList tl = new TransactionList();
        tl.addTransaction(new Transaction(TRANS1_PID, TRANS1_CHANGE, TRANS1_TIME));
        tl.addTransaction(new Transaction(TRANS2_PID, TRANS2_CHANGE, TRANS2_TIME));
        tl.addTransaction(new Transaction(TRANS3_PID, TRANS3_CHANGE, TRANS3_TIME));
        return tl;
    }
}
