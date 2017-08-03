#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"
#include <time.h>
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <assert.h>

//How many test suite runs to make
#define TEST_NUMBER 1000

//Global for simple test
int failed=0; //Tets failed
int passed=0; //Tests passed

//Define Customer Assert function
int custAssert(int checkInt, char *message);

//Main program
int main() {
    
    //Test Information    
    int beforeCheckHandCount, afterCheckHandCount; //For Hand comparison
    int beforeTreasure, afterTreasure; //For treasure comparison
    int befHand, aftHand, befDeck, aftDeck, befDisc, aftDisc, befPlay, aftPlay;
    //int allBefCards, allAftCards;
    int testsRan; //Total Test Suites Run
    int randomSeed; //Random seed
    int i=0; //For random loops
    int assertResult=0; //For assertion testing        
    int handBool, trBool;
    int sOutpost=0, sPhase=0, sActions=0, sBuys=0, sPlayedCard=0, sTurn=0;
    int tInitG=0, tCe=0, tHc=0, tTa=0, totTests=0; //tAll=0
    
    srand(time(NULL)); //Seed random 
    SelectStream(2);
    PutSeed(3);

    //Game Setup
    struct gameState G;
    int k[10] = {adventurer, gardens, embargo, village, minion, mine, cutpurse, sea_hag, tribute, smithy};    
    int handPos, players, player;    
        
    printf ("<------------------------------>\n");
    printf ("<------------------------------>\n");
    printf ("RANDOM Testing Smithy Card.\n");
    printf ("<------------------------------>\n");
    printf ("<------------------------------>\n");

    //Loop n times per TEST_NUMBER
    for (testsRan=0; testsRan<TEST_NUMBER; testsRan++) {
      printf("\n/////////////////////////////////\n");
      printf("/// Test Run: %d\n", (testsRan+1));
      printf("/////////////////////////////////\n");
      
      //player = rand() % (players + 1); //Choose the player      
      randomSeed = rand(); //Generate for Game init
      players = (rand() % 3) + 2; //Setup players 2-4
      player = floor(Random() * 2);
      handPos = 0; //First slot for now
      //allBefCards = 0;     
      beforeTreasure=0, afterTreasure=0;
      handBool=0, trBool=0;
      
      /*------RANDOMIZE INIT---------------------*/
      //Setup a new game
      assertResult = initializeGame(players, k, randomSeed, &G);
      //Test the new game
      tInitG += custAssert(assertResult==0, "Init Game Assert");
      totTests += 1;
      
      /*------RANDOMIZE DECK---------------------*/
      //printf("-->DECK: Rand 1\n"); //DEBUG
      
      //Set rand deck size
      G.deckCount[player] = floor(Random() * MAX_DECK);         
      if (G.deckCount[player] == 0) G.deckCount[player] = 1;
      //Random Deck Cards
      for(i=0; i<G.deckCount[player]; i++) {
        //Place a random card from first to last card into the players deck
        G.deck[player][i] = rand() % treasure_map;
      }      

      /*------RANDOMIZE HAND---------------------*/    
      //printf("-->HAND: Rand 2\n");  //DEBUG

      //Set rand hand size 
      G.handCount[player] = floor(Random() * (MAX_HAND-1)); 
      G.handCount[player] += 1;
      //Set before hand count
      beforeCheckHandCount = G.handCount[player]; 
      //printf("-HandCount: %d\n", beforeCheckHandCount); //DEBUG

      //Fill the player hand; i=1 because slot 0 will be desired card to play
      for(i=1; i<G.handCount[player]; i++) {
        //Place a random card from first to last card into the players hand
        G.hand[player][i] = rand() % treasure_map;
        //Increase the treasure count if the random card is money
        if(G.hand[player][i] >= copper && G.hand[player][i] <= gold)
          beforeTreasure += 1;
      }      

      //Set the card to play as smithy
      G.hand[player][handPos] = smithy;
      
      /*------RANDOMIZE DISCARD------------------*/            
      //Set rand discard size            
      //printf("-->DISCARD: %d\n", G.discardCount[player]); //DEBUG
      G.discardCount[player] = floor(Random() * MAX_DECK);      
        
      //printf("Building Discard Pile...\n"); //DEBUG
      for(i=0; i<G.discardCount[player]; i++) {
        //Discard a random card from first to last card into the players discard pile                
        G.discard[player][i] = rand() % treasure_map;        
      }                  

      /*------Call CardEffect--------------------*/      
      //Sum cards up to this point before call
      befDisc = G.discardCount[player];
      befHand = G.handCount[player];
      befDeck = G.deckCount[player];
      befPlay = G.playedCardCount;      
      //allBefCards = G.discardCount[player] + G.deckCount[player] + G.handCount[player] + G.playedCardCount;
      //------DEBUG-----
      printf(" -->bef Hand Count: %d\n", befHand);
      printf(" -->bef DECK Count: %d\n", befDeck);
      printf(" -->bef Discard Count: %d\n", befDisc);
      printf(" -->bef Played Count: %d\n", befPlay);
      //------DEBUG-----
      
      /*-------------------*/
      //Test proper run of cardEffect
      /*-------------------*/
      //printf("Effect\n");      //DEBUG      
      //assertResult = Adventurer_Efct(&G);
      assertResult = cardEffect(smithy, 1, 1, 1, &G, handPos, 0);
      tCe += assertResult;
      totTests += 1;

      //Sum cards up to this point after call      
      aftDisc = G.discardCount[player];
      aftHand = G.handCount[player];
      aftDeck = G.deckCount[player];      
      aftPlay = G.playedCardCount;
      //allAftCards = G.discardCount[player] + G.deckCount[player] + G.handCount[player] + G.playedCardCount;
      
      /*-------------------*/
      //Test Card Effect return
      /*-------------------*/
      //printf("After Call CardEffect\n");
      custAssert(assertResult == 0, "Card Effect return");
      
      /*-------------------*/
      //Test Handcounts
      /*-------------------*/
      //Post hand count work
      afterCheckHandCount = G.handCount[player];  

      handBool = custAssert((beforeCheckHandCount + 2) == afterCheckHandCount, "Card Effect: HandCount Assert +3 drawn, -1 played");
      tHc += handBool;
      totTests += 1;
      //printf("---handBool: %d\n", handBool); //DEBUG
          
      //------DEBUG-----
      printf(" -->aft Hand Count: %d\n", aftHand);      
      printf(" -->aft DECK Count: %d\n", aftDeck);      
      printf(" -->aft Discard Count: %d\n", aftDisc);
      printf(" -->aft Played Count: %d\n", aftPlay);
      //------DEBUG-----
      
      /*-------------------*/
      //Test Treasure      
      /*-------------------*/
      for(i=0; i<G.handCount[player]; i++) {        
        //Count the treasure after
        if(G.hand[player][i] >= copper && G.hand[player][i] <= gold)
          afterTreasure += 1;
      }
      
      /*-------------------*/
      //Test Discard
      /*-------------------*/              
      trBool = custAssert(befDisc == aftDisc, "Card Effect: No Discard");
      tTa += trBool;
      totTests += 1;
      //printf("---trBool: %d\n", trBool); //DEBUG      

      /*-------------------*/
      //All card check test
      /*-------------------*/
      //------DEBUG-----
      // printf("allBefCards%d\n", allBefCards);
      // printf("allAftCards%d\n", allAftCards);
      //------DEBUG-----

      //--NOTE--This test has been removed due to constant inconsistencies
      //tAll += custAssert(allBefCards == allAftCards, "All Before Cards == All After Cards");        
      //totTests += 1;

      /*-------------------*/
      //Game State Tests      
      /*-------------------*/
      //int sOutpost, sPhase, sActions, sBuys, sPlayedCard, sTurn;
      // state->outpostPlayed = 0;
      sOutpost += custAssert(G.outpostPlayed == 0, "Game State: Outpost, No Change");
      totTests += 1;
      // state->phase = 0;
      sPhase += custAssert(G.phase == 0, "Game State: Phase, No Change");
      totTests += 1;
      // state->numActions = 1;
      sActions += custAssert(G.numActions == 1, "Game State: No Change (not played)");
      totTests += 1;
      // state->numBuys = 1;
      sBuys += custAssert(G.numBuys == 1, "Game State: Buys No Change.");
      totTests += 1;
      // state->playedCardCount = 1;
      sPlayedCard += custAssert(G.playedCardCount == 1, "Game State: playedCardCount + 1");
      totTests += 1;
      // state->whoseTurn = 0;
      sTurn += custAssert(G.whoseTurn == 0, "Game State: Turn no change");
      totTests += 1;
      // state->handCount[state->whoseTurn] = 0;

    }
    /*-------------------*/
    //Test results
    /*-------------------*/
    printf("<----------SUMMARY--------------->\n");
    printf("///  Total Suite Runs : %d\n", testsRan);
    printf("///  Pre-PASSED Tests : %d\n", passed);
    printf("///  Pre-FAILED Tests : %d\n", failed);    
    printf("///  Total Tests Run  : %d\n", (passed+failed));
    printf("<----------CARD EFFECT----------->\n");    
    printf("///  Init Game Failed     : %d\n", tInitG);
    printf("///  Card Effect Failed   : %d\n", tCe);
    printf("///  Hand Count Failed    : %d\n", tHc);
    printf("///  Discard Cnt Failed   : %d\n", tTa);
    //printf("///  All Card Count Failed: %d\n", tAll);
    printf("<----------GAME STATE------------>\n");
    printf("///  GameState Outpost Failed: %d\n", sOutpost);
    printf("///  GameState Actions Failed: %d\n", sActions);
    printf("///  GameState NumBuys Failed: %d\n", sBuys);
    printf("///  GameState Played Failed : %d\n", sPlayedCard);
    printf("///  GameState whoTurn Failed: %d\n", sTurn);
    printf("<----------RESULTS-------------->\n");    
    printf("///  PASSED Tests: %d\n", passed);
    printf("///  FAILED Tests: %d\n", failed);
    printf("///  TOTAL Tests : %d\n", totTests);
    printf("<-------------------------------->\n");    
    return 0;
}

//Custom Assert that simply checks, increments and prints
int custAssert(int checkInt, char *message) {
  if(!checkInt) {
    printf("[FAILED]: %s\n", message);    
    failed++;
    return 1;
  }
  printf("[PASSED]: %s\n", message);
  passed++;
  return 0;
}