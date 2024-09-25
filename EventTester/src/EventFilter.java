import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class EventFilter {
    public static Map<String, Predicate<Event>> getEventFilters() {
        final int NUMBER_OF_FILTERS = 3;

        Map<String, Predicate<Event>> filters = new HashMap<>();
        // Completed filter
        filters.put( "Completed", event -> ((Completable) event).isComplete());
        // Deadline filter
        filters.put("Deadlines", event -> event instanceof Deadline);
        // Meeting filter
        filters.put("Meetings", event -> event instanceof Meeting);

        return filters;
    }
}
