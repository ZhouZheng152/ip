package vega;

/** Coordinates Vega's user interface, command parsing, task list, and storage. */
public class Vega {
    private final Parser parser;
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /** Creates Vega using the given task data file. */
    public Vega(String filePath) {
        parser = new Parser();
        storage = new Storage(filePath);
        ui = new Ui();

        TaskList loadedTasks;
        try {
            loadedTasks = new TaskList(storage.loadTasks());
        } catch (VegaException e) {
            ui.showError(e.getMessage());
            loadedTasks = new TaskList();
        }
        tasks = loadedTasks;
    }

    /** Starts Vega's command loop. */
    public void run() {
        ui.showWelcome();
        try (ui) {
            while (true) {
                String command = ui.readCommand();
                if (parser.getCommandWord(command).equals("bye")) {
                    break;
                }
                try {
                    handleCommand(command);
                } catch (VegaException e) {
                    ui.showError(e.getMessage());
                }
            }
        }
        ui.showGoodbye();
    }

    /** Runs Vega using its default data file. */
    public static void main(String[] args) {
        new Vega("data/vega.txt").run();
    }

    private void handleCommand(String command) throws VegaException {
        String commandWord = parser.getCommandWord(command);
        String argument = parser.getArguments(command);
        switch (commandWord) {
        case "list":
            ui.showTaskList(tasks.asList());
            break;
        case "mark":
            updateTaskStatus(argument, true);
            break;
        case "unmark":
            updateTaskStatus(argument, false);
            break;
        case "delete":
            deleteTask(argument);
            break;
        case "todo":
            if (argument.isEmpty()) {
                throw new VegaException("A todo needs a description. Try: todo buy milk");
            }
            addTask(new Todo(argument));
            break;
        case "deadline":
            addTask(parser.parseDeadline(argument));
            break;
        case "event":
            addTask(parser.parseEvent(argument));
            break;
        default:
            throw new VegaException("I don't recognise that command. "
                    + "Try todo, deadline, event, list, mark, unmark, delete, or bye.");
        }
    }

    private void updateTaskStatus(String argument, boolean shouldMark) throws VegaException {
        String commandWord = shouldMark ? "mark" : "unmark";
        int taskNumber = parser.parseTaskNumber(argument, commandWord);
        Task task = tasks.get(taskNumber);
        if (shouldMark) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
        storage.saveTasks(tasks.asList());
        ui.showTaskStatusChanged(task, shouldMark);
    }

    private void deleteTask(String argument) throws VegaException {
        int taskNumber = parser.parseTaskNumber(argument, "delete");
        Task removedTask = tasks.delete(taskNumber);
        storage.saveTasks(tasks.asList());
        ui.showTaskDeleted(removedTask, tasks.size());
    }

    private void addTask(Task task) throws VegaException {
        tasks.add(task);
        storage.saveTasks(tasks.asList());
        ui.showTaskAdded(task, tasks.size());
    }
}
