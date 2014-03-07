/*
 * Patrick Stephens
 * 3/4/14
 * Project 1: Day Planner / CMPSCI 182L - Ferguson
 * Description: Appointment class that allows you to create appointment objects that contain a date,
 * time and message.
 *
 * Added Class Methods:
 *
 * isValidMonth() - Checks to see if the three character string enter is one of the valid month abreviations.
 *                  Returns true if it is and false if it is not.
 * numDays() - returns the number of days in the month passed to it.
 */

package project1;

import userinput.UserInput;

public class Appointment {

    
    public static void main(String[] args) {
        
        Appointment myApp = new Appointment();
        myApp.inputAppointment();
        System.out.print(myApp.toString());
    }
    
    private String month;
    private int day;
    private int hour;
    private int minute;
    private String message;
    private int attempts = 0;
    
    public Appointment() {
        
    }
    
    public Appointment(String newMonth, int newDay, int newHour, int newMinute, String newMessage) {
        setMonth(newMonth);
        setDay(newDay);
        setHour(newHour);
        setMinute(newMinute);
        setMessage(newMessage); 
    }
    
    final public boolean setMonth(String newMonth) {
        if (isValidMonth(newMonth)) {
            month = newMonth.toLowerCase();
            return true;
        }
        else {
            return false;
        }
    }
    
    public String getMonth() {
        return month;
    }
    
    final public boolean setDay(int newDay) {
        if (newDay > 0 && newDay <= numDays()) {
            day = newDay;
            return true;
        }
        else {
            return false;
        }
    }
    
    public int getDay() {
        return day;
    }
    
    final public boolean setHour(int newHour) {
        if (newHour > -1 && newHour < 24) {
            hour = newHour;
            return true;
        }
        else {
            return false;
        }
    }
    
    public int getHour() {
        return hour;
    }
    
    final public boolean setMinute(int newMinute) {
        if (newMinute > -1 && newMinute < 60) {
            minute = newMinute;
            return true;
        }
        else {
            return false;
        }   
    }
    
    public int getMinute() {
        return minute;
    }
    
    final public boolean setMessage(String newMessage) {
        if (newMessage.length() > 0 && newMessage.length() < 41) {
            message = newMessage;
            return true;
        }
        else {
            return false;
        }  
    }
    
    public String getMessage() {
        return message;
    }
    
    
    @Override
    public String toString() {
        String data = "";
        data += month.toUpperCase() + " " + day + " - " + String.format("%02d",  hour) + ":" + String.format("%02d",  minute) + " "; 
        data += message;
        return data;
    }
    
    public void inputAppointment() {
    
        // Get month
        do {
            if (attempts < 2) {
                System.out.print("Please enter the three letter abbreviation for the month of your appointment: ");
                attempts++;
            }
            else{
                System.out.print("Please enter one of the following three letter abbreviation for the month of your appointment: \n");
                System.out.print("JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC: ");
            }
        } while (!setMonth(UserInput.getString(3, 3)));
        attempts = 0;
        
        do {
            System.out.print("Please the day of the month your appointment is on: ");
        } while(!setDay(UserInput.getInt(1, numDays())));
        
        System.out.print("Please the hour of your appointment: ");
        setHour(UserInput.getInt(0, 23));
        
     
        System.out.print("Please the minute of your appointment: ");
        setMinute(UserInput.getInt(0, 59));
       
        System.out.print("Please enter a note for this appointment (40 character maximum): ");
        setMessage(UserInput.getString(1, 40));
    }
    
    private boolean isValidMonth(String testValue) {
        
        String [] validMonths = { "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
        
        for (int i = 0; i < validMonths.length; i++) {
            if (testValue.equalsIgnoreCase(validMonths[i])) {
                return true;
            }
        }
        return false;
    }
    
    private int numDays() {
        
        switch (month) {
            case "jan":
                return 31;
            case "feb":
                return 28;
            case "mar":
                return 31;
            case "apr":
                return 30;
            case "may":
                return 31;
            case "jun":
                return 30;
            case "jul":
                return 31;
            case "aug":
                return 31;
            case "sep":
                return 30;
            case "oct":
                return 31;
            case "nov":
                return 30;
            case "dec":
                return 31;
        }
        
        return 0;
    }
    
    
}
