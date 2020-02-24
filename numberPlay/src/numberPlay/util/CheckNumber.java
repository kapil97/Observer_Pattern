package numberPlay.util;
import numberPlay.subject.Event;
import numberPlay.subject.NumberProcessor;
import numberPlay.subject.SubjectI;

/**
 * Class to check and generate an event based on number format
 */
public class CheckNumber {
    /**
     * Checks the value of input and generates events accordingly
     * @param line
     */
   public void checkValue(String line){
       SubjectI numberProcessor=new NumberProcessor();
       if(line==null){
           numberProcessor.notify(-99,Event.COMPLETE);
           //System.out.println("Control reached here in check in Complete");
       }
       else {
           try {
               int i = Integer.parseInt(line);
               numberProcessor.notify(i, Event.INT);

           } catch (Exception e) {
               try {
                   Float f = Float.parseFloat(line);
                   numberProcessor.notify(f, Event.FLOAT);
               }
               catch (Exception e1){
                System.out.println("String Values or Null Values are Not Allowed");
                System.exit(0);
               }

           }
       }
   }
    @Override
    public String toString(){
        String returnValue="Check Event class for filtering inputs";
        return returnValue;
    }
}
