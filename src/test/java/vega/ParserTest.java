package vega;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParserTest {
    private final Parser parser = new Parser();

    @Test
    public void getCommandWordAndArguments_commandWithDescription_parsesBothParts() {
        String input = "todo read a book";

        assertEquals("todo", parser.getCommandWord(input));
        assertEquals("read a book", parser.getArguments(input));
    }

    @Test
    public void parseDeadline_validDate_returnsFormattedDeadline() throws VegaException {
        Deadline deadline = parser.parseDeadline("submit report /by 2026-08-31");

        assertEquals("[D][ ] submit report (by: Aug 31 2026)", deadline.toString());
    }

    @Test
    public void parseDeadline_invalidDate_throwsVegaException() {
        assertThrows(VegaException.class,
                () -> parser.parseDeadline("submit report /by 2026-02-30"));
    }
}
