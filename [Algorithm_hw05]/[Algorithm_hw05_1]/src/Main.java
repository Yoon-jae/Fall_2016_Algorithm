import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by yoonjae on 10/12/16.
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BigInteger n = BigInteger.valueOf(90);
        int command = 0;

        while(command != 4) {
            printManual();
            command = input.nextInt();

            if(command == 4) break;

            printDivisionLine();
            switch (command) {
                case 1:
                    recursion(n);
                    break;

                case 2:
                    array(n);
                    break;

                case 3:
                    recursiveSquaring(n);
                    break;

                default:
                    break;
            }
            printDivisionLine();
        }

    }

    private static void printDivisionLine() {
        System.out.println("==========================================================");
    }

    private static void printManual() {
        System.out.println("방법을 선택하세요.\n");
        System.out.println("1. Recursion");
        System.out.println("2. Array");
        System.out.println("3. Recursive squaring");
        System.out.println("4. Exit");
    }

    private static void recursiveSquaring(BigInteger n) {
        long start;
        BigInteger value;
        long end;
        System.out.println("Recursive squaring !");
        for(BigInteger i = BigInteger.ZERO; i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {
            start = System.nanoTime();
            value = Fibonacci.usingRecursiveSquaring(i);
            end = System.nanoTime();
            printValueAndElapsedTime(start, value, end, i);
        }
    }

    private static void array(BigInteger n) {
        long start;
        BigInteger value;
        long end;
        System.out.println("Array !");
        for(BigInteger i = BigInteger.ZERO; i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {
            start = System.nanoTime();
            value = Fibonacci.usingArray(i);
            end = System.nanoTime();
            printValueAndElapsedTime(start, value, end, i);
        }
    }

    private static void recursion(BigInteger n) {
        long start;
        BigInteger value;
        long end;
        System.out.println("Recursion !");
        for(BigInteger i = BigInteger.ZERO; i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {
            start = System.nanoTime();
            value = Fibonacci.usingRecursion(i);
            end = System.nanoTime();
            printValueAndElapsedTime(start, value, end, i);
        }
    }

    private static void printValueAndElapsedTime(long start, BigInteger value, long end, BigInteger i) {
        System.out.printf("Fibonacci(%2d) : %20d", i, value );
        System.out.printf(", Elapsed time : %.10f\n", (double) (end - start) / 1000000000.0);
    }
}
