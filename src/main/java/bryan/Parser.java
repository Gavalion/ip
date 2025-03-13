package bryan;

import bryan.exception.*;

public class Parser {
    /**
     * All available commands
     */
    public static final String[] COMMAND_ARRAY = {"todo", "list", "deadline", "mark", "unmark", "event", "bye", "delete", "find", "before", "after"};

    /**
     * Command from user input
     */
    protected String commandText;

    /**
     * Text that comes after the command if there is any
     */
    protected String detail;

    /**
     * Check whether the command is valid and available.
     * It checks for formatting for event.
     *
     * @param userString user input from terminal
     * @throws InvalidCommandException due to command does not exist
     * @throws InvalidEventFormatException due to missing /from or /to and /from is stated after /to
     */
    public static void commandChecker(String userString) throws InvalidCommandException, InvalidEventFormatException {
        boolean isValid = false;
        for (String s : COMMAND_ARRAY) {
            if (userString.startsWith(s + " ") && !s.equals("bye") && !s.equals("list")) {
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
     * Constructor for the parser class.
     * Parse the user input and fill command and detail attribute
     * Check whether command is valid
     * Check whether command format is correct
     *
     * @param userString User input string
     * @throws InvalidFindFormatException due to empty detail
     * @throws InvalidBeforeFormatException due to empty detail
     * @throws InvalidAfterFormatException due to empty detail
     * @throws InvalidCommandException due to command does not exist
     * @throws InvalidDeleteFormatException due to empty detail
     * @throws InvalidDeadlineFormatException due to empty detail
     * @throws InvalidEventFormatException due to invalid event format
     * @throws InvalidMarkFormatException empty number to mark
     * @throws InvalidUnmarkFormatException empty number to mark
     * @throws InvalidTodoFormatException due to empty detail
     */
    public Parser(String userString) throws
            InvalidFindFormatException, InvalidBeforeFormatException, InvalidAfterFormatException, InvalidCommandException, InvalidDeleteFormatException, InvalidDeadlineFormatException, InvalidEventFormatException, InvalidMarkFormatException, InvalidUnmarkFormatException, InvalidTodoFormatException {
            commandChecker(userString);
            String[] parsedCommand = userString.split(" ", 2);
            this.commandText = parsedCommand[0];
            if (parsedCommand.length > 1) {
                this.detail = parsedCommand[1];
            }
            this.checkDescription();

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
     * @throws InvalidAfterFormatException due to empty detail
     * @throws InvalidBeforeFormatException due to empty detail
     * @throws InvalidFindFormatException due to empty detail
     * @throws InvalidDeleteFormatException due to empty detail
     * @throws InvalidDeadlineFormatException due to empty detail
     * @throws InvalidEventFormatException due to empty detail
     * @throws InvalidMarkFormatException empty number to mark
     * @throws InvalidUnmarkFormatException empty number to mark
     * @throws InvalidTodoFormatException due to empty detail
     */
    public void checkDescription() throws InvalidAfterFormatException, InvalidBeforeFormatException, InvalidFindFormatException,
            InvalidDeleteFormatException, InvalidDeadlineFormatException, InvalidEventFormatException, InvalidMarkFormatException,
            InvalidUnmarkFormatException, InvalidTodoFormatException {
        {
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
}
