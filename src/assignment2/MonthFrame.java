package assignment2;

import javax.swing.*;
import java.awt.*;

public class MonthFrame extends JPanel{
	private Month month;
	private JPanel monthPanel;
	private DayPanel[] daysArray;
	
	public MonthFrame(Month month) {
		this.month = month;
		daysArray = new DayPanel[this.month.getNumOfDays()];
		this.setLayout(new BorderLayout(0, 25));
		
		// add the name of the month on top of monthPanel using a JLabel
		JLabel monthName = new JLabel(this.month.getMonthName(), SwingConstants.CENTER);
		monthName.setFont(new Font("Serif", Font.BOLD, 24));
		monthName.setForeground(Color.red.darker());
		this.add(monthName, BorderLayout.NORTH);
		
		// initialize monthPanel (panel that actually has all the days of the month)
		monthPanel = new JPanel();
		monthPanel.setLayout(new GridLayout(0, 7, 5, 5));
		
		// skip grids/days until we reach the day of the week the month is supposed to start on (need to fill with empty date boxes)
		for (int i = 0; i < this.month.getFirstDayOfMonth(); i++) {
			monthPanel.add(new JLabel(" "));
		}
		// add the day panels to the month panel
		for (int i = 0; i < this.month.getNumOfDays(); i++) {
			DayPanel newDayPanel = new DayPanel(this.month.getDay(i));
			monthPanel.add(newDayPanel);
			daysArray[i] = newDayPanel;
		}
		this.add(monthPanel, BorderLayout.CENTER);
	}
	
	// This method returns a specific day panel in a month
	public DayPanel getDayPanel(int day) {
		return daysArray[day - 1];
	}
	
	public DayPanel[] getDayPanels() {
		return this.daysArray;
	}
}
