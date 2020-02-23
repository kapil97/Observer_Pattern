package numberPlay.util;

import numberPlay.observer.NumberPeaks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class NumberPeaksData implements PersisterI, NumberPeaksResultsI {
	String resultFileName;
	public NumberPeaksData(String resultFileNameIn){
		resultFileName=resultFileNameIn;
	}
	static ArrayList<Double> dataToBeWritten=new ArrayList<Double>();
	@Override
	public void store(Double d) {
			dataToBeWritten.add(d);
	}

	@Override
	public void writeToFile() {
		try {
			File resultFile=new File(resultFileName);
			if (resultFile.exists()){
				FileWriter fileWriter=new FileWriter(resultFileName,true);
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
	System.out.println("Processing Complete"+resultFileName);
	}

	@Override
	public void close() {

	}

}
