package numberPlay.subject;

import numberPlay.observer.ObserverI;
import numberPlay.util.FilterI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberProcessor implements SubjectI{

    Map<FilterI, ArrayList<ObserverI>> observers= new HashMap<>();

    @Override
    public void addObserver(ObserverI observerI, FilterI filterI){
        if (!observers.containsKey(filterI)) {
            observers.put(filterI, new ArrayList<>());
        }
        observers.get(filterI).add(observerI);
    }

    @Override
    public void removeObserver(ObserverI observerI) {
    }

    public void getUpdate(){}



    @Override
    public void notify(Number number,Event event) {
        for (Map.Entry<FilterI, ArrayList<ObserverI>> entry : observers.entrySet()) {
            if (entry.getKey().check(event)) {
                for (ObserverI o : entry.getValue()) {
                    o.update(number);
                }
            }
        }
    }
}

