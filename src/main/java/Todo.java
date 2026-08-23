/** Represents a task without a date or time. */
public class Todo extends Task {
    /** Creates a to-do task with the given description. */
    public Todo(String description) {
        super(description, "T");
    }
}
