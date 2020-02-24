package numberPlay.observer;
import java.lang.Math;
import numberPlay.util.PersisterI;
import numberPlay.util.RunningAverageData;
import numberPlay.util.RunningAverageResultsI;
import java.util.ArrayList;

/**
 * Calculates running average
 */
public class RunningAverage implements ObserverI {
    String runAvgResultFile;
    String windowSize;
    int windowSizeInt;
    ArrayList<Integer> currWindow=new ArrayList<>();

    /**
     * Constructor for RunningAverage
     * @param windowSizeIn
     * @param runningAvgFile
     */
    public RunningAverage(String windowSizeIn,String runningAvgFile){
       windowSize=windowSizeIn;
       runAvgResultFile =runningAvgFile;
    }

    /**
     * Overrides ObserverI interface
     * @param number
     */
    @Override
    public void update(Number number)
    {
        RunningAverageResultsI runningAverageData=new RunningAverageData(runAvgResultFile);
        PersisterI writingToFile=new RunningAverageData(runAvgResultFile);
        int check=number.intValue();
        if(-99==check)
        {
            writingToFile.writeToFile();
            writingToFile.close();
        }
        else {
            int currentValue = number.intValue();
            if (currWindow.isEmpty())
            {
                currWindow.add(currentValue);
            }
            else if(currWindow.size() < windowSizeInt)
            {
                currWindow.add(currentValue);
            } else {
                currWindow.remove(0);
                currWindow.add(currentValue);
            }

            windowSizeInt = Integer.parseInt(windowSize);
            double average = calculateAverage(currWindow);
            runningAverageData.store(average);
        }

    }


    double calculateAverage(ArrayList<Integer> currWindowIn)
    {
        double sum=0;
        for(Integer integer : currWindowIn)
        {
            sum += integer;
        }
        double avg=sum/currWindowIn.size();
        avg=Math.floor(avg * 100) / 100;
        return avg;
    }
    @Override
    public String toString(){
        String returnValue="Running average observer";
        return returnValue;
    }
}
