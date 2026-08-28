/** Represents a task that must be completed by a specified time. */
public class Deadline extends Task {
    private final String by;

    /** Creates a deadline task with its description and deadline text. */
    public Deadline(String description, String by) {
        super(description, "D");
        this.by = by;
    }

    /** Returns the deadline text for persistent storage. */
    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
