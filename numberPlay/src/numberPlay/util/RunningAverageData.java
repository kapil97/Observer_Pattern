package numberPlay.util;

public class RunningAverageData implements PersisterI, RunningAverageResultsI {
	String runningAvgFileName;
	public RunningAverageData(String resultFilename){
		runningAvgFileName=resultFilename;
	}
	@Override
	public void store(Double d) {

	}

	@Override
	public void writeToFile() {

	}

	@Override
	public void close() {

	}
}
