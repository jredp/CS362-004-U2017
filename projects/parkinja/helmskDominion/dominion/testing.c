/***********************************************************
 * Author:          Kelsey Helms
 * Date Created:    July 3, 2017
 * Filename:        testing.c
 *
 * Overview:
 * This is the implementation file for testing functions
 ************************************************************/

#include <stdio.h>
#include "testing.h"

int failedAssert = 0;

inline void testingAssert(const char* failed, const char* file, int line)
{
    failedAssert++;
    printf("Assert failed: {%s}, file %s, line %d.\n", failed, file, line);
}

void checkTest()
{
    if (failedAssert == 0)
        printf("All tests passed!\n");
    else
        printf("Test failed: %d failed assertions\n", failedAssert);
}
