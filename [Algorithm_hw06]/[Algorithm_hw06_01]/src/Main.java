import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by yoonjae on 10/20/16.
 */
public class Main {

    private static int inversionCount = 0;
    private static int[] digits;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data07_inversion.txt"));
        String line = br.readLine();
        StringTokenizer stk = new StringTokenizer(line, ", ");

        int digitIndex = readInputDataFromFile(stk);

        System.out.print("Initial Array : ");
        printDigitsArray(digitIndex);

        sortAndCount(1, digitIndex-1);

        System.out.println("Inversion count : " + inversionCount);

        System.out.print("Sorted Array : ");
        printDigitsArray(digitIndex);

        br.close();
    }

    private static int readInputDataFromFile(StringTokenizer stk) {
        digits = new int[1000001];
        int digitIndex = 1;

        while (stk.hasMoreTokens())
            digits[digitIndex++] = Integer.parseInt(stk.nextToken());
        return digitIndex;
    }

    private static void printDigitsArray(int digitIndex) {
        for(int i=1; i<digitIndex; i++)
            System.out.print(digits[i] + " ");
        System.out.println();
    }

    public static void sortAndCount(int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            sortAndCount(left, mid);
            sortAndCount(mid + 1, right);
            mergeAndCount(left, mid, mid + 1, right);
        }
    }

    public static void mergeAndCount(int leftStart, int leftEnd, int rightStart, int rightEnd) {
        int indexA = leftStart, indexB = rightStart;
        int currentIndex = leftStart;
        int[] list = new int[rightEnd + 1];

        while(indexA <= leftEnd && indexB <= rightEnd) {
            if(digits[indexA] > digits[indexB]) {
                inversionCount += leftEnd - indexA + 1;
                list[currentIndex++] = digits[indexB++];
            } else list[currentIndex++] = digits[indexA++];
        }

        while(indexA <= leftEnd)
            list[currentIndex++] = digits[indexA++];

        while(indexB <= rightEnd)
            list[currentIndex++] = digits[indexB++];

        for(int index = leftStart; index <= rightEnd; index++)
            digits[index] = list[index];
    }
}

