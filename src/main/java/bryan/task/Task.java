package bryan.task;

import bryan.exception.MarkingException;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
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
        if ((done && this.isDone() || (!done && !this.isDone()))) {
            throw new MarkingException();
        }
        isDone = done;
    }

    /**
     * Convert task to txt format that includes the marking
     * @return string of task in the txt format
     */
    public String convertToTxtFormat() {
        return (this.isDone() ? "1" : "0") + " # " + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

//...
}
