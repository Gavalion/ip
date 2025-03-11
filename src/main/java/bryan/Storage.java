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
//    public final String filePath = "data1/bryan2.txt"; //change to data/bryan.txt for real test
    protected String filePath;

    public Storage(String fileName){
        this.filePath = fileName;
    }
    public ArrayList<Task> readFile() throws FileNotFoundException, InvalidTextFormatException {
        ArrayList<Task> taskArray = new ArrayList<>();
        File f = new File(this.filePath);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            try {
                taskArray.add(textToTask(s.nextLine()));
            } catch (MarkingException | EmptyEventDescription | InvalidTextFormatException |
                     InvalidTodoFormatException | InvalidEventFormatException | InvalidDeadlineFormatException |
                     EmptyDeadlineDescription e) {
                throw new InvalidTextFormatException();
            }
        }

        return taskArray;
    }

    public Task textToTask(String input) throws MarkingException, EmptyEventDescription, InvalidDeadlineFormatException, InvalidEventFormatException, InvalidTodoFormatException, EmptyDeadlineDescription, InvalidTextFormatException {
        String[] parsedInput = input.split(" # ");
        Task t;
        String taskType = parsedInput[0];
        t = switch (taskType) {
            case "T" -> new Todo(parsedInput[2], false);
            case "D" -> new Deadline(parsedInput[2] + " /by " + parsedInput[3], false);
            case "E" -> new Event(parsedInput[2] + " /from " + parsedInput[3].split("-")[0] + " /to " + parsedInput[3].split("-")[1], false);
            default -> throw new InvalidTextFormatException();
        };
        boolean isDone = (parsedInput[1].equals("1"));
        if (isDone != t.isDone()){
            t.setDone(isDone);
        }
        return t;
    }

    //    overwrite all tasks to txt
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

//    append one task only to txt
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

