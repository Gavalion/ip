package bryan;

import bryan.exception.*;
import bryan.task.Deadline;
import bryan.task.Event;
import bryan.task.Task;
import bryan.task.Todo;

import java.util.Scanner;

public class Bryan {
    public static String[] commandArray = {"todo", "list", "deadline", "mark", "unmark", "event", "bye"};
    public Task[] taskArray = new Task[100];

    public static void main(String[] args) {
        printBryanLogo();
        printLine();
        Scanner userInput = new Scanner(System.in);  // Create a Scanner object
        System.out.println("how can I help you?");
        String userString;
        Task[] taskArray = new Task[100];
        int counter = 0;
        int numberMark;
        Task task;
        boolean isTaskAdded;
        while (true) {
            userString = userInput.nextLine();
            isTaskAdded = false;
            try {
                commandChecker(userString, taskArray);
                if (userString.startsWith("bye")) {
                    break;
                }
                if (userString.startsWith("list")) {
                    checkNullArray(taskArray);
                    printTask(taskArray);
                    continue;
                }
                String[] parsedCommand = userString.split(" ", 2);
                String command = parsedCommand[0];
                checkDescription(parsedCommand, command);
                switch (command) {
                    case "mark":
                    case "unmark":
                        numberMark = Integer.parseInt(userString.substring(userString.indexOf(" ") + 1));
                        boolean isMark = userString.startsWith("mark");
                        taskArray[numberMark - 1].setDone(isMark);
                        String message = (isMark) ? "Nice! I've marked this task as done:" : " OK, I've marked this task as not done yet:";
                        System.out.println(message);
                        System.out.println(taskArray[numberMark - 1]);
                        break;
                    case "todo":
                        String description = parsedCommand[1];
                        task = new Todo(userString.split(" ", 2)[1]);
                        taskArray[counter] = task;
                        isTaskAdded = true;
                        break;
                    case "deadline":
                        task = new Deadline(userString.split(" ", 2)[1]);
                        taskArray[counter] = task;
                        isTaskAdded = true;
                        break;
                    case "event":
                        task = new Event(userString.split(" ", 2)[1]);
                        taskArray[counter] = task;
                        isTaskAdded = true;
                        break;
                }

                if (isTaskAdded) {
                    counter++;
                    System.out.println("anything else?");
                }
            } catch (InvalidCommandException e) {
                System.out.println("invalid command");
                printCommandArray();
            } catch (EmptyTaskException e) {
                System.out.println("list is still empty, please add new tasks");
            } catch (NullPointerException e) {
                System.out.println("there is no task in that index, make sure you have the correct number");
            } catch (MarkingException e) {
                System.out.println("you can not marked an marked task, vice versa");
            } catch (InvalidDeadlineFormatException e) {
                System.out.println("wrong deadline format, it should contain description and followed with one '/by' and by date");
                System.out.println("eg. deadline {description} /by {by date}");
            } catch (InvalidEventFormatException e) {
                System.out.println("wrong event format, it should contain '/from' followed with the from date then '/to' followed with the to date");
                System.out.println("eg. event {description} /from {from date} /to {to date}");
            } catch (InvalidMarkFormatException e) {
                System.out.println("wrong mark format, a number after unmark");
                System.out.println("eg. mark {number}");
            } catch (InvalidUnmarkFormatException e) {
                System.out.println("wrong unmark format, a number after unmark");
                System.out.println("eg. unmark {number}");
            } catch (InvalidTodoFormatException e) {
                System.out.println("wrong todo format, it should contain description");
                System.out.println("eg. todo {description}");
            } catch (NumberFormatException e) {
                System.out.println("please enter an integer after the command 'mark' or 'unmark' ");
                System.out.println("eg. mark 1");
            } catch (EmptyDeadlineDescription e){
                System.out.println("description or by is empty, please follow the correct deadline format");
                System.out.println("eg. deadline {description} /by {by date}");
            } catch (EmptyEventDescription e){
                System.out.println("description or from is empty or to is empty, please follow the correct event format");
                System.out.println("eg. event {description} /from {from date} /to {to date}");
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("number is out of index, maximum list length is 100");
            }

        }
        sayBye();
    }

    public static void printCommandArray() {
        System.out.println("these are the valid commands:");
        for (int i = 0; i < commandArray.length; i++) {
            System.out.println("-" + commandArray[i]);
        }
    }

    public static void printTask(Task[] taskArray) {
//        String checkbox;
        for (int i = 0; i < taskArray.length; i++) {
            if (taskArray[i] == null) {
                break;
            }
            System.out.println(i + 1 + "." + taskArray[i]);
        }
    }

    public static void sayBye() {
        System.out.println("Goodbye, see you again!");
    }

    public static void echo(String userString) {
        System.out.println(userString);
    }

    public static void printLine() {
        System.out.println("----------------------------------------------------");
    }

    public static void printBryanLogo() {
        System.out.println("BBBBB  RRRR   Y   Y   A   N   N");
        System.out.println("B    B R   R   Y Y   A A  NN  N");
        System.out.println("BBBBB  RRRR     Y   A   A N N N");
        System.out.println("B    B R  R     Y   AAAAA N  NN");
        System.out.println("BBBBB  R   R    Y   A   A N   N");
    }

    public static void commandChecker(String userString, Task[] taskArray) throws InvalidCommandException, MarkingException, InvalidDeadlineFormatException, InvalidEventFormatException {
        boolean isValid = false;
        int numberMark;
        boolean isMark;
        for (int i = 0; i < commandArray.length; i++) {
            if (userString.startsWith(commandArray[i]) && !commandArray[i].equals("bye") && !commandArray[i].equals("list")) {
                isValid = true;
//            } else if (userString.equals("list") && taskArray[0] == null) {
//                throw new EmptyTaskException();
//            } else if (userString.startsWith("mark ") || userString.equals("unmark ")) {
//                numberMark = Integer.parseInt(userString.substring(userString.indexOf(" ") + 1));
//                isMark = (userString.startsWith("mark ")) ? true : false;
//                if ((isMark && taskArray[numberMark - 1].isDone() || (!isMark && taskArray[numberMark - 1].isDone()))) {
//                    throw new MarkingException();
//                }
            } else if (userString.startsWith("event ") && !(userString.contains("/from") && userString.contains("/to") && userString.indexOf("/from") < userString.indexOf("/to"))) {//&& userString.indexOf("/from") > userString.indexOf("/to")) {
                throw new InvalidEventFormatException();
            } else if (userString.equals(commandArray[i])) {
                isValid = true;
            }
        }
        if (!isValid) {
            throw new InvalidCommandException();
        }


    }

    public static void checkNullArray(Task[] taskArray) throws EmptyTaskException {
        if (taskArray[0] == null) {
            throw new EmptyTaskException();
        }
    }

    public static void checkDescription(String[] commandArray, String command) throws InvalidDeadlineFormatException, InvalidEventFormatException, InvalidMarkFormatException, InvalidUnmarkFormatException, InvalidTodoFormatException {
        if (commandArray.length != 2) {
            switch (command) {
                case "todo":
                    throw new InvalidTodoFormatException();
                case "deadline":
                    throw new InvalidDeadlineFormatException();
                case "mark":
                    throw new InvalidMarkFormatException();
                case "event":
                    throw new InvalidEventFormatException();
                case "unmark":
                    throw new InvalidUnmarkFormatException();
            }
        }
    }
}

