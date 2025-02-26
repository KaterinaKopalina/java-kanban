import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ManagersTest {
    @Test
    void getDefaultMemoryTaskManager() {assertInstanceOf(InMemoryTaskManager.class, Managers.getDefault());
    }

    @Test
    void getDefaultHistoryInMemoryHistoryManager() {
        assertInstanceOf(InMemoryHistoryManager.class, Managers.getDefaultHistory());
    }

}