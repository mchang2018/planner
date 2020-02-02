package assignment2;

import javax.swing.*;
import java.awt.*;

public class CalendarFrame extends JPanel {
	private JScrollPane scrollPane;
	private JPanel calendarPanel;
	private Month[] fullYear;
	private MonthFrame[] monthPanels;
	private JPanel weekHeaders;
	
	public CalendarFrame() {
		monthPanels = new MonthFrame[12];
		// create the full year JPanel by creating 12 MonthFrame Objects
		fullYear = new Month[] { new Month(1, 31, "January"), new Month(2, 28, "February"), new Month(3, 31, "March"),
								 new Month(4, 30, "April"), new Month(5, 31, "May"), new Month(6, 30, "June"),
								 new Month(7, 31, "July"), new Month(8, 31, "August"), new Month(9, 30, "September"),
								 new Month(10, 31, "October"), new Month(11, 30, "November"), new Month(12, 31, "December")};

		this.setLayout(new BorderLayout());
		
		// create days of week headers
		String[] weekDays = new String[] {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
		weekHeaders = new JPanel(new GridLayout(1, 7));
		weekHeaders.setBackground(Color.red.darker());
		for (int i = 0; i < 7; i++) {
			JLabel wkDay = new JLabel(weekDays[i], SwingConstants.CENTER);
			wkDay.setFont(new Font("Serif", Font.PLAIN, 14));
			wkDay.setForeground(Color.white);
			wkDay.setPreferredSize(new Dimension(100, 70));
			weekHeaders.add(wkDay);
		}
		
		calendarPanel = new JPanel(new GridLayout(0, 1, 0, 50));
		
		for (int i = 0; i < 12; i++) {
			MonthFrame newMonthPanel = new MonthFrame(fullYear[i]);
			calendarPanel.add(newMonthPanel);
			monthPanels[i] = newMonthPanel;
		}
		
		scrollPane = new JScrollPane(calendarPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(15);
		scrollPane.getViewport().setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);
		
		this.add(weekHeaders, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);
	}
	
	// This method returns a specific month panel in a year
	public MonthFrame getMonthFrame(int month) {
		return monthPanels[month - 1];
	}
	
	// This method returns a specific month in a year
	public Month getMonth(int month) {
		return fullYear[month - 1];
	}
	
	// This method returns the MonthFrame array
	public MonthFrame[] getMonthPanels() {
		return this.monthPanels;
	}
	
	// This method hides the week headers
	public void hideWeekHeaders() {
		this.weekHeaders.setVisible(false);
	}
}