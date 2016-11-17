import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by yoonjae on 17/11/2016.
 */
public class Main {
    static int[] weight = new int[51];
    static int[] value = new int[51];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data11.txt"));
        int numOfItems = 0;
        String line;
        while ((line = br.readLine()) != null) {
            StringTokenizer stk = new StringTokenizer(line, ",");
            while (stk.hasMoreTokens()) {
                int index = Integer.parseInt(stk.nextToken());
                value[index] = Integer.parseInt(stk.nextToken());
                weight[index] = Integer.parseInt(stk.nextToken());
                numOfItems = index;
            }
        }

        System.out.print("Input limit bag size(0 - 50) : ");
        int limitWeightSize = new Scanner(System.in).nextInt();
//        int limitWeightSize = 11;

        int[][] OPT = makeOPTTableUsingDP(numOfItems, limitWeightSize);

        printOPTTable(numOfItems, limitWeightSize, OPT);

        ArrayList<Integer> itemList = getItemListInNapsack(numOfItems, limitWeightSize, OPT);

        printResult(OPT[numOfItems][limitWeightSize], itemList);

    }

    /**
     * Make OTP Table using Dynamic Programming.
     * OPT[i][w] = 1) w_i > w : OPT[i - 1][w];
     *             2) w_i < w : OPT[i][w] = Math.max(OPT[i - 1][w], v_i + OPT[i - 1][w - w_i]);
     *
     * @param numOfItems
     * @param limitWeightSize
     * @return
     */
    private static int[][] makeOPTTableUsingDP(int numOfItems, int limitWeightSize) {
        int OPT[][] = new int[numOfItems + 1][limitWeightSize + 1];

        for (int i = 1; i <= numOfItems; i++) {
            for (int w = 0; w <= limitWeightSize; w++) {
                if (weight[i] > w)
                    OPT[i][w] = OPT[i - 1][w];
                else
                    OPT[i][w] = Math.max(OPT[i - 1][w], value[i] + OPT[i - 1][w - weight[i]]);
            }
        }
        return OPT;
    }

    /**
     * Get Item list in napsack using back tracking in OPT table.
     *
     * @param numOfItems
     * @param limitWeightSize
     * @param OPT
     * @return
     */
    private static ArrayList<Integer> getItemListInNapsack(int numOfItems, int limitWeightSize, int[][] OPT) {
        ArrayList<Integer> itemList = new ArrayList<>();
        int w = limitWeightSize;
        int i = numOfItems;
        while (i > 0 && w > 0) {
            if (w - weight[i] > 0 && OPT[i][w] == value[i] + OPT[i - 1][w - weight[i]]) {
                itemList.add(i);
                w -= weight[i];
            } else if (OPT[i][w] != OPT[i - 1][w]) {
                itemList.add(i);
                w -= weight[i];
            }
            i--;
        }
        return itemList;
    }

    private static void printResult(int i, ArrayList<Integer> itemList) {
        itemList.sort((i1, i2) -> Integer.compare(i1, i2));
        System.out.print("\nItem list in napsack : ");
        for (Integer num : itemList)
            System.out.print(num + " ");

        System.out.println("\nmax : " + i);
    }

    private static void printOPTTable(int numOfItems, int limitWeightSize, int[][] OPT) {
        for (int i = 0; i <= numOfItems; i++) {
            for (int j = 0; j <= limitWeightSize; j++) {
                System.out.printf("%5d", OPT[i][j]);
            }
            System.out.println();
        }
    }

}