package numberPlay.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.spec.ECField;
import java.util.ArrayList;

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

	@Override
	public void writeToFile() {
		try {
			File resultFile=new File("running.txt");
			if (resultFile.exists()){
				FileWriter fileWriter=new FileWriter("running.txt",true);
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
	}

	@Override
	public void close() {

	}
}
