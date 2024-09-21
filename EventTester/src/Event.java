import java.util.Date;

abstract class Event {
    String name;

    Date dateTime;

    abstract String getName();

    Date getDateTime(){
        return dateTime;
    }

    void setDateTime(Date dateTime){

    }

    void setName(String name){

    }

    int compareTo(Event e){
        return 0;
    }
}
