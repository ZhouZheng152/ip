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
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                } else if (command.startsWith("mark ")) {
                    try {
                        int taskNumber = Integer.parseInt(command.substring(5));
                        int taskIndex = taskNumber - 1;
                        if (taskIndex < 0 || taskIndex >= tasks.size()) {
                            System.out.println("There is no such task.");
                        } else {
                            tasks.get(taskIndex).markAsDone();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("  " + tasks.get(taskIndex));
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
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println("  " + tasks.get(taskIndex));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("There is no such task.");
                    }
                } else {
                    tasks.add(new Task(command));
                    System.out.println("added: " + command);
                }
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
