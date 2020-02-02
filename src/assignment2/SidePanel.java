package assignment2;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SidePanel extends JPanel {
	private CalendarFrame calFrame;
	private JPanel top;
	private JPanel middle;
	private JPanel bottom;
	
	String[] colorOptions = {"Red", "Blue", "Green", "Purple", "Black"};
	DefaultListModel<String> events = new DefaultListModel<>();
	
	public SidePanel(CalendarFrame calFrame) {
		this.calFrame = calFrame;
		this.setLayout(new BorderLayout());
		// this.setLayout(new GridLayout(3, 1, 0, 10));
		top = new JPanel(new BorderLayout(0, 10));
		
		// create the title of side panel
		JLabel sidePanelTitle = new JLabel("Customize Calendar");
		sidePanelTitle.setFont(new Font("Serif", Font.BOLD, 18));
		sidePanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		// create another JPanel for adding event and date
		JPanel addEventPanel = new JPanel();
		addEventPanel.setLayout(new FlowLayout());
		
		// add all components (2 labels and their corresponding text fields, and a button) to addEventPanel
		JLabel eventNameLabel = new JLabel("Event Name");
		final JTextField eventName = new JTextField(10);
		JLabel eventDateLabel = new JLabel("Event Date");
		final JTextField eventDate = new JTextField(10);
		final JButton addButton = new JButton("Add Event");
		
		addEventPanel.add(eventNameLabel);
		addEventPanel.add(eventName);
		addEventPanel.add(eventDateLabel);
		addEventPanel.add(eventDate);
		addEventPanel.add(addButton);
		
		top.add(sidePanelTitle, BorderLayout.NORTH);
		top.add(addEventPanel, BorderLayout.CENTER);
		
		// create dropdown menu to change text color and add to middle panel
		middle = new JPanel(new FlowLayout());
		JLabel changeText = new JLabel("Change Text Color");
		JComboBox colorMenu = new JComboBox(colorOptions);
		
		middle.add(changeText);
		middle.add(colorMenu);
		top.add(middle, BorderLayout.SOUTH);
		
		// create panel for storing history of events and add to bottom panel
		bottom = new JPanel(new BorderLayout(0, 5));
		JLabel eventHistory = new JLabel("Event History", SwingConstants.CENTER);
		eventHistory.setFont(new Font("Serif", Font.BOLD, 16));
		JList<String> history = new JList<>(events);
		JScrollPane scrollPane = new JScrollPane(history, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(15);
		scrollPane.getViewport().setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);
		JButton deleteButton = new JButton("Delete Event");
		
		bottom.add(eventHistory, BorderLayout.NORTH);
		bottom.add(scrollPane, BorderLayout.CENTER);
		bottom.add(deleteButton, BorderLayout.SOUTH);
		
		// add action listener for add event button
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalendarEvent newEvent = new CalendarEvent(eventName.getText(), eventDate.getText());
				if (!newEvent.checkDate()) {
					JOptionPane.showMessageDialog(null, "Invalid date, please try again", "ERROR", JOptionPane.INFORMATION_MESSAGE);
					eventDate.setText("");
				}
				else {
					// add event to that day with correct text color, then add event to the history JList
					calFrame.getMonthFrame(newEvent.getMonthNum()).getDayPanel(newEvent.getDayNum()).getDay().addCalendarEvent(newEvent);
					calFrame.getMonthFrame(newEvent.getMonthNum()).getDayPanel(newEvent.getDayNum()).setEventText();
					calFrame.getMonthFrame(newEvent.getMonthNum()).getDayPanel(newEvent.getDayNum()).setTextColor((String)colorMenu.getSelectedItem());
					events.addElement(newEvent.eventHistoryString());
					// clear text fields
					eventName.setText("");
					eventDate.setText("");
				}
			}
		});
		
		// add action listener for delete event button
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (history.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "You have no events to delete.", "ERROR", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				// remove event from calendar
				String[] tokens = history.getSelectedValue().split(", ");
				CalendarEvent toDelete = new CalendarEvent(tokens[0], tokens[1]);
				calFrame.getMonthFrame(toDelete.getMonthNum()).getDayPanel(toDelete.getDayNum()).getDay().deleteCalendarEvent(tokens[0], tokens[1]);
				calFrame.getMonthFrame(toDelete.getMonthNum()).getDayPanel(toDelete.getDayNum()).setEventText();
				
				// remove event from history
				int selectedIndex = history.getSelectedIndex();
				events.remove(selectedIndex);
			}
		});
		
		this.add(top, BorderLayout.NORTH);
		this.add(bottom, BorderLayout.CENTER);
	}
}
