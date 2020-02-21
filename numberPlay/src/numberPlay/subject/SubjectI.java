package numberPlay.subject;
import numberPlay.observer.*;
import numberPlay.util.FilterI;

public interface SubjectI{
void addObserver(ObserverI observerI, FilterI filterI);
void removeObserver(ObserverI observerI);
void notify(Number number,Event event);
}