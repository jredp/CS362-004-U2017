/*-----------------------------------------------*/
// Test the shuffle function
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
    printf("[---------- unittest1.c (initGame / shuffle)-----------]\n");
    //Init game using playercount
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

    /*------------------TEST 2 ------------------*/    
    //Invalid Deck Size
    G.deckCount[1] = -55;
    testRes = shuffle(1, &G);
    if (testRes == -1) {
        #if (MSG_PRINT == 1)            
            printf("-->Test 2 passed.\n");
        #endif
        passNum = passNum+1;
    }
    else {
        printf("-->Test 2 failed.\n");
    }
    
    /*------------------TEST 3 ------------------*/
    //Valid Deck Size
    G.deckCount[1] = 8;
    testRes = shuffle(1, &G);
    if (testRes == 0) {
        #if (MSG_PRINT == 1)
            printf("-->Test 3 passed.\n");
        #endif
        passNum = passNum+1;
    }
    else {
        printf("-->Test 3 failed.\n");
    }    
     
    /*------------------ Results ----------------*/
    printf("%d tests passed.\n", passNum);
    return 0;
}

// newassert(condition){
//     if(!(condition)) {
//         fprintf(stderr, "FAIL %s in file %s: \n\t\n", __FILE__, __FUNCTION__);
//     }
//     else {
//         fprintf(stderr, "PASSED %s in file %s: \n\t\n", __FILE__, __FUNCTION__);   
//     }
// }