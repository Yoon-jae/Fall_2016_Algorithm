import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by yoonjae on 9/29/16.
 */
public class Main {

    public static final int MAX_HEAP_SIZE = 1000;

    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("data03.txt"));
        Scanner input = new Scanner(System.in);

        MaxHeap maxHeap = new MaxHeap();

        storeElementFromInputFile(br, maxHeap);

        maxHeap.buildMaxHeap();
        maxHeap.printHeap();

        printMannual();

        int command = input.nextInt();
        input.nextLine();
        while(command != 6) {
            switch (command) {
                    
                /*
                * 1. Add element(Key, Value).
                * 2. Get max element.
                * 3. Extract max element.
                * 4. Increase value in node[x].
                * 5. Delete node[x].
                * 6. Exit program.
                */

                // Add element(Key, value);
                case 1:
                    System.out.print("과목 : ");
                    String subject = input.nextLine();

                    System.out.print("우선순위 값 (1~999) :");
                    int key = input.nextInt();

                    maxHeap.addElement(key, subject);
                    System.out.println(key + ", " + subject + " 가 추가 되었습니다");
                    break;

                // Get max element();
                case 2:
                    System.out.println("현재 최대 원소 : " + maxHeap.getMax());
                    break;

                // Extract max element();
                case 3:
                    System.out.println("최대 원소 " + "(" + maxHeap.extractMax() + ")가 삭제되었습니다. ");
                    break;

                // Increase key in maxHeap[x]
                case 4:
                    System.out.print("증가시킬 과목의 인덱스 : ");
                    int index = input.nextInt();
                    int oldKey = maxHeap.getKey(index);

                    System.out.printf("새로운 우선순위 값 (%d 이상) : ",maxHeap.getKey(index));
                    int newKey = input.nextInt();

                    maxHeap.increaseKey(index, newKey);

                    System.out.printf("수정 완료 (%d -> %d, %s)\n",oldKey,newKey,maxHeap.getValue(index));
                    break;

                // Delete node in maxHeap[x]
                case 5:
                    System.out.printf("삭제시킬 과목의 인데스 (%d 이하) : ",maxHeap.getSize());
                    index = input.nextInt();
                    maxHeap.deleteNode(index);
                    break;

                default:
                    break;
            }
            maxHeap.printHeap();

            printMannual();
            command = input.nextInt();
        }
    }

    private static void printMannual() {
        System.out.println("==============================================");
        System.out.println("1. 작업 추가    2. 최대값    3. 최대 우선순위 작업 처리");
        System.out.println("4. 원소 키값 증가    5. 작업 제거    6.종료");
        System.out.println("==============================================");
    }

    private static void storeElementFromInputFile(BufferedReader br, MaxHeap maxHeap) throws IOException {
        String line;
        StringTokenizer stk;
        while((line = br.readLine()) != null) {
            stk = new StringTokenizer(line, ", ");
            while(stk.hasMoreTokens()) {
                int key = Integer.parseInt(stk.nextToken());
                String subject = stk.nextToken();
                while(stk.hasMoreTokens()) {
                    subject += " " + stk.nextToken();
                }
                maxHeap.addElementAsRaw(key, subject);
            }
        }
    }
}
