/**
 * Created by yoonjae on 9/29/16.
 */
public class Node implements Comparable<Node>
{
    private int key;
    private String value;

    public Node(int key, String value)
    {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return  key + ", " + value;
    }

    @Override
    public int compareTo(Node node) {
        if(this.key >= node.key) return 1;
        else return -1;
    }
}