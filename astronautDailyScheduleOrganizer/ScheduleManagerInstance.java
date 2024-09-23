package astronautDailyScheduleOrganizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import astronautDailyScheduleOrganizer.scheduleObserver.ScheduleObserver;
import astronautDailyScheduleOrganizer.tasks.Task;

public class ScheduleManagerInstance {
    private static ScheduleManagerInstance instance;
    private List<Task> tasks;
    private List<ScheduleObserver> observers;

    public static final Logger logger = Logger.getLogger(ScheduleManagerInstance.class.getName());

    private ScheduleManagerInstance(){
        tasks = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static ScheduleManagerInstance getInstance(){
        if(instance == null){
            instance = new ScheduleManagerInstance();
        }

        return instance;
    }

    public void addTask(Task newTask){
        for(int i=0; i<tasks.size(); i++){
            if(isConflict(tasks.get(i), newTask)){
                notifyObservers(tasks.get(i));
                logger.warning("Error: Task conflicts with existing task. " + tasks.get(i).getDescription());
                return;
            }
        }
        tasks.add(newTask);
        System.out.println("Task added successfully. No conflicts.");
        logger.info("Task added successfully. No conflicts.");
    }

    public void deleteTask(Task task){
        tasks.remove(task);
        logger.info("Task removed successfully");
    }

    public List<Task> getTasks(){
        Collections.sort(tasks, new Comparator<Task>() {
            public int compare(Task task1, Task task2){
                if(task1.getStartTime().isBefore(task2.getStartTime())){
                    return -1;
                }else if(task2.getStartTime().isBefore(task1.getStartTime())){
                    return 1;
                }
                else{
                    return 0;
                }
            }
        });

        return tasks;
    }

    private boolean isConflict(Task task1, Task task2){
        return !(task1.getEndTime().isBefore(task2.getStartTime()) || task1.getStartTime().isAfter(task2.getEndTime()));
    }

    public void addObserver(ScheduleObserver observer){
        observers.add(observer);
    }

    public void notifyObservers(Task existingTask){
        for(ScheduleObserver observer : observers){
            observer.onConflict(existingTask);
        }
    }
}
