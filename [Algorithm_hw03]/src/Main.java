import java.io.IOException;

/**
 * Created by yoonjae on 10/1/16.
 */
public class Main {

    public static void main(String args[]) {
        Sorting test = new Sorting();

        try {
            test.storeNumberFromInputFile("data04.txt");
//            test.defaultNumberSetting();

            test.printDigits();

//            test.quickSort(1, test.getSize());
            test.quickSort_withRandom(1, test.getSize());
            test.printDigits();

//            test.storeSortedNumberToOutputFile("hw03_01_201202154_quick.txt");
            test.storeSortedNumberToOutputFile("hw03_01_201202154_quickRandom.txt");
        } catch(IOException ioe) {
            System.err.println(ioe);
        }
    }
}
