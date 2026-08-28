package vega;

import java.util.List;
import java.util.Scanner;

/** Handles all console input and output for Vega. */
public class Ui implements AutoCloseable {
    private static final String LINE = "____________________________________________________________";

    private final Scanner scanner;

    /** Creates a console UI that reads from standard input. */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /** Displays Vega's greeting. */
    public void showWelcome() {
        System.out.println("Hello! I'm Vega");
        System.out.println("What can I do for you?");
    }

    /** Reads the next command from the user. */
    public String readCommand() {
        return scanner.nextLine();
    }

    /** Displays Vega's farewell. */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /** Displays a user-facing error. */
    public void showError(String message) {
        showLine();
        System.out.println("OOPS!!! " + message);
        showLine();
    }

    /** Displays all tasks with one-based numbering. */
    public void showTaskList(List<Task> tasks) {
        showLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        showLine();
    }

    /** Displays confirmation that a task was added. */
    public void showTaskAdded(Task task, int taskCount) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        showTaskCount(taskCount);
        showLine();
    }

    /** Displays confirmation that a task was removed. */
    public void showTaskDeleted(Task task, int taskCount) {
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        showTaskCount(taskCount);
        showLine();
    }

    /** Displays confirmation that a task's completion status changed. */
    public void showTaskStatusChanged(Task task, boolean isDone) {
        showLine();
        System.out.println(isDone
                ? "Nice! I've marked this task as done:"
                : "OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        showLine();
    }

    private void showTaskCount(int taskCount) {
        String taskWord = taskCount == 1 ? "task" : "tasks";
        System.out.println("Now you have " + taskCount + " " + taskWord + " in the list.");
    }

    private void showLine() {
        System.out.println(LINE);
    }

    @Override
    public void close() {
        scanner.close();
    }
}
