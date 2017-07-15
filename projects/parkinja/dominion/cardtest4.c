/*-----------------------------------------------*/
// Test the Village_Efct Card function
/*-----------------------------------------------*/
#include "dominion.h"
#include <stdio.h>
#include <string.h>
#include "rngs.h"
#include <stdlib.h>

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
    //Test the Card Effect does what it says it does (+2 action count) 
    printf("[---------- cardtest4.c -----------]\n");
    genNum = rand() % 10;
    initializeGame(2, k, genNum, &G);     
    int actionCountBef = G.numActions;
    printf("actionCountBef: %d\n", actionCountBef);
    testRes = Village_Efct(&G, 0);    
    int actionCountAft  = G.numActions;
    printf("actionCountAft: %d\n", actionCountAft);

    if (actionCountAft == (actionCountBef+2)) {
        #if (MSG_PRINT == 1)
            printf("-->Test %d passed. cardEffect is accurate\n", testcount);
        #endif
        passNum = passNum+1;
    }
    else {
        printf("-->Test %d failed. Action count is off.\n", testcount);
    }        
    testcount = testcount+1;            

    /*------------------ Results ----------------*/    
    printf("%d tests passed.\n", passNum);
    return 0;
}