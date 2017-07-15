/*-----------------------------------------------*/
// Test the Adventurer Card
/*-----------------------------------------------*/
#include "dominion.h"
#include <stdio.h>
#include <string.h>
#include "rngs.h"
#include <stdlib.h>
#include "assert.h"

#define MSG_PRINT 1

int main () {

    int testRes = 0;
    int passNum = 0;
    int numPlayer = 2;                 
    int testcount = 1;        
    struct gameState G;    
    int genNum = 0;
    int k[10] = {feast, gardens, mine, adventurer, council_room, remodel, smithy, village, baron, great_hall};

    /*------------------TEST 1 ------------------*/
    //Test the Card Effect does what it says it does (dig)
    printf("[---------- cardtest2.c -----------]\n");    
    genNum = rand() % 10;
    initializeGame(2, k, genNum, &G);     
    testRes = Adventurer_Efct(&G);

    if (testRes == 0) {
        #if (MSG_PRINT == 1)
            printf("-->Test %d passed. cardEffect is accurate\n", testcount);
        #endif
        passNum = passNum+1;
    }
    else {
        printf("-->Test %d failed. Did not draw Cards accurately.\n", testcount);
    }        
    testcount = testcount+1;            

    /*------------------ Results ----------------*/    
    printf("%d tests passed.\n", passNum);
    return 0;
}