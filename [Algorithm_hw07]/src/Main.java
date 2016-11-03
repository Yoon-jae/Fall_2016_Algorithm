import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
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

        initDistAndWeightArray(INF, numOfVertex, startPoint, d, w);

        for (int i = 1; i <= numOfEdge; i++) {
            stk = new StringTokenizer(br.readLine());
            char from = stk.nextToken().charAt(0);
            char to = stk.nextToken().charAt(0);
            int weight = Integer.parseInt(stk.nextToken());
            w[charToNum(from)][charToNum(to)] = weight;
            System.out.println("Edge[" + i + "] : " + from + ", " + to + ", " + weight);
        }

        System.out.println();
        System.out.println("Result of Dijkstra algorithm...\n");

        PriorityQueue<Edge> pQueue = new PriorityQueue<>();
        pQueue.add(new Edge(startPoint, 0));

        while (!pQueue.isEmpty()) {
            Edge edge = pQueue.poll();
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

        for (int i = 1; i <= numOfVertex; i++)
            System.out.printf("d[%c] : %d\n", numToChar(i), d[i]);

        br.close();
    }

    private static void initDistAndWeightArray(int INF, int numOfVertex, char startPoint, int[] d, int[][] w) {
        for (int i = 1; i <= numOfVertex; i++) {
            if (i == charToNum(startPoint)) continue;
            d[i] = INF;
        }

        for (int i = 1; i <= numOfVertex; i++) {
            for (int j = 1; j < numOfVertex; j++) {
                w[i][j] = INF;
            }
        }
    }

    public static char numToChar(int num) {
        return (char) (num + '0' + 16);
    }

    public static int charToNum(char ch) {
        return ch - '0' - 16;
    }
}

class Edge implements Comparable<Edge> {
    char to;
    int weight;

    public Edge(char to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "to=" + to +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Edge edge) {
        if (this.weight < edge.weight)
            return -1;
        else return 1;
    }
}