import java.util.ArrayList;
import java.util.List;

public interface TaskManager {

    Task addTask(Task task);

    void printAllTask();

    Epic addEpic(Epic epic);

    void printAllEpic();

    Subtask addSubtask(Subtask subtask);

    void printAllSubtask();

    Task updateTask(Task task);

    Epic updateEpic(Epic epic);

    Subtask updateSubtask(Subtask subtask);

    Task getTaskByID(int id);

    Epic getEpicByID(int id);

    Subtask getSubtaskByID(int id);

    ArrayList<Task> getTasks();

    ArrayList<Epic> getEpics();

    ArrayList<Subtask> getSubtasks();

    ArrayList<Subtask> getSubtaskByEpic(int epicId);

    void deleteTasks();

    void deleteEpics();

    void deleteSubtasks();

    void deleteTaskByID(int id);

    void deleteEpicByID(int id);

    void deleteSubtaskByID(int id);

    List<Task> getHistory();

}
