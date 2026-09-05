package vega;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

/** Handles all console input and output for Vega. */
public class Ui implements AutoCloseable {
    private static final String LINE = "____________________________________________________________";

    private final PrintStream output;

    private final Scanner scanner;

    /** Creates a console UI that reads from standard input. */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Creates a UI using the supplied streams.
     *
     * @param input Source of commands.
     * @param output Destination for responses.
     */
    public Ui(InputStream input, PrintStream output) {
        scanner = new Scanner(input);
        this.output = output;
    }

    /** Displays Vega's greeting. */
    public void showWelcome() {
        output.println(getGreeting());
    }

    /**
     * Returns Vega's greeting without printing it.
     *
     * @return Greeting shown at startup.
     */
    public static String getGreeting() {
        return "Hello! I'm Vega\nWhat can I do for you?";
    }

    /**
     * Reads the next command from the user.
     *
     * @return Command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /** Displays Vega's farewell. */
    public void showGoodbye() {
        output.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a user-facing error.
     *
     * @param message Explanation of the error.
     */
    public void showError(String message) {
        showLines(LINE, "OOPS!!! " + message, LINE);
    }

    /**
     * Displays all tasks with one-based numbering.
     *
     * @param tasks Tasks to display.
     */
    public void showTaskList(List<Task> tasks) {
        showLine();
        output.println("Here are the tasks in your list:");
        showNumberedTasks(tasks);
        showLine();
    }

    /**
     * Displays tasks that match a search keyword.
     *
     * @param tasks Matching tasks to display.
     */
    public void showMatchingTasks(List<Task> tasks) {
        showLine();
        output.println("Here are the matching tasks in your list:");
        showNumberedTasks(tasks);
        showLine();
    }

    private void showNumberedTasks(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            output.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Displays confirmation that a task was added.
     *
     * @param task Task that was added.
     * @param taskCount Number of tasks after the addition.
     */
    public void showTaskAdded(Task task, int taskCount) {
        showLines(LINE, "Got it. I've added this task:", "  " + task);
        showTaskCount(taskCount);
        showLine();
    }

    /**
     * Displays confirmation that a task was removed.
     *
     * @param task Task that was removed.
     * @param taskCount Number of tasks after the removal.
     */
    public void showTaskDeleted(Task task, int taskCount) {
        showLines(LINE, "Noted. I've removed this task:", "  " + task);
        showTaskCount(taskCount);
        showLine();
    }

    /**
     * Displays confirmation that a task's completion status changed.
     *
     * @param task Task whose status changed.
     * @param isDone Whether the task is now complete.
     */
    public void showTaskStatusChanged(Task task, boolean isDone) {
        showLine();
        output.println(isDone
                ? "Nice! I've marked this task as done:"
                : "OK, I've marked this task as not done yet:");
        output.println("  " + task);
        showLine();
    }

    private void showTaskCount(int taskCount) {
        String taskWord = taskCount == 1 ? "task" : "tasks";
        output.println("Now you have " + taskCount + " " + taskWord + " in the list.");
    }

    private void showLine() {
        output.println(LINE);
    }

    /**
     * Displays any number of response lines in their supplied order.
     *
     * @param lines Response lines to display.
     */
    private void showLines(String... lines) {
        for (String line : lines) {
            output.println(line);
        }
    }

    @Override
    public void close() {
        scanner.close();
    }
}
