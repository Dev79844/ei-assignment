package astronautDailyScheduleOrganizer.tasks;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import astronautDailyScheduleOrganizer.Priority;

public class Task {
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private Priority priotityLevel;

    public Task(String description, LocalTime startTime, LocalTime endTime, Priority priority) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priotityLevel = priority;
    }

    public String getDescription(){
        return description;
    }

    public LocalTime getStartTime(){
        return startTime;
    }

    public LocalTime getEndTime(){
        return endTime;
    }

    public Priority getPriority(){
        return priotityLevel;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setStartTime(LocalTime startTime){
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime){
        this.endTime = endTime;
    }

    public void setPriority(Priority priority){
        this.priotityLevel = priority;
    }

    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
        return String.format("%s - %s: %s [%s]", startTime.format(formatter), endTime.format(formatter), description, priotityLevel);
    }
}
