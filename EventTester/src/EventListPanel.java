import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import java.util.function.Predicate;

public class EventListPanel extends JPanel {
    ArrayList<Event> events;
    JPanel controlPanel;
    JPanel displayPanel;
    JComboBox<String> sortDropDown;
    JButton addEventButton;
    final String[] SORT_OPTIONS = {"ALPHABETICAL NAME", "REVERSE ALPHABETICAL NAME", "ASCENDING DATE", "DESCENDING DATE"};

    final String[] FILTER_TYPES = {"Completed", "Deadlines", "Meetings"};
    Map<String, Predicate<Event>> filters;
    ArrayList<JCheckBox> filterBoxes;


    EventListPanel() {
        setPreferredSize(new Dimension(1000, 700));
        setBackground(Color.BLACK);
        events = new ArrayList<>();

        controlPanel = new JPanel();
        controlPanel.setPreferredSize(new Dimension(1000, 100));

        addEventButton = new JButton();
        addEventButton = new JButton("Add Event");
        addEventButton.setFont(new Font("Arial", Font.BOLD, 30));
        addEventButton.addActionListener(e -> {
            new AddEventModal(this);
        });
        controlPanel.add(addEventButton);

        sortDropDown = new JComboBox<>(SORT_OPTIONS);
        sortDropDown.setFont(new Font("Arial", Font.BOLD, 30));
        sortDropDown.addActionListener(e -> {
            if (Objects.equals(sortDropDown.getSelectedItem(), SORT_OPTIONS[0])) {
                events.sort((a1, a2) -> a1.getName().compareToIgnoreCase(a2.getName()));
            }
            if (Objects.equals(sortDropDown.getSelectedItem(), SORT_OPTIONS[1])) {
                events.sort((a1, a2) -> a1.getName().compareToIgnoreCase(a2.getName()) * -1);
            }
            if (Objects.equals(sortDropDown.getSelectedItem(), SORT_OPTIONS[2])) {
                events.sort(Comparator.comparing(Event::getDateTime));
            }
            if (Objects.equals(sortDropDown.getSelectedItem(), SORT_OPTIONS[3])) {
                events.sort((a1, a2) -> a1.getDateTime().compareTo(a2.getDateTime()) * -1);
            }
            updateDisplay();
        });
        controlPanel.add(sortDropDown);
        add(controlPanel);

        filters = new HashMap<>();
        filters.putAll(EventFilter.getEventFilters());
        filterBoxes = new ArrayList<>();
        // initialize individual Checkboxes and add to ArrayList
        for ( String filter : filters.keySet()){
            JCheckBox box = new JCheckBox(filter);
            box.setFont(new Font("Arial", Font.BOLD, 30));
            box.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    updateDisplay();
                }
            });
            filterBoxes.add(box);
        }
        // Add each checkbox to the controlPanel
        for (JCheckBox filter : filterBoxes)
            controlPanel.add(filter);
        add(controlPanel);

        displayPanel = new JPanel();
        displayPanel.setPreferredSize(new Dimension(1000, 600));
        add(displayPanel);
    }

    public void addEvent(Event event) {
        events.add(event);
        //displayPanel.add(new EventPanel(event));
        updateDisplay();
    }

    public boolean isFiltered(Event event)  {
        boolean result = false;
        for (JCheckBox filter : filterBoxes)
            if (filter.isSelected()) {
                Predicate<Event> pred = filters.get(filter.getText());
                if (pred.test(event))
                    result = true;
            }
        return result;
    }

    public void updateDisplay() {
        displayPanel.removeAll();
        for (Event event : events) {
            if (!isFiltered(event)) {
                displayPanel.add(new EventPanel(event));
            }
        }
        revalidate();
        repaint();
    }
}
