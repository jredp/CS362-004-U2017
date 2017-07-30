CFLAGS = -Wall -fpic -coverage -lm

rngs.o: rngs.h rngs.c
	gcc -c rngs.c -g  $(CFLAGS)

dominion.o: dominion.h dominion.c rngs.o
	gcc -c dominion.c -g  $(CFLAGS)

playdom: dominion.o playdom.c
	gcc -o playdom playdom.c -g dominion.o rngs.o $(CFLAGS)
#To run playdom you need to entere: ./playdom <any integer number> like ./playdom 10*/
testDrawCard: testDrawCard.c dominion.o rngs.o
	gcc  -o testDrawCard -g  testDrawCard.c dominion.o rngs.o $(CFLAGS)

badTestDrawCard: badTestDrawCard.c dominion.o rngs.o
	gcc -o badTestDrawCard -g  badTestDrawCard.c dominion.o rngs.o $(CFLAGS)

testBuyCard: testDrawCard.c dominion.o rngs.o
	gcc -o testDrawCard -g  testDrawCard.c dominion.o rngs.o $(CFLAGS)

testAll: dominion.o testSuite.c
	gcc -o testSuite testSuite.c -g  dominion.o rngs.o $(CFLAGS)

interface.o: interface.h interface.c
	gcc -c interface.c -g  $(CFLAGS)

runtests: testDrawCard 
	./testDrawCard &> unittestresult.out
	gcov dominion.c >> unittestresult.out
	cat dominion.c.gcov >> unittestresult.out

player: player.c interface.o
	gcc -o player player.c -g  dominion.o rngs.o interface.o $(CFLAGS)

unittest1: unittest1.c dominion.o rngs.o
	gcc -o unittest1 -g unittest1.c dominion.o rngs.o $(CFLAGS)
	#gcc -fprofile-arcs -ftest-coverage -Wall -std=c99 dominion.c unittest1.c rngs.c -o unittest1 -lm -g

unittest2: unittest2.c dominion.o rngs.o
	gcc -o unittest2 -g unittest2.c dominion.o rngs.o $(CFLAGS)
	#gcc -fprofile-arcs -ftest-coverage -Wall -std=c99 dominion.c unittest2.c rngs.c -o unittest2 -lm -g

unittest3: unittest3.c dominion.o rngs.o
	gcc -o unittest3 -g unittest3.c dominion.o rngs.o $(CFLAGS)
	#gcc -fprofile-arcs -ftest-coverage -Wall -std=c99 dominion.c unittest3.c rngs.c -o unittest3 -lm -g

unittest4: unittest4.c dominion.o rngs.o
	gcc -o unittest4 -g unittest4.c dominion.o rngs.o $(CFLAGS)
	#gcc -fprofile-arcs -ftest-coverage -Wall -std=c99 dominion.c unittest4.c rngs.c -o unittest4 -lm -g

cardtest1: cardtest1.c dominion.o rngs.o
	gcc -o cardtest1 -g cardtest1.c dominion.o rngs.o $(CFLAGS)
	#gcc -fprofile-arcs -ftest-coverage -Wall -std=c99 dominion.c cardtest1.c rngs.c -o cardtest1 -lm -g	

cardtest2: cardtest2.c dominion.o rngs.o
	gcc -o cardtest2 -g cardtest2.c dominion.o rngs.o $(CFLAGS)
	#gcc -fprofile-arcs -ftest-coverage -Wall -std=c99 dominion.c cardtest2.c rngs.c -o cardtest2 -lm -g

cardtest3: cardtest3.c dominion.o rngs.o
	gcc -o cardtest3 -g cardtest3.c dominion.o rngs.o $(CFLAGS)
	#gcc -fprofile-arcs -ftest-coverage -Wall -std=c99 dominion.c cardtest3.c rngs.c -o cardtest3 -lm -g

cardtest4: cardtest4.c dominion.o rngs.o	
	gcc -o cardtest4 -g cardtest4.c dominion.o rngs.o $(CFLAGS)
	#gcc -fprofile-arcs -ftest-coverage -Wall -std=c99 dominion.c cardtest4.c rngs.c -o cardtest4 -lm -g    

unittestresults.out: unittest1 unittest2 unittest3 unittest4 cardtest1 cardtest2 cardtest3 cardtest4
	./unittest1 &> unittestresults.out
	./unittest2 >> unittestresults.out
	./unittest3 >> unittestresults.out
	./unittest4 >> unittestresults.out
	./cardtest1 >> unittestresults.out
	./cardtest2 >> unittestresults.out
	./cardtest3 >> unittestresults.out
	./cardtest4 >> unittestresults.out
	gcov unittest1.c -b >> unittestresults.out
	gcov unittest2.c -b >> unittestresults.out
	gcov unittest3.c -b >> unittestresults.out
	gcov unittest4.c -b >> unittestresults.out
	gcov cardtest1.c -b >> unittestresults.out
	gcov cardtest2.c -b >> unittestresults.out
	gcov cardtest3.c -b >> unittestresults.out
	gcov cardtest4.c -b >> unittestresults.out
	gcov dominion.c -b >> unittestresults.out
	cat unittest1.c.gcov >> unittestresults.out		
	cat unittest2.c.gcov >> unittestresults.out	
	cat unittest3.c.gcov >> unittestresults.out	
	cat unittest4.c.gcov >> unittestresults.out			
	cat cardtest1.c.gcov >> unittestresults.out	
	cat cardtest2.c.gcov >> unittestresults.out	
	cat cardtest3.c.gcov >> unittestresults.out	
	cat cardtest4.c.gcov >> unittestresults.out	
	cat dominion.c.gcov >> unittestresults.out

myTests: unittest1 unittest2 unittest3 unittest4 cardtest1 cardtest2 cardtest3 cardtest4 unittestresults.out

#Random Test Adventurer
randomtestadventurer: randomtestadventurer.c dominion.o rngs.o
	gcc -o randomtestadventurer -g randomtestadventurer.c dominion.o rngs.o $(CFLAGS)	

randomtestadventurer.out: randomtestadventurer
	./randomtestadventurer &> randomtestadventurer.out	
	gcov randomtestadventurer.c -b >> randomtestadventurer.out	
	gcov dominion.c -b >> randomtestadventurer.out
	cat randomtestadventurer.c.gcov >> randomtestadventurer.out
	cat dominion.c.gcov >> randomtestadventurer.out
	
#Random Test Card1 (Smithy)
randomtestcard1: randomtestcard1.c dominion.o rngs.o
	gcc -o randomtestcard1 -g randomtestcard1.c dominion.o rngs.o $(CFLAGS)	

randomtestcard1.out: randomtestcard1
	./randomtestcard1 &> randomtestcard1.out
	gcov randomtestcard1.c -b >> randomtestcard1.out
	gcov dominion.c -b >> randomtestcard1.out
	cat randomtestcard1.c.gcov >> randomtestcard1.out
	cat dominion.c.gcov >> randomtestcard1.out

#Random Test Card2 (village)
randomtestcard2: randomtestcard2.c dominion.o rngs.o
	gcc -o randomtestcard2 -g randomtestcard2.c dominion.o rngs.o $(CFLAGS)	

randomtestcard2.out: randomtestcard2
	./randomtestcard2 &> randomtestcard2.out
	gcov randomtestcard2.c -b >> randomtestcard2.out
	gcov dominion.c -b >> randomtestcard2.out
	cat randomtestcard2.c.gcov >> randomtestcard2.out
	cat dominion.c.gcov >> randomtestcard2.out

rTests: randomtestadventurer randomtestadventurer.out randomtestcard1 randomtestcard1.out randomtestcard2 randomtestcard2.out

clean:
	rm -f *.o playdom.exe playdom player player.exe *.gcov *.gcda *.gcno *.so *.out testDrawCard testDrawCard.exe
	rm -f unittest1 unittest2 unittest3 unittest4 cardtest1 cardtest2 cardtest3 cardtest4 *.gcov *.gcda *.gcno *.so
	rm -f randomtestadventurer randomtestcard1 randomtestcard2
