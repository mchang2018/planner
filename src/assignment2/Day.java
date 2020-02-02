package assignment2;

import java.util.ArrayList;

public class Day {
	// attributes
	private int dayOfMonth;
	private String month;
	private boolean firstDay;
	private boolean holiday;
	private String holidayName;
	private ArrayList<CalendarEvent> events;
	
	// Day constructor
	public Day(int dayOfMonth, String month) {
		this.dayOfMonth = dayOfMonth;
		this.month = month;
		this.events = new ArrayList<CalendarEvent>();
		
		// check if day is first day of the month
		if (this.dayOfMonth == 1) {
			firstDay = true;
		}
		else {
			firstDay = false;
		}
		
		// check if day is a holiday and if yes, set the name of the holiday
		if (this.month == "January" && this.dayOfMonth == 1) {
			this.holiday = true;
			this.holidayName = "New Year's Day";
		}
		else if (this.month == "January" && this.dayOfMonth == 21) {
			this.holiday = true;
			this.holidayName = "Martin Luther King Jr. Day";
		}
		else if (this.month == "February" && this.dayOfMonth == 18) {
			this.holiday = true;
			this.holidayName = "President's Day";
		}
		else if (this.month == "May" && this.dayOfMonth == 27) {
			this.holiday = true;
			this.holidayName = "Memorial Day";
		}
		else if (this.month == "July" && this.dayOfMonth == 4) {
			this.holiday = true;
			this.holidayName = "Independence Day";
		}
		else if (this.month == "September" && this.dayOfMonth == 2) {
			this.holiday = true;
			this.holidayName = "Labor Day";
		}
		else if (this.month == "October" && this.dayOfMonth == 14) {
			this.holiday = true;
			this.holidayName = "Columbus Day";
		}
		else if (this.month == "November" && this.dayOfMonth == 11) {
			this.holiday = true;
			this.holidayName = "Veterans Day";
		}
		else if (this.month == "November" && this.dayOfMonth == 28) {
			this.holiday = true;
			this.holidayName = "Thanksgiving";
		}
		else if (this.month == "December" && this.dayOfMonth == 25) {
			this.holiday = true;
			this.holidayName = "Christmas";
		}
		else {
			this.holiday = false;
			this.holidayName = "";
		}
	}
	
	// Getter for day of month
	public int getDayOfMonth() {
		return this.dayOfMonth;
	}
	
	// Getter for month name
	public String getMonth() {
		return this.month;
	}
	
	// This method checks if it's the first day of month
	public boolean isFirstDay() {
		return firstDay;
	}
	
	// This method returns true if the day is a holiday, and false if not
	public boolean isHoliday() {
		return this.holiday;
	}
	
	// This method returns the name of holiday
	public String getHolidayName() {
		return this.holidayName;
	}
	
	// This method adds a calendar event to events arraylist
	public void addCalendarEvent(CalendarEvent newEvent) {
		events.add(newEvent);
	}
	
	// This method deletes a calendar event from events arraylist
	public void deleteCalendarEvent(String eventName, String eventDate) {
		CalendarEvent toDelete = new CalendarEvent(eventName, eventDate);
		for (int i = 0; i < events.size(); i++) {
			if (events.get(i).equalCalendarEvent(toDelete)) {
				events.remove(i);
				break;
			}
		}
	}
	
	// This method returns an array list of event strings
	public ArrayList<String> eventStrings() {
		ArrayList<String> evntStrings = new ArrayList<>();
		for (int i = 0; i < events.size(); i++) {
			evntStrings.add(events.get(i).eventHistoryString());
		}
		return evntStrings;
	}
	
	// This method returns an array list of event names
	public ArrayList<String> evntNames() {
		ArrayList<String> eventNames = new ArrayList<>();
		for (int i = 0; i < events.size(); i++) {
			eventNames.add(events.get(i).getEventName());
		}
		return eventNames;
	}
	
	// for debugging
	public void printEvents() {
		for (int i = 0; i < events.size(); i++) {
			System.out.println(events.get(i).getEventName());
		}
	}
}