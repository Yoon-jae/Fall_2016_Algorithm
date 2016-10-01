import java.io.*;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Created by yoonjae on 10/1/16.
 */
public class Sorting {

    public static final int MAX_ARRAY_SIZE = 1000001;
    private int[] digits;
    private int size;

    public Sorting() {
        this.digits = new int[MAX_ARRAY_SIZE];
        this.size = 0;
    }

    public void quickSort(int left, int right) {
        if (left < right) {
            int pivot = partition(left, right);
            quickSort(left, pivot - 1);
            quickSort(pivot + 1, right);
        }
    }

    private int partition(int left, int right) {

        int pivot = this.digits[right];
        int i = left - 1;
        for (int j = left; j <= right - 1; j++) {
            if (this.digits[j] <= pivot) {
                i++;
                swap(i, j);
            }
        }
        i++;
        swap(i, right);
        return i;

        /*
        do {
            do up++; while (this.digits[up] < pivot && up <= this.size);
            do down--; while (this.digits[down] > pivot && down >= 1);
            if (up < down) swap(up, down);
        } while (up < down);

        return down;
        */
    }

    public void quickSort_withRandom(int left, int right) {
        if (left < right) {
            int randomPivot = randomizedPartition(left, right);
            quickSort_withRandom(left, randomPivot - 1);
            quickSort_withRandom(randomPivot + 1, right);
        }
    }

    private int randomizedPartition(int left, int right) {
        Random random = new Random();

        int aIndex = random.nextInt(right - left + 1) + left;
        int a = this.digits[aIndex];
        int bIndex = random.nextInt(right - left + 1) + left;
        int b = this.digits[bIndex];
        int cIndex = random.nextInt(right - left + 1) + left;
        int c = this.digits[cIndex];

        int pivot;
        if (a <= b) {
            if (b <= c) pivot = bIndex;
            else { // b > c
                if (a <= c) pivot = cIndex;
                else pivot = aIndex;
            }
        } else { // b < a
            if (a <= c) pivot = aIndex;
            else { // c < a
                if (b <= c) pivot = cIndex;
                else pivot = bIndex;
            }
        }

        swap(pivot, right);
        return partition(left, right);
    }

    public void swap(int a, int b) {
        int temp = this.digits[a];
        this.digits[a] = this.digits[b];
        this.digits[b] = temp;
    }

    public void defaultNumberSetting() {
        this.digits[1] = 10;
        this.digits[2] = 1;
        this.digits[3] = 3;
        this.digits[4] = 9;
        this.digits[5] = 6;
        this.digits[6] = 2;
        this.digits[7] = 5;
        this.digits[8] = 8;
        this.digits[9] = 4;
        this.digits[10] = 7;

        this.size = 10;
    }

    public void storeNumberFromInputFile(String inputFileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        String line;
        while ((line = br.readLine()) != null) {
            StringTokenizer stk = new StringTokenizer(line, ", ");
            while (stk.hasMoreTokens()) {
                this.digits[++size] = Integer.parseInt(stk.nextToken());
            }
        }
        br.close();
    }

    public void storeSortedNumberToOutputFile(String outputFileName) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName));
        for (int i = 1; i <= this.size; i++) {
            if (i != this.size) {
                bw.write(this.digits[i] + ",");
            } else {
                bw.write(this.digits[i] + "\n");
            }
        }
        bw.close();
    }

    public void printDigits() {
        for (int i = 1; i <= this.size; i++) {
            System.out.print(this.digits[i] + " ");
        }
        System.out.println();
    }

    public int getSize() {
        return this.size;
    }
}
