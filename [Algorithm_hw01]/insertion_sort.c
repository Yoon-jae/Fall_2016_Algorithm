#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int is_digit(char c);
int read_int(char c, FILE * fp);
double time_diff(clock_t start, clock_t end);
void insertion_sort(int * arr, int n);

int main()
{
    char * input_file_name = "./input/data02.txt";
    char * output_file_name = "hw01_01_201202154_insertion.txt";

    FILE * ifp = fopen(input_file_name,"rt");
    FILE * ofp = fopen(output_file_name,"wt");

    char ch;

    int * digit_array;
    int digit, index = 1, size = 10;
    
    if(ifp == NULL || ofp == NULL) {
        printf("File I/O error..\n");
        return 0;
    }

    digit_array = (int *)malloc(sizeof(int) * size);

    /* File input part */
    printf("\nInput file from %s ..\n",input_file_name);

    while((ch = fgetc(ifp)) != EOF) {
        digit = read_int(ch,ifp);
        digit_array[index++] = digit;
        if(index == size-1) {
            size *= 2;
            digit_array = (int *)realloc(digit_array, sizeof(int) * size);
        }
        if(index%10000==0) printf("%d\n",index);
    }

    /* Insertion sort */
    insertion_sort(digit_array, index-1);

    /* File output part */
    for(int i=1; i<index; i++) {
        if(i != index-1) {
            fprintf(ofp, "%d,",digit_array[i]);
        } else fprintf(ofp, "%d\n",digit_array[i]);
    }

    printf("Write in %s \n",output_file_name);
    puts("");

    free(digit_array);
    
    fclose(ifp);
    fclose(ofp);

    return 0;
}

int is_digit(char c)
{
    if('0' <= c && c <= '9') {
        return 1;
    } else return 0;
}

int read_int(char c, FILE * fp)
{
    int digit = c - '0';
    char ch;
    while(is_digit(ch = fgetc(fp))) {
        digit = digit * 10 + ch - '0';
    }
    return digit;
}

double time_diff(clock_t start, clock_t end)
{
    return (double)(end - start) / CLOCKS_PER_SEC;
}

void insertion_sort(int * a, int n)
{
    int i,j,key;
    clock_t start, end;
    double elapsed = 0;

    puts("\n\nNow!! We're gonna insertion_sort");

    for(int j=2; j<=n; j++) {

        start = clock();

        key = a[j];
        i = j-1;
        while(i > 0 && a[i] > key) {
            a[i+1] = a[i];
            i--;
        }
        a[i+1] = key;

        end = clock();

        elapsed += time_diff(start, end);

        if(j%10000 == 0)
            printf("Sorting..  %d\n",j);
    }

    puts("\nComplete insertion_sort !!\n");
    puts("=========================================\n");
    printf("Elapsed time for sorting : %.5fsec\n",elapsed);
    puts("\n=========================================\n");
}
