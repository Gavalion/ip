package bryan;

import bryan.exception.*;
import bryan.task.Deadline;
import bryan.task.Event;
import bryan.task.Task;
import bryan.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;

public class Bryan {
    public static String[] commandArray = {"todo", "list", "deadline", "mark", "unmark", "event", "bye", "delete"};
//    public ArrayList<Task> taskArray = new ArrayList<>();

    public static void main(String[] args) {
        printBryanLogo();
        printLine();
        Scanner userInput = new Scanner(System.in);  // Create a Scanner object
        System.out.println("how can I help you?");
        String userString;
        ArrayList<Task> taskArray = new ArrayList<>();
//        int counter = 0;
        int numberMark;
        Task task;
        boolean isNumberOfTaskChanged;
        while (true) {
            userString = userInput.nextLine();
            //echo(userString); //uncomment for txt ui-testing 
            isNumberOfTaskChanged = false;
            try {
                commandChecker(userString);
                if (userString.startsWith("bye")) {
                    break;
                }
                if (userString.startsWith("list")) {
                    checkEmptyList(taskArray);
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
                        taskArray.get(numberMark - 1).setDone(isMark);
                        String message = (isMark) ? "Nice! I've marked this task as done:" : " OK, I've marked this task as not done yet:";
                        System.out.println(message);
                        System.out.println(taskArray.get(numberMark - 1));
                        break;
                    case "todo":
//                        String description = parsedCommand[1];
                        task = new Todo(userString.split(" ", 2)[1]);
                        taskArray.add(task);
                        isNumberOfTaskChanged = true;
                        break;
                    case "deadline":
                        task = new Deadline(userString.split(" ", 2)[1]);
                        taskArray.add(task);
                        isNumberOfTaskChanged = true;
                        break;
                    case "event":
                        task = new Event(userString.split(" ", 2)[1]);
                        taskArray.add(task);
                        isNumberOfTaskChanged = true;
                        break;
                    case "delete":
                        taskArray.remove(Integer.parseInt(parsedCommand[1]) -1);
                        isNumberOfTaskChanged = true;
                }

                if (isNumberOfTaskChanged) {
//                    counter++;
                    System.out.println("now you have "+ taskArray.size()+ " tasks in the list");
                }
            } catch (InvalidCommandException e) {
                System.out.println("invalid command");
                printCommandArray();
            } catch (EmptyTaskException e) {
                System.out.println("list is still empty, please add new tasks");
            } catch (IndexOutOfBoundsException e) {
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
            } catch (InvalidDeleteFormatException e) {
                    System.out.println("wrong delete format, it should be followed by index");
                    System.out.println("eg. delete {index}");
            } catch (NumberFormatException e) {
                System.out.println("please enter an integer after the command 'mark' or 'unmark' ");
                System.out.println("eg. mark 1");
            } catch (EmptyDeadlineDescription e){
                System.out.println("description or by is empty, please follow the correct deadline format");
                System.out.println("eg. deadline {description} /by {by date}");
            } catch (EmptyEventDescription e){
                System.out.println("description or from is empty or to is empty, please follow the correct event format");
                System.out.println("eg. event {description} /from {from date} /to {to date}");
            }
//            catch (ArrayIndexOutOfBoundsException e){
//                System.out.println("number is out of index, maximum list length is 100");
//            }

        }
        sayBye();
    }

    public static void printCommandArray() {
        System.out.println("these are the valid commands:");
        for (String s : commandArray) {
            System.out.println("-" + s);
        }
    }

    public static void printTask(ArrayList<Task> taskArray) {
//        String checkbox;
        for (int i = 0; i < taskArray.size(); i++) {
//            if (taskArray.get == null) {
//                break;
//            }
            System.out.println(i + 1 + "." + taskArray.get(i));
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

    public static void commandChecker(String userString) throws InvalidCommandException, MarkingException, InvalidDeadlineFormatException, InvalidEventFormatException {
        boolean isValid = false;
//        int numberMark;
//        boolean isMark;
        for (String s : commandArray) {
            if (userString.startsWith(s) && !s.equals("bye") && !s.equals("list")) {
                isValid = true;
            } else if (userString.startsWith("event ") && !(userString.contains("/from") && userString.contains("/to") && userString.indexOf("/from") < userString.indexOf("/to"))) {//&& userString.indexOf("/from") > userString.indexOf("/to")) {
                throw new InvalidEventFormatException();
            } else if (userString.equals(s)) {
                isValid = true;
            }
        }
        if (!isValid) {
            throw new InvalidCommandException();
        }


    }

    public static void checkEmptyList(ArrayList<Task> taskArray) throws EmptyTaskException {
        if (taskArray.isEmpty()) {
            throw new EmptyTaskException();
        }
    }

    public static void checkDescription(String[] commandArray, String command) throws InvalidDeleteFormatException, InvalidDeadlineFormatException, InvalidEventFormatException, InvalidMarkFormatException, InvalidUnmarkFormatException, InvalidTodoFormatException {
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
                case "delete":
                    throw new InvalidDeleteFormatException();
            }
        }
    }
}

