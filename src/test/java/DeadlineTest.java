import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    private static final int YEAR = 2019;

    @Test
    public void getInfo_incompleteTask() {
        Calendar dateTime1 = Calendar.getInstance();
        dateTime1.set(YEAR, 9, 28, 23, 59);
        assertEquals("D | 0 | return book | 28/9 2359",
                (new Deadline("return book", "28/9 2359", dateTime1)).getInfo());
    }

    @Test
    public void getInfo_completedTask() {
        Calendar dateTime = Calendar.getInstance();
        dateTime.set(YEAR, 9, 28, 23, 59);
        Deadline deadline = new Deadline("return book", "28/9 2359", dateTime);
        deadline.done();
        assertEquals("D | 1 | return book | 28/9 2359", deadline.getInfo());
    }

    @Test
    public void stringToTask() {
        String name = "read book";
        String timeInfo = "29/9 1259";

        Calendar dateTime = Calendar.getInstance();
        dateTime.set(YEAR, 9, 29, 12, 59);

        assertEquals(new Deadline(name, timeInfo, dateTime), Deadline.stringToTask(name, timeInfo));
    }

    @Test
    public void testToString_incompleteTask() {
        Calendar dateTime = Calendar.getInstance();
        dateTime.set(YEAR, 9, 28, 23, 59);
        assertEquals("[D][\u2718] return book (by: 28/09/19 11:59PM)",
                (new Deadline("return book", "28/9 2359", dateTime)).toString());
    }

    @Test
    public void testToString_completedTask() {
        Calendar dateTime = Calendar.getInstance();
        dateTime.set(YEAR, 9, 28, 23, 59);
        Deadline deadline = new Deadline("return book", "28/9 2359", dateTime);
        deadline.done();
        assertEquals("[D][\u2714] return book (by: 28/09/19 11:59PM)", deadline.toString());
    }
}