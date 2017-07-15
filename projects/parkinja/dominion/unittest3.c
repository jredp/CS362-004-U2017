/*-----------------------------------------------*/
// Test the isGameOver function
/*-----------------------------------------------*/

#include "dominion.h"
#include <stdio.h>
#include "rngs.h"
#include <stdlib.h>

#define MSG_PRINT 1

int main(){
    int passNum = 0;
    int numPlayer = 2;    
    int k[10] = {feast, gardens, mine, adventurer, council_room, remodel, smithy, village, baron, great_hall};
    struct gameState G;
    int testRes = 0;    

    /*------------------TEST 1 ------------------*/
    printf("[---------- unittest3.c -----------]\n");
    //Init game using playercount
    G.whoseTurn = 5;
    testRes = whoseTurn(&G);    
    if (testRes == 5) {
        #if (MSG_PRINT == 1)            
            printf("-->Test 1 passed.\n");
        #endif
        passNum = passNum+1;
    }
    else {
        printf("-->Test 1 failed.\n");
    }

    /*------------------TEST 2-27 ------------------*/    
    //Check multiple game overs
    int testcount = 2;    
    int i;
    for(i=25; i >= 0; i--) {    
        G.whoseTurn = i;
        testRes = whoseTurn(&G);
                
        //Test 2-26        
        if (testRes == i) {
            #if (MSG_PRINT == 1)            
                printf("-->Test %d passed.\n", testcount);
            #endif
            passNum = passNum+1;
        }
        else {
            printf("-->Test %d failed.\n", testcount);
        }        
        testcount = testcount+1;
    }      
     
    /*------------------ Results ----------------*/    
    printf("%d tests passed.\n", passNum);
    return 0;
}