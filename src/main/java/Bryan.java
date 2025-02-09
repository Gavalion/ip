import java.util.Scanner;
public class Bryan {
    public static void main(String[] args) {
        printBryanLogo();
        printLine();
        Scanner userInput = new Scanner(System.in);  // Create a Scanner object
        System.out.println("how can I help you?");
        String userString;
        Task [] taskArray = new Task[100];
        int counter = 0;
        int numberMark;
        Task task;
        boolean isTaskAdded;
        while (true) {
            userString = userInput.nextLine();
            if (userString.equals("bye")){
                break;
            }
            if (userString.equals("list")) {
                printTask(taskArray);
                continue;
            }
            if (userString.startsWith("mark") || userString.startsWith("unmark")) {
                numberMark = Integer.parseInt(userString.substring( userString.indexOf(" ")+1));
                boolean isMark = userString.startsWith("mark");
                taskArray[numberMark-1].setDone(isMark);
                String message = (isMark) ? "Nice! I've marked this task as done:" : " OK, I've marked this task as not done yet:";
                System.out.println(message);
                System.out.println(taskArray[numberMark-1]);
                continue;
            }
            String command = userString.split(" ")[0];
            switch (command) {
                case "todo":
                    task = new Todo (userString.split(" ", 2)[1]);
                    taskArray[counter] = task;
                    isTaskAdded = true;
                    break;
                case "deadline":
                    task = new Deadline(userString.split(" ", 3)[1], userString.split(" ", 3)[2]);
                    taskArray[counter] = task;
                    isTaskAdded = true;
                    break;
                case "event":
                    task = new Event(userString.split(" ", 3)[1], userString.split(" ", 3)[2]);
                    taskArray[counter] = task;
                    isTaskAdded = true;
                    break;
                default:
                    System.out.println("wrong input please try again");
                    isTaskAdded = false;
                    break;
            }
            if (isTaskAdded){
                counter ++;
                System.out.println("task added");
                System.out.println("anything else?");
            }

        }
        sayBye();
    }
    public static void printTask(Task[] taskArray){
//        String checkbox;
        for (int i = 0; i<taskArray.length ;i++){
            if (taskArray[i] == null){
                break;
            }
            System.out.println(i+1 + "." + taskArray[i]);
        }
    }

    public static void sayBye(){
        System.out.println("Goodbye, see you again!");
    }
    public static void echo(String userString){
        System.out.println(userString);
    }
    public static void printLine(){
        System.out.println("----------------------------------------------------");
    }
    public static void printBryanLogo() {
        System.out.println("BBBBB  RRRR   Y   Y   A   N   N");
        System.out.println("B    B R   R   Y Y   A A  NN  N");
        System.out.println("BBBBB  RRRR     Y   A   A N N N");
        System.out.println("B    B R  R     Y   AAAAA N  NN");
        System.out.println("BBBBB  R   R    Y   A   A N   N");
    }
}

