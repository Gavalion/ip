# Bryan

Bryan is a simple task management application based on Duke, featuring a cli-based interface for managing tasks efficiently.

## **Quick Start**
1. Ensure you have **Java 17 or higher** installed.
2. Go to the main github page
3. Download the latest `ip.jar` file.
4. Run the application using:
   ```sh
   java -jar ip.jar
   ```


---

## Commands

<div markdown="block" class="alert alert-info">

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `deadline {DESCRIPTION} /by {DATE}`, `DESCRIPTION` and `DATE` parameter which can be used as `deadline submit ip /by 14-03-2025`.


* Parameters must be in any order.<br>
  e.g. in event `{DESCRIPTION} /from {FROM DATE} /to {TO DATE}` the `/from` is always stated befor `/to`.

* Extraneous parameters for commands that do not take in parameters (such as `bye` and `list`) will be considered and raised as exception.<br>
  e.g. if the command specifies `bye 11`, it will be considered as an error and valid commands are printed for user.

  ![Extra Parameter](images/extra%20param.png)

* ❗ Indicates possible errors encountered, some errors are not listed in this README because their messages clearly explain the issue.
</div>


Bryan supports the following commands to help you manage your tasks:

---

### **1. Exit the program: `bye`**
Shuts down the application with exit code `130`.

📌 **Format:**  
```
bye
```
📌 **Example Output:**  
![Bye Message](images/bye%20message.png)

---

### **2. Listing all tasks: `list`**
Displays all the tasks currently stored in the application.

📌 **Format:**  
```
list
```
📌 **Example Output:**  
![List Message](images/list%20message.png)

❗ **If no tasks are added, an error will occur.**  
![List Error](images/list%20error.png)  

---

### **3. Create a To-Do Task: `todo`**
Creates a new **To-Do** task with the given description.

📌 **Format:**  
```
todo {DESCRIPTION}
```
📌 **Example:**  
```
todo go to school
```

❗ **If the description is empty, an error will occur.**  
![Todo Error](images/todo%20error.png)

---

### **4. Create a Deadline Task: `deadline`**
Creates a **Deadline** task with a specific due date in (dd-mm-yyyy) format.

📌 **Format:**  
```
deadline {DESCRIPTION} /by {DATE}
```
📌 **Example:**  
```
deadline submit assignment /by 15-05-2025
```

❗ **The deadline format is invalid due to missing `/by`, empty date or empty description.**  
![Deadline Error](images/deadline%20error.png) 

---

### **5. Create an Event Task: `event`**
Creates an **Event** task with a specified start and end time. The date is stored as a string instead of date time.

📌 **Format:**  
```
event {DESCRIPTION} /from {FROM DATE} /to {TO DATE}
```
📌 **Example:**  
```
event japan trip /from 20th may /to 31th may
```

❗ **The event format is invalid due to missing `/from`, `/to`, empty from date, empty to date, `/from` is stated after `/to` or empty description.**  
![Event Error](images/event%20error.png) 

---

### **6. Mark/Unmark a Task: `mark` / `unmark`**
Marks a task as done or not done based on the provided index.

- The index refers to the index number shown in the displayed person list.
- The index must be a positive integer 1, 2, 3, …​

📌 **Format:**  
```
mark {INDEX}
unmark {INDEX}
```
📌 **Example:**  
```
mark 3
```

❗ **There is going to be an exception raised when marking a marked task, vice versa.**  
❗ **The index can not be out of bounds, if so it will raise an exception.**  


---

### **7. Delete a Task: `delete`**
Deletes a task from the list based on the provided index.

- The index refers to the index number shown in the displayed person list.
- The index must be a positive integer 1, 2, 3, …​

📌 **Format:**  
```
delete {INDEX}
```
📌 **Example:**  
```
delete 2
```

❗ **The index can not be out of bounds, if so it will raise an exception.**

---

### **8. Search tasks by Keyword: `find`**
Finds and displays all tasks that contain the specified keyword. The keyword can only be used to search based on tasks description.
 
 - The search is case-sensitive. e.g **meeting** will not match **Meeting**.

📌 **Format:**  
```
find {KEYWORD}
```
📌 **Example:**  

![Find Example](images/find%20example.png) 

❗ **The keyword is needed, otherwise an exception is raised.**

---

### **9. Search deadlines by Date: `before` / `after`**
Finds and lists all **deadline** tasks that are due before or after the given date.

📌 **Format:**  
```
before {DATE}
after {DATE}
```
📌 **Example:**  

![After Example](images/after%20example.png) 

❗ **The date is needed, otherwise an exception is raised.**   
❗ **The date must be in `dd-mm-yyyy` format, otherwise an exception is raised.**

---

## **Saving**

The application will automatically save all tasks when task is being manipulated from commands like `mark`, `unmark`, `delete`, `todo`, `deadline` and `event`.    
the file path is based on the current directory of where the jar file being exceuted, change `FILE_PATH` in bryan.java to change the directory.  

---

## **Usage**
Once the application is running, type any of the commands listed above in the command input field to interact with Bryan.

---

