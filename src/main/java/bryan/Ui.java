package bryan;

import bryan.task.Task;

import java.util.ArrayList;

public class Ui {
    public static String[] COMMAND_ARRAY = {"todo", "list", "deadline", "mark", "unmark", "event", "bye", "delete", "find", "after", "before"};

    public void printLine() {
        System.out.println("----------------------------------------------------");
    }

    public void printBryanLogo() {
        System.out.println("BBBBB  RRRR   Y   Y   A   N   N");
        System.out.println("B    B R   R   Y Y   A A  NN  N");
        System.out.println("BBBBB  RRRR     Y   A   A N N N");
        System.out.println("B    B R  R     Y   AAAAA N  NN");
        System.out.println("BBBBB  R   R    Y   A   A N   N");
    }

    public void greeting() {
        printBryanLogo();
        printLine();
        System.out.println("how can I help you?");
    }

    /**
     * Print all the commands available in this software.
     */
    public void printCommandArray() {
        System.out.println("these are the valid commands:");
        for (String s : COMMAND_ARRAY) {
            System.out.println("-" + s);
        }
    }

    /**
     * Print all tasks inside the task array param.
     *
     * @param taskArray The arraylist that contains tasks to be printed
     */
    public void printTask(ArrayList<Task> taskArray) {
        for (int i = 0; i < taskArray.size(); i++) {
            System.out.println(i + 1 + "." + taskArray.get(i));
        }
    }

    public void sayBye() {
        System.out.println("Goodbye, see you again!");
    }

    /**
     * Print ui message every time the size of the array list changed.
     * Indicates whether a task is added or deleted.
     * Indicates the number of tasks inside the array
     *
     * @param operation Indicates whether a task is added or deleted
     * @param taskSize  Number of tasks inside the arraylist
     */
    public void printListSize(String operation, int taskSize) {
        System.out.println("task " + operation);
        System.out.println("now you have " + taskSize + " tasks in the list");
    }

    /**
     * Print ui message for marked and unmarked task and print the task as well.
     *
     * @param task The task that is marked/unmarked
     */
    public void printMarkedTask(Task task) {
        String markMessage = (task.isDone()) ? "Nice! I've marked this task as done:" : " OK, I've marked this task as not done yet:";
        System.out.println(markMessage);
        System.out.println(task);
    }

    public void printDateSearchTasks(ArrayList<Task> taskArray, String type) {
        if (taskArray.isEmpty()) {
            System.out.println("no deadline is " + type + " that date");
        }
        printTask(taskArray);
    }

    public void printInvalidBeforeAfterFormatException() {
        System.out.println("invalid before/after format");
        System.out.println("there should be a date following the word 'after' or 'before'");
        System.out.println("eg. before {dd-mm-yyyy}");
    }

    public void printInvalidDateFormat() {
        System.out.println("invalid date format");
        System.out.println("it should be dd-mm-yyyy");
    }

    public void printSearchedTasks(ArrayList<Task> taskArray) {
        if (taskArray.isEmpty()) {
            System.out.println("no task contains that description");
        }
        printTask(taskArray);
    }


    /**
     * Print ui message error if command is invalid.
     */
    public void printInvalidCommandException() {
        System.out.println("invalid command");
        this.printCommandArray();
    }

    /**
     * Print ui message error if the event description is empty due to wrong format or missing /from and /to details.
     */
    public void printEmptyEventDescription() {
        System.out.println("description or from is empty or to is empty, please follow the correct event format");
        System.out.println("eg. event {description} /from {from date} /to {to date}");
    }

    /**
     * Print ui error message when list feature is called but task array is empty
     */
    public void printEmptyTaskException() {
        System.out.println("list is still empty, please add new tasks");
    }

    public void printIndexOutOfBoundsException() {
        System.out.println("there is no task in that index, make sure you have the correct number");
    }

    /**
     * Print ui message error due to marking/unmarking an marked/unmarked task, respectively
     */
    public void printMarkingException() {
        System.out.println("you can not marked an marked task, vice versa");
    }

    /**
     * Print ui message error due to invalid deadline format
     */
    public void printInvalidDeadlineFormatException() {
        System.out.println("wrong deadline format, it should contain description and followed with one '/by' and by date");
        System.out.println("by date is formated dd-mm-yyyy");
        System.out.println("eg. deadline {description} /by {by date}");
    }

    /**
     * Print ui message error due to invalid event format
     */
    public void printInvalidEventFormatException() {
        System.out.println("wrong event format, it should contain '/from' followed with the from date then '/to' followed with the to date");
        System.out.println("eg. event {description} /from {from date} /to {to date}");
    }

    /**
     * Print ui message error due to invalid mark format
     */
    public void printInvalidMarkFormatException() {
        System.out.println("wrong mark format, a number after unmark");
        System.out.println("eg. mark {number}");
    }

    /**
     * Print ui message error due to invalid unmark format
     */
    public void printInvalidUnmarkFormatException() {
        System.out.println("wrong unmark format, a number after unmark");
        System.out.println("eg. unmark {number}");
    }

    /**
     * Print ui message error due to invalid todo format
     */
    public void printInvalidTodoFormatException() {
        System.out.println("wrong todo format, it should contain description");
        System.out.println("eg. todo {description}");
    }

    /**
     * Print ui message error due to invalid delete format
     */
    public void printInvalidDeleteFormatException() {
        System.out.println("wrong delete format, it should be followed by index");
        System.out.println("eg. delete {index}");
    }

    /**
     * Print ui message error due to invalid mark/unmark format
     */
    public void printNumberFormatException() {
        System.out.println("please enter an integer after the commandText 'mark' or 'unmark' ");
        System.out.println("eg. mark 1");
    }

    /**
     * Print ui message error due to invalid deadline format caused by empty description or /by date
     */
    public void printEmptyDeadlineDescription() {
        System.out.println("description or by is empty, please follow the correct deadline format");
        System.out.println("eg. deadline {description} /by {by date}");
    }

    public void printFindFormatException() {
        System.out.println("Wrong find format, add details after find");
        System.out.println("eg. find {details}");
    }


}
