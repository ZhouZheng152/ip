import java.util.ArrayList;
import java.util.Scanner;

public class Vega {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("Hello! I'm Vega");
        System.out.println("What can I do for you?");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String command = scanner.nextLine();

                if (command.equals("bye")) {
                    break;
                }
                try {
                    handleCommand(tasks, command);
                } catch (VegaException e) {
                    printError(e.getMessage());
                }
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    /** Handles one user command or reports a Vega-specific input error. */
    private static void handleCommand(ArrayList<Task> tasks, String command) throws VegaException {
        if (command.equals("list")) {
            printList(tasks);
        } else if (command.equals("mark") || command.startsWith("mark ")) {
            updateTaskStatus(tasks, command, true);
        } else if (command.equals("unmark") || command.startsWith("unmark ")) {
            updateTaskStatus(tasks, command, false);
        } else if (command.equals("delete") || command.startsWith("delete ")) {
            deleteTask(tasks, command);
        } else if (command.equals("todo") || command.startsWith("todo ")) {
            String description = command.length() == 4 ? "" : command.substring(5).trim();
            if (description.isEmpty()) {
                throw new VegaException("A todo needs a description. Try: todo buy milk");
            }
            addTask(tasks, new Todo(description));
        } else if (command.equals("deadline") || command.startsWith("deadline ")) {
            addDeadline(tasks, command);
        } else if (command.equals("event") || command.startsWith("event ")) {
            addEvent(tasks, command);
        } else {
            throw new VegaException("I don't recognise that command. Try todo, deadline, event, list, mark, unmark, delete, or bye.");
        }
    }

    /** Displays every task in the list. */
    private static void printList(ArrayList<Task> tasks) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        printLine();
    }

    /** Marks or unmarks a numbered task. */
    private static void updateTaskStatus(ArrayList<Task> tasks, String command, boolean shouldMark) throws VegaException {
        String prefix = shouldMark ? "mark" : "unmark";
        String numberText = command.substring(prefix.length()).trim();
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(numberText);
        } catch (NumberFormatException e) {
            throw new VegaException("Please give a valid task number. Try: " + prefix + " 1");
        }

        int taskIndex = taskNumber - 1;
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new VegaException("There is no task numbered " + taskNumber + ".");
        }

        if (shouldMark) {
            tasks.get(taskIndex).markAsDone();
            printLine();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            tasks.get(taskIndex).markAsNotDone();
            printLine();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("  " + tasks.get(taskIndex));
        printLine();
    }

    /** Removes a numbered task from the list. */
    private static void deleteTask(ArrayList<Task> tasks, String command) throws VegaException {
        String numberText = command.substring("delete".length()).trim();
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(numberText);
        } catch (NumberFormatException e) {
            throw new VegaException("Please give a valid task number. Try: delete 1");
        }

        int taskIndex = taskNumber - 1;
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new VegaException("There is no task numbered " + taskNumber + ".");
        }

        Task removedTask = tasks.remove(taskIndex);
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        String taskWord = tasks.size() == 1 ? "task" : "tasks";
        System.out.println("Now you have " + tasks.size() + " " + taskWord + " in the list.");
        printLine();
    }

    /** Adds a task and displays the standard confirmation message. */
    private static void addTask(ArrayList<Task> tasks, Task task) {
        tasks.add(task);
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        String taskWord = tasks.size() == 1 ? "task" : "tasks";
        System.out.println("Now you have " + tasks.size() + " " + taskWord + " in the list.");
        printLine();
    }

    /** Parses and adds a deadline command in the form "deadline DESCRIPTION /by TIME". */
    private static void addDeadline(ArrayList<Task> tasks, String command) throws VegaException {
        String details = command.length() == 8 ? "" : command.substring(9);
        String[] parts = details.split(" /by ", 2);
        if (parts.length < 2 || parts[0].isBlank() || parts[1].isBlank()) {
            throw new VegaException("A deadline needs a description and a /by time. Try: deadline submit report /by Friday");
        }
        addTask(tasks, new Deadline(parts[0].trim(), parts[1].trim()));
    }

    /** Parses and adds an event command in the form "event DESCRIPTION /from START /to END". */
    private static void addEvent(ArrayList<Task> tasks, String command) throws VegaException {
        String details = command.length() == 5 ? "" : command.substring(6);
        String[] fromParts = details.split(" /from ", 2);
        if (fromParts.length < 2) {
            throw new VegaException("An event needs /from and /to times. Try: event meeting /from 2pm /to 4pm");
        }
        String[] toParts = fromParts[1].split(" /to ", 2);
        if (fromParts[0].isBlank() || toParts.length < 2 || toParts[0].isBlank() || toParts[1].isBlank()) {
            throw new VegaException("An event needs a description, /from time, and /to time.");
        }
        addTask(tasks, new Event(fromParts[0].trim(), toParts[0].trim(), toParts[1].trim()));
    }

    /** Prints a user-friendly error inside Vega's standard response box. */
    private static void printError(String message) {
        printLine();
        System.out.println("OOPS!!! " + message);
        printLine();
    }

    /** Prints the separator used around Vega's responses. */
    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
