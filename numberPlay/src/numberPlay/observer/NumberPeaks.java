package numberPlay.observer;

public class NumberPeaks implements ObserverI{
    String peakFileName;
    public NumberPeaks(String resultsFileName){
        peakFileName=resultsFileName;
    }
    @Override
    public void update(Number number) {
        //System.out.println("Got Number in NumberPeaks"+number);
    }
}
