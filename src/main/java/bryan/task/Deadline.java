package bryan.task;

import bryan.exception.EmptyDeadlineDescription;
import bryan.exception.InvalidDeadlineFormatException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected LocalDate by;
    public static final DateTimeFormatter DATE_TIME_FORMATER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Deadline(String description) throws InvalidDeadlineFormatException, EmptyDeadlineDescription {
        super(description);
        String[] descriptionArray = description.split(" /by ");
        if (descriptionArray.length != 2) {
            throw new InvalidDeadlineFormatException();
        }
        this.description = descriptionArray[0];
        try {
            this.by = LocalDate.parse(descriptionArray[1],DATE_TIME_FORMATER);
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineFormatException();
        }
        if (this.description.isBlank()) {
            throw new EmptyDeadlineDescription();
        }
        System.out.println("got it task added");
        System.out.println(this);
    }

    public Deadline(String description, boolean printMessage) throws InvalidDeadlineFormatException, EmptyDeadlineDescription {
        super(description);
        String[] descriptionArray = description.split(" /by ");
        if (descriptionArray.length != 2) {
            throw new InvalidDeadlineFormatException();
        }
        this.description = descriptionArray[0];
        try {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            this.by = LocalDate.parse(descriptionArray[1], DATE_TIME_FORMATER);
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

    @Override
    public String convertToTxtFormat() {
        return "D # " + super.convertToTxtFormat() + " # " + this.getBy().format(DATE_TIME_FORMATER);
    }
}
