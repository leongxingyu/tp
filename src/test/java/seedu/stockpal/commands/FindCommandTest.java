package seedu.stockpal.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.exceptions.StockPalException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author EdmundTangg
public class FindCommandTest {
    private static final String LOWERCASE_EXPECTED_OUTPUT = "src/test/data/FindCommandTest/LowerCaseMatch.txt";
    private static final String NO_MATCH_EXPECTED_OUTPUT = "src/test/data/FindCommandTest/NoMatchesFound.txt";
    public ProductList productList;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() throws StockPalException{

        System.setOut(new PrintStream(outContent));

        productList = new ProductList();
        NewCommand userInput = new NewCommand("Corn", 100,
                2.00, "ingredient");
        userInput.execute(productList);
        NewCommand userInput2 = new NewCommand("Choco", 100,
                2.00, "ingredient2");
        userInput2.execute(productList);
    }

    public void verifyOutput(String expectedOutput) {
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void findCommand_subString_expectBothProductsPrinted() throws StockPalException, IOException {
        FindCommand command = new FindCommand("c");
        command.execute(productList);
        String expectedOutput = new String(Files.readAllBytes(Paths.get(LOWERCASE_EXPECTED_OUTPUT)));
        verifyOutput(expectedOutput);
    }

    @Test
    public void findCommand_noMatch_expectNoMatch() throws StockPalException, IOException {
        FindCommand command = new FindCommand("abc");
        command.execute(productList);
        String expectedOutput = new String(Files.readAllBytes(Paths.get(NO_MATCH_EXPECTED_OUTPUT)));
        verifyOutput(expectedOutput);
    }



}
