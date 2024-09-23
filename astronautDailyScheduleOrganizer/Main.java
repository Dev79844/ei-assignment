package astronautDailyScheduleOrganizer;
import java.time.LocalTime;
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
    }

    public static void listTasks(ScheduleManagerInstance manager){
        List<Task> tasks = manager.getTasks();

        if(tasks.isEmpty()){
            System.out.println("No tasks found...");
            return;
        }

        for(int i=0; i<tasks.size(); i++){
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public static void removeTask(Scanner sc, ScheduleManagerInstance manager){
        List<Task> tasks = manager.getTasks();

        if(tasks.isEmpty()){
            System.out.println("No tasks found...");
            return;
        }

        
        for(int i=0; i<tasks.size(); i++){
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        
        System.out.print("\nEnter the task to be deleted: ");
        
        int idx = Integer.parseInt(sc.nextLine()) - 1;
        if (idx >= 0 && idx < tasks.size()) {
            manager.deleteTask(tasks.get(idx));
        } else {
            System.out.println("Invalid task number.");
        }

    }
}