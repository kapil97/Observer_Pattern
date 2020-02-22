package numberPlay.util;

import numberPlay.subject.Event;

public class FilterInt implements FilterI {
    @Override
    public boolean check(Event eventIn) {
        Event event=Event.INT;
        return event.equals(eventIn);
    }
}
