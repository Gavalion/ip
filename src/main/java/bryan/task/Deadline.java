package bryan.task;

import bryan.exception.EmptyDeadlineDescription;
import bryan.exception.InvalidDeadlineFormatException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate by;
    // set the date time format
    public static final DateTimeFormatter DATE_TIME_FORMATER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Deadline constructor that convert the detail into description and by date
     * Prints out when a task is added
     * Method is called whenever the user add a new deadline
     * @param detail Any text that comes after "deadline" command
     * @throws InvalidDeadlineFormatException Invalid deadline exception due to missing by
     * @throws EmptyDeadlineDescription Invalid deadline exception due to empty description
     */
    public Deadline(String detail) throws InvalidDeadlineFormatException, EmptyDeadlineDescription {
        super(detail);
        String[] detailArray = description.split(" /by ");
        if (detailArray.length != 2) {
            throw new InvalidDeadlineFormatException();
        }
        this.description = detailArray[0];
        try {
            this.by = LocalDate.parse(detailArray[1],DATE_TIME_FORMATER);
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineFormatException();
        }
        if (this.description.isBlank()) {
            throw new EmptyDeadlineDescription();
        }
        System.out.println("got it task added");
        System.out.println(this);
    }

    /**
     * Deadline constructor that convert the detail into description and by date
     * It has the option to not print task added.
     * Mainly for creating task when reading the txt file
     * @param detail Any text that comes after "deadline" command
     * @param printMessage Boolean value to choose whether to print or not print the message
     * @throws InvalidDeadlineFormatException Invalid deadline exception due to missing by
     * @throws EmptyDeadlineDescription Invalid deadline exception due to empty description
     */
    public Deadline(String detail, boolean printMessage) throws InvalidDeadlineFormatException, EmptyDeadlineDescription {
        super(detail);
        String[] detailArray = description.split(" /by ");
        if (detailArray.length != 2) {
            throw new InvalidDeadlineFormatException();
        }
        this.description = detailArray[0];
        try {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            this.by = LocalDate.parse(detailArray[1], DATE_TIME_FORMATER);
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineFormatException();
        }
        if (this.description.isBlank()) {
            throw new EmptyDeadlineDescription();
        }
        if (printMessage) {
            System.out.println("got it task added");
            System.out.println(this);
        }
    }

    public LocalDate getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by " + getBy().format(DATE_TIME_FORMATER) + ")";
    }

    /**
     * Convert the task into deadline txt format
     * @return String of the deadline txt format
     */
    @Override
    public String convertToTxtFormat() {
        return "D # " + super.convertToTxtFormat() + " # " + this.getBy().format(DATE_TIME_FORMATER);
    }
}
