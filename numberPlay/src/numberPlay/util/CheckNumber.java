package numberPlay.util;
import numberPlay.subject.Event;
import numberPlay.subject.NumberProcessor;
import numberPlay.subject.SubjectI;

public class CheckNumber {
   public void checkValue(String line){
       SubjectI numberProcessor=new NumberProcessor();
       if(line==null){
           numberProcessor.notify(-99,Event.COMPLETE);
           System.out.println("Control reached here in check in Complete");
       }
       try{
           int i=Integer.parseInt(line);
           System.out.println("Value is Int"+i);
           numberProcessor.notify(i,Event.INT);
           System.out.println("Control reached here in check number");
       }
       catch(Exception e){
           Float f=Float.parseFloat(line);
           System.out.println("Value is Float"+f);
           numberProcessor.notify(f,Event.FLOAT);
           System.out.println("Control reached here in check number in float");
       }
   }
}
