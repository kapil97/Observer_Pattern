package numberPlay.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * T0 Calculate Running Average
 */
public class RunningAverageData implements PersisterI, RunningAverageResultsI {
	String runningAvgFileName;
	public RunningAverageData(String resultFilename){
		runningAvgFileName=resultFilename;
	}
	static ArrayList<Double> dataToBeWritten=new ArrayList<Double>();
	@Override
	public void store(Double d) {
		dataToBeWritten.add(d);
	}

	/**
	 * Writes values to the file after process complete event is triggered
	 */
	@Override
	public void writeToFile() {
		try {
			File resultFile=new File(runningAvgFileName);
			if (resultFile.exists()){
				FileWriter fileWriter=new FileWriter(runningAvgFileName,true);
				for(int i=0;i<dataToBeWritten.size();i++){
					String data=Double.toString(dataToBeWritten.get(i));
					fileWriter.write(data);
					fileWriter.write(System.getProperty("line.separator"));
				}

				fileWriter.close();
			}
			else{
				resultFile.createNewFile();
					System.out.println("File Created:" + resultFile.getName());
					writeToFile();
			}
		}
		catch (IOException e){
			System.out.println("Error occured");
			e.printStackTrace();
		}
		finally {
			System.out.println("Process Complete");
		}
	}
	@Override
	public String toString(){
		String returnValue="File path written is"+runningAvgFileName;
		return returnValue;
	}
	@Override
	public void close() {
		System.out.println("Data written to "+ runningAvgFileName);
	}
}
