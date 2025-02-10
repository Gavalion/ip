package bryan.task;

import bryan.exception.EmptyDeadlineDescription;
import bryan.exception.InvalidDeadlineFormatException;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description) throws InvalidDeadlineFormatException, EmptyDeadlineDescription {
        super(description);
        String[] descriptionArray = description.split(" /by ");
        if (descriptionArray.length != 2) {
            throw new InvalidDeadlineFormatException();
        }
        this.description = descriptionArray[0];
        this.by = descriptionArray[1];
        if (this.by.isBlank() || this.description.isBlank()){
            throw new EmptyDeadlineDescription();
        }
        System.out.println("got it task added");
        System.out.println(this);
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by " + getBy() + ")";
//        return "description: "+super.getDescription() +"\n" +" " + ((isDone) ? "yes": "no") +"\n";
    }
}
