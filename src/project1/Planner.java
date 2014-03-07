/*
 * Patrick Stephens
 * 3/4/14
 * Project 1: Day Planner / CMPSCI 182L - Ferguson
 * Description: Day planner class that allows you to add, delete, and list appointments, 
 * storing them in a sorted array.
 *
 * Added Class Methods:
 *
 * monthToNum() - Converts string which contains the abbreviation for a month to a int value.
 * printMenu() - Outputs main menu options.
 */

package project1;

import userinput.UserInput;

public class Planner {
    
    private Appointment [] appointments = new Appointment [20];
    private int lastApptIndex = 0; //Keeps track of the index in the array which contains the last appointment.
    private static Planner myPlanner;
    
    public static void main(String[] args) {
        // TODO code application logic here
        myPlanner = new Planner();
        myPlanner.run();
        
    }
   
    //Planner () constructor that places the 4 default Appointment objects in the array
    public Planner() {
        appointments[0] = new Appointment("mar", 4, 17, 30, "Quiz 1");
        appointments[1] = new Appointment("apr", 1, 17, 30, "Midterm");
        appointments[2] = new Appointment("may", 6, 17, 30, "Quiz 2");
        appointments[3] = new Appointment("jun", 3, 17, 30, "Final");
        lastApptIndex = 3;
    }
    
    //run() method that displays the menu, gets input, acts on that input
    public void run() {
        
        char selection;
        
        do {
           
            printMenu();
            
            selection = UserInput.getChar();
            
            switch (Character.toLowerCase(selection)) {
                case 'a':
                    myPlanner.addAppointment();
                    break;
                case 'd':
                    System.out.print("Please enter the number of the appointment that you would like to delete: ");
                    int apptNum = UserInput.getInt(1, lastApptIndex + 1);
                    myPlanner.deleteAppointment(apptNum);
                    break;
                case 'l':
                    System.out.print(myPlanner.listAppointment());
                    break;
                default:
                    System.out.print("\n****You must enter one of the valid values from the menu.**** \n");
                    break;
            }
        } while(selection != 'e');
    }
    
    // method that returns true if A1 < A2, false otherwise
    public boolean compareAppointment(Appointment A1, Appointment A2) {
     
        if (monthToNum(A1.getMonth()) < monthToNum(A2.getMonth())) {
            return true;
        }
        else if (monthToNum(A1.getMonth()) == monthToNum(A2.getMonth())){
            if (A1.getDay() < A2.getDay()) {
                return true;
            }
            else if (A1.getDay() == A2.getDay()) {
                if (A1.getHour() < A2.getHour()) {
                    return true;
                }
                else if (A1.getHour() == A2.getHour()) {
                    if (A1.getMinute() < A2.getMinute()) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                else {
                    return false;
                }
            }
            else {
                    return false;
            }
        }
        else {
            return false;
        }
    }
    
    //method that lists all of the appointments in the array
    public String listAppointment() {
        
        String appts = "\n";
        
        for (int i = 0; i < appointments.length; i++) {
            if (appointments[i] != null) {
                appts += (i + 1) + ". ";
                appts += appointments[i].toString();
                appts += "\n";
            }
            else {
                break;
            }
        }
        return appts;
    }
    
    //delete an object from the array using the number listAppointment () outputs in front of the item
    public void deleteAppointment(int appt) {
        if (appt > 0 && appt - 1 <= lastApptIndex) {
            appointments[appt - 1] = null;
                
            for (int i = appt; i < appointments.length; i++) {
                if (appointments[i] != null) {
                    appointments[i - 1] = appointments[i];
                }
                else {
                    appointments[i - 1] = null;
                    break;
                }
            }
        
            lastApptIndex -= 1;
        }
    }
    
    //calls inputAppointment () from the Appointment class and places it in the proper position of the 
    //array. Use an algorithm that shifts objects in the array (if needed) to make room for the new object. 
    //DO NOT sort the entire array, just shift objects
    public void addAppointment() {
        
        //if the last index used is NOT the last index in the array
        if (lastApptIndex != appointments.length - 1) {
            Appointment newAppointment = new Appointment();
        
            newAppointment.inputAppointment();
            insertAppointment(newAppointment);
        }
    }
    
    private void insertAppointment(Appointment newAppointment) {
        
        int insertionIndex = 0;
            
            //find insertion point
            for (int i = 0; i < appointments.length; i++) {
                if (appointments[i] != null) {
                    if (compareAppointment(newAppointment, appointments[i])) {
                        insertionIndex = i;
                        break;
                    }
                }
                else {
                    insertionIndex = i;
                    break;
                }
            }
        
            //shift appointments to the right.
            for (int i = lastApptIndex + 1; i > insertionIndex; i--) {
                appointments[i] = appointments[i - 1];
            }
        
            //add appointment
            appointments[insertionIndex] = newAppointment;
            lastApptIndex += 1;
    }
    
    //Outputs main menu options
    private void printMenu() {
       String output = "\nA) Add Appointment \n";
       output += "D) Delete Appointment \n";
       output += "L) List Appointments \n";
       output += "E) Exit \n\n";
       output += "Please enter the letter of the action you would like to perform: ";
       
       System.out.print(output);
    }
    
    //Converts string which contains the abbreviation for a month to a int value
    private int monthToNum(String month) {
        
        switch (month) {
            case "jan":
                return 1;
            case "feb":
                return 2;
            case "mar":
                return 3;
            case "apr":
                return 4;
            case "may":
                return 5;
            case "jun":
                return 6;
            case "jul":
                return 7;
            case "aug":
                return 8;
            case "sep":
                return 9;
            case "oct":
                return 10;
            case "nov":
                return 11;
            case "dec":
                return 12;
        }
        return 0;
    }
}