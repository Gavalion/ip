package bryan;

import bryan.task.Task;

import java.util.ArrayList;

public class Ui {
    public static String[] COMMAND_ARRAY = {"todo", "list", "deadline", "mark", "unmark", "event", "bye", "delete"};
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
    public void greeting(){
        printBryanLogo();
        printLine();
        System.out.println("how can I help you?");
    }
    public void printCommandArray() {
        System.out.println("these are the valid commands:");
        for (String s : COMMAND_ARRAY) {
            System.out.println("-" + s);
        }
    }
    public void printTask(ArrayList<Task> taskArray) {
        for (int i = 0; i < taskArray.size(); i++) {
            System.out.println(i + 1 + "." + taskArray.get(i));
        }
    }
    public void sayBye() {
        System.out.println("Goodbye, see you again!");
    }

    public void printListSize(String operation, int taskSize){
        System.out.println("task " + operation);
        System.out.println("now you have " + taskSize+ " tasks in the list");
    }

    public void printMarkedTask(Task task) {
        String markMessage = (task.isDone()) ? "Nice! I've marked this task as done:" : " OK, I've marked this task as not done yet:";
        System.out.println(markMessage);
        System.out.println(task);
    }

    public void printInvalidCommandException(){
        System.out.println("invalid commandText");
        this.printCommandArray();
    }
    public void printEmptyEventDescription (){
        System.out.println("description or from is empty or to is empty, please follow the correct event format");
        System.out.println("eg. event {description} /from {from date} /to {to date}");
    }
    public void printEmptyTaskException (){
        System.out.println("list is still empty, please add new tasks");
    }
    public void printIndexOutOfBoundsException (){
        System.out.println("there is no task in that index, make sure you have the correct number");
    }
    public void printMarkingException (){
        System.out.println("you can not marked an marked task, vice versa");
    }
    public void printInvalidDeadlineFormatException (){
        System.out.println("wrong deadline format, it should contain description and followed with one '/by' and by date");
        System.out.println("eg. deadline {description} /by {by date}");
    }
    public void printInvalidEventFormatException (){
        System.out.println("wrong event format, it should contain '/from' followed with the from date then '/to' followed with the to date");
        System.out.println("eg. event {description} /from {from date} /to {to date}");
    }
    public void printInvalidMarkFormatException (){
        System.out.println("wrong mark format, a number after unmark");
        System.out.println("eg. mark {number}");
    }
    public void printInvalidUnmarkFormatException (){
        System.out.println("wrong unmark format, a number after unmark");
        System.out.println("eg. unmark {number}");
    }
    public void printInvalidTodoFormatException (){
        System.out.println("wrong todo format, it should contain description");
        System.out.println("eg. todo {description}");
    }
    public void printInvalidDeleteFormatException (){
        System.out.println("wrong delete format, it should be followed by index");
        System.out.println("eg. delete {index}");
    }
    public void printNumberFormatException (){
        System.out.println("please enter an integer after the commandText 'mark' or 'unmark' ");
        System.out.println("eg. mark 1");
    }
    public void printEmptyDeadlineDescription () {
        System.out.println("description or by is empty, please follow the correct deadline format");
        System.out.println("eg. deadline {description} /by {by date}");
    }

}
