package numberPlay.observer;

import numberPlay.util.NumberPeaksData;
import numberPlay.util.NumberPeaksResultsI;
import numberPlay.util.PersisterI;

public class NumberPeaks implements ObserverI{
    String peakFileName;
    static double peak = 0;
    public NumberPeaks(String resultsFileName){
        peakFileName=resultsFileName;
    }
    @Override
    public void update(Number number) {
        PersisterI writingToFile=new NumberPeaksData(peakFileName);
        NumberPeaksResultsI numberPeaksData=new NumberPeaksData(peakFileName);
        double incomingNum = number.doubleValue();
        incomingNum=Math.floor(incomingNum*100)/100;
        if(incomingNum==-99.0)
        {
            writingToFile.writeToFile();
        }
        else {
            if (peak <= incomingNum)
                peak=incomingNum;
            else {
                numberPeaksData.store(peak);
                System.out.println("\nPeak Found" + peak);
            }
        }
    }
}

