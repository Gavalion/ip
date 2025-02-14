package bryan.task;

import bryan.exception.InvalidTodoFormatException;

public class Todo extends Task {

    public Todo(String description) throws InvalidTodoFormatException {
        super(description);
        if (description.isBlank()) {
            throw new InvalidTodoFormatException();
        }
        System.out.println("got it task added");
        System.out.println(this);
    }

    public Todo(String description, boolean printMessage) throws InvalidTodoFormatException {
        super(description);
        if (description.isBlank()) {
            throw new InvalidTodoFormatException();
        }

        if (printMessage) {
            System.out.println("got it task added");
            System.out.println(this);
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
//        return "description: "+super.getDescription() +"\n" +" " + ((isDone) ? "yes": "no") +"\n";
    }

    @Override
    public String convertToTxtFormat() {
        return "T # " + super.convertToTxtFormat();
    }
}
