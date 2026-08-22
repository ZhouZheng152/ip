import java.util.ArrayList;
import java.util.Scanner;

public class Vega {
    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>();

        System.out.println("Hello! I'm Vega");
        System.out.println("What can I do for you?");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String command = scanner.nextLine();

                if (command.equals("bye")) {
                    break;
                } else if (command.equals("list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                } else {
                    tasks.add(command);
                    System.out.println("added: " + command);
                }
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
