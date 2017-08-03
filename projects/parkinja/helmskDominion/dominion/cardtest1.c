/*-----------------------------------------------*/
// Test the Smithy Card
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


    /*------------------TEST 1 ------------------*/
    //Test the Card Effect does what it says it does (+3 cards)    
    printf("[---------- cardtest1.c (SMITHY)-----------]\n");    
    //genNum = rand() % 10;                     //REFACTOR
    //initializeGame(2, k, genNum, &G);         //REFACTOR
    initializeGame(players, k, randomSeed, &G); //REFACTOR
    int handCountBef = G.handCount[0];
    printf("handCountBef: %d\n", handCountBef);
    //testRes = Smithy_Efct(&G, 4);    
    cardEffect(smithy, 1, 1, 1, &G, handPos, 0); //REFACTOR

    int handCountAft = G.handCount[0];
    printf("handCountAft: %d\n", handCountAft);

    if (handCountAft == (handCountBef+3)) {
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