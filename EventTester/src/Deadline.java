import java.time.LocalDateTime;

public class Deadline extends Event implements Completable {
    boolean complete;

    Deadline(String name, LocalDateTime deadline){
        super.name = name;
        super.dateTime = deadline;
    }

    @Override
    public void complete() {
        complete = true;
    }

    @Override
    public boolean isComplete() {
        return complete;
    }

    @Override
    String getName() {
        return super.name;
    }
}
