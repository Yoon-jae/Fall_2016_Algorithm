import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by yoonjae on 10/11/2016.
 */
public class Main {

    public static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("graphData.txt"));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int numOfVertex = Integer.parseInt(stk.nextToken());
        int numOfEdge = Integer.parseInt(stk.nextToken());
        char startPoint = stk.nextToken().charAt(0);

        System.out.println("numOfVertex : " + numOfVertex);
        System.out.println("numOfEdge : " + numOfEdge);
        System.out.println("startPoint : " + startPoint);
        System.out.println();

        int[][] w = new int[numOfVertex + 1][numOfVertex + 1];
        boolean[] visited = new boolean[numOfVertex + 1];

        initValue(numOfVertex, w, visited);

        inputWeightedGraph(br, numOfEdge, w);

        int mstCost = getMinimumSpanningTree(numOfVertex, startPoint, w, visited);

        System.out.println("\nw<MST> : " + mstCost);


    }

    private static int getMinimumSpanningTree(int numOfVertex, char startPoint, int[][] w, boolean[] visited) {
        MinHeap minHeap = new MinHeap();
        minHeap.add(new Edge(0, charToNum(startPoint), 0));

        int edgeCount = 0;
        int mstCost = 0;

        while (edgeCount != numOfVertex) {
            Edge edge = minHeap.poll();
            if (visited[edge.to]) continue;

            visited[edge.to] = true;
            edgeCount++;
            mstCost += edge.weight;
            System.out.printf("w<%c, %c> = %d\n", numToChar(edge.from), numToChar(edge.to), edge.weight);

            for (int i = 1; i <= numOfVertex; i++) {
                int dist = w[edge.to][i];
                if (isAdjacency(dist)) {
                    minHeap.add(new Edge(edge.to, i, dist));
                }
            }
        }
        return mstCost;
    }

    private static void inputWeightedGraph(BufferedReader br, int numOfEdge, int[][] w) throws IOException {
        StringTokenizer stk;
        for (int i = 1; i <= numOfEdge; i++) {
            stk = new StringTokenizer(br.readLine());

            char from = stk.nextToken().charAt(0);
            char to = stk.nextToken().charAt(0);
            int weight = Integer.parseInt(stk.nextToken());

            w[charToNum(from)][charToNum(to)] = weight;
            w[charToNum(to)][charToNum(from)] = weight;
            System.out.printf("Edge[%2d] : %c, %c, %d\n", i, from, to, weight);
        }
        System.out.println();
    }

    private static void initValue(int numOfVertex, int[][] w, boolean[] visited) {
        for (int i = 1; i <= numOfVertex; i++) {
            for (int j = 1; j <= numOfVertex; j++)
                w[i][j] = (i == j) ? 0 : INF;
            visited[i] = false;
        }
    }

    private static boolean isAdjacency(int dist) {
        return dist != 0 && dist != INF;
    }

    /**
     * Index number to char function.
     * <p>
     * Eg)
     * 1 -> A
     * 2 -> B
     *
     * @param num
     * @return
     */
    public static char numToChar(int num) {
        return (char) (num + '0' + 16);
    }


    /**
     * Char to index number function.
     * <p>
     * Eg)
     * A -> 1
     * B -> 2
     *
     * @param ch
     * @return
     */
    public static int charToNum(char ch) {
        return ch - '0' - 16;
    }
}
