import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by yoonjae on 10/10/16.
 */
public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("큰 정수 N 값을 입력하세요.");
        BigInteger n = input.nextBigInteger();

        BigInteger[] bigArray = new BigInteger[1000];

        // Setting initial value.
        int e = 1;
        BigInteger k = BigInteger.valueOf(2);

        // Store 2^n value in array.
        bigArray[e] = k;

        int prevIndex = 0 , curIndex = 0;
        BigInteger prevValue = null, curValue = null;

        while(k.compareTo(n) <= 0) {
            prevIndex = e;
            prevValue = k;

            e += e;
            k = k.multiply(k);

            curIndex = e;
            curValue = k;

            // Store 2^n value in array.
            bigArray[e] = k;
        }

        // Binary search
        e = binarySeacrhForBigInteger(n, bigArray, prevIndex, curIndex);

        System.out.println("log(" + n + ")'s floor value: " + e);
    }

    /**
     * Get value from binary search with stored BigInteger array and prev, cur index.
     *
     * @param n : Key value (User input data)
     * @param bigArray : Stored BigInteger array
     * @param left : Start index
     * @param right : End index
     * @return
     */
    private static int binarySeacrhForBigInteger(BigInteger n, BigInteger[] bigArray, int left, int right) {

        int e;
        int midIndex = 0, diffIndex = 0;

        BigInteger midValue = null;

        while (true) {

            // Exit case
            if(left > right) {
                e = left;
                break;
            }

            midIndex = (left + right) / 2;
            diffIndex = (right - left) / 2;

            // Exit case
            if(diffIndex == 0) {
                e = left;
                break;
            }

            // Get mid value from calculating between bigArray's two elements.
            midValue = bigArray[right].divide(bigArray[diffIndex]);

            // Store 2^n value in array.
            bigArray[midIndex] = midValue;

            if(midValue.compareTo(n) < 0) {
                left = midIndex;
            } else if(midValue.compareTo(n) > 0) {
                right = midIndex;
            } else {
                e = midIndex;
                break;
            }
        }
        return e;
    }
}
