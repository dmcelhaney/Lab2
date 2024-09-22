import java.time.Duration;
import java.time.LocalDateTime;

public class Meeting extends Event implements Completable{

    LocalDateTime endDateTime;   // the time the meeting is over.

    String location;    // represents the location of the event.

    boolean complete;

    Meeting(String name, LocalDateTime start, LocalDateTime end, String location){
        super.name = name;
        super.dateTime = start;
        endDateTime = end;
        this.location = location;
    }

    @Override
    public void complete() {
        complete = true;
    }

    @Override
    public boolean isComplete() {
        return complete;
    }

    LocalDateTime getEndDateTime() {
        return endDateTime;
    }    // returns the endDateTime

    Duration getDuration(){
        return Duration.between(dateTime, endDateTime);

    }     // returns the duration of the meeting, (dateTime – endDateTime)

    String getLocation(){
        return location;
    }   // returns the location of the meeting.

    void setEndDateTime(LocalDateTime end){
        endDateTime = end;
    }    // sets the end of the meeting.

    void setLocation(String location) {
        this.location = location;
    }  // sets the location of the meeting.


    @Override
    String getName() {
        return super.name;
    }
}
