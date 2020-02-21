package numberPlay.util;

import numberPlay.subject.Event;

public class FilterComplete implements FilterI{
    @Override
    public boolean check(Event event) {
        return false;
    }
}
