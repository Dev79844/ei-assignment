package astronautDailyScheduleOrganizer.scheduleObserver;

import astronautDailyScheduleOrganizer.tasks.Task;

public interface ScheduleObserver {
    void onConflict(Task existingTask);
}
