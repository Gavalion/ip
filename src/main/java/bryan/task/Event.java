package bryan.task;

import bryan.exception.EmptyEventDescription;
import bryan.exception.InvalidEventFormatException;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description) throws InvalidEventFormatException, EmptyEventDescription {
        super(description);
        if (!(description.contains(" /from ") && description.contains(" /to ") && description.indexOf("/from") < description.indexOf("/to"))) {//&& userString.indexOf("/from") > userString.indexOf("/to")) {
            throw new InvalidEventFormatException();
        }
        String[] descriptionArray = description.split(" /from ");
        String[] fromToArray = descriptionArray[1].split(" /to ");
        if (descriptionArray.length != 2 || fromToArray.length != 2) {
            throw new InvalidEventFormatException();
        }
        this.description = descriptionArray[0];
        this.from = description.split(" /from ")[1].split(" /to ")[0];
        this.to = description.split(" /to ")[1];
        if (this.description.isBlank() || this.from.isBlank() || this.to.isBlank()) {
            throw new EmptyEventDescription();
        }
        System.out.println("got it task added");
        System.out.println(this);
    }

    public Event(String description, boolean printMessage) throws InvalidEventFormatException, EmptyEventDescription {
        super(description);
        if (!(description.contains(" /from ") && description.contains(" /to ") && description.indexOf("/from") < description.indexOf("/to"))) {//&& userString.indexOf("/from") > userString.indexOf("/to")) {
            throw new InvalidEventFormatException();
        }
        String[] descriptionArray = description.split(" /from ");
        String[] fromToArray = descriptionArray[1].split(" /to ");
        if (descriptionArray.length != 2 || fromToArray.length != 2) {
            throw new InvalidEventFormatException();
        }
        this.description = descriptionArray[0];
        this.from = description.split(" /from ")[1].split(" /to ")[0];
        this.to = description.split(" /to ")[1];
        if (this.description.isBlank() || this.from.isBlank() || this.to.isBlank()) {
            throw new EmptyEventDescription();
        }
        if (printMessage) {
            System.out.println("got it task added");
            System.out.println(this);
        }
    }


    public String getFrom() {
        return from;
    }

    public void setFrom(String by) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFrom() + " to: " + getTo() + ")";
//        return "description: "+super.getDescription() +"\n" +" " + ((isDone) ? "yes": "no") +"\n";
    }

    @Override
    public String convertToTxtFormat() {
        return "E # " + super.convertToTxtFormat() + " # " + this.getFrom() + "-" + this.getTo();
    }
}

