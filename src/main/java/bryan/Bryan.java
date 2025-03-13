package bryan;

import bryan.exception.*;
import bryan.task.*;

import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;



public class Bryan {
    public static final String FILE_PATH = "data/bryan.txt"; //change to data/bryan.txt for real test


    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.greeting();
        Scanner userInput = new Scanner(System.in);  // Create a Scanner object
        String userString;
        Storage storage = new Storage(FILE_PATH);
        ArrayList<Task> taskArray = storage.run();
        Task task;
        boolean isNewTaskAdded;
        while (true) {
            userString = userInput.nextLine();
            //echo(userString); //uncomment for txt ui-testing
            isNewTaskAdded = false;
            try {

                Parser parsedCommand = new Parser(userString);
                String commandText = parsedCommand.getCommandText();
                String detail = parsedCommand.getDetail();
                TaskList tasklist = new TaskList(commandText, detail, taskArray, storage);
                tasklist.process();

            } catch (InvalidCommandException e) {
                ui.printInvalidCommandException();
            } catch (EmptyTaskException e) {
                ui.printEmptyTaskException();
            } catch (IndexOutOfBoundsException e) {
                ui.printIndexOutOfBoundsException();
            } catch (MarkingException e) {
                ui.printMarkingException();
            } catch (InvalidDeadlineFormatException e) {
                ui.printInvalidDeadlineFormatException();
            } catch (InvalidEventFormatException e) {
                ui.printInvalidEventFormatException();
            } catch (InvalidMarkFormatException e) {
                ui.printInvalidMarkFormatException();
            } catch (InvalidUnmarkFormatException e) {
                ui.printInvalidUnmarkFormatException();
            } catch (InvalidTodoFormatException e) {
                ui.printInvalidTodoFormatException();
            } catch (InvalidDeleteFormatException e) {
                ui.printInvalidDeleteFormatException();
            } catch (NumberFormatException e) {
                ui.printNumberFormatException();
            } catch (EmptyDeadlineDescription e) {
                ui.printEmptyDeadlineDescription();
            } catch (EmptyEventDescription e) {
                ui.printEmptyEventDescription();
            } catch (InvalidFindFormatException e) {
                ui.printFindFormatException();
            }catch (InvalidAfterFormatException | InvalidBeforeFormatException e){
                ui.printInvalidBeforeAfterFormatException();
            }catch (DateTimeParseException e){
                ui.printInvalidDateFormat();
            }
//            catch (ArrayIndexOutOfBoundsException e){
//                System.out.println("number is out of index, maximum list length is 100");
//            }

        }
//        ui.sayBye();
    }

    public static void echo(String userString) {
        System.out.println(userString);
    }

}

