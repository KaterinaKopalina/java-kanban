package model;

public class Subtask extends Task {
    private final int idEpic;

    public Subtask(String name, String description, int idEpic) {
        super(name, description);
        this.idEpic = idEpic;
    }

    public Subtask(String name, String description, Status status, int idEpic) {
        super(name, description, status);
        this.idEpic = idEpic;
    }

    public int getIdEpic() {
        return idEpic;
    }


    @Override
    public String toString() {
        return "model.Subtask{" +
            "name='" + getName() + '\'' +
                    ", description='" + getDescription() + '\'' +
                    ", id=" + getId() +
                    ", epicID=" + idEpic +
                    ", status=" + getStatus() +
                    '}';
    }
}
