package vega;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void add_validTask_increasesSizeAndStoresTask() throws VegaException {
        TaskList tasks = new TaskList();
        Todo todo = new Todo("read book");

        tasks.add(todo);

        assertEquals(1, tasks.size());
        assertSame(todo, tasks.get(1));
    }

    @Test
    public void delete_existingTask_removesAndReturnsTask() throws VegaException {
        TaskList tasks = new TaskList();
        Todo todo = new Todo("read book");
        tasks.add(todo);

        Task deletedTask = tasks.delete(1);

        assertSame(todo, deletedTask);
        assertEquals(0, tasks.size());
    }

    @Test
    public void get_outOfRangeTaskNumber_throwsVegaException() {
        TaskList tasks = new TaskList();

        assertThrows(VegaException.class, () -> tasks.get(1));
    }
}
