package numberPlay.util;

import numberPlay.subject.Event;

public class FilterComplete implements FilterI{
    @Override
    public boolean check(Event eventIn) {
        Event event=Event.COMPLETE;
        return event.equals(eventIn);
    }
}
