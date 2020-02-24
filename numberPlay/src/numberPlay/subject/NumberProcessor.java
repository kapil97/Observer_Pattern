package numberPlay.subject;

import numberPlay.observer.ObserverI;
import numberPlay.util.FileProcessor;
import numberPlay.util.FilterI;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Subject NumberProcessors
 */
public class NumberProcessor implements SubjectI{

    static Map<FilterI, ArrayList<ObserverI>> observers= new HashMap<>();

    /**
     * registering Observers
     * @param observer
     * @param filter
     */

    @Override
    public void addObserver(ObserverI observer, FilterI filter){
        if (!observers.containsKey(filter)) {
            observers.put(filter, new ArrayList<>());
        }
        observers.get(filter).add(observer);
}

    /**
     * removing observers from the list
     * @param observerI
     */
    @Override
    public void removeObserver(ObserverI observerI) {
        for (Map.Entry<FilterI, ArrayList<ObserverI>> entry : observers.entrySet()) {
                for (ObserverI o : entry.getValue()) {
                    if(o.equals(observerI)){
                        entry.getValue().remove(o);
                }
            }
        }
    }

    /**
     * notifying observers on an event
     * @param number
     * @param event
     */
    @Override
    public void notify(Number number,Event event) {
        for (Map.Entry<FilterI, ArrayList<ObserverI>> entry : observers.entrySet()) {
            if (entry.getKey().check(event)) {
                //System.out.println("The value for checking is"+entry.getKey().check(event));
                for (ObserverI o : entry.getValue()) {
                    o.update(number);
                }
            }
        }
    }
    @Override
    public String toString(){
        String returnValue="Subjects are"+observers;
        return returnValue;
    }
}

