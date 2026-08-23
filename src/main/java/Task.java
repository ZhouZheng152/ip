public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return "[" + status + "] " + description;
    }
}
