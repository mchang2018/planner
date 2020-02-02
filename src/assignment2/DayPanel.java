package assignment2;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.util.ArrayList;

public class DayPanel extends JPanel {
	private Day day;
	private JLabel date;
	private JPanel dateBox;
	private JLabel events;
	private ArrayList<String> eventNames;
	private Color textColor;
	
	public DayPanel(Day day) {
		this.day = day;
		this.setLayout(new BorderLayout());
		
		// initialize the date JLabel which holds the actual date (day number) 
		date = new JLabel(day.getMonth() + " " + String.valueOf(this.day.getDayOfMonth()), SwingConstants.CENTER);
		date.setForeground(Color.white);
		date.setOpaque(true); date.setBackground(Color.red);
		this.add(date, BorderLayout.NORTH);
		
		// initialize dateBox JPanel, the box underneath the date 
		dateBox = new JPanel();
		dateBox.setLayout(new BorderLayout());
		
		// highlight the first day of each month
		if (this.day.isFirstDay()) {
			dateBox.setBackground(Color.yellow);
		}
		else {
			dateBox.setBackground(Color.white);
		}		
		
		// add events and holidays to dateBox
		eventNames = this.day.evntNames();
		String eventsText = "<html>";
		events = new JLabel("", SwingConstants.CENTER);
				
		if (this.day.isHoliday()) {
			eventsText += ("<br>" + this.day.getHolidayName() + "<br>");
		}
		
		eventsText += "<html>";
		events.setText(eventsText);
		events.setFont(new Font(Font.SERIF, Font.BOLD + Font.ITALIC, 11));
		events.setForeground(Color.red);
		dateBox.add(events, BorderLayout.CENTER);
		
		// set borders and add dateBox to the DayPanel
		dateBox.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.red.darker()));
		this.add(dateBox, BorderLayout.CENTER);
		
		this.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		this.setPreferredSize(new Dimension(95, 80));
	}
	
	public Day getDay() {
		return this.day;
	}
	
	
	public void setEventText() {
		eventNames = this.day.evntNames();
		String eventsText = "<html>";
				
		if (this.day.isHoliday()) {
			eventsText += ("<br>" + this.day.getHolidayName() + "<br>");
		}
		for (int i = 0; i < eventNames.size(); i++) {
			eventsText += ("<br>" + eventNames.get(i) + "<br>");
		}
		
		eventsText += "<html>";
		events.setText(eventsText);
	}
	
	// Setter for text color
	public void setTextColor(String color) {
		switch (color) {
			case "Red":
				textColor = Color.red;
				break;
			case "Blue":
				textColor = Color.blue;
				break;
			case "Green":
				textColor = Color.green;
				break;
			case "Purple":
				textColor = Color.magenta;
				break;
			case "Black":
				textColor = Color.black;
				break;
		}
		events.setForeground(textColor);
	}
	
	// Set size of DayPanel to month view size
	public void setMonthView() {
		this.setPreferredSize(new Dimension(95, 80));
	}
	
	// Set size of DayPanel to week view size
	public void setWeekView() {
		this.setPreferredSize(new Dimension(95, 390));
	}
	
	// Set size of DayPanel to day view size
	public void setDayView() {
		this.setPreferredSize(new Dimension(695, 450));
	}
}
