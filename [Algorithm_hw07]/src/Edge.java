/**
 * Created by yoonjae on 04/11/2016.
 */

public class Edge implements Comparable<Edge> {
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