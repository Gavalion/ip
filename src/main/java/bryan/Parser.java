package bryan;

import bryan.exception.*;

public class Parser {
    public static final String[] COMMAND_ARRAY = {"todo", "list", "deadline", "mark", "unmark", "event", "bye", "delete","find", "before", "after"};
    protected String commandText;
    protected String detail;
//    protected String by;
//    protected String from;


    public static void commandChecker(String userString) throws
            InvalidCommandException, InvalidEventFormatException {
        boolean isValid = false;
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

    /**
     * Parser constructor to get the command name and detail.
     * Detail is the string after the command, it will be further processed by other methods based on its command.
     * @param userString The user input from the gui
     * @throws InvalidCommandException Exception due to error in command format
     * @throws InvalidDeleteFormatException Exception due to error in delete format
     * @throws InvalidDeadlineFormatException Exception due to error in deadline format
     * @throws InvalidEventFormatException Exception due to error in event format
     * @throws InvalidMarkFormatException Exception due to error in mark format
     * @throws InvalidUnmarkFormatException Exception due to error in unmark format
     * @throws InvalidTodoFormatException Exception due to error in todo format
     */
    public Parser(String userString) throws

        
            InvalidFindFormatException,InvalidBeforeFormatException, InvalidAfterFormatException,InvalidCommandException, InvalidDeleteFormatException, InvalidDeadlineFormatException, InvalidEventFormatException, InvalidMarkFormatException, InvalidUnmarkFormatException, InvalidTodoFormatException {

        try {
            commandChecker(userString);
            String[] parsedCommand = userString.split(" ", 2);
            this.commandText = parsedCommand[0];
            if (parsedCommand.length > 1) {
                this.detail = parsedCommand[1];
            }
            this.checkDescription();


        } catch (InvalidFindFormatException | InvalidCommandException | InvalidDeleteFormatException |
                 InvalidDeadlineFormatException | InvalidEventFormatException | InvalidMarkFormatException |
                 InvalidUnmarkFormatException |
        } catch (InvalidFindFormatException |InvalidCommandException | InvalidDeleteFormatException | InvalidDeadlineFormatException |
                 InvalidEventFormatException | InvalidMarkFormatException | InvalidUnmarkFormatException |

                 InvalidTodoFormatException e) {
            throw e;
        }

    }

    public String getCommandText() {
        return commandText;
    }

    public String getDetail() {
        return detail;
    }


    /**
     * Check whether the command have any error in formatting.
     * Throw error if there is any exception triggered.
     * Command checked are commands that requires details to be functional.
     *
     * @throws InvalidDeleteFormatException
     * @throws InvalidDeadlineFormatException
     * @throws InvalidEventFormatException
     * @throws InvalidMarkFormatException
     * @throws InvalidUnmarkFormatException
     * @throws InvalidTodoFormatException
     */
    public void checkDescription() throws

            InvalidFindFormatException, InvalidDeleteFormatException, InvalidDeadlineFormatException, InvalidEventFormatException, InvalidMarkFormatException, InvalidUnmarkFormatException, InvalidTodoFormatException {

            InvalidFindFormatException,InvalidBeforeFormatException, InvalidAfterFormatException, InvalidDeleteFormatException, InvalidDeadlineFormatException, InvalidEventFormatException, InvalidMarkFormatException, InvalidUnmarkFormatException, InvalidTodoFormatException {

        if (this.detail == null) {
            switch (this.getCommandText()) {
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

                case "find":
                    throw new InvalidFindFormatException();
                case "before":
                    throw new InvalidBeforeFormatException();
                case "after":
                    throw new InvalidAfterFormatException();

            }
        }
    }
}
