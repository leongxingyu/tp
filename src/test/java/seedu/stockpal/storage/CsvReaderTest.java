package seedu.stockpal.storage;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CsvReaderTest {

    private static final String RANDOM_FILEPATH = "data/randomPath.csv";

    /**
     * Tests if the constructor throws a NullPointerException if null is passed in as the file path.
     */
    @Test
    public void constructor_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CsvReader(null));
    }

    /**
     * Test if the constructor throws an AssertionError if a wrong filepath is passed in as the file path.
     */
    @Test
    public void constructor_wrongFilePath_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new CsvReader(RANDOM_FILEPATH));
    }
}
