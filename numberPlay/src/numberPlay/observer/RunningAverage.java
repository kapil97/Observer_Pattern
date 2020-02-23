package numberPlay.observer;
import java.lang.Math;

import numberPlay.util.PersisterI;
import numberPlay.util.RunningAverageData;
import numberPlay.util.RunningAverageResultsI;

import java.util.ArrayList;

public class RunningAverage implements ObserverI {
    String runAvgResultFile;
    String windowSize;
    int windowSizeInt;
    ArrayList<Integer> currWindow=new ArrayList<>();

    public RunningAverage(String windowSizeIn,String runningAvgFile){
       windowSize=windowSizeIn;
       runAvgResultFile =runningAvgFile;
    }

    @Override
    public void update(Number number)
    {
        RunningAverageResultsI runningAverageData=new RunningAverageData(runAvgResultFile);
        PersisterI writingToFile=new RunningAverageData(runAvgResultFile);
        int check=number.intValue();
        if(check==-99)
        {
            writingToFile.writeToFile();
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
}
