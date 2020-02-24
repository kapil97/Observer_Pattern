package numberPlay.driver;

import numberPlay.observer.NumberPeaks;
import numberPlay.observer.ObserverI;
import numberPlay.observer.RunningAverage;
import numberPlay.observer.TopKNumbers;
import numberPlay.subject.Event;
import numberPlay.subject.NumberProcessor;
import numberPlay.subject.SubjectI;
import numberPlay.util.*;

import javax.print.attribute.standard.NumberUp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;

/**
 * @author Kamleshwar Ragava
 *
 */

public class Driver {
	/**
	 * Main Method
	 * Command line validation happens here
	 * takes input as command line arguments
	 * @param args
	 */
	public static void main(String[] args) {

		final int REQUIRED_NUMBER_OF_ARGS = 6;
		if ((args.length != REQUIRED_NUMBER_OF_ARGS) || 
				(args[0].equals("${inputNumStream}")) || 
				(args[1].equals("${runAvgWindowSize}")) || 
				(args[2].equals("${runAvgOutFile}")) ||
				(args[3].equals("${k}")) ||
				(args[4].equals("${topKNumOutFile}")) ||
				(args[5].equals("${numPeaksOutFile}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_ARGS);
			System.exit(0);
		}
		ValidatorI validator=new Validator(args[0],args[3],args[1]);
		if(validator.valid()) {
			SubjectI numberProcessor = new NumberProcessor();
			ObserverI numberPeaks = new NumberPeaks(args[5]);
			ObserverI runningAverage = new RunningAverage(args[1], args[2]);
			ObserverI topKNumbers = new TopKNumbers(args[3], args[4]);

			FilterI filterInt = new FilterInt();
			FilterI filterFloat = new FilterFloat();
			FilterI filterComplete = new FilterComplete();

			numberProcessor.addObserver(numberPeaks, filterInt);
			numberProcessor.addObserver(runningAverage, filterInt);
			numberProcessor.addObserver(topKNumbers, filterInt);

			numberProcessor.addObserver(numberPeaks, filterFloat);
			numberProcessor.addObserver(topKNumbers, filterFloat);


			numberProcessor.addObserver(numberPeaks, filterComplete);
			numberProcessor.addObserver(runningAverage, filterComplete);
			numberProcessor.addObserver(topKNumbers, filterComplete);

			try {
				FileProcessor fileProcessor = new FileProcessor(args[0]);
				String line = fileProcessor.poll();
				CheckNumber checkNumber = new CheckNumber();

				while (line != null) {
					checkNumber.checkValue(line);
					line = fileProcessor.poll();
				}
				checkNumber.checkValue(line);
				fileProcessor.close();
			} catch (InvalidPathException e) {
				System.out.println("Invalid File Path");
			} catch (IOException | SecurityException e) {
				System.out.println("File Cannot be read");
			} finally {
				System.out.println("All File Operations are Closed");
			}
		}
		else{
			System.exit(0);
		}
	}
	@Override
	public String toString(){
		String returnValue="Main Class";
		return returnValue;
	}
}
