import java.util.ArrayList;
import java.util.List;

/** Owns Vega's task collection and validates numbered task access. */
public class TaskList {
    private final ArrayList<Task> tasks;

    /** Creates an empty task list. */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /** Creates a task list containing the loaded tasks. */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /** Adds a task to the end of the list. */
    public void add(Task task) {
        tasks.add(task);
    }

    /** Returns the task at the given one-based task number. */
    public Task get(int taskNumber) throws VegaException {
        return tasks.get(toIndex(taskNumber));
    }

    /** Removes and returns the task at the given one-based task number. */
    public Task delete(int taskNumber) throws VegaException {
        return tasks.remove(toIndex(taskNumber));
    }

    /** Returns the number of tasks in the list. */
    public int size() {
        return tasks.size();
    }

    /** Returns an unmodifiable snapshot for display and storage. */
    public List<Task> asList() {
        return List.copyOf(tasks);
    }

    private int toIndex(int taskNumber) throws VegaException {
        int taskIndex = taskNumber - 1;
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new VegaException("There is no task numbered " + taskNumber + ".");
        }
        return taskIndex;
    }
}
