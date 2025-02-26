import static org.junit.jupiter.api.Assertions.*;

import model.Status;
import model.Task;
import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    public void tasksWithEqualIdShouldBeEqual() {
        Task task1 = new Task("Полить цветы", "дома", Status.NEW);
        task1.setId(1);
        Task task2 = new Task("Полить цветы", "дома", Status.NEW);
        task2.setId(1);
        boolean value = task1.equals(task2);
        assertTrue(value,
                "Ошибка! Экземпляры класса model.Task должны быть равны друг другу, если равен их id;");
    }
}