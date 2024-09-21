import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScheduleManagerInstance {
    private static ScheduleManagerInstance instance;
    private List<Task> tasks;

    private ScheduleManagerInstance(){
        tasks = new ArrayList<>();
    }

    public static ScheduleManagerInstance getInstance(){
        if(instance == null){
            instance = new ScheduleManagerInstance();
        }

        return instance;
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void deleteTask(Task task){
        tasks.remove(task);
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
}
