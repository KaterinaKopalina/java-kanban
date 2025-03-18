
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
    Task task1 = new Task("Помыть полы", "С новым средством");
    Task task2 = new Task("NewTask2", "NewTask2 description", Status.NEW);
    Epic epic1 = new Epic("NewEpic1", "NewEpic1 description");
    Subtask subtask11 = new Subtask("NewSubtask11", "NewSubtask11 description", 3);
    Subtask subtask12 = new Subtask("NewSubtask12", "NewSubtask12 description", 3);
    Epic epic2 = new Epic("NewEpic2", "NewEpic2 description");
    Subtask subtask21 = new Subtask("NewSubtask21", "NewSubtask21 description", 4);


    @BeforeEach
    public void beforeEach() {
        taskManager = Managers.getDefault();
    }

    @Test
    public void getHistoryShoulAddTask() {
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.getTaskByID(1);
        taskManager.getTaskByID(2);
        taskManager.getTaskByID(2);
        taskManager.addEpic(epic1);
        taskManager.getEpicByID(epic1.getId());
        taskManager.addSubtask(subtask11);
        taskManager.getSubtaskByID(subtask11.getId());
        List<Task> tasks = taskManager.getHistory();
        assertEquals(4, tasks.size(), "Количество задач в истории не верное!");
    }

    @Test
    public void getHistoryDelete() {
      taskManager.addTask(task1);
      taskManager.getTaskByID(task1.getId());

      taskManager.addTask(task2);
      taskManager.getTaskByID(task2.getId());

      taskManager.addEpic(epic1);
      taskManager.getEpicByID(epic1.getId());

      taskManager.addEpic(epic2);
      taskManager.getEpicByID(epic2.getId());
      taskManager.addSubtask(subtask11);
      taskManager.addSubtask(subtask12);
      taskManager.addSubtask(subtask21);
      taskManager.getSubtaskByID(subtask11.getId());
      taskManager.getSubtaskByID(subtask12.getId());
      taskManager.getSubtaskByID(subtask21.getId());

      taskManager.deleteTaskByID(1);
      taskManager.deleteEpicByID(3);

      List<Task> tasks = taskManager.getHistory();
      assertEquals(3, tasks.size(), "Количество задач не совпадает!");
    }

    @Test
    public void getHistoryShouldReturnOldTask() {
        Task task1 = new Task("Помыть полы", "С новым средством");
        taskManager.addTask(task1);
        taskManager.getTaskByID(task1.getId());
        taskManager.updateTask(new Task("Не забыть помыть полы",
                "Можно без средства", Status.IN_PROGRESS));
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
