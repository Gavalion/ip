import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        printBryanLogo();
        printLine();
        Scanner userInput = new Scanner(System.in);  // Create a Scanner object
        System.out.println("how can I help you?");
        String userString;
        Task [] taskArray = new Task[100];
        int counter = 0;
        int numberMark;
        while (true){
            userString = userInput.nextLine();
            if (userString.equals("bye")){
                break;
            }
            if(userString.equals("list")){
                printTask(taskArray);
                continue;
            }
            if(userString.contains("mark")){
                numberMark = Integer.parseInt(userString.substring( userString.indexOf(" ")+1));
                if(userString.contains("unmark")){
                    taskArray[numberMark-1].setDone(false);
                    System.out.println(" OK, I've marked this task as not done yet:");
                }
                else{
                    taskArray[numberMark-1].setDone(true);
                    System.out.println("Nice! I've marked this task as done:");
                }
                System.out.println("["+taskArray[numberMark-1].getStatusIcon()+"] "+ taskArray[numberMark-1].getDescription());
                continue;

            }
            taskArray[counter] = new Task(userString);
            counter ++;
            System.out.println("anything else?");
        }
        sayBye();
    }
    public static void printTask(Task[] taskArray){
        String checkbox;
        for (int i = 0; i<taskArray.length ;i++){
            if (taskArray[i] == null){
                break;
            }
            checkbox = "["+ taskArray[i].getStatusIcon() +"]";
            System.out.println(i+1 + "."+ checkbox+ " "+ taskArray[i].getDescription());
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

