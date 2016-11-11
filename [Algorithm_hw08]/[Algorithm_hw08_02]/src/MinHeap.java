/**
 * Created by yoonjae on 11/11/2016.
 */
public class MinHeap {

    public static final int MIN_HEAP_SIZE = 1001;
    public static final int ROOT = 1;

    private int size;
    private Trecord[] minHeap;

    public MinHeap() {
        this.size = 0;
        this.minHeap = new Trecord[MIN_HEAP_SIZE];
    }

    public void add(Trecord node) {
        this.size++;
        this.minHeap[this.size] = node;
        minHeapifyToTop(this.size);
    }

    public Trecord poll() {
        Trecord top = minHeap[ROOT];
        swapEdge(ROOT, this.size--);
        minHeapify(ROOT);
        return top;
    }

    public boolean isEmpty() {
        if (this.size == 0)
            return true;
        else return false;
    }

    public int size() {
        return this.size;
    }

    private void minHeapify(int index) {
        int leftChild = index * 2;
        int rightChild = index * 2 + 1;
        int smallerChild = index;

        if (leftChild <= this.size && minHeap[leftChild].compareTo(minHeap[index]) < 0)
            smallerChild = leftChild;
        if (rightChild <= this.size && minHeap[rightChild].compareTo(minHeap[smallerChild]) < 0)
            smallerChild = rightChild;

        if (smallerChild != index) {
            swapEdge(index, smallerChild);
            minHeapify(smallerChild);
        }
    }

    private void minHeapifyToTop(int index) {
        while ((index > ROOT) && (this.minHeap[index].compareTo(this.minHeap[index / 2])) < 0) {
            swapEdge(index, index / 2);
            index /= 2;
        }
    }

    private void swapEdge(int a, int b) {
        Trecord temp;
        temp = this.minHeap[a];
        this.minHeap[a] = this.minHeap[b];
        this.minHeap[b] = temp;
    }
}
