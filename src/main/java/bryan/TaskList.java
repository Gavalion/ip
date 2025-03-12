package bryan;

import bryan.exception.*;
import bryan.task.Deadline;
import bryan.task.Event;
import bryan.task.Task;
import bryan.task.Todo;
import java.util.stream.Collectors;
//import bryan.*;

import java.util.ArrayList;


public class TaskList {
    protected String commandText;
    protected String detail;
    protected ArrayList<Task> taskArray;
    protected Storage storage;

    public TaskList(String commandText, String detail, ArrayList<Task> taskArray, Storage storage) {
        this.detail = detail;
        this.commandText = commandText;
        this.taskArray = taskArray;
        this.storage = storage;
    }

    public void process() throws EmptyTaskException, MarkingException, EmptyEventDescription, InvalidDeadlineFormatException, InvalidEventFormatException, InvalidTodoFormatException, EmptyDeadlineDescription {
        Ui ui = new Ui();
        boolean isNewTaskAdded = false;
        Task task;
        switch (this.commandText) {
            case "bye":
                System.exit(130);
                ui.sayBye();
                break;
            case "list":
                this.checkEmptyList(taskArray);
                ui.printTask(taskArray);
                break;
            case "mark":
            case "unmark":
                int numberMark = Integer.parseInt(this.detail);
                boolean isMark = this.commandText.equals("mark");
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
            case "find":
                ArrayList<Task> searchedTasks = taskArray.stream()
                        .filter(searchedTask -> searchedTask.getDescription().contains(this.detail))
                        .collect(Collectors.toCollection(ArrayList::new));
                ui.printSearchedTasks(searchedTasks);
        }

        if (isNewTaskAdded) {
            storage.taskToText(taskArray.get(taskArray.size() - 1));
            ui.printListSize("added", taskArray.size());
        }

    }
    public void checkEmptyList(ArrayList<Task> taskArray) throws EmptyTaskException {
        if (taskArray.isEmpty()) {
            throw new EmptyTaskException();
        }
    }
}
