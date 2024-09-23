import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class EventPanel extends JPanel {
    Event event;
    JButton completeButton;
    static final Font labelFont = new Font("Arial", Font.PLAIN, 14);
    Color backgroundColor = Color.GREEN;

    public EventPanel(Event event) {
        setPreferredSize(new Dimension(1000, 50));
        this.event = event;

        updateUrgency();
        setBackground(backgroundColor);

        addElements();
    }

    void updateUrgency(){
        if (event.dateTime.isBefore(LocalDateTime.now())) {
            backgroundColor = Color.RED;
        } else {
            LocalDateTime imminentDate = event.dateTime.minusDays(2);
            if (LocalDateTime.now().isAfter(imminentDate)) {
                backgroundColor = Color.YELLOW;
            } else {
                backgroundColor = Color.GREEN;
            }
        }
    }

    public void addElements() {
        JLabel nameLabel = new JLabel("Event name: " + event.getName());
        nameLabel.setFont(labelFont);
        add(nameLabel);

        JLabel timeLabel = new JLabel("  Time:" + event.getDateTime());
        timeLabel.setFont(labelFont);
        add(timeLabel);

        if (event instanceof Meeting) {
            JLabel durationLabel = new JLabel("  Duration:" + ((Meeting) event).getDuration());
            durationLabel.setFont(labelFont);
            add(durationLabel);

            JLabel locationLabel = new JLabel("  Location:" + ((Meeting) event).getLocation());
            locationLabel.setFont(labelFont);
            add(locationLabel);

            String completeText = "NOT COMPLETE";
            if (((Meeting) event).endDateTime.isBefore(LocalDateTime.now())) {
                completeText = "COMPLETE";
            }

            JLabel statusLabel = new JLabel("  Status:" + completeText);
            statusLabel.setFont(labelFont);
            add(statusLabel);
        } else {
            String completeText = "NOT COMPLETE";
            if (((Deadline) event).isComplete()) {
                completeText = "COMPLETE";
            }

            JLabel statusLabel = new JLabel("  Status:" + completeText);
            statusLabel.setFont(labelFont);
            add(statusLabel);

            completeButton = new JButton("Complete");
            completeButton.setFont(labelFont);
            completeButton.addActionListener(e -> {
                ((Completable) event).complete();
                updateDisplay();
            });
            add(completeButton);
        }
    }

    public void updateDisplay() {
        removeAll();
        addElements();
        revalidate();
        repaint();
    }
}