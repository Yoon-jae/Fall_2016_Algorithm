/**
 * Created by yoonjae on 11/11/2016.
 */
public class Trecord implements Comparable<Trecord> {
    char alpha;
    int freq;
    Trecord left;
    Trecord right;

    public Trecord() {

    }

    public Trecord(char alpha, int freq) {
        this.alpha = alpha;
        this.freq = freq;
    }

    public Trecord(char alpha) {
        this.alpha = alpha;
    }

    @Override
    public String toString() {
        return "{" + "alpha=" + alpha +
                ", freq=" + freq +
                '}';
    }

    @Override
    public int compareTo(Trecord t) {
        if(this.freq < t.freq)
            return -1;
        else return 1;
    }
}
