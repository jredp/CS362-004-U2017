/*-----------------------------------------------*/
// Test the isGameOver function
/*-----------------------------------------------*/

#include "dominion.h"
#include <stdio.h>
#include "rngs.h"
#include <stdlib.h>

#define MSG_PRINT 1

int main(){
    int numPlayer = 2;
    int passNum = 0;
    int k[10] = {feast, gardens, mine, adventurer, council_room, remodel, smithy, village, baron, great_hall};
    struct gameState G;
    int testRes = 0;
    int numb = 0;    

    /*------------------TEST 1 ------------------*/
    printf("[---------- unittest2.c (isGameOver)-----------]\n");    
    numb = rand() % 8;
    testRes = initializeGame(numPlayer, k, numb, &G);    
    if (testRes == 0) {
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
        G.supplyCount[i] = i;
        testRes = isGameOver(&G);

        //Only 0 is game over
        //Test 27
        if(i == 0) {            
            G.supplyCount[province] = 0;
            testRes = isGameOver(&G);
            if (testRes == 1) {
                #if (MSG_PRINT == 1)            
                    printf("-->Test %d passed.\n", testcount);
                #endif
                passNum = passNum+1;
            }
            else {
                printf("-->Test %d failed.\n", testcount);
            }    
        }
        //Test 2-26
        else{
            if (testRes == 0) {
                #if (MSG_PRINT == 1)            
                    printf("-->Test %d passed.\n", testcount);
                #endif
                passNum = passNum+1;
            }
            else {
                printf("-->Test %d failed.\n", testcount);
            }       
        }
        testcount = testcount+1;
    }      
     
    /*------------------ Results ----------------*/    
    printf("%d tests passed.\n", passNum);
    return 0;
}