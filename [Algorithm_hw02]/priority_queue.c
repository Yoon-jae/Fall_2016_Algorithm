#include <stdio.h>
#include <stdlib.h> 
#define MAX_SIZE 301

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
void insert(priority_queue * x);
void max();
int  extract_max();
void increase_key(int x, int k);
void delete(int x);

void swap_pq(priority_queue * a, priority_queue * b);
void printHeap();

priority_queue pq[MAX_SIZE];
int size;

int main() {
    char * input_file_name = "data03.txt";

    FILE * fp = fopen(input_file_name,"rt");

    char ch; 
    int i,j;

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

        //printf("[%2d] key,value : (%s,%d)\n",size,pq[size].key,pq[size].value);
    }


    build_max_heap();
    printHeap();
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

void insert(priority_queue * x);

void max() {
    printf("Max element's (Key, value) : (%s, %d)", pq[size].key,
            pq[size].value);
}

int extract_max();

void increase_key(int x, int k);

void delete(int x);

void swap_pq(priority_queue * a, priority_queue * b) {
    priority_queue temp;
    temp = *a;
    *a = *b;
    *b = temp;
}

void printHeap() {
    printf("node[##] : (Key, Value) -> (leftChild, rightChild)\n");
    for(int i=1; i<=size; i++) {
        if(i*2+1 <= size)
            printf("node[%2d] : (%s , %d) -> (%d , %d)\n",i,pq[i].key,pq[i].value,pq[i*2].value,pq[i*2+1].value);
        else if(i*2 <= size)
            printf("node[%2d] : (%s , %d) -> (%d)\n",i,pq[i].key,pq[i].value,pq[i*2].value);
        else
            printf("node[%2d] : (%s , %d)\n",i,pq[i].key,pq[i].value);
    }
}
