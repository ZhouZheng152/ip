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
                } else if (command.equals("list")) {
                    printLine();
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i));
                    }
                    printLine();
                } else if (command.startsWith("mark ")) {
                    try {
                        int taskNumber = Integer.parseInt(command.substring(5));
                        int taskIndex = taskNumber - 1;
                        if (taskIndex < 0 || taskIndex >= tasks.size()) {
                            System.out.println("There is no such task.");
                        } else {
                            tasks.get(taskIndex).markAsDone();
                            printLine();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("  " + tasks.get(taskIndex));
                            printLine();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("There is no such task.");
                    }
                } else if (command.startsWith("unmark ")) {
                    try {
                        int taskNumber = Integer.parseInt(command.substring(7));
                        int taskIndex = taskNumber - 1;
                        if (taskIndex < 0 || taskIndex >= tasks.size()) {
                            System.out.println("There is no such task.");
                        } else {
                            tasks.get(taskIndex).markAsNotDone();
                            printLine();
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println("  " + tasks.get(taskIndex));
                            printLine();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("There is no such task.");
                    }
                } else if (command.startsWith("todo ")) {
                    addTask(tasks, new Todo(command.substring(5)));
                } else if (command.startsWith("deadline ")) {
                    addDeadline(tasks, command);
                } else if (command.startsWith("event ")) {
                    addEvent(tasks, command);
                } else {
                    addTask(tasks, new Todo(command));
                }
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
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
    private static void addDeadline(ArrayList<Task> tasks, String command) {
        String[] parts = command.substring(9).split(" /by ", 2);
        if (parts.length < 2 || parts[0].isBlank() || parts[1].isBlank()) {
            System.out.println("Please use: deadline DESCRIPTION /by TIME");
            return;
        }
        addTask(tasks, new Deadline(parts[0], parts[1]));
    }

    /** Parses and adds an event command in the form "event DESCRIPTION /from START /to END". */
    private static void addEvent(ArrayList<Task> tasks, String command) {
        String[] fromParts = command.substring(6).split(" /from ", 2);
        if (fromParts.length < 2) {
            System.out.println("Please use: event DESCRIPTION /from START /to END");
            return;
        }
        String[] toParts = fromParts[1].split(" /to ", 2);
        if (fromParts[0].isBlank() || toParts.length < 2 || toParts[0].isBlank() || toParts[1].isBlank()) {
            System.out.println("Please use: event DESCRIPTION /from START /to END");
            return;
        }
        addTask(tasks, new Event(fromParts[0], toParts[0], toParts[1]));
    }

    /** Prints the separator used around Vega's responses. */
    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
