import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by yoonjae on 10/12/16.
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Fibonacci fibo = new Fibonacci();
        BigInteger value;
        long start, end;

        System.out.print("Enter N(Natural number) for get fibonacci value : ");
        BigInteger n = input.nextBigInteger();

        System.out.println("==========================================================");
        start = System.nanoTime();
        value = fibo.fibonacciUsingDivideAndConquer(n);
        end = System.nanoTime();
        System.out.println("Fibonacci(n) using Divide and Conquer : " + value);
        System.out.println("Elapsed time : " + (double) (end - start) / 1000000000.0);
        System.out.println("==========================================================");
        start = System.nanoTime();
        value = fibo.fibonacciUsingArray(n);
        end = System.nanoTime();
        System.out.println("Fibonacci(n) using array : " + value);
        System.out.println("Elapsed time : " + (double) (end - start) / 1000000000.0);
        System.out.println("==========================================================");
        start = System.nanoTime();
        value = fibo.fibonacciUsingRecursiveSquaring(n);
        end = System.nanoTime();
        System.out.println("fibonacci(n) using RecursiveSquaring: " + value);
        System.out.println("Elapsed time : " + (double) (end - start) / 1000000000.0);
        System.out.println("==========================================================");
    }
}
