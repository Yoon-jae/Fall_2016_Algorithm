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
int get_command();

int is_empty();
int is_full();

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

    build_max_heap();
    print_heap();

    command = get_command();

    while(command != 6) {
        switch(command) {
            /*
               printf("1. Add elements(Key, Value)\n");
               printf("2. Get max element\n");
               printf("3. Extract max element\n");
               printf("4. Increase value in node[x]\n");
               printf("5. Delete node[x]\n");
               printf("6. Exit\n");
             */
            case 1:
                if(is_full())
                    puts("#####  Heap is full now. #####");
                else insert();
                break;

            case 2:
                if(is_empty())
                    puts("#####  Heap is empty now. #####");
                else max();
                break;

            case 3:
                if(is_empty()) 
                    puts("#####  Heap is empty now. #####");
                else {
                    priority_queue max_element = extract_max();
                    printf("\n#####  Max element (Key, value) : (%s, %d) #####\n",max_element.key,max_element.value);
                }
                break;

            case 4:
                printf("Node number : ");
                scanf("%d",&x);
                if(x > size) {
                    printf("#####  node[%d] is not in here. #####\n",x);
                    break;
                }
                printf("New value of node[%d] (more than %d): ", x, pq[x].value);
                scanf("%d",&value);

                increase_value(x, value);
                break;

            case 5:
                printf("Node number : ");
                scanf("%d",&x);
                if(x > size) {
                    printf("#####  node[%d] is not in here. #####\n",x);
                    break;
                }
                delete(x);
                break;

            case 6:
                puts("Exit program..");
                exit(1);
                break;

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

void build_max_heap() {
    for(int i = size/2; i >= 1; i--)
        max_heapify(i);
}

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
}

void max() {
    printf("\n#####  Max element's (Key, value) : (%s, %d) #####\n", pq[size].key,
            pq[size].value);
}

priority_queue extract_max() {
    priority_queue element = pq[ROOT];
    swap_pq(&pq[ROOT], &pq[size--]);

    max_heapify(ROOT);

    return element;
}

void increase_value(int x, int value) {
    pq[x].value = value;
    while((x > ROOT) && (pq[x].value > pq[x/2].value)) {
        swap_pq(&pq[x], &pq[x/2]);
        x /= 2;
    }
}

void delete(int x) {
    swap_pq(&pq[x], &pq[size]);
    size--;
    max_heapify(x);
    printf("#####  Completely deleted node[%d] #####\n",x);
}

void swap_pq(priority_queue * a, priority_queue * b) {
    priority_queue temp;
    temp = *a;
    *a = *b;
    *b = temp;
}

void print_heap() {
    puts("#####  Here is current elements in Max_heap. #####\n");
    printf("Current heap size : %d\n\n",size);
    puts("node[##] : (Key, Value) -> (leftChild, rightChild)");
    for(int i=1; i<=size; i++) {
        if(i*2+1 <= size)
            printf("node[%2d] : (%s , %d) -> (%d , %d)\n",i,pq[i].key,pq[i].value,pq[i*2].value,pq[i*2+1].value);
        else if(i*2 <= size)
            printf("node[%2d] : (%s , %d) -> (%d)\n",i,pq[i].key,pq[i].value,pq[i*2].value);
        else
            printf("node[%2d] : (%s , %d)\n",i,pq[i].key,pq[i].value);
    }
}

int get_command() {
    int command;
    puts("=============================");
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
