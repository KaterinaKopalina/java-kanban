
import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();

    private int numberId = 1;

    private int getNumberId() {
        return numberId++;
    }

    public Task addTask(Task task) {
        task.setId(getNumberId());
        tasks.put(task.getId(), task);
        return task;
    }

    public void printAllTask() {
        System.out.println(tasks);
    }

    public Epic addEpic(Epic epic) {
        epic.setId(getNumberId());
        epics.put(epic.getId(), epic);
        return epic;
    }

    public void printAllEpic() {
        System.out.println(epics);
    }

    public Subtask addSubtask(Subtask subtask) {
     int epicID = subtask.getIdEpic();
     if (epicID == 0 || !epics.containsKey(epicID)) {
     return null;
     }
        subtask.setId(getNumberId());
        Epic epic = epics.get(subtask.getIdEpic());
        epic.addSubtask(subtask);
        subtasks.put(subtask.getId(), subtask);
        updateStatusEpic(epic);
        return subtask;
    }

    public void printAllSubtask() {
        System.out.println(subtasks);
    }

    public Task updateTask(Task task) {
        int taskID = task.getId();
        if (taskID == 0 || !tasks.containsKey(taskID)) {
            return null;
        }
        tasks.replace(taskID, task);
        return task;
    }

    public Epic updateEpic(Epic epic) {
        int epicID = epic.getId();
        if (epicID == 0 || !epics.containsKey(epicID)) {
            return null;
        }
        Epic saved = epics.get(epic.getId());
        saved.setName(epic.getName());
        saved.setDescription(epic.getDescription());
        return epic;
    }

    public Subtask updateSubtask(Subtask subtask) {
        int subtaskID = subtask.getId();
        int epicId = subtask.getIdEpic();
        if (subtaskID == 0 || !subtasks.containsKey(subtaskID) || epicId != subtasks.get(subtaskID).getIdEpic()) {
            return null;
        }
        Subtask oldSubtask = subtasks.get(subtaskID);
        subtasks.replace(subtaskID, subtask);

        Epic epic = epics.get(epicId);
        epic.deleteSubtask(oldSubtask);
        epic.addSubtask(subtask);
        updateStatusEpic(epic);
        return subtask;
    }

    public Task getTaskByID(int id) {
        return tasks.get(id);
    }

    public Epic getEpicByID(int id) {
        return epics.get(id);
    }

    public Subtask getSubtaskByID(int id) {
        return subtasks.get(id);
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public ArrayList<Subtask> getSubtaskByEpic (int epicId) {
        if (epics.containsKey(epicId)) {
                return epics.get(epicId).getSubtaskList();
            }
        return null;
    }

    public void deleteTasks() {
        tasks.clear();
    }

    public void deleteEpics() {
        epics.clear();
        subtasks.clear();
    }

    public void deleteSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.clearSubtasks();
            updateStatusEpic(epic);
        }
    }

    public void deleteTaskByID(int id) {
        tasks.remove(id);
    }

    public void deleteEpicByID(int id) {
       if (!epics.containsKey(id)) {
           return;
       }
        ArrayList<Subtask> epicSubtasks = epics.get(id).getSubtaskList();
        epics.remove(id);
        for (Subtask subtask : epicSubtasks) {
            subtasks.remove(subtask.getId());
        }
    }

    public void deleteSubtaskByID(int id) {
        if (!subtasks.containsKey(id)) {
         return;
         }
        Subtask subtask = subtasks.get(id);
        int epicID = subtask.getIdEpic();
        subtasks.remove(id);
        // обновляем список подзадач и статус эпика
        Epic epic = epics.get(epicID);
        epic.deleteSubtask(subtask);
        updateStatusEpic(epic);
    }

    private void updateStatusEpic(Epic epic) {
        int allIsDoneCount = 0;
        int allIsInNewCount = 0;
        ArrayList<Subtask> list = epic.getSubtaskList();

        for (Subtask subtask : list) {
            if (subtask.getStatus() == Status.DONE) {
                allIsDoneCount++;
            }
            if (subtask.getStatus() == Status.NEW) {
                allIsInNewCount++;
            }
        }

        if (allIsInNewCount == list.size()) {
            epic.setStatus(Status.NEW);
        } else if (allIsDoneCount == list.size()) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }
}