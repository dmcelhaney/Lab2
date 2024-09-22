import java.time.LocalDateTime;

abstract class Event implements Comparable<Event> {
    String name;

    LocalDateTime dateTime;

    abstract String getName();

    LocalDateTime getDateTime(){
        return dateTime;
    }

    void setDateTime(LocalDateTime dateTime){
        this.dateTime = dateTime;
    }

    void setName(String name){
        this.name = name;
    }

    @Override
    public int compareTo(Event e){
        return dateTime.compareTo(e.getDateTime());
    }
}
