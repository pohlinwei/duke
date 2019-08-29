import duke.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void getInfo_incompleteTask() {
        assertEquals("T | 0 | read book",
                (new Todo("read book")).getInfo());
    }

    @Test
    public void getInfo_completedTask() {
        Todo todo = new Todo("read book");
        todo.setDone();
        assertEquals("T | 1 | read book", todo.getInfo());
    }

    @Test
    public void stringToTask() {
        String name = "read book";
        assertEquals(new Todo(name), Todo.stringToTask(name));
    }

    @Test
    public void testToString_incompleteTask() {
        assertEquals("[T][\u2718] read book",
                (new Todo("read book")).toString());
    }

    @Test
    public void testToString_completedTask() {
        Todo todo = new Todo("read book");
        todo.setDone();
        assertEquals("[T][\u2714] read book", todo.toString());
    }
}