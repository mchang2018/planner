package assignment2;

public class Month {
	// attributes
	private int numOfDays;
	private int monthNum;
	private String monthName;
	private Day[] days;
	
	// Month constructor
	public Month(int monthNum, int numOfDays, String monthName) {
		this.numOfDays = numOfDays;
		this.monthNum = monthNum;
		this.monthName = monthName;
		days = new Day[numOfDays];
		
		// create a Day object for each day of the month
		for (int i = 0; i < numOfDays; i++) {
			days[i] = new Day(i + 1, this.monthName);
		}
	}
	
	// getter for numOfDays
	public int getNumOfDays() {
		return this.numOfDays;
	}
	
	// getter for month name
	public String getMonthName() {
		return this.monthName;
	}
	
	/* This method calculates what day of the week the 1st of the month falls on (0 - Sunday, 1 - Monday, 2 - Tuesday, etc...)
	   Algorithm: https://www.reddit.com/r/LearnUselessTalents/comments/23l668/request_how_to_determine_that_day_of_the_week_a
	*/
	public int getFirstDayOfMonth() {
		int arr[] = {6, 2, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};
		return (19 / 4 + 19 + (arr[this.monthNum - 1]) + 1) % 7;
	}
	
	// get a specific day from the array of days
	public Day getDay(int whichDay) {
		return days[whichDay];
	}
}