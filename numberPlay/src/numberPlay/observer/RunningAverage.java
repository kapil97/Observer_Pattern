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
        //System.out.println("Inside Observer File Name\t\t"+ runAvgResultFile);
        int check=number.intValue();
        //System.out.println("VAlue inside the update \t\t\t\t\t"+check);
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
            //System.out.println("Got Number in RunningAverage\t" + number);
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
