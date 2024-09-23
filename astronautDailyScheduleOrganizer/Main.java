package astronautDailyScheduleOrganizer;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.logging.Logger;

import astronautDailyScheduleOrganizer.scheduleObserver.ConsoleObserver;
import astronautDailyScheduleOrganizer.tasks.Task;
import astronautDailyScheduleOrganizer.tasks.TaskFactory;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static ScheduleManagerInstance manager = ScheduleManagerInstance.getInstance();
    public static void main(String[] args) {
        // add console as observer
        manager.addObserver(new ConsoleObserver());

        // add a task
        addTask("Morning excercise", LocalTime.parse("07:00"), LocalTime.parse("08:00"), Priority.HIGH);

        addTask("Team Meeting", LocalTime.parse("09:00"), LocalTime.parse("10:00"), Priority.MEDIUM);

        // view tasks
        viewTasks();

        // remove a task
        removeTask("Morning excercise");

        // add a task
        addTask("Lunch Break", LocalTime.parse("12:00"), LocalTime.parse("13:00"), Priority.LOW);
    }

    public static void addTask(String description, LocalTime startTime, LocalTime endTime, Priority priority){
        try{

            if(description == "" || startTime == null || endTime == null || priority == null){
                System.out.println("Error: fields are missing");
                logger.warning("Error: fields are missing");
                return;
            }

            Task task = TaskFactory.createTask(description, startTime, endTime, priority);
            manager.addTask(task);
        }catch(DateTimeParseException e){
            System.out.println("Error: Invalid time format.");
        }catch(IllegalArgumentException e){
            System.out.println("Error: Invalid priority type");
        }
    }

    public static void viewTasks(){
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

    public static void removeTask(String description){
        try{
            List<Task> tasks= manager.getTasks();
            Task taskToRemove = null;

            for(int i=0; i<tasks.size(); i++){
                if(tasks.get(i).getDescription().equalsIgnoreCase(description)){
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