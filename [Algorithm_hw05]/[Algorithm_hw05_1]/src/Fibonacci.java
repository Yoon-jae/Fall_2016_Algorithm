import java.math.BigInteger;

/**
 * Created by yoonjae on 10/12/16.
 */
public class Fibonacci {

    public BigInteger fibonacciUsingRecursion(BigInteger n) {
        if (n.equals(BigInteger.ZERO))
            return BigInteger.ZERO;

        if (n.equals(BigInteger.ONE))
            return BigInteger.ONE;

        return fibonacciUsingRecursion(n.subtract(BigInteger.ONE))
                .add(fibonacciUsingRecursion(n.subtract(BigInteger.valueOf(2))));
    }

    public BigInteger fibonacciUsingArray(BigInteger n) {

        if (n.equals(BigInteger.ZERO)) {
            return BigInteger.ZERO;
        }

        if (n.equals(BigInteger.ONE)) {
            return BigInteger.ONE;
        }

        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        BigInteger c = null;

        for (BigInteger i = BigInteger.valueOf(2); i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {
            c = a.add(b);
            a = b;
            b = c;
        }

        return c;
    }

    public BigInteger fibonacciUsingRecursiveSquaring(BigInteger n) {

        return null;
    }
}
