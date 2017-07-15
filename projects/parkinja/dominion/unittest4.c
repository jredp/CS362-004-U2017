/*-----------------------------------------------*/
// Test the kingdomCards function
/*-----------------------------------------------*/

#include "dominion.h"
#include <stdio.h>
#include "rngs.h"
#include <stdlib.h>

#define MSG_PRINT 1

int main(){
    int i;
    int passNum = 0;
    int numPlayer = 2;    
    int k[10] = {feast, gardens, mine, adventurer, council_room, remodel, smithy, village, baron, great_hall};
    struct gameState G;    

    /*------------------TEST 1-10 ------------------*/
    printf("[---------- unittest4.c -----------]\n");        
    int *testRes = kingdomCards(1,2,3,4,5,6,7,8,9,10);
    //Check multiple game overs
    int testcount = 1;    
    for(i=0; i<10; i++) {
        //Test 1-10        
        if (testRes[i] == i+1) {
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