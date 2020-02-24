package numberPlay.observer;

import numberPlay.util.PersisterI;
import numberPlay.util.TopKNumbersData;
import numberPlay.util.TopKNumbersResultsI;

import java.util.ArrayList;
import java.util.Collections;

public class TopKNumbers implements ObserverI {
    String topKNumbersFile;
    String kValue;
    int kValueInt;
    public TopKNumbers(String kValueIn, String topKNumbersFileIn) {
        topKNumbersFile = topKNumbersFileIn;
        kValue = kValueIn;
        kValueInt=Integer.parseInt(kValue);
    }

    ArrayList<Double> topKList=new ArrayList<>();

    @Override
    public void update(Number number) {

        double receivedNumber=number.doubleValue();
        PersisterI writeToFile=new TopKNumbersData(topKNumbersFile);
        TopKNumbersResultsI storeList=new TopKNumbersData(topKNumbersFile);
        receivedNumber=Math.floor(receivedNumber*100)/100;
        if(-99.0==receivedNumber){
            writeToFile.writeToFile();
            writeToFile.close();
        }
        else {

            if (topKList.isEmpty()) {
                topKList.add(receivedNumber);
            } else if (kValueInt > topKList.size()) {
                topKList.add(receivedNumber);
                topKList.sort(Collections.reverseOrder());
            } else {
                topKList.remove(kValueInt - 1);
                topKList.add(receivedNumber);
                topKList.sort(Collections.reverseOrder());
            }

            storeList.store(topKList);

            //System.out.println(topKList);
        }
    //System.out.println("Values Received"+kValue+topKNumbersFile+" "+ receivedNumber);
    }
}
