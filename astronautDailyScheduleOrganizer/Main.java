package astronautDailyScheduleOrganizer;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import astronautDailyScheduleOrganizer.scheduleObserver.ConsoleObserver;
import astronautDailyScheduleOrganizer.tasks.Task;
import astronautDailyScheduleOrganizer.tasks.TaskFactory;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        ScheduleManagerInstance manager = ScheduleManagerInstance.getInstance();
        manager.addObserver(new ConsoleObserver());
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("\n1. Add task");
            System.out.println("2. View tasks");
            System.out.println("3. Remove a task");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            try{

                int choice = Integer.parseInt(sc.nextLine());
    
                switch (choice) {
                    case 1:
                        addTask(sc, manager);
                        break;
                    case 2:
                        listTasks(manager);
                        break;
                    case 3:
                        removeTask(sc, manager);
                        break;
                    case 4:
                        System.out.println("Exit...");
                        return;
                    default:
                        break;
                }
            }catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                logger.warning(e.toString());
            }
        }
    }

    public static void addTask(Scanner sc, ScheduleManagerInstance manager){
        try{

            System.out.print("Enter Task description: ");
            String description = sc.nextLine();
    
            System.out.print("Enter start time (hh:mm): ");
            LocalTime startTime = LocalTime.parse(sc.nextLine());
    
            System.out.print("Enter end time (hh:mm): ");
            LocalTime endTime = LocalTime.parse(sc.nextLine());
    
            System.out.print("Enter priority level (LOW/MEDIUM/HIGH): ");
            Priority priority = Priority.valueOf(sc.nextLine().toUpperCase());

            Task task = TaskFactory.createTask(description, startTime, endTime, priority);
            manager.addTask(task);
        }catch(DateTimeParseException e){
            System.out.println("Error: Invalid time format.");
        }catch(IllegalArgumentException e){
            System.out.println("Error: Invalid priority type");
        }
    }

    public static void listTasks(ScheduleManagerInstance manager){
        List<Task> tasks = manager.getTasks();

        if(tasks.isEmpty()){
            System.out.println("No tasks scheduled for the day.");
            return;
        }

        char bullet = 'a';
        for(int i=0; i<tasks.size(); i++){
            System.out.println((bullet) + ". " + tasks.get(i));
            bullet++;
        }
    }

    public static void removeTask(Scanner sc, ScheduleManagerInstance manager){
        try{
            System.out.print("Enter task name: ");
            String taskName = sc.nextLine();

            List<Task> tasks= manager.getTasks();
            Task taskToRemove = null;

            for(int i=0; i<tasks.size(); i++){
                if(tasks.get(i).getDescription().equalsIgnoreCase(taskName)){
                    taskToRemove = tasks.get(i);
                }
            }

            if(taskToRemove != null){
                manager.deleteTask(taskToRemove);
                System.out.println("Task removed successfully");
                logger.info("Task removed successfully");
            }else{
                System.out.println("Error: Task not found.");
                logger.warning("Error: Task not found.");
            }

        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
}