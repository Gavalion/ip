package bryan;

import bryan.exception.*;
import bryan.task.Deadline;
import bryan.task.Event;
import bryan.task.Task;
import bryan.task.Todo;
import com.sun.source.tree.ReturnTree;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Bryan {
    public static final String[] COMMAND_ARRAY = {"todo", "list", "deadline", "mark", "unmark", "event", "bye", "delete"};
    public static final String FILE_PATH = "data1/bryan1.txt"; //change to data/bryan.txt for real test


    public static Task stringToTask(String input) throws MarkingException, EmptyEventDescription, InvalidDeadlineFormatException, InvalidEventFormatException, InvalidTodoFormatException, EmptyDeadlineDescription, InvalidTextFormatException {
        String[] parsedInput = input.split(" # ");
        Task t;
        if (parsedInput[0].equals("T")) {
            t = new Todo(parsedInput[2], false);
        } else if (parsedInput[0].equals("D")) {
            t = new Deadline(parsedInput[2] + " /by " + parsedInput[3], false);
        } else if (parsedInput[0].equals("E")) {
            t = new Event(parsedInput[2] + " /from " + parsedInput[3].split("-")[0] + " /to " + parsedInput[3].split("-")[1], false);
        } else {
            throw new InvalidTextFormatException();
        }
        t.setDone((parsedInput[1].equals("1") ? true : false));
        return t;
    }

    public static ArrayList<Task> readFile() throws FileNotFoundException, InvalidTextFormatException {
        ArrayList<Task> taskArray = new ArrayList<>();
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            try {
                taskArray.add(stringToTask(s.nextLine()));
            } catch (MarkingException | EmptyEventDescription | InvalidTextFormatException |
                     InvalidTodoFormatException |
                     InvalidEventFormatException | InvalidDeadlineFormatException | EmptyDeadlineDescription e) {
                throw new InvalidTextFormatException();
            }
        }

        return taskArray;
    }


    public static void main(String[] args) {

//        System.out.println("full path: " + f.getAbsolutePath());
        printBryanLogo();
        printLine();
        Scanner userInput = new Scanner(System.in);  // Create a Scanner object
        String userString;
        ArrayList<Task> taskArray = new ArrayList<>();
        try {
            taskArray = readFile();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.out.println("please check the txt file, it should be bryan.txt under the \"data\" folder");
            File directory = new File("data1");
            boolean directoryCreated = directory.mkdir();
            if (directoryCreated) {
                System.out.println("Directory created");
            } else {
                System.out.println("Failed to create directory. It may already exist");
            }
            try {
                File f = new File(FILE_PATH);
                if (f.createNewFile()) {
                    System.out.println("File created: " + f.getName());
                    System.out.println("path: " + f.getPath());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException ex) {
                System.out.println("IO exception error");
                System.exit(1);
            }
        } catch (InvalidTextFormatException e) {
            System.out.println("Error in text format");
            System.exit(1);

        }

//        int counter = 0;
        System.out.println("how can I help you?");
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
                        try {
                            FileWriter fw = new FileWriter(FILE_PATH);
                            for (Task t : taskArray) {
                                fw.write(t.convertToTxtFormat() + System.lineSeparator());
                            }
                            fw.close();
                        } catch (IOException e) {
                            System.out.println("error in file io");
                            System.exit(1);
                        }
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
                        taskArray.remove(Integer.parseInt(parsedCommand[1]) - 1);
                        try {
                            FileWriter fw = new FileWriter(FILE_PATH);
                            for (Task t : taskArray) {
                                fw.write(t.convertToTxtFormat() + System.lineSeparator());
                            }
                            fw.close();
                        } catch (IOException e) {
                            System.out.println("error in file io");
                            System.exit(1);
                        }
                        System.out.println("task deleted");
                        System.out.println("now you have " + taskArray.size() + " tasks in the list");
                }

                if (isNumberOfTaskChanged) {
//                    counter++;
                    try {
                        FileWriter fw = new FileWriter(FILE_PATH, true);
                        fw.write(taskArray.get(taskArray.size() - 1).convertToTxtFormat() + System.lineSeparator());
                        fw.close();
                    } catch (IOException e) {
                        System.out.println("error in file io");
                        System.exit(1);
                    }
                    System.out.println("now you have " + taskArray.size() + " tasks in the list");
                }


            } catch (InvalidCommandException e) {
                System.out.println("invalid command");
                printCOMMAND_ARRAY();
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
            } catch (EmptyDeadlineDescription e) {
                System.out.println("description or by is empty, please follow the correct deadline format");
                System.out.println("eg. deadline {description} /by {by date}");
            } catch (EmptyEventDescription e) {
                System.out.println("description or from is empty or to is empty, please follow the correct event format");
                System.out.println("eg. event {description} /from {from date} /to {to date}");
            }
//            catch (ArrayIndexOutOfBoundsException e){
//                System.out.println("number is out of index, maximum list length is 100");
//            }

        }
        sayBye();
    }

//    public static void overWriteFile()

    public static void printCOMMAND_ARRAY() {
        System.out.println("these are the valid commands:");
        for (String s : COMMAND_ARRAY) {
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

    public static void commandChecker(String userString) throws
            InvalidCommandException, MarkingException, InvalidDeadlineFormatException, InvalidEventFormatException {
        boolean isValid = false;
//        int numberMark;
//        boolean isMark;
        for (String s : COMMAND_ARRAY) {
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

    public static void checkDescription(String[] COMMAND_ARRAY, String command) throws
            InvalidDeleteFormatException, InvalidDeadlineFormatException, InvalidEventFormatException, InvalidMarkFormatException, InvalidUnmarkFormatException, InvalidTodoFormatException {
        if (COMMAND_ARRAY.length != 2) {
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

