import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by yoonjae on 10/12/16.
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Fibonacci fibo = new Fibonacci();
        BigInteger n = BigInteger.valueOf(90);
        int command = 0;

        while(command != 4) {
            printManual();
            command = input.nextInt();

            if(command == 4) break;

            System.out.println("==========================================================");
            switch (command) {
                case 1:
                    recursion(fibo, n);
                    break;

                case 2:
                    array(fibo, n);
                    break;

                case 3:
                    recursiveSquaring(fibo, n);
                    break;

                default:
                    break;
            }
            System.out.println("==========================================================");
        }

    }

    private static void printManual() {
        System.out.println("방법을 선택하세요.\n");
        System.out.println("1. Recursion");
        System.out.println("2. Array");
        System.out.println("3. Recursive squaring");
        System.out.println("4. Exit");
    }

    private static void recursiveSquaring(Fibonacci fibo, BigInteger n) {
        long start;
        BigInteger value;
        long end;
        System.out.println("Recursive squaring !");
        for(BigInteger i = BigInteger.ZERO; i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {
            start = System.nanoTime();
            value = fibo.fibonacciUsingRecursiveSquaring(i);
            end = System.nanoTime();
            System.out.printf("Fibonacci(%2d) : %20d", i, value );
            System.out.println(", Elapsed time : " + (double) (end - start) / 1000000000.0);
        }
    }

    private static void array(Fibonacci fibo, BigInteger n) {
        long start;
        BigInteger value;
        long end;
        System.out.println("Array !");
        for(BigInteger i = BigInteger.ZERO; i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {
            start = System.nanoTime();
            value = fibo.fibonacciUsingArray(i);
            end = System.nanoTime();
            System.out.printf("Fibonacci(%2d) : %20d", i, value );
            System.out.println(", Elapsed time : " + (double) (end - start) / 1000000000.0);
        }
    }

    private static void recursion(Fibonacci fibo, BigInteger n) {
        long start;
        BigInteger value;
        long end;
        System.out.println("Recursion !");
        for(BigInteger i = BigInteger.ZERO; i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {
            start = System.nanoTime();
            value = fibo.fibonacciUsingRecursion(i);
            end = System.nanoTime();
            System.out.printf("Fibonacci(%2d) : %20d", i, value );
            System.out.println(", Elapsed time : " + (double) (end - start) / 1000000000.0);
        }
    }
}
