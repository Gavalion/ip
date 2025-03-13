package bryan.task;


import bryan.exception.InvalidTodoFormatException;

public class Todo extends Task {

    /**
     * Todo constructor that convert the detail into description
     * Prints out when a task is added
     * Method is called whenever the user add a new todo
     * @param detail Any text that comes after "todo" command
     * @throws InvalidTodoFormatException@throws InvalidTodoFormatException Invalid todo format that occurs because of empty description
     */
    public Todo(String detail) throws InvalidTodoFormatException {
        super(detail);
        if (detail.isBlank()) {
            throw new InvalidTodoFormatException();
        }
        System.out.println("got it task added");
        System.out.println(this);
    }

    /**
     * Todo constructor that convert the detail into description
     * It has the option to not print task added.
     * Mainly for creating task when reading the txt file
     * @param detail Any text that comes after "todo" command
     * @param printMessage Boolean value to choose whether to print or not print the message
     * @throws InvalidTodoFormatException@throws InvalidTodoFormatException Invalid todo format that occurs because of empty description
     */
    public Todo(String detail, boolean printMessage) throws InvalidTodoFormatException {
        super(detail);
        if (detail.isBlank()) {
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
    /**
     * Convert the task into todo txt format
     * @return String of the todo txt format
     */
    @Override
    public String convertToTxtFormat() {
        return "T # " + super.convertToTxtFormat();
    }
}
