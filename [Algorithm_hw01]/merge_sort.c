#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int is_digit(char c);
int read_int(char c, FILE * fp);
double time_diff(clock_t start, clock_t end);
void merge_sort(int * a, int i, int j);
void merge(int * a, int i, int mid, int j);

int merge_count;

int main()
{
    char * input_file_name = "data02.txt";
    char * output_file_name = "hw01_01_201202154_merge.txt";

    FILE * ifp = fopen(input_file_name,"rt");
    FILE * ofp = fopen(output_file_name,"wt");

    char ch;

    int * digit_array;
    int digit, index = 1, size = 10;

    int i;

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
        digit = read_int(ch,ifp);
        digit_array[index++] = digit;
        if(index == size-1) {
            size *= 2;
            digit_array = (int *)realloc(digit_array, sizeof(int) * size);
        }
        if(index%10000==0) printf("%d\n",index);
    }

    puts("\n\nNow!! We're gonna merge_sort");

    merge_count = 0;

    start = clock();

    /* Merge
     * sort
     * */
    merge_sort(digit_array, 1, index-1);

    end = clock();

    elapsed = time_diff(start,end);

    /* File
     * output
     * part
     * */
    for(i=1; i<index; i++) {
        if(i != index-1) {
            fprintf(ofp, "%d,",digit_array[i]);
        } else fprintf(ofp, "%d\n",digit_array[i]);
    }

    puts("\nComplete merge_sort !!\n");
    puts("=========================================\n");
    printf("Elapsed time for sorting : %.5fsec\n",elapsed);
    printf("Merge count : %d\n",merge_count);
    puts("\n=========================================\n");
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

void merge_sort(int * a, int i, int j)
{
    int mid = (i+j) / 2;

    if(i < j) {
        merge_sort(a, i, mid);
        merge_sort(a, mid + 1, j);
        merge(a, i, mid, j);
    }
}

void merge(int * a, int i, int mid, int j) {

    int idx;

    /* Array pointer value */
    int lp = 0, rp = 0, ap = i;

    int left_size = mid - i + 1;
    int right_size = j - mid;

    int * left = (int *)malloc(sizeof(int) * left_size);
    int * right = (int *)malloc(sizeof(int) * right_size);

    merge_count++;

    /* Copy element */
    for(idx = 0; idx < left_size; idx++)
        left[idx] = a[i + idx];
    for(idx = 0; idx < right_size; idx++)
        right[idx] = a[mid + 1 + idx];

    /* Compare left, right array's
     * element */
    while(lp < left_size && rp < right_size) {
        if(left[lp] <= right[rp])
            a[ap++] = left[lp++];
        else
            a[ap++] = right[rp++];
    }

    /* Managing remain elements */ 
    while(lp < left_size)
        a[ap++] = left[lp++];
    while(rp < right_size)
        a[ap++] = right[rp++];

    free(left);
    free(right);
}

