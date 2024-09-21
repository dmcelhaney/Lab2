import java.util.Date;

public class Meeting extends Event implements Comparable<Meeting>{

    Date endDateTime;   // the time the meeting is over.

    String location;    // represents the location of the event.

    void complete(){

    }    // sets the complete boolean to true;

    boolean isComplete(){
        return false;
    }    // returns the complete boolean.

    Date getEndTime() {
        return endDateTime;
    }    // returns the endDateTime

    int getDuration(){
        return 0;
    }     // returns the duration of the meeting, (dateTime – endDateTime)

    String getLocation(){
        return location;
    }   // returns the location of the meeting.

    void setEndTime(Date end){

    }    // sets the end of the meeting.

    void setLocation(String location) {

    }  // sets the location of the meeting.

    String getName() {
        return "";
    }

    @Override
    public int compareTo(Meeting o) {
        return 0;
    }
}
