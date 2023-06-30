package logica;

public class TaskUpdateRequest {

    private int id;
    private String descripcion;
    private boolean completed;

    private Task task;

    /*public TaskUpdateRequest() {
    }

    public TaskUpdateRequest(int id, String descripcion, boolean completed, Task task) {
        this.id = id;
        this.descripcion = descripcion;
        this.completed = completed;
        this.task = task;
    }*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

}
