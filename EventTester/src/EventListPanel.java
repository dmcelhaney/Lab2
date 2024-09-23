import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
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
        controlPanel.setBackground(Color.blue);
        controlPanel.setPreferredSize(new Dimension(1000, 100));

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

        //TODO: Tuesday
        //filterBoxes = new JCheckBox();

        //TODO: Tuesday
        addEventButton = new JButton();

        displayPanel = new JPanel();
        displayPanel.setPreferredSize(new Dimension(1000, 600));
        add(displayPanel);
    }

    public void addEvent(Event event) {
        events.add(event);
        //displayPanel.add(new EventPanel(event));
        updateDisplay();
    }

    public void updateDisplay() {
        displayPanel.removeAll();
        for (Event event : events) {
            displayPanel.add(new EventPanel(event));
        }
        revalidate();
        repaint();
    }
}
