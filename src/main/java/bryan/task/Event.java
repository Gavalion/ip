package bryan.task;

import bryan.exception.EmptyEventDescription;
import bryan.exception.InvalidEventFormatException;

public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Event constructor that convert the detail into description, from and to
     * Prints out when a task is added
     * Method is called whenever the user add a new event
     * @param detail Any text that comes after "event" command
     * @throws InvalidEventFormatException Invalid deadline exception due to missing from and to or from is after to
     * @throws EmptyEventDescription Invalid deadline exception due to empty description
     */
    public Event(String detail) throws InvalidEventFormatException, EmptyEventDescription {
        super(detail);
        // Check whether there are /from and /to inside the detail dnd /from is before /to
        if (!(detail.contains(" /from ") && detail.contains(" /to ") && detail.indexOf("/from") < detail.indexOf("/to"))) {
            throw new InvalidEventFormatException();
        }
        String[] detailArray = detail.split(" /from ");
        String[] fromToArray = detailArray[1].split(" /to ");
        if (detailArray.length != 2 || fromToArray.length != 2) {
            throw new InvalidEventFormatException();
        }
        this.description = detailArray[0];
        this.from = detail.split(" /from ")[1].split(" /to ")[0];
        this.to = detail.split(" /to ")[1];
        if (this.description.isBlank() || this.from.isBlank() || this.to.isBlank()) {
            throw new EmptyEventDescription();
        }
        System.out.println("got it task added");
        System.out.println(this);
    }

    /**
     * event constructor that convert the detail into description, from and to
     * It has the option to not print task added.
     * Mainly for creating task when reading the txt file
     * @param detail Any text that comes after "deadline" command
     * @param printMessage Boolean value to choose whether to print or not print the message
     * @throws InvalidEventFormatException Invalid deadline exception due to missing from and to or from is after to
     * @throws EmptyEventDescription Invalid deadline exception due to empty description
     */
    public Event(String detail, boolean printMessage) throws InvalidEventFormatException, EmptyEventDescription {
        super(detail);
        if (!(detail.contains(" /from ") && detail.contains(" /to ") && detail.indexOf("/from") < detail.indexOf("/to"))) {//&& userString.indexOf("/from") > userString.indexOf("/to")) {
            throw new InvalidEventFormatException();
        }
        String[] detailArray = detail.split(" /from ");
        String[] fromToArray = detailArray[1].split(" /to ");
        if (detailArray.length != 2 || fromToArray.length != 2) {
            throw new InvalidEventFormatException();
        }
        this.description = detailArray[0];
        this.from = detail.split(" /from ")[1].split(" /to ")[0];
        this.to = detail.split(" /to ")[1];
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

    public void setFrom(String from) {
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

    /**
     * Convert the task into event txt format
     * @return String of the event txt format
     */
    @Override
    public String convertToTxtFormat() {
        return "E # " + super.convertToTxtFormat() + " # " + this.getFrom() + "-" + this.getTo();
    }
}

