/** Represents a task scheduled from one time to another. */
public class Event extends Task {
    private final String from;
    private final String to;

    /** Creates an event with a description, start time, and end time. */
    public Event(String description, String from, String to) {
        super(description, "E");
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
