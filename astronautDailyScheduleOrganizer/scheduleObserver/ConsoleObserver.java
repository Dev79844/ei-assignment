package astronautDailyScheduleOrganizer.scheduleObserver;

import astronautDailyScheduleOrganizer.tasks.Task;

public class ConsoleObserver implements ScheduleObserver {
    @Override
    public void onConflict(Task existingTask){
        System.out.println("Error: Task conflicts with existing task: " + existingTask.getDescription());
    }
}
