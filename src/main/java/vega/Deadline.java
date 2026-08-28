package vega;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/** Represents a task that must be completed by a specified time. */
public class Deadline extends Task {
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern(
            "MMM dd yyyy", Locale.ENGLISH);

    private final LocalDate by;

    /** Creates a deadline task with its description and deadline date. */
    public Deadline(String description, LocalDate by) {
        super(description, "D");
        this.by = by;
    }

    /** Returns the deadline date for persistent storage. */
    public LocalDate getBy() {
        return by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by.format(DISPLAY_FORMAT) + ")";
    }
}
