package numberPlay.observer;

public class TopKNumbers implements ObserverI {
    String topKNumbersFile;
    String kValue;

    public TopKNumbers(String kValueIn, String topKNumbersFileIn) {
        topKNumbersFile = topKNumbersFileIn;
        kValue = kValueIn;
    }

    @Override
    public void update(Number number) {

    }
}
