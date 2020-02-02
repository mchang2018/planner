package assignment2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CalendarEvent {
	private String eventName;
	private String eventDate;
	
	// constructor for CalendarEvent
	public CalendarEvent(String name, String date) {
		eventName = name;
		eventDate = date;
	}
	
	// This method checks if entered date was valid (only acceptable format is MM/DD/YYYY)
	public boolean checkDate() {
		int monthNum;
		int[] daysInEachMonth = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		// check if date format is correct
		SimpleDateFormat df = new SimpleDateFormat("mm/dd/yyyy");
		try {
			df.parse(this.eventDate);
		}
		catch(ParseException e) {
			return false;
		}
		// check if actual date is valid
		String tokens[] = this.eventDate.split("/");
		if (Integer.parseInt(tokens[0]) < 1 || Integer.parseInt(tokens[0]) > 12) {
			return false;
		}
		else {
			monthNum = Integer.parseInt(tokens[0]);
		}
		if (Integer.parseInt(tokens[1]) < 1 || Integer.parseInt(tokens[1]) > daysInEachMonth[monthNum - 1]) {
			return false;
		}
		if (Integer.parseInt(tokens[2]) != 2019 && Integer.parseInt(tokens[2]) != 19) {
			return false;
		}
		
		return true;
	}
	
	// This method combines the event name and event date into a single string for the event history list
	public String eventHistoryString() {
		return eventName + ", " + eventDate;
	}
	
	// This method returns the event name
	public String getEventName() {
		return this.eventName;
	}
	
	// This method returns the event date
	public String getEventDate() {
		return this.eventDate;
	}
	
	// This method returns the month number
	public int getMonthNum() {
		String[] tokens = this.eventDate.split("/");
		return Integer.parseInt(tokens[0]);
	}
	
	// This method returns day number
	public int getDayNum() {
		String[] tokens = this.eventDate.split("/");
		return Integer.parseInt(tokens[1]);
	}
	
	// Checks if 2 CalendarEvent objects
	public boolean equalCalendarEvent(CalendarEvent calEvent) {
		if (this.eventName.equals(calEvent.getEventName()) && this.eventDate.equals(calEvent.getEventDate())) {
			return true;
		}
		return false;
	}
}
