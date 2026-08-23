/**
 * Represents a task in Vega's list. Subclasses provide the task-specific
 * details while this class keeps the common description and completion state.
 */
public class Task {
    private final String description;
    private final String type;
    private boolean isDone;

    /** Creates a task with a description and a one-letter task type. */
    public Task(String description, String type) {
        this.description = description;
        this.type = type;
    }

    /** Marks this task as complete. */
    public void markAsDone() {
        isDone = true;
    }

    /** Marks this task as incomplete. */
    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return "[" + type + "][" + status + "] " + description;
    }
}
