import static org.junit.jupiter.api.Assertions.*;

import model.Subtask;
import org.junit.jupiter.api.Test;

class SubtaskTest {

    @Test
    public void SubtasksWithEqualIdShouldBeEqual() {
        Subtask subtask1 = new Subtask("Купить продукты", "В магазине",5);
        Subtask subtask2 = new Subtask("Купить продукты", "В магазине",5);
        subtask1.setId(1);
        subtask2.setId(1);

        boolean value = subtask1.equals(subtask2);
        assertTrue(value,
                "Ошибка! Наследники класса model.Task должны быть равны друг другу, если равен их id;");
    }

  
}