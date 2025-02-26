import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import java.util.ArrayList;
import java.util.HashMap;


public class InMemoryTaskManagerTest {
    TaskManager taskManager = new InMemoryTaskManager();    //Получение менеджера задач
    Task task1 = new Task("NewTask1", "NewTask1 description", Status.NEW);
    Task task2 = new Task("NewTask2", "NewTask2 description", Status.NEW);
    Epic epic1 = new Epic("NewEpic1", "NewEpic1 description");
    Subtask subtask11 = new Subtask("NewSubtask11", "NewSubtask11 description", epic1.getId());
    Subtask subtask12 = new Subtask("NewSubtask12", "NewSubtask12 description", epic1.getId());
    Epic epic2 = new Epic("NewEpic2", "NewEpic2 description");
    Subtask subtask21 = new Subtask("NewSubtask21", "NewSubtask21 description", 1);

@BeforeEach
void beforeEach() {
    taskManager = Managers.getDefault();
}
    @Test
    void addNewTask() {
        taskManager.addTask(task1);
        final int taskId = task1.getId();
        final Task savedTask = taskManager.getTaskByID(taskId);

        taskManager.addTask(task2);
        task2.setId(3);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task1, savedTask, "Задачи не совпадают.");
        assertEquals(3, task2.getId(), "Id не совпадают.");

        final ArrayList<Task> tasks = taskManager.getTasks();


        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(2, tasks.size(), "Неверное количество задач.");
        assertEquals(task1, tasks.get(0), "Задачи не совпадают.");


    }

    @Test
    void addNewEpic() {
        taskManager.addEpic(epic1);
        final int epicId = epic1.getId();

        final Epic savedEpic = taskManager.getEpicByID(epicId);

        assertNotNull(savedEpic, "Эпик не найден.");
        assertEquals(epic1, savedEpic, "Эпики не совпадают.");

        final ArrayList<Epic> epics = taskManager.getEpics();

        assertNotNull(epics, "Эпики не возвращаются.");
        assertEquals(1, epics.size(), "Неверное количество задач.");
        assertEquals(epic1, epics.get(0), "Эпики не совпадают.");
    }

    @Test
    void addNewSubtask() {
       Epic thisEpic = taskManager.addEpic(epic2);
       Subtask thisSubtask = taskManager.addSubtask(subtask21);
        ArrayList<Subtask> listOfSubtask = taskManager.getSubtaskByEpic(thisEpic.getId());

        assertNotNull(listOfSubtask, "Подзадачи не возвращаются.");
        assertEquals(subtask21, listOfSubtask.get(0), "Подзадачи не совпадают.");
        assertEquals(1, listOfSubtask.size(), "Неверное количество подзадач.");
        assertEquals(thisEpic.getId(), thisSubtask.getIdEpic(), "Эпики не совпадают.");
    }

    @Test
    void TaskCreatedAndTaskAddedShouldHaveSameVariables() {
        Task expected = new Task("Помыть полы", "С новым средством", Status.DONE);
        taskManager.addTask(expected);
        ArrayList<Task> list = taskManager.getTasks();
        Task actual = list.getFirst();
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getStatus(), actual.getStatus());
    }
}