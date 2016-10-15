import java.math.BigInteger;

/**
 * Created by yoonjae on 10/12/16.
 */
public class Fibonacci {

    public static BigInteger usingRecursion(BigInteger n) {

        if (n.equals(BigInteger.ZERO))
            return BigInteger.ZERO;

        if (n.equals(BigInteger.ONE))
            return BigInteger.ONE;

        return usingRecursion(n.subtract(BigInteger.ONE))
                .add(usingRecursion(n.subtract(BigInteger.valueOf(2))));
    }

    public static BigInteger usingArray(BigInteger n) {

        if (n.equals(BigInteger.ZERO))
            return BigInteger.ZERO;

        if (n.equals(BigInteger.ONE))
            return BigInteger.ONE;

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

    public static BigInteger usingRecursiveSquaring(BigInteger n) {

        BigInteger[][] fibonacciMatrix = new BigInteger[][]{{BigInteger.ONE, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ZERO}};
        BigInteger[][] retMatrix = new BigInteger[][]{{BigInteger.ONE, BigInteger.ZERO}, {BigInteger.ZERO, BigInteger.ONE}};

        while (n.compareTo(BigInteger.ZERO) > 0) {
            if (n.remainder(BigInteger.valueOf(2)).equals(BigInteger.ONE))
                retMatrix = multiplyMatrix(retMatrix, fibonacciMatrix);

            fibonacciMatrix = multiplyMatrix(fibonacciMatrix, fibonacciMatrix);
            n = n.divide(BigInteger.valueOf(2));
        }

        return retMatrix[0][1];
    }

    private static BigInteger[][] multiplyMatrix(BigInteger[][] a, BigInteger[][] b) {

        BigInteger[][] zeroMatrix = new BigInteger[][]{{BigInteger.ZERO, BigInteger.ZERO}, {BigInteger.ZERO, BigInteger.ZERO}};
        int arraySize = a.length;
        for (int i = 0; i < arraySize; i++)
            for (int j = 0; j < arraySize; j++)
                for (int k = 0; k < arraySize; k++)
                    zeroMatrix[i][j] = zeroMatrix[i][j].add(a[i][k].multiply(b[k][j]));

        return zeroMatrix;
    }
}
