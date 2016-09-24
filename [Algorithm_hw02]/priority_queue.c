#include <stdio.h>
#include <stdlib.h> 

#define MAX_SIZE 1001 
#define ROOT 1

typedef struct _priority_queue {
    char key[100];
    int value;
} priority_queue;

/* File Input */
int is_digit(char ch);
int read_int(char ch, FILE * fp);

/* Max Heap */
void max_heapify(int i);
void build_max_heap();
void insert();
void max();
priority_queue extract_max();
void increase_value(int x, int value);
void delete(int x);

void swap_pq(priority_queue * a, priority_queue * b);
void print_heap();
void print_heap_in_tree(int node, int l);
int get_command();

int is_empty();
int is_full();

/* Clear console */
void clrscr();

/* Max heap using array */
priority_queue pq[MAX_SIZE];
int size;

int main() {

    char * input_file_name = "data03.txt";

    FILE * fp = fopen(input_file_name,"rt");

    char ch; 

    int i;
    int command;

    int x;
    int value;

    if(fp == NULL) {
        printf("File I/O error..\n");
        return 0;
    }

    size = 0;

    /* File Input part */
    while((ch = fgetc(fp)) != EOF) {

        if(ch == 10) continue;
        size++;

        /* Get value from file */
        pq[size].value = read_int(ch,fp);
        fgetc(fp);

        /* Get key from file */
        i = 0;
        while((ch = fgetc(fp)) != 10) 
            pq[size].key[i++] = ch;
        pq[size].key[i] = '\0';

    }

    /* Build max heap with input data from text file */
    build_max_heap();
    print_heap();

    command = get_command();
    while(command != 6) {
        puts("");
        switch(command) {

            /*
             * 1. Add element(Key, Value).
             * 2. Get max element.
             * 3. Extract max element.
             * 4. Increase value in node[x].
             * 5. Delete node[x].
             * 6. Exit program.
             */

            case 1:
                if(is_full()) {
                    clrscr();
                    puts("\n*****  Heap is full now. *****");
                } else insert();
                break;

            case 2:
                clrscr();
                if(is_empty())
                    puts("\n*****  Heap is empty now. *****");
                else max();
                break;

            case 3:
                clrscr();
                if(is_empty()) 
                    puts("\n*****  Heap is empty now. *****");
                else {
                    priority_queue max_element = extract_max();
                    printf("\n*****  Max element (%s, %d) deleted succesfully.  *****\n",max_element.key, max_element.value);
                }
                break;

            case 4:
                if(is_empty()) {
                    clrscr();
                    puts("\n*****  Heap is empty now.  *****");
                } else {
                    printf("Node number : ");
                    scanf("%d",&x);
                    if(x > size) {
                        clrscr();
                        printf("*****  node[%d] is not in here.  *****\n",x);
                        break;
                    }
                    printf("New value of node[%d] (more than %d): ", x, pq[x].value);
                    scanf("%d",&value);

                    if(value > pq[x].value)
                        increase_value(x, value);
                    else {
                        clrscr();
                        printf("\n*****  Error : It is less than %d !  *****\n",pq[x].value);
                    }
                }
                break;

            case 5:
                if(is_empty()) {
                    clrscr();
                    puts("\n*****  Heap is empty now.  *****");
                } else {
                    printf("Node number : ");
                    scanf("%d",&x);
                    if(x > size) {
                        clrscr();
                        printf("\n*****  node[%d] is not in here.  *****\n",x);
                        break;
                    }
                    delete(x);
                }
                break;

            case 6:
                puts("Exit program..");
                exit(1);

            default:
                break;
        }
        print_heap();
        command = get_command();
    }
}

int is_digit(char ch) {
    return ('0' <= ch && ch <= '9');
}

int read_int(char ch, FILE * fp) {
    int digit = ch - '0';
    char c;
    while(is_digit(c = fgetc(fp))) {
        digit = digit * 10 + c - '0';
    }
    return digit;
}

/*
 * Find bigger child value and swap element and trikling down while condition is true.
 *
 */

void max_heapify(int i) {
    int l = i * 2;
    int r = i * 2 + 1;
    int bigger_child = i;

    if(l <= size && pq[l].value > pq[i].value)
        bigger_child = l;
    if(r <= size && pq[r].value > pq[bigger_child].value)
        bigger_child = r;

    if(bigger_child != i) {
        swap_pq(&pq[i],&pq[bigger_child]);
        max_heapify(bigger_child);
    }
}

/*
 * Build max heap with input data from text file.
 *
 * Start from node[n/2], which has last node as a child.
 *
 */

void build_max_heap() {
    for(int i = size/2; i >= 1; i--)
        max_heapify(i);
}

/*
 * Insert an element with key, value at last node.
 *
 * And swap with parent while condition is true.
 *
 */

void insert() {
    int index = ++size;

    printf("Key(char size : 1 ~ 100) : ");
    scanf("%s",pq[index].key);
    printf("Value(1 ~ 1000) : ");
    scanf("%d",&pq[index].value);

    while((index > ROOT) && (pq[index].value > pq[index/2].value)) {
        swap_pq(&pq[index], &pq[index/2]);
        index /= 2;
    }
    clrscr();
    printf("\n*****  Added (%s, %d) to node[%d]  *****\n",pq[index].key, pq[index].value, index);
}

void max() {
    printf("\n*****  Max element is (%s, %d)  *****\n", pq[ROOT].key,
            pq[ROOT].value);
}

/*
 * Extract max element from heap and heapfiy root element.
 *
 */

priority_queue extract_max() {
    priority_queue element = pq[ROOT];
    swap_pq(&pq[ROOT], &pq[size--]);

    max_heapify(ROOT);

    return element;
}

/*
 * Increase value in node[x] and swap element with parent.
 *
 */

void increase_value(int x, int value) {
    clrscr();
    printf("\n***** node[%2d] : (%s, %d) changed to ",x, pq[x].key, pq[x].value);
    pq[x].value = value;
    while((x > ROOT) && (pq[x].value > pq[x/2].value)) {
        swap_pq(&pq[x], &pq[x/2]);
        x /= 2;
    }
    printf("node[%2d] : (%s, %d)  *****\n",x, pq[x].key, pq[x].value);
}

/*
 * Delete node[x] and swap with last element, then heapify node[x]
 *
 */ 

void delete(int x) {
    clrscr();
    printf("\n*****  Completely deleted node[%2d] : (%s, %d)  *****\n",x,pq[x].key,pq[x].value);
    swap_pq(&pq[x], &pq[size]);
    size--;
    max_heapify(x);
}

void swap_pq(priority_queue * a, priority_queue * b) {
    priority_queue temp;
    temp = *a;
    *a = *b;
    *b = temp;
}

void print_heap() {
    printf("\nCurrent heap size : %d\n\n",size);
    puts("###################################################\n");
    puts("node[##] : (Key, Value) -> (leftChild, rightChild)\n");
    puts("###################################################\n");
    for(int i=1; i<=size; i++) {
        if(i*2+1 <= size)
            printf("node[%2d] : (%s , %d) -> (%d , %d)\n",i,pq[i].key,pq[i].value,pq[i*2].value,pq[i*2+1].value);
        else if(i*2 <= size)
            printf("node[%2d] : (%s , %d) -> (%d)\n",i,pq[i].key,pq[i].value,pq[i*2].value);
        else
            printf("node[%2d] : (%s , %d)\n",i,pq[i].key,pq[i].value);
    }

    puts("\nDisplaying heap structure..");
    puts("\nROOT - - - - - - - - - - - - - - - - > \n");
    print_heap_in_tree(ROOT, 0);
}

/*
 * Print binary tree in console.
 *
 */

void print_heap_in_tree(int node, int l) {

    int i;

    if(node > size) return;

    print_heap_in_tree(node*2+1, l+1);
    for(i=0; i<l; i++) printf("     ");
    printf("%3d\n", pq[node].value);
    print_heap_in_tree(node*2, l+1);
}

int get_command() {
    int command;
    puts("\n=============================");
    puts("    Enter command key..\n");
    printf("1. Add elements(Key, Value)\n");
    printf("2. Get max element\n");
    printf("3. Extract max element\n");
    printf("4. Increase value in node[x]\n");
    printf("5. Delete node[x]\n");
    printf("6. Exit\n");
    puts("=============================");
    scanf("%d",&command);
    return command;
}

int is_empty() {
    return !size;
}

int is_full() {
    return (size == MAX_SIZE);
}

/*
 * Clear console.
 *
 */

void clrscr() {
    system("clear");
}
