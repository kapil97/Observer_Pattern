package numberPlay.subject;

import numberPlay.observer.ObserverI;
import numberPlay.util.FileProcessor;
import numberPlay.util.FilterI;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberProcessor implements SubjectI{

    static Map<FilterI, ArrayList<ObserverI>> observers= new HashMap<>();


    @Override
    public void addObserver(ObserverI observer, FilterI filter){
        if (!observers.containsKey(filter)) {
            observers.put(filter, new ArrayList<>());
        }
        observers.get(filter).add(observer);
}
    @Override
    public void update(){
        System.out.println("Here in update");
        for (Map.Entry<FilterI,ArrayList<ObserverI>> entry : observers.entrySet()) {
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
        }
    }

    @Override
    public void removeObserver(ObserverI observerI) {
    }

    @Override
    public void notify(Number number,Event event) {
        System.out.println("Control Reached in notify "+ number);
        System.out.println("and the event is "+event);
        update();
        for (Map.Entry<FilterI, ArrayList<ObserverI>> entry : observers.entrySet()) {
            if (entry.getKey().check(event)) {
                System.out.println("The value for checking is"+entry.getKey().check(event));
                for (ObserverI o : entry.getValue()) {
                    o.update(number);
                }
            }
        }
    }
}

