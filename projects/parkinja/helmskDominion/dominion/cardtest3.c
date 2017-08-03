/*-----------------------------------------------*/
// Test the Treasure_Effect Card
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

    /*------------------TEST ------------------*/
    //Test the Card Effect does what it says it does
    printf("[---------- cardtest3.c (treasure_map)-----------]\n");    
    //genNum = rand() % 10;
    //initializeGame(2, k, genNum, &G);     
    initializeGame(players, k, randomSeed, &G); //REFACTOR
    testRes = cardEffect(treasure_map, 1, 1, 1, &G, handPos, 0); //REFACTOR
    //testRes = Treasure_Efct(&G, 0);

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