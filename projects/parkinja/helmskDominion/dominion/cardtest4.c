/*-----------------------------------------------*/
// Test the Village_Efct Card function
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
    int passNum = 0;    
    int testcount = 1;        
    struct gameState G;    
    //int genNum = 0;
    int k[10] = {feast, gardens, mine, adventurer, council_room, remodel, smithy, village, baron, great_hall};

    /*------------------ASSIGNMENT 5 REFACTOR------------------*/
    //Setup
    int handPos, players;            
    int randomSeed; //Random seed    
    srand(time(NULL)); //Seed random 
    SelectStream(2);
    PutSeed(3);    
    randomSeed = rand(); //Generate for Game init
    players = (rand() % 3) + 2; //Setup players 2-4    
    handPos = 0; //First slot for now    
    /*------------------ASSIGNMENT 5 REFACTOR------------------*/

    /*------------------TEST ------------------*/
    //Test the Card Effect does what it says it does (+2 action count) 
    printf("[---------- cardtest4.c (village)-----------]\n");
    //genNum = rand() % 10;
    //initializeGame(2, k, genNum, &G); 
    initializeGame(players, k, randomSeed, &G); //REFACTOR    
    int actionCountBef = G.numActions;
    printf("actionCountBef: %d\n", actionCountBef);
    cardEffect(village, 1, 1, 1, &G, handPos, 0); //REFACTOR
    //testRes = Village_Efct(&G, 0);    
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