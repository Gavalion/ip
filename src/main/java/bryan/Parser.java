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
