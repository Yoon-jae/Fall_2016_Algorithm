#include <stdio.h>
#include <stdlib.h>

int isdigit(char c);
int readint(char c, FILE * fp);
void insertion_sort(int * arr, int n);

int main()
{
    FILE * ifp = fopen("./input/hw02_10man.txt","r");
    FILE * ofp = fopen("output.txt","w");

    char ch;

    int * digit_array;
    int digit, index = 0, size = 10;
 
    digit_array = (int *)malloc(sizeof(int) * size);

    while((ch = fgetc(ifp)) != EOF) {
        digit = readint(ch,ifp);
        digit_array[index++] = digit;
        if(index == size-1) {
            size *= 2;
            digit_array = (int *)realloc(digit_array, sizeof(int) * size);
            printf("Realloc! now size is %d\n",size);
        }
        if(index%10000==0) printf("%d\n",index);
    }

    insertion_sort(digit_array, index-1);

    for(int i=0; i<index; i++) {
        if(i != index-1) {
            fprintf(ofp, "%d, ",digit_array[i]);
        } else fprintf(ofp, "%d\n",digit_array[i]);
    }


    puts("");
    
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
        digit = digit*10 + ch - '0';
    }
    return digit;
}

void insertion_sort(int * a, int n)
{
    int i,j,key;
    puts("We're gonna insertion_sort");

    for(int j=2; j<=n; j++) {
        key = a[j];
        i = j-1;
        while(i > 0 && a[i] > key) {
            a[i+1] = a[i];
            i--;
        }
        a[i+1] = key;
    }
}
