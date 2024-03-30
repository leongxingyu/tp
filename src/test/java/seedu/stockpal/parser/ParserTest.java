package seedu.stockpal.parser;

import org.junit.jupiter.api.Test;
import seedu.stockpal.commands.Command;
import seedu.stockpal.commands.DeleteCommand;
import seedu.stockpal.commands.EditCommand;
import seedu.stockpal.commands.FindCommand;
import seedu.stockpal.commands.HelpCommand;
import seedu.stockpal.commands.HistoryCommand;
import seedu.stockpal.commands.InflowCommand;
import seedu.stockpal.commands.ListCommand;
import seedu.stockpal.commands.ExitCommand;
import seedu.stockpal.commands.NewCommand;

import seedu.stockpal.commands.OutflowCommand;
import seedu.stockpal.exceptions.InvalidCommandException;
import seedu.stockpal.exceptions.InvalidFormatException;
import seedu.stockpal.exceptions.UnsignedIntegerExceededException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    private final Parser testParser = new Parser();
    private String testInput;

    @Test
    public void parseInput_invalidCommand_invalidCommandExceptionThrown() {
        testInput = "notACommand n/test q/123";
        assertThrows(InvalidCommandException.class, () -> testParser.parseInput(testInput));
    }
    @Test
    public void parseInput_helpCommand_success()
            throws InvalidCommandException, InvalidFormatException, UnsignedIntegerExceededException {
        testInput = "help";
        assertEquals(HelpCommand.class, testParser.parseInput(testInput).getClass());
    }
    @Test
    public void parseInput_listCommand_success()
            throws InvalidCommandException, InvalidFormatException, UnsignedIntegerExceededException {
        testInput = "list";
        assertEquals(ListCommand.class, testParser.parseInput(testInput).getClass());
    }
    @Test
    public void parseInput_exitCommand_success()
            throws InvalidCommandException, InvalidFormatException, UnsignedIntegerExceededException {
        testInput = "exit";
        assertEquals(ExitCommand.class, testParser.parseInput(testInput).getClass());
    }

    @Test
    public void parseInput_newCommandWithoutPriceWithoutDescription_success() {
        try {
            testInput = "new n/Test name q/123";
            Command command = testParser.parseInput(testInput);
            assertEquals(NewCommand.class, command.getClass());
        } catch (InvalidCommandException | InvalidFormatException | UnsignedIntegerExceededException e) {
            fail();
        }
    }

    @Test
    public void parseInput_newCommandWithPriceWithoutDescription_success() {
        try {
            testInput = "new n/Test name q/123 p/4.56";
            Command command = testParser.parseInput(testInput);
            assertEquals(NewCommand.class, command.getClass());
        } catch (InvalidCommandException | InvalidFormatException | UnsignedIntegerExceededException e) {
            fail();
        }
    }
    @Test
    public void parseInput_newCommandWithoutPriceWithDescription_success() {
        try {
            testInput = "new n/Test Name q/123 d/Test description";
            Command command = testParser.parseInput(testInput);
            assertEquals(NewCommand.class, command.getClass());
        } catch (InvalidCommandException | InvalidFormatException | UnsignedIntegerExceededException e) {
            fail();
        }
    }

    @Test
    public void parseInput_newCommandWithPriceWithDescription_success() {
        try {
            testInput = "new n/Test Name q/123 p/4.56 d/test description";
            Command command = testParser.parseInput(testInput);
            assertEquals(NewCommand.class, command.getClass());
        } catch (InvalidCommandException | InvalidFormatException | UnsignedIntegerExceededException e) {
            fail();
        }
    }

    @Test
    public void parseInput_newCommandWithoutName_invalidFormatExceptionThrown() {
        testInput = "new q/123 p/4.56 d/Test description";
        assertThrows(InvalidFormatException.class, () -> testParser.parseInput(testInput));
    }

    @Test
    public void parseInput_newCommandWithEmptyName_invalidFormatExceptionThrown() {
        testInput = "new n/           q/123 p/4.56 d/Test description";
        assertThrows(InvalidFormatException.class, () -> testParser.parseInput(testInput));
    }

    @Test
    public void parseInput_newCommandWithoutQuantity_invalidFormatExceptionThrown() {
        testInput = "new n/Test Name p/4.56 d/Test description";
        assertThrows(InvalidFormatException.class, () -> testParser.parseInput(testInput));
    }

    @Test
    public void parseInput_editCommandWithNoOptionalArguments_success() {
        try {
            testInput = "edit 1";
            Command command = testParser.parseInput(testInput);
            assertEquals(EditCommand.class, command.getClass());
        } catch (InvalidCommandException | InvalidFormatException | UnsignedIntegerExceededException e) {
            fail();
        }
    }

    @Test
    public void parseInput_editCommandWithOneOptionalArgument_success() {
        try {
            testInput = "edit 1 q/100";
            Command command = testParser.parseInput(testInput);
            assertEquals(EditCommand.class, command.getClass());
        } catch (InvalidCommandException | InvalidFormatException | UnsignedIntegerExceededException e) {
            fail();
        }
    }

    @Test
    public void parseInput_editCommandWithTwoOptionalArguments_success() {
        try {
            testInput = "edit 1 q/100 d/new test description";
            Command command = testParser.parseInput(testInput);
            assertEquals(EditCommand.class, command.getClass());
        } catch (InvalidCommandException | InvalidFormatException | UnsignedIntegerExceededException e) {
            fail();
        }
    }

    @Test
    public void parseInput_editCommandWithThreeOptionalArguments_success() {
        try {
            testInput = "edit 1 n/new test name q/100 d/new test description";
            Command command = testParser.parseInput(testInput);
            assertEquals(EditCommand.class, command.getClass());
        } catch (InvalidCommandException | InvalidFormatException | UnsignedIntegerExceededException e) {
            fail();
        }
    }

    @Test
    public void parseInput_editCommandWithFourOptionalArguments_success() {
        try {
            testInput = "edit 1 n/new test name q/100 p/1.23 d/new test description";
            Command command = testParser.parseInput(testInput);
            assertEquals(EditCommand.class, command.getClass());
        } catch (InvalidCommandException | InvalidFormatException | UnsignedIntegerExceededException e) {
            fail();
        }
    }

    @Test
    public void parseInput_editCommandWithoutPid_InvalidFormatExceptionThrown() {
        testInput = "edit n/new test name q/100 p/1.23 d/new test description";
        assertThrows(InvalidFormatException.class, () -> testParser.parseInput(testInput));
    }

    @Test
    public void parseInput_editCommandWithPidExceed_UnsignedIntegerExceededExceptionThrown() {
        testInput = "edit 100000000000 n/new test name q/100 p/1.23 d/new test description";
        assertThrows(UnsignedIntegerExceededException.class, () -> testParser.parseInput(testInput));
    }

    @Test
    public void parseInput_deleteCommandWithValidPid_success() {
        testInput = "delete 1";
        try {
            Command command = testParser.parseInput(testInput);
            assertEquals(DeleteCommand.class, command.getClass());

        } catch (InvalidCommandException | InvalidFormatException | UnsignedIntegerExceededException e) {
            fail();
        }
    }

    @Test
    public void parseInput_deleteCommandWithPidExceed_UnsignedIntegerExceedExceptionThrown() {
        testInput = "delete 100000000000000";
        assertThrows(UnsignedIntegerExceededException.class, () -> testParser.parseInput(testInput));
    }

    @Test
    public void parseInput_inflowCommandWithValidPidAndValidAmount_success() {
        testInput = "inflow 1 a/1";
        try {
            Command command = testParser.parseInput(testInput);
            assertEquals(InflowCommand.class, command.getClass());

        } catch (InvalidCommandException | InvalidFormatException | UnsignedIntegerExceededException e) {
            fail();
        }
    }

    @Test
    public void parseInput_inflowCommandWithLargePidAndValidAmount_UnsignedIntegerExceedExceptionThrown() {
        testInput = "inflow 100000000000 a/1";
        assertThrows(UnsignedIntegerExceededException.class, () -> testParser.parseInput(testInput));
    }

    @Test
    public void parseInput_inflowCommandWithValidPidAndLargeAmount_UnsignedIntegerExceedExceptionThrown() {
        testInput = "inflow 1 a/1000000000000";
        assertThrows(UnsignedIntegerExceededException.class, () -> testParser.parseInput(testInput));
    }

    @Test
    public void parseInput_outflowCommandWithValidPidAndValidAmount_success() {
        testInput = "outflow 1 a/1";
        try {
            Command command = testParser.parseInput(testInput);
            assertEquals(OutflowCommand.class, command.getClass());

        } catch (InvalidCommandException | InvalidFormatException | UnsignedIntegerExceededException e) {
            fail();
        }
    }

    @Test
    public void parseInput_outflowCommandWithLargePidAndValidAmount_UnsignedIntegerExceedExceptionThrown() {
        testInput = "outflow 100000000000 a/1";
        assertThrows(UnsignedIntegerExceededException.class, () -> testParser.parseInput(testInput));
    }

    @Test
    public void parseInput_outflowCommandWithValidPidAndLargeAmount_UnsignedIntegerExceedExceptionThrown() {
        testInput = "outflow 1 a/1000000000000";
        assertThrows(UnsignedIntegerExceededException.class, () -> testParser.parseInput(testInput));
    }

    @Test
    public void parseInput_findCommandWithNonEmptyKeyword_success() {
        testInput = "find apple";
        try {
            Command command = testParser.parseInput(testInput);
            assertEquals(FindCommand.class, command.getClass());

        } catch (InvalidCommandException | InvalidFormatException | UnsignedIntegerExceededException e) {
            fail();
        }
    }

    @Test
    public void parseInput_findCommandWithEmptyKeyword_InvalidFormatExceptionThrown() {
        testInput = "find     ";
        assertThrows(InvalidFormatException.class, () -> testParser.parseInput(testInput));
    }

    @Test
    public void parseInput_historyCommandWithValidPid_success() {
        testInput = "history 1";
        try {
            Command command = testParser.parseInput(testInput);
            assertEquals(HistoryCommand.class, command.getClass());

        } catch (InvalidCommandException | InvalidFormatException | UnsignedIntegerExceededException e) {
            fail();
        }
    }

    @Test
    public void parseInput_historyCommandWithPidExceed_UnsignedIntegerExceedExceptionThrown() {
        testInput = "delete 100000000000000";
        assertThrows(UnsignedIntegerExceededException.class, () -> testParser.parseInput(testInput));
    }

}
