import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class EventPlanner {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Control Options");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        EventListPanel eventListPanel = new EventListPanel();
        addDefaultEvents(eventListPanel);
        frame.getContentPane().add(eventListPanel);

        frame.pack();
        frame.setVisible(true);
    }

    static void addDefaultEvents(EventListPanel eventListPanel){
        Meeting finsihedMeeting = new Meeting("finished", LocalDateTime.of(2024, 8, 7, 15, 0), LocalDateTime.of(2024, 8, 7, 16, 0), "Conference room 1");
        Meeting firstMeeting = new Meeting("firstMeeting", LocalDateTime.of(2024, 10, 7, 15, 0), LocalDateTime.of(2024, 10, 7, 16, 0), "Conference room 1");
        Meeting secondMeeting = new Meeting("secondMeeting", LocalDateTime.of(2024, 10, 7, 16, 30), LocalDateTime.of(2024, 10, 7, 18, 30), "Conference room 2");
        Deadline labDeadline = new Deadline("labDeadline", LocalDateTime.of(2024, 9, 25, 15, 0));
        Deadline quizDeadline = new Deadline("quizDeadline", LocalDateTime.of(2024, 9, 23, 23, 59));
        Deadline lateDeadline = new Deadline("lateDeadline", LocalDateTime.of(2024, 9, 21, 23, 59));

        eventListPanel.addEvent(finsihedMeeting);
        eventListPanel.addEvent(firstMeeting);
        eventListPanel.addEvent(secondMeeting);
        eventListPanel.addEvent(labDeadline);
        eventListPanel.addEvent(quizDeadline);
        eventListPanel.addEvent(lateDeadline);
    }
}
