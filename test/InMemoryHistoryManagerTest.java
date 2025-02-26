
import static org.junit.jupiter.api.Assertions.*;

import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.Managers;
import service.TaskManager;

import java.util.List;

class InMemoryHistoryManagerTest {
    private static TaskManager taskManager;

    @BeforeEach
    public void beforeEach() {
        taskManager = Managers.getDefault();
    }

    @Test
    public void getHistoryShouldReturnL10Tasks() {
        for (int i = 0; i < 15; i++) {
            taskManager.addTask(new Task("Задача", "Расшифровка"));
        }

        List<Task> tasks = taskManager.getTasks();
        for (Task task : tasks) {
            taskManager.getTaskByID(task.getId());
        }

        List<Task> list = taskManager.getHistory();
        assertEquals(10, list.size(), "Неверное количество элементов в истории ");
    }

    @Test
    public void getHistoryShouldReturnOldTask() {
        Task task1 = new Task("Помыть полы", "С новым средством");
        taskManager.addTask(task1);
        taskManager.getTaskByID(task1.getId());
        taskManager.updateTask(new Task("Не забыть помыть полы",
                "Можно средства", Status.IN_PROGRESS));
        List<Task> tasks = taskManager.getHistory();
        Task oldTask = tasks.getFirst();
        assertEquals(task1.getName(), oldTask.getName(), "В истории не сохранилась старая версия задачи");
        assertEquals(task1.getDescription(), oldTask.getDescription(),
                "В истории не сохранилась старая версия задачи");
    }

    @Test
    public void getHistoryShouldReturnOldEpic() {
        Epic epic1 = new Epic("Сделать ремонт", "В комнате");
        taskManager.addEpic(epic1);
        taskManager.getEpicByID(epic1.getId());
        taskManager.updateEpic(new Epic("Новое имя", "новое описание"));
        List<Task> epics = taskManager.getHistory();
        Epic oldEpic = (Epic) epics.getFirst();
        assertEquals(epic1.getName(), oldEpic.getName(),
                "В истории не сохранилась старая версия эпика");
        assertEquals(epic1.getDescription(), oldEpic.getDescription(),
                "В истории не сохранилась старая версия эпика");
    }


    @Test
    public void getHistoryShouldReturnOldSubtask() {
        Epic epic1 = new Epic("Сделать ремонт", "На кухне");
        taskManager.addEpic(epic1);
        Subtask epic1Subtask1 = new Subtask("Заказать пиццу", "Из пиццерии",
                epic1.getId());
        taskManager.addSubtask(epic1Subtask1);
        taskManager.getSubtaskByID(epic1Subtask1.getId());
        taskManager.updateSubtask(new Subtask("Новое имя",
                "новое описание", Status.IN_PROGRESS, epic1Subtask1.getId()));
        List<Task> subtasks = taskManager.getHistory();
        Subtask oldSubtask = (Subtask) subtasks.getFirst();
        assertEquals(epic1Subtask1.getName(), oldSubtask.getName(),
                "В истории не сохранилась старая версия подзадачи");
        assertEquals(epic1Subtask1.getDescription(), oldSubtask.getDescription(),
                "В истории не сохранилась старая версия подзадачи");
    }
}
