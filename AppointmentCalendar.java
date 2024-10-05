// AppointmentCalendar.java

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentCalendar {

    // List for storing Appointment objects 
    private List<Appointment> appointments;

    // constructor for appointment
    public AppointmentCalendar() {
        this.appointments = new ArrayList<>();
    }

    // method to add an appointment to list
    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }

    // method to remove an appointment from list 
    public void removeAppointment(Appointment appointment) {
        this.appointments.remove(appointment);
    }

    // method to get appointments for the day
    public List<Appointment> getAppointmentsForDay(LocalDate date) {
        return appointments.stream()
                .filter(appt -> appt.getStartTime().toLocalDate().equals(date)) // filters appointments by matching start date
                .collect(Collectors.toList()); // collects filtered appointments into a new list 
    }

    // print appointments for that day 
    public void printAppointmentsForDay(LocalDate date) {
        List<Appointment> dailyAppointments = getAppointmentsForDay(date);

        // exception for the case when there are no assignments 
        if (dailyAppointments.isEmpty()) {
            System.out.println("No appointments for " + date);
        } else {
            // print each appointment in the list 
            dailyAppointments.forEach(System.out::println);
        }
    }
}
