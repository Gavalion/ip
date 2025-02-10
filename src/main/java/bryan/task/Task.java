package bryan.task;

import bryan.exception.MarkingException;

public class Task{
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) throws MarkingException {
        if ((done && this.isDone() || (!done && this.isDone()))) {
            throw new MarkingException();
        }
        isDone = done;
    }

    @Override
    public String toString() {
        return "["+this.getStatusIcon()+"] "+ this.getDescription();
    }

//...
}
