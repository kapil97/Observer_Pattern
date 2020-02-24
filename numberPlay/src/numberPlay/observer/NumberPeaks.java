package numberPlay.observer;

import numberPlay.util.NumberPeaksData;
import numberPlay.util.NumberPeaksResultsI;
import numberPlay.util.PersisterI;

/**
 * Performs operations to calculate Number Peaks
 */
public class NumberPeaks implements ObserverI{
    String peakFileName;
    static double peak = 0;
    public NumberPeaks(String resultsFileName){
        peakFileName=resultsFileName;
    }

    /**
     *
     * Overrides update method from ObserverI interface
     * @param number
     */
    @Override
    public void update(Number number) {
        PersisterI writingToFile=new NumberPeaksData(peakFileName);
        NumberPeaksResultsI numberPeaksData=new NumberPeaksData(peakFileName);
        double incomingNum = number.doubleValue();
        incomingNum=Math.floor(incomingNum*100)/100;
        if(-99.0==incomingNum)
        {
            writingToFile.writeToFile();
            writingToFile.close();
        }
        else {
            if (peak <= incomingNum)
                peak=incomingNum;
            else {
                numberPeaksData.store(peak);
            }
        }
    }
    @Override
    public String toString(){
        String returnValue="Number Peaks Observer";
        return returnValue;
    }
}

