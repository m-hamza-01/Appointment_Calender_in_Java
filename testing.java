//testing.java

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class testing {
    // Define a DateTimeFormatter for consistent date/time formatting
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    public static void main(String[] args) {
        // Test Appointment Class
        System.out.println("Testing Appointment class...");
        LocalDateTime startTime = LocalDateTime.of(2022, 12, 1, 9, 0); // Start time: Dec 1, 2022, 9:00 AM
        LocalDateTime endTime = LocalDateTime.of(2022, 12, 1, 10, 0);  // End time: Dec 1, 2022, 10:00 AM
        Appointment appointment = new Appointment("Doctor's appointment", startTime, endTime);

        // Check if values are set correctly
        System.out.println("Description: " + appointment.getDescription()); // output the description 
        System.out.println("Start Time: " + startTime.format(formatter));  // formats and outputs the start time 
        System.out.println("End Time: " + endTime.format(formatter));   // formats and outputs the end time    
        System.out.println("Appointment toString(): " + appointment.getDescription() + 
                           " from " + startTime.format(formatter) + 
                           " to " + endTime.format(formatter)); // print appointment details
        System.out.println();

        // Test AppointmentCalendar Class
        System.out.println("Testing AppointmentCalendar class...");

        // instance of appointmentCalender to store appointments
        AppointmentCalendar calendar = new AppointmentCalendar();

        // Add appointment to the calender 
        calendar.addAppointment(appointment);
        System.out.println("Added appointment: " + appointment.getDescription() + 
                           " from " + startTime.format(formatter) + 
                           " to " + endTime.format(formatter));  

        // Simulate the "View Appointments" functionality
        viewAppointments(calendar, LocalDate.of(2022, 12, 1)); // Testing with Dec 1,2022

        // Remove appointment from the calender 
        calendar.removeAppointment(appointment);
        System.out.println("Removed appointment: " + appointment.getDescription() + 
                           " from " + startTime.format(formatter) + 
                           " to " + endTime.format(formatter)); 

        // View appointments after removal to verify it's no longer in calender 
        viewAppointments(calendar, LocalDate.of(2022, 12, 1));
    }

    // Simulates the "View Appointments" option from AppointmentCalendarApp
    private static void viewAppointments(AppointmentCalendar calendar, LocalDate date) {
        System.out.println("\n--- View Appointments ---");

        // get all appointments for the provided date 
        List<Appointment> appointments = calendar.getAppointmentsForDay(date);

        // checking appointments for a specific date 
        if (appointments.isEmpty()) {
            // if no appointments than print this 
            System.out.println("\nNo appointments for this date.\n");
        } else {
            //otherwise print all appointments for the date 
            System.out.println("\nAppointments for " + date + ":");
            appointments.forEach(appointment -> {
                // format and print each appointments start and end date 
                String formattedStartTime = appointment.getStartTime().format(formatter);
                String formattedEndTime = appointment.getEndTime().format(formatter);
                System.out.println(appointment.getDescription() + " from " + formattedStartTime + " to " + formattedEndTime);
            });
        }
    }
}
