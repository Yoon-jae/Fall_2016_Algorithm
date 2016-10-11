import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by yoonjae on 10/10/16.
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("정수 N 값을 입력하세요.");
        int n = input.nextInt();
        int e = -1, k = 1;

        while(k <= n) {
            e++;
            k *= 2;
        }

        System.out.println("log(" + n + ")'s floor : " + e);
    }
}
