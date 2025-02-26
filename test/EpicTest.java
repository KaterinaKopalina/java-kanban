
import static org.junit.jupiter.api.Assertions.*;

import model.Epic;
import org.junit.jupiter.api.Test;

class EpicTest {

    @Test
    public void EpicsWithEqualIdShouldBeEqual() {
        Epic epic1 = new Epic ("Сделать ремонт", "На кухне");
        Epic epic2 = new Epic("Сделать ремонт", "На кухне");
        epic1.setId(1);
        epic2.setId(1);
        boolean value = epic1.equals(epic2);
        assertTrue(value,
                "Ошибка! Наследники класса model.Task должны быть равны друг другу, если равен их id;");
    }

}