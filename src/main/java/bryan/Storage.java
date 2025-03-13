package bryan;

import bryan.exception.*;
import bryan.task.Deadline;
import bryan.task.Event;
import bryan.task.Task;
import bryan.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected String filePath;

    public Storage(String fileName){
        this.filePath = fileName;
    }

    /**
     * Read txt file and convert it into Arraylist of tasks
     *
     * @return Arraylist of tasks
     * @throws FileNotFoundException Exception die to missing file or directory
     * @throws InvalidTextFormatException exception due to invalid task text format in the txt file Exception due to format error inside the txt file
     */
    public ArrayList<Task> readFile() throws FileNotFoundException, InvalidTextFormatException {
        ArrayList<Task> taskArray = new ArrayList<>();
        File f = new File(this.filePath);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            try {
                // convert each line in txt to task
                taskArray.add(textToTask(s.nextLine()));
            } catch (MarkingException | EmptyEventDescription | InvalidTextFormatException |
                     InvalidTodoFormatException | InvalidEventFormatException | InvalidDeadlineFormatException |
                     EmptyDeadlineDescription e) {
                throw new InvalidTextFormatException();
            }
        }

        return taskArray;
    }

    /**
     * Convert string into task
     * @param input String input receieved from a line in the txt file
     * @return task read from the input
     * @throws MarkingException exception due to marking an marked task, vice versa
     * @throws EmptyEventDescription exception due to empty event description
     * @throws InvalidDeadlineFormatException exception due to invalid deadline format
     * @throws InvalidEventFormatException exception due to invalid event format
     * @throws InvalidTodoFormatException exception due to invalid todo format
     * @throws EmptyDeadlineDescription exception due to empty deadline description
     * @throws InvalidTextFormatException exception due to invalid task text format in the txt file
     */
    public Task textToTask(String input) throws MarkingException, EmptyEventDescription, InvalidDeadlineFormatException, InvalidEventFormatException, InvalidTodoFormatException, EmptyDeadlineDescription, InvalidTextFormatException {
        String[] parsedInput = input.split(" # ");
        Task t;
        String taskType = parsedInput[0];
        t = switch (taskType) {
            // identify the type of task
            case "T" -> new Todo(parsedInput[2], false);
            case "D" -> new Deadline(parsedInput[2] + " /by " + parsedInput[3], false);
            case "E" -> new Event(parsedInput[2] + " /from " + parsedInput[3].split("-")[0] + " /to " + parsedInput[3].split("-")[1], false);
            default -> throw new InvalidTextFormatException();
        };
        // check whether the  task is marked or unmarked
        boolean isDone = (parsedInput[1].equals("1"));
        if (isDone != t.isDone()){
            t.setDone(isDone);
        }
        return t;
    }

    /**
     * Convert tasks to String
     * Overwrite the txt file
     * This method is used whenever the user delete or mark/unmark a task
     *
     * @param taskArray An arraylist of tasks
     */
    public void taskToText(ArrayList<Task> taskArray){
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (Task t : taskArray) {
                fw.write(t.convertToTxtFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("error in file io");
            System.exit(1);
        }
    }


    /**
     * Convert a task into String
     * Append a line of task into the txt file
     * This method is used whenever the user add a task.
     * Adding task commands are todo, event and deadline
     *
     * @param task A single task that just been added by user
     */
    public void taskToText(Task task){
        try {
            FileWriter fw = new FileWriter(this.filePath,true);
            fw.write(task.convertToTxtFormat() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println("error in file io");
            System.exit(1);
        }
    }

    /**
     * Read the txt file and create a new file if it does not exist
     * If there is no error, then return arraylist of task.
     * If the file is missing then return empty array list
     * @return Arraylist of tasks if the file exist
     */
    public ArrayList<Task> run() {
        ArrayList<Task> taskArray = new ArrayList<>();

        try {
            taskArray = readFile();
        } catch (FileNotFoundException e) {
            createNewFolder();
        } catch (InvalidTextFormatException e) {
            System.out.println("Error in text format");
            System.exit(1);
        }
        return taskArray;
    }


    /**
     * Create new folder and file if it does not exist
     */
    public void createNewFolder(){
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
            File f = new File(filePath);
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

    }
}

