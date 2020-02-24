package numberPlay.util;

import numberPlay.observer.TopKNumbers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * To calculate Top K Numbers
 */
public class TopKNumbersData implements PersisterI, TopKNumbersResultsI {
	String resultsFileName;
	static List<List<Double>> dataToBeWrittenList=new ArrayList<>();
	public TopKNumbersData(String resultsFileNameIn){
		resultsFileName=resultsFileNameIn;
	}

	@Override
	public void store(List<Double> topK) {
		dataToBeWrittenList.add(new ArrayList<>(topK));
	}

	/**
	 * Write values to the file after process complete event is triggered
	 */
	@Override
	public void writeToFile() {
		try {
			File resultFile=new File(resultsFileName);
			if (resultFile.exists()){
				FileWriter fileWriter=new FileWriter(resultsFileName,true);
				for(int i=0;i<dataToBeWrittenList.size();i++){
					List<Double> data=dataToBeWrittenList.get(i);
					String stringData=""+data;
					fileWriter.write(stringData);
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
		String returnValue="File path written is"+resultsFileName;
		return returnValue;
	}
	@Override
	public void close() {
		System.out.println("Data written to "+ resultsFileName);
	}
}
