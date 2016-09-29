/**
 * Created by yoonjae on 9/29/16.
 */
public class MaxHeap {

    public static final int MAX_HEAP_SIZE = 1001;
    public static final int ROOT = 1;

    private int size;
    private Node[] maxHeap;

    public MaxHeap() {
        this.size = 0;
        this.maxHeap = new Node[MAX_HEAP_SIZE];
    }

    /**
     * Store element from input text file.
     *
     * @param key
     * @param value
     */
    public void addElementAsRaw(int key, String value) {
        this.size++;
        this.maxHeap[size] = new Node(key, value);
    }

    /**
     * Store element at last node from stdin and max heapify it to top.
     *
     * @param key
     * @param value
     */
    public void addElement(int key, String value) {
        this.size++;
        this.maxHeap[size] = new Node(key, value);
        maxHeapifyToTop(size);
    }

    public Node getMax() {
        return this.maxHeap[ROOT];
    }

    /**
     * Extract max node element and max heapify ROOT element.
     *
     * @return Max node element
     */
    public Node extractMax() {
        Node maxNode = this.maxHeap[ROOT];
        swapNode(ROOT, this.size--);
        maxHeapify(ROOT);
        return maxNode;
    }

    /**
     * Increasing key value in maxHeap[index].
     *
     * @param index
     * @param newKey
     */
    public void increaseKey(int index, int newKey) {
        this.maxHeap[index].setKey(newKey);
        maxHeapifyToTop(index);
    }

    /**
     * Delete maxHeap[index] and max heapify.
     *
     * @param index
     */
    public void deleteNode(int index) {
        swapNode(index, this.size--);
        maxHeapify(index);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getKey(int index) {
        return this.maxHeap[index].getKey();
    }

    public String getValue(int index) {
        return this.maxHeap[index].getValue();
    }

    /**
     * Find bigger child and swap with current node until proper position.
     *
     * @param index
     */
    public void maxHeapify(int index) {
        int leftChild = index * 2;
        int rightChild = index * 2 + 1;
        int biggerChild = index;

        if(leftChild <= this.size && maxHeap[leftChild].compareTo(maxHeap[index]) > 0)
            biggerChild = leftChild;
        if(rightChild <= this.size && maxHeap[rightChild].compareTo(maxHeap[biggerChild]) > 0)
            biggerChild = rightChild;

        if(biggerChild != index) {
            swapNode(index, biggerChild);
            maxHeapify(biggerChild);
        }

    }

    /**
     * Compare with parent node and swap with current node until proper position.
     *
     * @param index
     */
    public void maxHeapifyToTop(int index) {
        while((index > ROOT) && (this.maxHeap[index].compareTo(this.maxHeap[index/2])) > 0) {
            swapNode(index, index/2);
            index /= 2;
        }
    }

    /**
     * Start max heapify from maxHeap[size/2] to ROOT.
     *
     */
    public void buildMaxHeap() {
        for(int i = this.size/2; i >= 1; i--)
            maxHeapify(i);
    }

    private void swapNode(int a, int b) {
        Node temp;
        temp = this.maxHeap[a];
        this.maxHeap[a] = this.maxHeap[b];
        this.maxHeap[b] = temp;
    }

    public void printHeap() {
        System.out.println("**** 현재 우선순위 큐에 저장 되어있는 목록은 다음과 같습니다 ****\n");
        System.out.println("현재 원소의 개수 : " + this.size + "\n");
        for(int i=1; i<=this.size; i++) {
            System.out.println(this.maxHeap[i]);
        }
        System.out.println();
        printHeapInTreeStructure(ROOT, 0);
    }

    public void printHeapInTreeStructure(int index, int l) {
        if(index > size) return;
        printHeapInTreeStructure(index*2+1, l+1);
        for(int i=0; i<l; i++)
            System.out.print("      ");
        System.out.printf("%3d\n",this.maxHeap[index].getKey());
        printHeapInTreeStructure(index*2, l+1);
    }
}
