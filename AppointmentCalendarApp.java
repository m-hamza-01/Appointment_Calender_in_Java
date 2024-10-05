//AppointmentCalenderApp.java
// the file to run

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AppointmentCalendarApp {

    // Formatter for input (used for adding appointments)
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    // Formatter for output date and an time in a readable format 
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");


    public static void main(String[] args) {

        //instance of appointment of appointment calendar 
        AppointmentCalendar calendar = new AppointmentCalendar();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {

                // Display the menu options to the user 
                System.out.println("\n====================================");
                System.out.println("    Appointment Calendar Menu");
                System.out.println("====================================");
                System.out.println("1. Add Appointment");
                System.out.println("2. Remove Appointment");
                System.out.println("3. View Appointments");
                System.out.println("4. Exit");
                System.out.println("====================================");
                System.out.print("Choose an option (1-4): ");

                // user choice input to ensure the correct inout 
                int choice = Integer.parseInt(scanner.nextLine()); // Ensure correct input


                //swtich case for each case
                switch (choice) {
                    case 1:
                        // Add appointment
                        System.out.println("\n--- Add Appointment ---");
                        try {
                            System.out.print("Enter description: ");
                            String description = scanner.nextLine();

                            // get and validate the start time input 
                            LocalDateTime startTime = null;
                            while (startTime == null) {
                                try {
                                    System.out.print("Enter start time (yyyy/MM/dd HH:mm): ");
                                    String start = scanner.nextLine();
                                    startTime = LocalDateTime.parse(start, formatter);
                                } catch (DateTimeParseException e) {
                                    // dealing with invalid date/time format 
                                    System.out.println("\nInvalid date/time format. Please use 'yyyy/MM/dd HH:mm'.\n");
                                }
                            }

                            // get and validate the end time input
                            LocalDateTime endTime = null;
                            while (endTime == null) {
                                try {
                                    System.out.print("Enter end time (yyyy/MM/dd HH:mm): ");
                                    String end = scanner.nextLine(); // get date input from user 
                                    endTime = LocalDateTime.parse(end, formatter);

                                    // a checker to test if the end time is not before the start time
                                    if (endTime.isBefore(startTime)) {
                                        System.out.println("\nEnd time cannot be before start time. Please enter again.\n");
                                        endTime = null;  // Reset to null to re-enter
                                    }
                                } catch (DateTimeParseException e) {
                                    // dealing with invalid date/time format
                                    System.out.println("\nInvalid date/time format. Please use 'yyyy/MM/dd HH:mm'.\n");
                                }
                            }

                            // appointment object and add it to the calender
                            Appointment appointment = new Appointment(description, startTime, endTime);
                            calendar.addAppointment(appointment); // add appointment to calender 
                            System.out.println("\nAppointment added successfully!\n");
                        } catch (Exception e) {

                            // dealing with any inexpected errors 
                            System.out.println("\nAn unexpected error occurred: " + e.getMessage() + "\n");
                        }
                        break;

                    case 2:
                        // Remove appointment
                        System.out.println("\n--- Remove Appointment ---");
                        try {
                            //get and validate date
                            System.out.print("Enter date (yyyy/MM/dd): ");
                            String dateInput = scanner.nextLine();
                            LocalDate date = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy/MM/dd"));

                            // all appointments for that specific date 
                            System.out.println("\nAppointments for " + dateInput + ":");
                            var appointments = calendar.getAppointmentsForDay(date);

                            // dealing with the case when appointments are found for the selected date
                            if (appointments.isEmpty()) {
                                System.out.println("\nNo appointments found for this date.\n");
                            } else {
                                // Display the list of appointments with indices
                                for (int i = 0; i < appointments.size(); i++) {
                                    Appointment appt = appointments.get(i);
                                    String formattedStartTime = appt.getStartTime().format(outputFormatter);
                                    String formattedEndTime = appt.getEndTime().format(outputFormatter);
                                    System.out.println((i + 1) + ". " + appt.getDescription() + " from " + formattedStartTime + " to " + formattedEndTime);
                                }

                                // given a way for the user for removal by entering 0 
                                System.out.println("\nEnter 0 if you don't want to remove any appointment.");
                                System.out.print("Enter the number of the appointment to remove: ");
                                int index = Integer.parseInt(scanner.nextLine()) - 1;  // Subtract 1 to match the list index

                                if (index == -1) 
                                {   // not remove any appointment 
                                    System.out.println("\nNo appointment removed.\n");
                                } else if (index >= 0 && index < appointments.size()) {
                                    // remove selected appointment
                                    Appointment toRemove = appointments.get(index);
                                    calendar.removeAppointment(toRemove);
                                    System.out.println("\nAppointment removed successfully!\n");
                                } else {
                                    // invalid index selected 
                                    System.out.println("\nInvalid selection.\n");
                                }
                            }
                        } catch (DateTimeParseException e) {
                            // dealing with invalid date format 
                            System.out.println("\nInvalid date format. Please use 'yyyy/MM/dd'.\n");
                        } catch (NumberFormatException e) {
                            // handle invalid number input for selecting appointment
                            System.out.println("\nInvalid input. Please enter a valid number.\n");
                        }
                        break;

                    case 3:
                        // View appointments
                        System.out.println("\n--- View Appointments ---");
                        try {
                            // get and validate date
                            System.out.print("Enter date (yyyy/MM/dd): ");
                            String dateInput = scanner.nextLine();
                            LocalDate date = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy/MM/dd"));

                            //retieve and display appointments for selected date 
                            var appointments = calendar.getAppointmentsForDay(date);

                            if (appointments.isEmpty()) {
                                // case for no appointments found 
                                System.out.println("\nNo appointments for this date.\n");
                            } else {
                                // display appointments for selected date
                                System.out.println("\nAppointments for " + dateInput + ":");
                                appointments.forEach(appointment -> {
                                    String formattedStartTime = appointment.getStartTime().format(outputFormatter);
                                    String formattedEndTime = appointment.getEndTime().format(outputFormatter);
                                    System.out.println(appointment.getDescription() + " from " + formattedStartTime + " to " + formattedEndTime);
                                });
                            }
                        } catch (DateTimeParseException e) {
                            // dealing with invalid date input format 
                            System.out.println("\nInvalid date format. Please use 'yyyy/MM/dd'.\n");
                        }
                        break;

                    case 4:
                        // Exit application
                        System.out.println("\nExiting...\n");
                        System.exit(0);
                        break;

                    default:
                        // dealing with invlaid menu option
                        System.out.println("\nInvalid choice. Please enter a number between 1 and 4.\n");
                }
            } catch (NumberFormatException e) {
                //dealing with invlaid input for menu option 
                System.out.println("\nInvalid input. Please enter a number.\n");
            } catch (Exception e) {
                // catch any unexpected errors 
                System.out.println("\nAn unexpected error occurred: " + e.getMessage() + "\n");
            }
        }
    }
}
