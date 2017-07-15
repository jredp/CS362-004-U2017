/*-----------------------------------------------*/
// Test the Treasure_Effect Card
/*-----------------------------------------------*/
#include "dominion.h"
#include <stdio.h>
#include <string.h>
#include "rngs.h"
#include <stdlib.h>
// #include <setjmp.h>
// #include <signal.h>

#define MSG_PRINT 1

int main () {

    int testRes = 0;
    int passNum = 0;
    int numPlayer = 2;                 
    int testcount = 1;        
    struct gameState G;    
    int genNum = 0;
    int k[10] = {feast, gardens, mine, adventurer, council_room, remodel, smithy, village, baron, great_hall};

    /*------------------TEST ------------------*/
    //Test the Card Effect does what it says it does
    printf("[---------- cardtest3.c -----------]\n");    
    genNum = rand() % 10;
    initializeGame(2, k, genNum, &G);     
    testRes = Treasure_Efct(&G, 0);

    if ((testRes == 1) || (testRes == -1)) {
        #if (MSG_PRINT == 1)
            printf("-->Test %d passed. Function validated\n", testcount);
        #endif
        passNum = passNum+1;
    }
    else {
        printf("-->Test %d failed. Function failed to respond normally.\n", testcount);
    }        
    testcount = testcount+1;            

    /*------------------ Results ----------------*/    
    printf("%d tests passed.\n", passNum);
    return 0;
}