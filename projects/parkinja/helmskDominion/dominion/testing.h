/***********************************************************
 * Author:          Kelsey Helms
 * Date Created:    July 3, 2017
 * Filename:        testing.h
 *
 * Overview:
 * This is the header file for testing functions.
 ************************************************************/

#ifndef TESTING_H
#define TESTING_H

void testingAssert(const char* expression, const char* file, int line);
void checkTest();

#endif

#ifdef NDEBUG
#define assert(EXPRESSION) ((void)0)
#else
#define assert(EXPRESSION) ((EXPRESSION) ? (void)0 : testingAssert(#EXPRESSION, __FILE__, __LINE__))
#endif
