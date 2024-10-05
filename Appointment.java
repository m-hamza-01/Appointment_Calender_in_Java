// Appointment.java

import java.time.LocalDateTime;

public class Appointment {

    // fields to store the appointment's details
    private String description; // description of appointment 
    private LocalDateTime startTime; // start time of appointment 
    private LocalDateTime endTime; // end time of appointment


    // constructor to initialize appointment object 
    public Appointment(String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getter method for description
    public String getDescription() {
        return description;
    }

    // Getter method for start time
    public LocalDateTime getStartTime() {
        return startTime;
    }

    // Getter method for end time 
    public LocalDateTime getEndTime() {
        return endTime;
    }

    // toDtring method to return appointment's details
    @Override
    public String toString() {
        return description + " " + startTime.toString() + " to " + endTime.toString();
    }
}
