package assignment2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.*;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Assignment 2 - Calendar");
		// initialize CalendarFrame which is the JPanel that holds the actual calendar with all months and days
		CalendarFrame calendar = new CalendarFrame();
		
		// create calendar header which has the SCU logo
		JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 0));
		header.setBackground(Color.black);
		
		// create logo
		JLabel logo = new JLabel();
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("sculogo.png").getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
		logo.setIcon(imageIcon);
		logo.setBorder( new EmptyBorder(20, 10, 20, 10));
		header.add(logo);
		
		// create the JLabel that displays the year 2019
		JLabel year = new JLabel("2019", SwingConstants.CENTER);
		year.setFont(new Font("Serif", Font.PLAIN, 24));
		year.setForeground(Color.white);
		header.add(year);

		JPanel radioButtonPanel = new JPanel(new FlowLayout());
		
		// create radio buttons that allow user to select view
		JRadioButton monthView = new JRadioButton("Month");
		monthView.setSelected(true);
		JRadioButton weekView = new JRadioButton("Week");
		JRadioButton dayView = new JRadioButton("Day");
		
		monthView.setForeground(Color.black);
		weekView.setForeground(Color.black);
		dayView.setForeground(Color.black);
		
		radioButtonPanel.add(monthView); radioButtonPanel.add(weekView); radioButtonPanel.add(dayView); 
		
		ButtonGroup viewButtons = new ButtonGroup();
		viewButtons.add(monthView); viewButtons.add(weekView); viewButtons.add(dayView);
		
		// add action listener to each radio button
		monthView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// set all day panels to month view size
				MonthFrame[] mPanels = calendar.getMonthPanels();
				for (int i = 0; i < mPanels.length; i++) {
					DayPanel[] dPanels = mPanels[i].getDayPanels();
					for (int j = 0; j < dPanels.length; j++) {
						dPanels[j].setMonthView();
						dPanels[j].revalidate();
						dPanels[j].getIgnoreRepaint();
					}
				}
			}
		});
		
		weekView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// set all day panels to week view size
				MonthFrame[] mPanels = calendar.getMonthPanels();
				for (int i = 0; i < mPanels.length; i++) {
					DayPanel[] dPanels = mPanels[i].getDayPanels();
					for (int j = 0; j < dPanels.length; j++) {
						dPanels[j].setWeekView();
						dPanels[j].revalidate();
						dPanels[j].getIgnoreRepaint();
					}
				}
			}
		});
		
		dayView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// hide week headers
				calendar.hideWeekHeaders();
				// set all day panels to day view size
				MonthFrame[] mPanels = calendar.getMonthPanels();
				for (int i = 0; i < mPanels.length; i++) {
					DayPanel[] dPanels = mPanels[i].getDayPanels();
					for (int j = 0; j < dPanels.length; j++) {
						dPanels[j].setDayView();
						dPanels[j].revalidate();
						dPanels[j].getIgnoreRepaint();
					}
				}
			}
		});
		
		// header.add(monthView); header.add(weekView); header.add(dayView);	
		header.add(radioButtonPanel);
		
		// create side panel that allows user to add/remove events to/from calendar
		SidePanel sidePnl = new SidePanel(calendar);
		
		// add all the components to main frame
		frame.add(header, BorderLayout.NORTH);
		frame.add(calendar, BorderLayout.CENTER);
		frame.add(sidePnl, BorderLayout.EAST);
		frame.setSize(1250, 700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}