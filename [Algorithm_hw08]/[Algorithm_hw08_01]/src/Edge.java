/**
 * Created by yoonjae on 11/11/2016.
 */
public class Edge implements Comparable<Edge> {
    int from, to;
    int weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge edge) {
        if(this.weight < edge.weight)
            return -1;
        else return 1;
    }
}
