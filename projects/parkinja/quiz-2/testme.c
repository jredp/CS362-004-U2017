#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

/*-----------------------------------------------*/
// Produce random character values
// Possible random values from 32 to 125
// Smallest ASCII Char is Space. ASCII Dec 32
// Largest ASCII Char is } bracket. ASCII Dec 125
/*-----------------------------------------------*/
char inputChar() {
  //possible: '[', '(', '{', ' ', 'a', 'x', '}', ')', ']'
  int min = 32;
  int max = 125;
  int range = (max-min);
  //Between 32 and 125
  int randCharInt = rand() % (range + 1) + min;
  char returnChar = randCharInt;
  return returnChar;
}

/*-----------------------------------------------*/
// For s[5] to be possible, string must have 6 chars
// Function generates random string of 6 chars
// Letters required to be from e-t (ASCII 101-116)
//
// For now, using that small range for testing
/*-----------------------------------------------*/
char *inputString() {
  //Between 101 and 116
  int min = 101;
  int max = 116;
  int range = (max-min);

  //String of 7 chars to include terminate  
  char myString[6] = {'a','a','a','a','a','\0'};
  char *myStringPtr = myString;
  //Fill 0-4 slots (letters 1-5)
  int i = 0;
  for(i=0;i<5;i++) {
    int randCharInt = rand() % (range + 1) + min;
    char returnChar = randCharInt;
    myString[i] = returnChar;
  }
  return myStringPtr;
}

void testme()
{
  int tcCount = 0;
  char *s;
  char c;
  int state = 0;
  while (1)
  {
    tcCount++;
    c = inputChar();
    s = inputString();
    printf("Iteration %d: c = %c, s = %s, state = %d\n", tcCount, c, s, state);

    if (c == '[' && state == 0) state = 1;
    if (c == '(' && state == 1) state = 2;
    if (c == '{' && state == 2) state = 3;
    if (c == ' '&& state == 3) state = 4;
    if (c == 'a' && state == 4) state = 5;
    if (c == 'x' && state == 5) state = 6;
    if (c == '}' && state == 6) state = 7;
    if (c == ')' && state == 7) state = 8;
    if (c == ']' && state == 8) state = 9;
    if (s[0] == 'r' && s[1] == 'e'
       && s[2] == 's' && s[3] == 'e'
       && s[4] == 't' && s[5] == '\0'
       && state == 9)
    {
      printf("error ");
      exit(200);
    }
  }
}


int main(int argc, char *argv[])
{
    srand(time(NULL));
    testme();
    return 0;
}
