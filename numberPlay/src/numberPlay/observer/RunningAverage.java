package numberPlay.observer;

import numberPlay.util.RunningAverageData;

import java.util.ArrayList;

public class RunningAverage implements ObserverI {
    String runAvgResultFile;
    String windowSize;
    int windowSizeInt;
    ArrayList<Integer> currWindow=new ArrayList<>();
    RunningAverageData runningAverageData=new RunningAverageData(runAvgResultFile);
    public RunningAverage(String windowSizeIn,String runningAvgFile){
       windowSize=windowSizeIn;
       runAvgResultFile =runningAvgFile;
    }
    @Override
    public void update(Number number)
    {

        int currentValue=number.intValue();
        if(currWindow.isEmpty()){
            currWindow.add(currentValue);
        }
        else
        if(currWindow.size()<windowSizeInt) {
            currWindow.add(currentValue);
        }
        else
        {
            currWindow.remove(0);
            currWindow.add(currentValue);
        }
        System.out.println("Got Number in RunningAverage\t"+number);
        windowSizeInt=Integer.parseInt(windowSize);
        double average=calculateAverage(currWindow);
        runningAverageData.store(average);
    }


    double calculateAverage(ArrayList<Integer> currWindowIn){
        double sum=0;
        for(Integer integer:currWindowIn){
            System.out.print( "["+integer+"]");
        }
            for(Integer integer : currWindowIn) {
                sum += integer;
            }
        System.out.println("SUM is "+sum);
        double avg=sum/windowSizeInt;
        System.out.println("Average is "+avg);
        return avg;
    }
}
