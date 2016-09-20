#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAX(a,b) (a) >= (b)? (a):(b)
#define MIN(a,b) (a) < (b)? (a):(b)

int isdigit(char c);
int readint(char c, FILE * fp);
double timediff(clock_t start, clock_t end);
void merge_sort(int * a, int i, int j);
void merge(int * a, int i, int j, int mid);

int main()
{
    //char * input_file_name = "./input/data02.txt";
    char * input_file_name = "./input/in.txt";
    char * output_file_name = "hw01_01_201202154_merge.txt";

    FILE * ifp = fopen(input_file_name,"rt");
    FILE * ofp = fopen(output_file_name,"wt");

    char ch;

    int * digit_array;
    int digit, index = 1, size = 10;

    clock_t start, end;
    double elapsed = 0;

    if(ifp == NULL || ofp == NULL) {
        printf("File I/O error..\n");
        return 0;
    }

    digit_array = (int *)malloc(sizeof(int) * size);

    /* File input part */
    printf("\nInput file from %s ..\n",input_file_name);

    while((ch = fgetc(ifp)) != EOF) {
        digit = readint(ch,ifp);
        digit_array[index++] = digit;
        if(index == size-1) {
            size *= 2;
            digit_array = (int *)realloc(digit_array, sizeof(int) * size);
        }
        if(index%10000==0) printf("%d\n",index);
    }

    puts("\n\nNow!! We're gonna merge_sort");

    /* Merge sort */
    merge_sort(digit_array, 1, index-1);

    puts("S O R T E D");
    for(int i=1; i<index; i++) {
        printf("%d ",digit_array[i]);
    }
    puts("");

    /* File output part */
    for(int i=1; i<index; i++) {
        if(i != index-1) {
            fprintf(ofp, "%d,",digit_array[i]);
        } else fprintf(ofp, "%d\n",digit_array[i]);
    }

    puts("\nComplete merge_sort !!\n");
    puts("=========================================\n");
    printf("Elapsed time for sorting : %.5fsec\n",elapsed);
    puts("\n=========================================\n");
    printf("Write in %s \n",output_file_name);
    puts("");

    free(digit_array);

    fclose(ifp);
    fclose(ofp);

    return 0;
}

int isdigit(char c)
{
    if('0' <= c && c <= '9') {
        return 1;
    } else return 0;
}

int readint(char c, FILE * fp)
{
    int digit = c - '0';
    char ch;
    while(isdigit(ch = fgetc(fp))) {
        digit = digit * 10 + ch - '0';
    }
    return digit;
}

double timediff(clock_t start, clock_t end)
{
    return (double)(end - start) / CLOCKS_PER_SEC;
}

void merge_sort(int * a, int i, int j)
{
    int mid = (i+j) / 2;
    printf("start,mid,end : %d,%d,%d\n",i,mid,j);


    if(i < j) {
        merge_sort(a, i, mid);

        merge_sort(a, mid + 1, j);

        merge(a, i, j, mid);
    }
}

void merge(int * a, int i, int j, int mid) {

    int * new = (int *)malloc(sizeof(int) * (j-i+1));

    int np = i;
    int fp = i;
    int sp = mid + 1;

    puts("Before merge");
    for(int idx = i; idx<=j; idx++)
        printf("a[%d] : %d\n",idx,a[idx]);
    puts("====================");

    printf("fp,sp,np : %d,%d,%d\n",fp,sp,np);
    if(fp == mid && sp == j) {
        puts("Size 1 element");
        new[fp] = MIN(a[fp],a[sp]);
        new[sp] = MAX(a[fp],a[sp]);
    } else {
        while(fp != mid+1 || sp != j+1) {
            printf("While loop start -> fp,sp,np : %d,%d,%d\n",fp,sp,np);
            if(fp == mid + 1) 
                new[np++] = a[sp++];
            else if(sp == j + 1) 
                new[np++] = a[fp++];
            else {
                if(a[fp] <= a[sp]) 
                    new[np++] = a[fp++];
                else 
                    new[np++] = a[sp++];
            }
            printf("While loop end -> fp,sp,np : %d,%d,%d\n",fp,sp,np);
        }
    }

    for(int idx = j; idx >= i; idx--)
        a[idx] = new[idx];

    free(new);

    puts("After merge");
    for(int idx = i; idx <= j; idx++)
        printf("a[%d] : %d\n",idx,a[idx]);
    puts("====================");
}
