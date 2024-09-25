import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AddEventModal extends JDialog{
    private EventListPanel eventListPanel;  // This is the listPanel that made this modal
    AddEventModal modal;                   // self reference for use in inner classes
    record Attribute(String name, JComponent value){}  // record for associating names with JComponents
    ArrayList<Attribute> attributes;           // will vary
    JPanel infoCollectorPanel;                  // Holds the attribute components
    JComboBox<String> eventTypeComboBox;       // Chooses which type of event to create

    // passes the list of strings into an abstract List, which is then used to build a concrete ArrayList.
    // Represents the different types of Events I want to create.
    public static final String[] eventTypes =  {"Meeting", "Deadline"};

    // Constructor for the modal: Creates a JFrame and adds content to it.
    public AddEventModal(EventListPanel eventListPanel) {
        modal = this;                              // Needed so that dispose can be in action Listener.
        this.eventListPanel = eventListPanel;      // Need so Modal can send new Event back to event List.

        this.setTitle("Add Event");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().add(addEventPanel());
        this.pack();
        this.setVisible(true);
    }

    // Creates the panel that will hold all the components in this modal.
    // Should only run once when this Modal is created.
    private JPanel addEventPanel() {

        JPanel panel = new JPanel();
        attributes = new ArrayList<>();

        infoCollectorPanel = new JPanel();
        infoCollectorPanel.setPreferredSize(new Dimension(600, 400));
        infoCollectorPanel.setBackground(Color.magenta);

        eventTypeComboBox = new JComboBox<String>(eventTypes);
        eventTypeComboBox.addActionListener(getEventChooser());

        panel.add(eventTypeComboBox);
        panel.add(infoCollectorPanel);

        JButton addEventButton = new JButton("Submit");
        addEventButton.addActionListener(getEventCreator());

        panel.setPreferredSize(new Dimension(600, 400));
        panel.add(addEventButton);

        return panel;
    }

    // This returns an ActionListener that adds the appropriate fields to the infoCollectorPanel depending on which
    // Event is selected from the EventTypeComboBox
    // This is separate for readability
    private ActionListener getEventChooser() {
        return e -> {
            attributes.clear();
            infoCollectorPanel.removeAll();

            switch (eventTypeComboBox.getSelectedIndex()) {
                case 0: {
                    //LocalDateTime.of(2024, 12, 25, 7, 0)
                    attributes.add(new Attribute("Name", new JTextField(10)));
                    attributes.add(new Attribute("DateTime Year", new JTextField(10)));
                    attributes.add(new Attribute("DateTime Month", new JTextField(10)));
                    attributes.add(new Attribute("DateTime Day", new JTextField(10)));
                    attributes.add(new Attribute("DateTime Hour", new JTextField(10)));
                    attributes.add(new Attribute("DateTime Minute", new JTextField(10)));
                    attributes.add(new Attribute("EndDateTime Year", new JTextField(10)));
                    attributes.add(new Attribute("EndDateTime Month", new JTextField(10)));
                    attributes.add(new Attribute("EndDateTime Day", new JTextField(10)));
                    attributes.add(new Attribute("EndDateTime Hour", new JTextField(10)));
                    attributes.add(new Attribute("EndDateTime Minute", new JTextField(10)));
                    attributes.add(new Attribute("Location", new JTextField(10)));
                    break;
                }
                case 1: {
                    attributes.add(new Attribute("Name", new JTextField(10)));
                    attributes.add(new Attribute("DateTime Year", new JTextField(10)));
                    attributes.add(new Attribute("DateTime Month", new JTextField(10)));
                    attributes.add(new Attribute("DateTime Day", new JTextField(10)));
                    attributes.add(new Attribute("DateTime Hour", new JTextField(10)));
                    attributes.add(new Attribute("DateTime Minute", new JTextField(10)));
                    break;
                }
            }
            attributes.forEach(attr -> {
                infoCollectorPanel.add(new JLabel(attr.name));
                infoCollectorPanel.add(attr.value);
            });
            infoCollectorPanel.revalidate();
            infoCollectorPanel.repaint();
        };
    }


    // This function returns an ActionLister that creates an event based on the fields in the InfoCollection Panel
    // It calls the appropriate constructor based on the EventTypeComboBox.
    // It then disposes of the creator modal.
    // This is separate for readability
    private ActionListener getEventCreator() {
        return e -> {
            Event newEvent = new Meeting("default", LocalDateTime.of(2024, 8, 7, 15, 0), LocalDateTime.of(2024, 8, 7, 16, 0), "Home");   // need to initialize abstract Event Variable
            switch (eventTypeComboBox.getSelectedIndex()) {
                case 0: {
                    newEvent = new Meeting(getInput(attributes.get(0).value)
                            , LocalDateTime.of(Integer.parseInt(getInput(attributes.get(1).value))
                                            , Integer.parseInt(getInput(attributes.get(2).value))
                                            , Integer.parseInt(getInput(attributes.get(3).value))
                                            , Integer.parseInt(getInput(attributes.get(4).value))
                                            , Integer.parseInt(getInput(attributes.get(5).value)))
                            , LocalDateTime.of(Integer.parseInt(getInput(attributes.get(6).value))
                                            , Integer.parseInt(getInput(attributes.get(7).value))
                                            , Integer.parseInt(getInput(attributes.get(8).value))
                                            , Integer.parseInt(getInput(attributes.get(9).value))
                                            , Integer.parseInt(getInput(attributes.get(10).value)))
                            , getInput(attributes.get(11).value));
                    break;
                }
                case 1: {
                    newEvent = new Deadline(getInput(attributes.get(0).value), LocalDateTime.of(Integer.parseInt(getInput(attributes.get(1).value)), Integer.parseInt(getInput(attributes.get(2).value)), Integer.parseInt(getInput(attributes.get(3).value)), Integer.parseInt(getInput(attributes.get(4).value)), Integer.parseInt(getInput(attributes.get(5).value))));
                    break;
                }
            }
            eventListPanel.addEvent(newEvent);
            modal.dispose();
        };
    }

    //  This just helps get Text values from generic Components.
    private String getInput (JComponent c) {
        if (c instanceof JTextComponent) {
            return ((JTextComponent) c).getText();
        }
        return "";
    }

}
