package service;


import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, Subtask> subtasks = new HashMap<>();

    private final HistoryManager historyManager = Managers.getDefaultHistory();


    private int numberId = 1;

    private int getNumberId() {
        return numberId++;
    }

    @Override
    public Task addTask(Task task) {
        task.setId(getNumberId());
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public void printAllTask() {
        System.out.println(tasks);
    }

    @Override
    public Epic addEpic(Epic epic) {
        epic.setId(getNumberId());
        epics.put(epic.getId(), epic);
        return epic;
    }

    @Override
    public void printAllEpic() {
        System.out.println(epics);
    }

    @Override
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

    @Override
    public void printAllSubtask() {
        System.out.println(subtasks);
    }

    @Override
    public Task updateTask(Task task) {
        int taskID = task.getId();
        if (taskID == 0 || !tasks.containsKey(taskID)) {
            return null;
        }
        tasks.replace(taskID, task);
        return task;
    }

    @Override
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

    @Override
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

    @Override
    public Task getTaskByID(int id) {
        Task task = tasks.get(id);
        if (task != null) {
            historyManager.add(task);
        }
        return task;
    }

    @Override
    public Epic getEpicByID(int id) {
        Epic epic = epics.get(id);
        if (epic != null) {
            historyManager.add(epic);
        }
        return epic;
    }

    @Override
    public Subtask getSubtaskByID(int id) {
        Subtask subtask = subtasks.get(id);
        if (subtask != null) {
            historyManager.add(subtask);
        }
        return subtask;
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public ArrayList<Subtask> getSubtaskByEpic(int epicId) {
        if (epics.containsKey(epicId)) {
            return epics.get(epicId).getSubtaskList();
        }
        return null;
    }

    @Override
    public void deleteTasks() {
        for (int taskId : tasks.keySet()) {
            historyManager.remove(taskId);
        }
        tasks.clear();
    }

    @Override
    public void deleteEpics() {

        for (int subId : subtasks.keySet()) {
            historyManager.remove(subId);
        }
        for (int epicId : epics.keySet()) {
            historyManager.remove(epicId);
        }
        subtasks.clear();
        epics.clear();

    }

    @Override
    public void deleteSubtasks() {

        for (Epic epic : epics.values()) {
            epic.clearSubtasks();
            updateStatusEpic(epic);
        }
        for (int subId : subtasks.keySet()) {
            historyManager.remove(subId);
        }
        subtasks.clear();
    }

    @Override
    public void deleteTaskByID(int id) {
        tasks.remove(id);
        historyManager.remove(id);
    }

    @Override
    public void deleteEpicByID(int id) {
        if (!epics.containsKey(id)) {
            return;
        }
        ArrayList<Subtask> epicSubtasks = epics.get(id).getSubtaskList();
        epics.remove(id);
        historyManager.remove(id);
        for (Subtask subtask : epicSubtasks) {
            subtasks.remove(subtask.getId());
            historyManager.remove(subtask.getId());
        }
    }

    @Override
    public void deleteSubtaskByID(int id) {
        if (!subtasks.containsKey(id)) {
            return;
        }
        Subtask subtask = subtasks.get(id);
        int epicID = subtask.getIdEpic();
        subtasks.remove(id);
        historyManager.remove(id);
        // обновляем список подзадач и статус эпика
        Epic epic = epics.get(epicID);
        epic.deleteSubtask(subtask);
        updateStatusEpic(epic);
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
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

