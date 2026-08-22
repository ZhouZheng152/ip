import java.util.Scanner;

public class Vega {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Vega");
        System.out.println("What can I do for you?");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String command = scanner.nextLine();

                if (command.equals("bye")) {
                    break;
                }

                System.out.println("You said: " + command);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
