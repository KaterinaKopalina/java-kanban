package model;

import java.util.ArrayList;

public class Epic extends Task {


    private ArrayList<Subtask> subtaskList = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
    }

    public void addSubtask(Subtask subtask) {
        subtaskList.add(subtask);
    }

    public void clearSubtasks() {
        subtaskList.clear();
    }

    public ArrayList<Subtask> getSubtaskList() {
        return subtaskList;
    }

    public void deleteSubtask(Subtask subtask) {
        subtaskList.remove(subtask);
    }


    public String toString() {
        return "model.Epic{"
                + "id=" + getId()
                + ", name=\"" + getName()
                + "\", description=\"" + getDescription()
                + ",\nstatus=" + getStatus()
                + "}";
    }
}




