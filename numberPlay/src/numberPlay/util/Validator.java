package numberPlay.util;

import java.io.File;

/**
 * Validates input files and command line arguments
 */
public class Validator implements ValidatorI {
    String inputFileName;
    int kValue;
    int windowSize;
    public Validator(String inputFileNameIn, String windowSizeIn, String kValueIn){
        inputFileName=inputFileNameIn;
        kValue=Integer.parseInt(kValueIn);
        windowSize=Integer.parseInt(windowSizeIn);
    }

    /**
     *
     * @return true if the file is valid or else returns false
     */
    @Override
   public boolean valid(){
        File file=new File(inputFileName);
        if(file.length()==0){
            System.out.println("File is empty");
            return false;
        }

        else if(kValue<0){
            System.out.println("K Value cannot be negative");
            return false;
        }
       else if(windowSize<0){
            System.out.println("windowSize cannot be Negative");
            return false;
        }
        else{
           return true;
        }
    }
    @Override
    public String toString(){
        String returnValue="This performs Validation and retruns a boolean value" ;
        return returnValue;
    }

}
