import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by yoonjae on 03/11/2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("graphData.txt"));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        final int INF = Integer.MAX_VALUE;

        int numOfVertex = Integer.parseInt(stk.nextToken());
        int numOfEdge = Integer.parseInt(stk.nextToken());
        char startPoint = stk.nextToken().charAt(0);

        System.out.println("numOfVertex : " + numOfVertex);
        System.out.println("numOfEdge : " + numOfEdge);
        System.out.println("startPoint : " + startPoint);

        int[] d = new int[numOfVertex + 1];
        int[][] w = new int[numOfVertex + 1][numOfVertex + 1];
        boolean[] visited = new boolean[numOfVertex + 1];

        initValue(INF, numOfVertex, startPoint, d, w, visited);

        inputWeightedGraph(br, numOfEdge, w);

        System.out.println("\nResult of Dijkstra algorithm...\n");

        dijkstra(INF, numOfVertex, startPoint, d, w, visited);

        printDist(numOfVertex, d);

        br.close();
    }

    private static void dijkstra(int INF, int numOfVertex, char startPoint, int[] d, int[][] w, boolean[] visited) {
        MinHeap pQueue = new MinHeap();
        pQueue.add(new Edge(startPoint, 0));

        while (!pQueue.isEmpty()) {
            Edge edge = pQueue.poll();
            if(visited[charToNum(edge.to)]) continue;
            visited[charToNum(edge.to)] = true;
            int here = charToNum(edge.to);
            System.out.println("S <- " + edge);
            for (int to = 1; to <= numOfVertex; to++) {
                if (w[here][to] != 0 && w[here][to] != INF) { // found adjacency node
                    if (d[to] > d[here] + w[here][to]) {
                        System.out.printf("Update d[%c] : %d -> %d\n", numToChar(to), d[to], d[here] + w[here][to]);
                        d[to] = d[here] + w[here][to];
                        pQueue.add(new Edge(numToChar(to), d[to]));
                    }
                }
            }
            System.out.println();
        }
    }

    private static void initValue(int INF, int numOfVertex, char startPoint, int[] d, int[][] w, boolean[] visited) {
        for (int i = 1; i <= numOfVertex; i++) {
            if (i == charToNum(startPoint)) continue;
            d[i] = INF;
        }

        for (int i = 1; i <= numOfVertex; i++) {
            for (int j = 1; j < numOfVertex; j++) {
                w[i][j] = INF;
            }
        }

        for (int i = 1; i<= numOfVertex; i++) {
            visited[i] = false;
        }
    }

    private static void inputWeightedGraph(BufferedReader br, int numOfEdge, int[][] w) throws IOException {
        StringTokenizer stk;
        for (int i = 1; i <= numOfEdge; i++) {
            stk = new StringTokenizer(br.readLine());
            char from = stk.nextToken().charAt(0);
            char to = stk.nextToken().charAt(0);
            int weight = Integer.parseInt(stk.nextToken());
            w[charToNum(from)][charToNum(to)] = weight;
            System.out.println("Edge[" + i + "] : " + from + ", " + to + ", " + weight);
        }
    }

    private static void printDist(int numOfVertex, int[] d) {
        for (int i = 1; i <= numOfVertex; i++)
            System.out.printf("d[%c] : %d\n", numToChar(i), d[i]);
    }

    /**
     * Index number to char function.
     *
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
     *
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
