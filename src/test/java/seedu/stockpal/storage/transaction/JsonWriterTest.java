package seedu.stockpal.storage.transaction;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class JsonWriterTest {

    /**
     * Tests if the constructor throws a NullPointerException if null is passed in as the file path.
     */
    @Test
    public void constructor_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JsonWriter(null));
    }
}
