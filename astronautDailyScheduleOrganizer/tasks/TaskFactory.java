package astronautDailyScheduleOrganizer.tasks;
import java.time.LocalTime;

import astronautDailyScheduleOrganizer.Priority;

public class TaskFactory {
    public static Task createTask(String description, LocalTime startTime, LocalTime endTime, Priority priority){
        return new Task(description, startTime, endTime, priority);
    }
}
