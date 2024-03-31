package seedu.stockpal.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.TransactionList;
import seedu.stockpal.exceptions.StockPalException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author EdmundTangg
public class HistoryCommandTest {
    private static final String NO_TRANSACTION_EXPECTED_OUTPUT =
            "src/test/data/HistoryCommandTest/NoTransactionFound.txt";

    public ProductList productList;
    public TransactionList transactionList;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() throws StockPalException {

        System.setOut(new PrintStream(outContent));

        productList = new ProductList();
        transactionList = new TransactionList();
        NewCommand userInput = new NewCommand("Corn", 100,
                2.00, "ingredient");
        userInput.execute(productList);
    }

    public void verifyOutput(String expectedOutput) {
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void historyCommand_noTransaction_noTransactionsPrinted() throws StockPalException, IOException {
        HistoryCommand command = new HistoryCommand(1);
        command.execute(productList, transactionList);
        String expectedOutput = new String(Files.readAllBytes(Paths.get(NO_TRANSACTION_EXPECTED_OUTPUT)));
        verifyOutput(expectedOutput);
    }

}
