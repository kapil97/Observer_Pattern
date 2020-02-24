package numberPlay.util;

import numberPlay.subject.Event;

public class FilterFloat implements FilterI {
    @Override
    public boolean check(Event eventIn) {
        Event event=Event.FLOAT;
        return event.equals(eventIn);
    }
    @Override
    public String toString(){
        return "Float Event Filter";
    }
}
