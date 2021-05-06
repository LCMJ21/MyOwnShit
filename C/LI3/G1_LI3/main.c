#include "files.h"

int main() {

    //Le o primeiro ficheiro
    FILE *f1 =  fopen("users.csv","r");

    char *buffer = malloc(MAX_STRING);

    int i_f1 = 0;
    size_t str_f1_sz = INIT_STRUCT_SIZE;
    Users str_f1 = malloc(str_f1_sz * sizeof(struct user));

    while (fgets(buffer,MAX_STRING,f1)){
        strcpy(str_f1[i_f1++].string,buffer);
        //printf("%d %s",i_f1-1,str_f1[i_f1-1].string);
        if (i_f1 == str_f1_sz) {
            str_f1_sz*=2;
            str_f1 = realloc(str_f1,str_f1_sz * sizeof(struct user));
        }
    }

    //Le o segundo ficheiro
    char *buffer2 = malloc(MAX_STRING2);

    int i_f2 = 0;
    size_t str_f2_sz = INIT_STRUCT_SIZE;
    Businesses str_f2 = malloc(str_f2_sz * sizeof(struct business));

    FILE *f2  =  fopen("business.csv","r");

    while (fgets(buffer2,MAX_STRING2,f2)){
        sscanf(buffer2,"%[^;];%[^;];%[^;];%lf;%d",str_f2[i_f2].string1,str_f2[i_f2].string2,str_f2[i_f2].string3,&(str_f2[i_f2].x),&(str_f2[i_f2].y));
        //printf("%d - %s**%s**%s**%lf**%d\n",i_f2,str_f2[i_f2].string1,str_f2[i_f2].string2,str_f2[i_f2].string3,(str_f2[i_f2].x),(str_f2[i_f2].y));
        i_f2++;
        if (i_f2 == str_f2_sz) {
            str_f2_sz*=2;
            str_f2 = realloc(str_f2,str_f2_sz * sizeof(struct business));
        }
    }

    //Le o terceiro ficheiro
    FILE *f3 =  fopen("reviews.csv","r");

    int i_f3 = 0;
    size_t str_f3_sz = INIT_STRUCT_SIZE;
    Reviews str_f3 = malloc(str_f3_sz * sizeof(struct review));

    while (fgets(buffer2,MAX_STRING2,f3)){
        sscanf(buffer2,"%[^;];%[^;];%[^;];%lf;%d;%d;%d",str_f3[i_f3].string1,str_f3[i_f3].string2,
               str_f3[i_f3].string3,&(str_f3[i_f3].x),&(str_f3[i_f3].y1),&(str_f3[i_f3].y2),&(str_f3[i_f3].y3));
        //printf("%d  %s**%s**%s**%lf**%d**%d**%d\n",i_f3,str_f3[i_f3].string1,str_f3[i_f3].string2,str_f3[i_f3].string3,(str_f3[i_f3].x),(str_f3[i_f3].y1),(str_f3[i_f3].y2),(str_f3[i_f3].y3));
        i_f3++;
        if (i_f3 == str_f3_sz) {
            str_f3_sz*=2;
            str_f3 = realloc(str_f3,str_f3_sz * sizeof(struct review));
        }
    }

    printf("%d businesses tem reviews\n",qBusRev(str_f2,i_f2));
    printf("%d businesses tem 4 ou mais estrelas\n",fourOrMore(str_f2,i_f2));
    int maisR = maxReviews(str_f2,i_f2);
    printf("%s e o businesses com mais reviews com %d\n",str_f2[maisR].string2,str_f2[maisR].y);




    return 0;
}
