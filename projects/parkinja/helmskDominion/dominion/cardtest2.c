/*-----------------------------------------------*/
// Test the Adventurer Card
/*-----------------------------------------------*/
#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"
#include <time.h>
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <assert.h>

#define MSG_PRINT 1

int main () {

    int testRes = 0;
    int passNum = 0;    
    int testcount = 1;        
    struct gameState G;    
    //int genNum = 0;
    int k[10] = {feast, gardens, mine, adventurer, council_room, remodel, smithy, village, baron, great_hall};

    /*------------------ASSIGNMENT 5 REFACTOR------------------*/
    //Setup
    int handPos, players;            
    int randomSeed; //Random seed        
    randomSeed = rand(); //Generate for Game init
    players = (rand() % 3) + 2; //Setup players 2-4    
    handPos = 0; //First slot for now    
    /*------------------ASSIGNMENT 5 REFACTOR------------------*/

    /*------------------TEST 1 ------------------*/
    //Test the Card Effect does what it says it does (dig)
    printf("[---------- cardtest2.c (Adventurer)-----------]\n");    
    //genNum = rand() % 10;
    //initializeGame(2, k, genNum, &G);     
    initializeGame(players, k, randomSeed, &G); //REFACTOR
    testRes = cardEffect(adventurer, 1, 1, 1, &G, handPos, 0); //REFACTOR
    //testRes = Adventurer_Efct(&G);

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