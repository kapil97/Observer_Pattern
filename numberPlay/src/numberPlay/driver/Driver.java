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
import java.util.ArrayList;

/**
 * @author Kamleshwar Ragava
 *
 */
public class Driver {
	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as argX, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 * FIXME Refactor commandline validation using the validation design taught in class.
		 */
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




		// FIXME Create an instance of each of the classes implementing PersisterI and the 
		// corresponding ResultsI interface.
		// Observers use these objects to dump data to be stored and eventually persisted 
		// to the corresponding output file.

		SubjectI numberProcessor=new NumberProcessor();
		ObserverI numberPeaks=new NumberPeaks(args[5]);
		ObserverI runningAverage=new RunningAverage(args[1],args[2]);
		ObserverI topKNumbers=new TopKNumbers(args[1],args[3],args[4]);

		FilterI filterInt=new FilterInt();
		FilterI filterFloat=new FilterFloat();
		FilterI filterComplete=new FilterComplete();

		numberProcessor.addObserver(numberPeaks,filterInt);
		numberProcessor.addObserver(runningAverage,filterInt);
		numberProcessor.addObserver(topKNumbers,filterInt);

		numberProcessor.addObserver(numberPeaks,filterFloat);
		numberProcessor.addObserver(topKNumbers,filterFloat);


		numberProcessor.addObserver(numberPeaks,filterComplete);
		numberProcessor.addObserver(runningAverage,filterComplete);
		numberProcessor.addObserver(topKNumbers,filterComplete);
		try {
			FileProcessor fileProcessor = new FileProcessor(args[0]);
			String line=fileProcessor.poll();
			CheckNumber checkNumber = new CheckNumber();

			while(line!=null) {
				checkNumber.checkValue(line);
				line=fileProcessor.poll();
			}

			//checkNumber.checkValue(null);
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println(e);
		}
		// FIXME Delegate control to a separate utility class/method that provides numbers one at a time, from the FileProcessor to the subject.
	}
}
