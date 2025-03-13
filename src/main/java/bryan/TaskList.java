package bryan;

import bryan.exception.*;
import bryan.task.Deadline;
import bryan.task.Event;
import bryan.task.Task;
import bryan.task.Todo;
import java.util.stream.Collectors;
//import bryan.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


public class TaskList {
    /**
     * Command from user input
     */
    protected String commandText;

    /**
     * Text that comes after the command if there is any
     */
    protected String detail;
    /**
     * Array list of tasks
     */
    protected ArrayList<Task> taskArray;
    /**
     * Storage class to manipulate txt file
     */
    protected Storage storage;

    /**
     * Constructor for task list
     *
     * @param commandText command from the user
     * @param detail text that comes after command
     * @param taskArray Task arraylist
     * @param storage Storage class to manipulate the txt file
     */
    public TaskList(String commandText, String detail, ArrayList<Task> taskArray, Storage storage) {
        this.detail = detail;
        this.commandText = commandText;
        this.taskArray = taskArray;
        this.storage = storage;
    }

    /**
     * Process the command and details from the constructor.
     * "bye" will shutdown the process
     * "list" will print out all task inside the array list
     * "Todo" will create a new todo task
     * "Event" will create a new Event task
     * "Deadline" will create a new deadline task
     * "mark" will mark a task as done
     * "unmark" will mark a task as undone
     * "delete" will delete a task based on index number
     * "find" will print out tasks that contains inputted string inside the description
     * "before" will print out tasks that is before the inputted date
     * "after" will print out the tasks that is after the inputted date
     *
     * @throws DateTimeParseException exception due to parsing error in deadline
     * @throws EmptyTaskException exception due to empty arraylist
     * @throws MarkingException exception due to marking a marked task, vice versa
     * @throws EmptyEventDescription exception due to empty event description
     * @throws InvalidDeadlineFormatException  exception due to invalid deadline format
     * @throws InvalidEventFormatException exception due to invalid event format
     * @throws InvalidTodoFormatException  exception due to invalid todo format
     * @throws EmptyDeadlineDescription exception due to empty deadline description
     */
    public void process() throws DateTimeParseException, EmptyTaskException, MarkingException, EmptyEventDescription, InvalidDeadlineFormatException, InvalidEventFormatException, InvalidTodoFormatException, EmptyDeadlineDescription {
        Ui ui = new Ui();
        // indicates when a new task is added from todo, deadline and event
        boolean isNewTaskAdded = false;
        Task task;
        ArrayList<Task> searchedTasks;
        switch (this.commandText) {
            case "bye":
                ui.sayBye();
                // system exit 130 to break out of the loop and indicate that the process has finished
                System.exit(130);
                break;
            case "list":
                // send error message if the array is empty
                this.checkEmptyList(taskArray);
                ui.printTask(taskArray);
                break;
            case "mark":
            case "unmark":
                int numberMark = Integer.parseInt(this.detail);
                boolean isMark = this.commandText.equals("mark");
                // set the task to be mark or unmark, the -1 is added since array indexing starts 0 while ui list indexing starts from 1
                taskArray.get(numberMark - 1).setDone(isMark);
                ui.printMarkedTask(taskArray.get(numberMark - 1));
                this.storage.taskToText(taskArray);
                break;
            case "todo":
                task = new Todo(this.detail);
                taskArray.add(task);
                isNewTaskAdded = true;
                break;
            case "deadline":
                task = new Deadline(this.detail);
                taskArray.add(task);
                isNewTaskAdded = true;
                break;
            case "event":
                task = new Event(this.detail);
                taskArray.add(task);
                isNewTaskAdded = true;
                break;
            case "delete":
                taskArray.remove(Integer.parseInt(this.detail) - 1);
                this.storage.taskToText(taskArray);
                ui.printListSize("deleted", taskArray.size());
                break;
            case "find":
                // find all tasks that contains this.detail inside the task description attribute
                searchedTasks = taskArray.stream()
                        .filter(searchedTask -> searchedTask.getDescription().contains(this.detail))
                        .collect(Collectors.toCollection(ArrayList::new));
                ui.printSearchedTasks(searchedTasks);
                break;
            case "before":
                try {
                    LocalDate dateBefore = LocalDate.parse(this.detail, Deadline.DATE_TIME_FORMATER);
                    // find all deadlines with by attribute before this.detail date
                    searchedTasks = taskArray.stream()
                            .filter(searchedTask -> searchedTask instanceof Deadline)
                            .filter(searchedTask -> ((Deadline) searchedTask).getBy().isBefore(dateBefore))
                            .collect(Collectors.toCollection(ArrayList::new));
                    ui.printDateSearchTasks(searchedTasks, "before");
                } catch (DateTimeParseException e) {
                    System.out.println(2);
                    throw e;
                }
                break;
            case "after":
                // find all deadlines with by attribute after this.detail date
                try {
                    LocalDate dateAfter = LocalDate.parse(this.detail, Deadline.DATE_TIME_FORMATER);
                    searchedTasks = taskArray.stream()
                            .filter(searchedTask -> searchedTask instanceof Deadline)
                            .filter(searchedTask -> ((Deadline) searchedTask).getBy().isAfter(dateAfter))
                            .collect(Collectors.toCollection(ArrayList::new));
                    ui.printDateSearchTasks(searchedTasks, "after");
                } catch (DateTimeParseException e) {
                    System.out.println(3);
                    throw e;
                }
                break;
        }
        // print the task that is added recently from todo, event and deadline; print also the current size of the list
        if (isNewTaskAdded) {
            storage.taskToText(taskArray.get(taskArray.size() - 1));
            ui.printListSize("added", taskArray.size());
        }

    }

    /**
     * Check whether the current array list is empty.
     * Empty list will throw EmptyTaskException
     * @param taskArray ArrayList of tasks
     * @throws EmptyTaskException exception due to empty arraylist Exception to indicate the ArrayList is empty
     */
    public void checkEmptyList(ArrayList<Task> taskArray) throws EmptyTaskException {
        if (taskArray.isEmpty()) {
            throw new EmptyTaskException();
        }
    }
}
