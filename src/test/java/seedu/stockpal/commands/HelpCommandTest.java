package seedu.stockpal.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.stockpal.common.Messages;
import seedu.stockpal.exceptions.InvalidFormatException;
import seedu.stockpal.exceptions.StockPalException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.stockpal.common.Messages.LINE_SEPARATOR;

public class HelpCommandTest {
    private static final String ALL_COMMANDS_EXPECTED_OUTPUT = "src/test/data/HelpCommandTest/AllCommandsHelpPage.txt";
    private static final String NEW_COMMAND_EXPECTED_OUTPUT = "src/test/data/HelpCommandTest/NewCommandHelpPage.txt";
    private static final String EDIT_COMMAND_EXPECTED_OUTPUT = "src/test/data/HelpCommandTest/EditCommandHelpPage.txt";
    private static final String DELETE_COMMAND_EXPECTED_OUTPUT =
            "src/test/data/HelpCommandTest/DeleteCommandHelpPage.txt";
    private static final String INFLOW_COMMAND_EXPECTED_OUTPUT =
            "src/test/data/HelpCommandTest/InflowCommandHelpPage.txt";
    private static final String OUTFLOW_COMMAND_EXPECTED_OUTPUT =
            "src/test/data/HelpCommandTest/OutflowCommandHelpPage.txt";
    private static final String FIND_COMMAND_EXPECTED_OUTPUT = "src/test/data/HelpCommandTest/FindCommandHelpPage.txt";
    private static final String LIST_COMMAND_EXPECTED_OUTPUT = "src/test/data/HelpCommandTest/ListCommandHelpPage.txt";
    private static final String HISTORY_COMMAND_EXPECTED_OUTPUT =
            "src/test/data/HelpCommandTest/HistoryCommandHelpPage.txt";
    private static final String EXIT_COMMAND_EXPECTED_OUTPUT = "src/test/data/HelpCommandTest/ExitCommandHelpPage.txt";
    private static final String HELP_COMMAND_EXPECTED_OUTPUT = "src/test/data/HelpCommandTest/HelpCommandHelpPage.txt";

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    public void verifyOutput(String expectedOutput) {
        expectedOutput = expectedOutput.replace("\n", LINE_SEPARATOR);
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void helpCommand_allCommands_printAllCommandHelpPage() throws StockPalException, IOException {
        HelpCommand command = new HelpCommand();
        command.execute();
        String expectedOutput = new String(Files.readAllBytes(Paths.get(ALL_COMMANDS_EXPECTED_OUTPUT)));
        verifyOutput(expectedOutput);
    }

    @Test
    public void helpCommand_newCommand_printNewCommandHelpPage() throws StockPalException, IOException {
        HelpCommand command = new HelpCommand(NewCommand.COMMAND_KEYWORD);
        command.execute();
        String expectedOutput = new String(Files.readAllBytes(Paths.get(NEW_COMMAND_EXPECTED_OUTPUT)));
        verifyOutput(expectedOutput);
    }

    @Test
    public void helpCommand_editCommand_printEditCommandHelpPage() throws StockPalException, IOException {
        HelpCommand command = new HelpCommand(EditCommand.COMMAND_KEYWORD);
        command.execute();
        String expectedOutput = new String(Files.readAllBytes(Paths.get(EDIT_COMMAND_EXPECTED_OUTPUT)));
        verifyOutput(expectedOutput);
    }

    @Test
    public void helpCommand_deleteCommand_printDeleteCommandHelpPage() throws StockPalException, IOException {
        HelpCommand command = new HelpCommand(DeleteCommand.COMMAND_KEYWORD);
        command.execute();
        String expectedOutput = new String(Files.readAllBytes(Paths.get(DELETE_COMMAND_EXPECTED_OUTPUT)));
        verifyOutput(expectedOutput);
    }

    @Test
    public void helpCommand_inflowCommand_printInflowCommandHelpPage() throws StockPalException, IOException {
        HelpCommand command = new HelpCommand(InflowCommand.COMMAND_KEYWORD);
        command.execute();
        String expectedOutput = new String(Files.readAllBytes(Paths.get(INFLOW_COMMAND_EXPECTED_OUTPUT)));
        verifyOutput(expectedOutput);
    }

    @Test
    public void helpCommand_outflowCommand_printOutflowCommandHelpPage() throws StockPalException, IOException {
        HelpCommand command = new HelpCommand(OutflowCommand.COMMAND_KEYWORD);
        command.execute();
        String expectedOutput = new String(Files.readAllBytes(Paths.get(OUTFLOW_COMMAND_EXPECTED_OUTPUT)));
        verifyOutput(expectedOutput);
    }

    @Test
    public void helpCommand_findCommand_printFindCommandHelpPage() throws StockPalException, IOException {
        HelpCommand command = new HelpCommand(FindCommand.COMMAND_KEYWORD);
        command.execute();
        String expectedOutput = new String(Files.readAllBytes(Paths.get(FIND_COMMAND_EXPECTED_OUTPUT)));
        verifyOutput(expectedOutput);
    }

    @Test
    public void helpCommand_historyCommand_printHistoryCommandHelpPage() throws StockPalException, IOException {
        HelpCommand command = new HelpCommand(HistoryCommand.COMMAND_KEYWORD);
        command.execute();
        String expectedOutput = new String(Files.readAllBytes(Paths.get(HISTORY_COMMAND_EXPECTED_OUTPUT)));
        verifyOutput(expectedOutput);
    }

    @Test
    public void helpCommand_listCommand_printListCommandHelpPage() throws StockPalException, IOException {
        HelpCommand command = new HelpCommand(ListCommand.COMMAND_KEYWORD);
        command.execute();
        String expectedOutput = new String(Files.readAllBytes(Paths.get(LIST_COMMAND_EXPECTED_OUTPUT)));
        verifyOutput(expectedOutput);
    }

    @Test
    public void helpCommand_exitCommand_printExitCommandHelpPage() throws StockPalException, IOException {
        HelpCommand command = new HelpCommand(ExitCommand.COMMAND_KEYWORD);
        command.execute();
        String expectedOutput = new String(Files.readAllBytes(Paths.get(EXIT_COMMAND_EXPECTED_OUTPUT)));
        verifyOutput(expectedOutput);
    }

    @Test
    public void helpCommand_helpCommand_printHelpCommandHelpPage() throws StockPalException, IOException {
        HelpCommand command = new HelpCommand(HelpCommand.COMMAND_KEYWORD);
        command.execute();
        String expectedOutput = new String(Files.readAllBytes(Paths.get(HELP_COMMAND_EXPECTED_OUTPUT)));
        verifyOutput(expectedOutput);
    }

    @Test
    public void helpCommand_invalidCommand_showInvalidCommandMessage() {
        try {
            HelpCommand command = new HelpCommand("bleh");
            fail();
        } catch (InvalidFormatException exception) {
            assertEquals(Messages.MESSAGE_ERROR_INVALID_COMMAND, exception.getMessage());
        }
    }
}
