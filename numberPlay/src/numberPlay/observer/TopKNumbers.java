package numberPlay.observer;

public class TopKNumbers implements ObserverI  {
    String topKNumbersFile;
    String windowSize;
    String kValue;
    public TopKNumbers(String windowSizeIn, String kValueIn,String topKNumbersFileIn){
        topKNumbersFile=topKNumbersFileIn;
        kValue=kValueIn;
        windowSize=windowSizeIn;
    }
    @Override
    public void update(Number number) {
        System.out.println("Got Number in TopKnumbers"+number);

    }
}
