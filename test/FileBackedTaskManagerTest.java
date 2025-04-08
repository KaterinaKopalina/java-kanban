import static org.junit.jupiter.api.Assertions.*;

import model.Epic;
import model.Subtask;
import model.Task;
import org.junit.jupiter.api.Test;
import service.FileBackedTaskManager;

import java.io.File;

public class FileBackedTaskManagerTest {
    private final File file = new File("test1.csv");
    private FileBackedTaskManager taskManager = new FileBackedTaskManager(file);

    @Test
    public void testSaveAndLoad() {
        Task task = new Task("Задача №1", "Расшифровка");
        taskManager.addTask(task);

        Epic epic = new Epic("Эпик №1", "Расшифровка");
        taskManager.addEpic(epic);

        Subtask subtask = new Subtask("Подзадача №1", "Расшифровка", epic.getId());
        taskManager.addSubtask(subtask);

        taskManager = FileBackedTaskManager.loadFromFile(file);

        assertNotNull(taskManager.getTaskByID(task.getId()), "Задача не добавлена");
        assertNotNull(taskManager.getEpicByID(epic.getId()), "Эпик не добавлен");
        assertNotNull(taskManager.getSubtaskByID(subtask.getId()), "Подзадача не найдена");
    }

}
