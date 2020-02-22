package numberPlay.observer;

public class RunningAverage implements ObserverI {
    String runAvgResultFile;
    String windowSize;
    public RunningAverage(String windowSizeIn,String runningAvgFile){
       windowSize=windowSizeIn;
       runAvgResultFile =runningAvgFile;
    }
    @Override
    public void update(Number number) {
        System.out.println("Got Number in RunningAverage"+number);
    }
}
