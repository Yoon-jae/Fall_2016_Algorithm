import java.math.BigInteger;

/**
 * Created by yoonjae on 10/12/16.
 */
public class Fibonacci {

    public BigInteger fibonacciUsingDivideAndConquer(BigInteger n) {
        if (n.compareTo(BigInteger.ZERO) == 0)
            return BigInteger.ZERO;

        if (n.compareTo(BigInteger.ONE) == 0)
            return BigInteger.ONE;

        return fibonacciUsingDivideAndConquer(n.subtract(BigInteger.ONE))
                .add(fibonacciUsingDivideAndConquer(n.subtract(BigInteger.valueOf(2))));
    }

    public BigInteger fibonacciUsingArray(BigInteger n) {
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
