/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import junit.framework.TestCase;

/**
 * Performs Validation Test for url validations.
 *
 * //@version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */

public class UrlValidatorTest extends TestCase {

  public UrlValidatorTest(String testName) {
    super(testName);
  }

  private boolean printStatus = false;
  private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

  /************************************************************************************
                              MANUAL TESTING  
  *************************************************************************************/

  public void testManualTest()
   {    
     //Original test code
     UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
     //Should return true
     System.out.println(urlVal.isValid("http://www.amazon.com"));
     
     //Should return true
     System.out.println(urlVal.isValid("http://www.google.com"));
     
     //Should return true (Testing odd TLD)
     System.out.println(urlVal.isValid("http://www.google.cm"));
     
     //Should return false (Bad TLD)
     System.out.println(urlVal.isValid("http://www.google.blah"));
     
     //Should return false (No text entered) BOUNDARY TEST
     System.out.println(urlVal.isValid(""));
     
     //Should return false (No domain or TLD or Path)
     System.out.println(urlVal.isValid("BLAH"));
     
     //Should return true (fille is an invalid scheme but ALLOW_ALL_SCHEMES is on) 
     System.out.println(urlVal.isValid("fille://www.google.com"));
     
     //Should return false (space after scheme)
     System.out.println(urlVal.isValid("http ://www.google.com"));
     
     //Should return true (Tests valid authority with path, query and fragment) ***POSSIBLE BUG*** 
     System.out.println(urlVal.isValid("http://www.google.com/examplepath?somequery#goodfrag"));
     
     //Should return true (Tests valid authority with path and query) ***POSSIBLE BUG*** 
     System.out.println(urlVal.isValid("http://www.google.com/examplepath?somequery"));
     
     //Should return true (Tests valid authority with path)
     System.out.println(urlVal.isValid("http://www.google.com/examplepath"));
     
     //Should return true (Tests valid authority with path and fragment)
     System.out.println(urlVal.isValid("http://www.google.com/examplepath#goodfrag"));
     
     //Should return false (All numbers) BOUNDARY TEST
     System.out.println(urlVal.isValid("123456"));
     
     //Should return false (All letters) BOUNDARY TEST
     System.out.println(urlVal.isValid("ABCDEFG"));
     
     //Should return true (valid scheme and domain with addition of username, password, and port) ***POSSIBLE BUG*** 
     System.out.println(urlVal.isValid("https://testuser:123@www.google.com:80"));
     
     //Should return true (valid scheme and domain with addition of username, and password) ***POSSIBLE BUG*** 
     System.out.println(urlVal.isValid("https://testuser:123@www.google.com"));
     
     //Should return true (valid scheme and domain with addition of port)
     System.out.println(urlVal.isValid("https://www.google.com:80"));
     
     //Should return true (valid scheme and domain with multiple paths)
     System.out.println(urlVal.isValid("https://www.google.com/path1/path2"));
     
     //Should return true (valid scheme with valid IPv4)
     System.out.println(urlVal.isValid("https://123.123.123.123"));
     
     //Should return false (valid scheme with invalid IP Only 3 octets)
     System.out.println(urlVal.isValid("https://123.123.123"));
     
     //Should return true (valid scheme with valid IPv4) BOUNDARY CASE
     System.out.println(urlVal.isValid("https://0.0.0.0"));
   
     //Should return true (valid scheme with valid IPv4) BOUNDARY CASE
     System.out.println(urlVal.isValid("https://255.255.255.255"));
     
     //Should return false (valid scheme with invalid IPv4 an octet is out of range)  ***POSSIBLE BUG***
     System.out.println(urlVal.isValid("https://255.255.255.256"));
     
     //Should return false (valid scheme and domain but a period after TLD and before path)
     System.out.println(urlVal.isValid("https://www.google.com./path"));
     
     //Should return true (valid scheme with valid IPv4 and valid paths)
     System.out.println(urlVal.isValid("https://123.123.123.123/path1/path2"));
     
     //Should return false (valid scheme with valid IPv4 and valid paths but a space in the path2)
     System.out.println(urlVal.isValid("https://123.123.123.123/path1/pa th2"));
     
     //Should return false (valid scheme with invalid authority)
     System.out.println(urlVal.isValid("https://1234567"));
     
     //Should return true (valid scheme with valid authority of numbers with TLD)
     System.out.println(urlVal.isValid("https://1234567.com"));
     
     //Should return false (No scheme provided)
     System.out.println(urlVal.isValid("google.com"));
     
   //Should return true (valid scheme and authority with valid parenthesis now in path)
     System.out.println(urlVal.isValid("https://www.google.com/(somePath)"));
     
   }
  
/************************************************************************************
                            PARTITION TESTING  
*************************************************************************************/

  public void testYourFirstPartition() {
    
    System.out.println("\n--------------------Testing Schemes--------------------\n");

    String[] testSchemes = {"http://", "ftp://", "h3t://", "http:/", "http//", "ftp:/", "ftp//", ""};
    UrlValidator schemeVal = new UrlValidator(testSchemes, 0);
    for (int i = 0; i < testSchemes.length; i++) {
      String curScheme = testSchemes[i];
      System.out.print("Testing " + curScheme);
      boolean valid = schemeVal.isValidScheme(curScheme);
      if (valid == false && i == 0 || valid == false && i == 1 || valid == false && i == 2 || valid == true && i == 3 || valid == true && i == 4 || valid == true && i == 5 || valid == true && i == 6 || valid == false && i == 7 ) {
        System.out.println("\t\t\tResult: BUG\n");
      } else {
        System.out.println("\t\t\tResult: CLEAR\n");
      }
    }
  }


  public void testYourSecondPartition() {
    System.out.println("\n--------------------Testing Authority--------------------\n");

    String[] testAuthority = {"www.google.com", "www.google", "this.google.com", "www.*google.com", "255.255.255.255", "0.0.0.0", "256.256.256.256", "-1.-1.-1.-1", "aaaaa", ""};
    UrlValidator authVal = new UrlValidator(testAuthority, 0);
    for (int i = 0; i < testAuthority.length; i++) {
      String curAuth = testAuthority[i];
      System.out.print("Testing " + curAuth);
      boolean valid = authVal.isValidAuthority(curAuth);
      if (valid == false && i == 0 || valid == true && i == 1 || valid == true && i == 2 || valid == true && i == 3 || valid == false && i == 4 || valid == false && i == 5 || valid == true && i == 6 || valid == true && i == 7 || valid == true && i == 8 || valid == true && i == 9) {
        System.out.println("\t\tResult: BUG\n");
      } else {
        System.out.println("\t\tResult: CLEAR\n");
      }
    }
  }

  
  public void testYourThirdPartition() {    
    System.out.println("\n--------------------Testing Port--------------------\n");

    String[] testPort = {"www.google.com:80", "www.google.com:65000", "www.google.com:0", "www.google.com:80a", "www.google.com:-1", "www.google.com:"};
    UrlValidator portVal = new UrlValidator(testPort, 0);
    for (int i = 0; i < testPort.length; i++) {
      String curPort = testPort[i];
      System.out.print("Testing " + curPort);
      boolean valid = portVal.isValidAuthority(curPort);
      if (valid == false && i == 0 || valid == false && i == 1 || valid == false && i == 2 || valid == true && i == 3 || valid == true && i == 4 || valid == true && i == 5) {
        System.out.println("\tResult: BUG\n");
      } else {
        System.out.println("\tResult: CLEAR\n");
      }
    }
  }


  public void testYourFourthPartition(){
    System.out.println("\n--------------------Testing Path--------------------\n");

    String[] testPath = {"/test", "/123", "/..", "test", "/test/test", "/test/123", "/…/test", "/#", "/#/test", ""};
    UrlValidator pathVal = new UrlValidator(testPath, 0);
      for (int i = 0; i < testPath.length; i++) {
        String curPath = testPath[i];
        System.out.print("Testing " + curPath);
        boolean valid = pathVal.isValidPath(curPath);
        if (valid == false && i == 0 || valid == false && i == 1 || valid == true && i == 2 || valid == true && i == 3 || valid == false && i == 4 || valid == false && i == 5 || valid == true && i == 6 || valid == true && i == 7 || valid == true && i == 8 || valid == false && i == 9) {
          System.out.println("\t\t\tResult: BUG\n");
        } else {
          System.out.println("\t\t\tResult: CLEAR\n");
        }
      }
  }


  public void testYourFifthPartition() {
    System.out.println("\n--------------------Testing Queries--------------------\n");

    String[] testQueries = {"/?query=test", "?query=test", "?query=", "=test", "??query=test"};
    UrlValidator pathQuery = new UrlValidator(testQueries, 0);
    for (int i = 0; i < testQueries.length; i++) {
      String curQuery = testQueries[i];
      System.out.print("Testing " + curQuery);
      boolean valid = pathQuery.isValidQuery(curQuery);
      if(valid == true && i == 0 || valid == false && i == 1 || valid == false && i == 2 || valid == true && i == 3 || valid == false && i == 4) {  
        System.out.println("\t\tResult: BUG\n");
      } else {
        System.out.println("\t\tResult: CLEAR\n");
      }
    }
  }


  /************************************************************************************
                            PROGRAMMING TESTING  
  *************************************************************************************/

  //Programmatic Testing   
   //Because the default of ALLOW_ALL_SCHEMES is set, this means all SCHEMES will pass no matter what!
   //Scheme/Authority/Port/Path/Query
   public void testIsValid() 
   {
    //https://www.iana.org/assignments/uri-schemes/uri-schemes.xhtml         
    ResultPair[] testUrlScheme = {
      new ResultPair("ftp://", true),
      new ResultPair("https://", true),
      new ResultPair("https:/", false),
      new ResultPair("ftp/", false),
      new ResultPair("https:/", false),
      new ResultPair(":/", false),
      new ResultPair("/", false),
      new ResultPair("//", false),
      new ResultPair("", false)
    };

    ResultPair[] testUrlAuthority = {
      new ResultPair("www.google.com", true),
      new ResultPair("go.com", true),
      new ResultPair("go.au", true), //Below are DomainValidator.java tests
      new ResultPair("go.root", true),// INFRASTRUCTURE_TLDS
      new ResultPair("go.biz", true),// GENERIC_TLDS businesses
      new ResultPair("go.cat", true),// GENERIC_TLDS Catalan linguistic/cultural community
      new ResultPair("go.com", true),// GENERIC_TLDS commercial enterprises
      new ResultPair("go.coop", true),// GENERIC_TLDS cooperative associations
      new ResultPair("go.info", true),// GENERIC_TLDS informational sites
      new ResultPair("go.jobs", true),// GENERIC_TLDS Human Resource managers
      new ResultPair("go.au", true),// COUNTRY_CODE_TLDS
      new ResultPair("go.at", true),// COUNTRY_CODE_TLDS
      new ResultPair("localhost", false),// LOCAL_TLDS (This should fail, no ALLOW_LOCAL_URLS enabled
      new ResultPair("go.a", false), //Not valid http://data.iana.org/TLD/tlds-alpha-by-domain.txt
      new ResultPair("go.a1a", false),//Not valid http://data.iana.org/TLD/tlds-alpha-by-domain.txt
      new ResultPair("go.cc", true), //Valid http://data.iana.org/TLD/tlds-alpha-by-domain.txt
      new ResultPair("go.1aa", false),//Not valid http://data.iana.org/TLD/tlds-alpha-by-domain.txt
      new ResultPair("0.0.0.0", true), //InetAddressValidator IPv4
      new ResultPair("255.255.255.255", true),
      new ResultPair("256.256.256.256", false),
      new ResultPair("255.com", true), //Non-IP
      new ResultPair("192.168.1.2.3", false), //Too long
      new ResultPair("3.3.3.3.", false), //Partial
      new ResultPair("3.3.3", false), //Too short
      new ResultPair(".3.3.3.3", false), //Partial
      new ResultPair("aaa.", false),//Not valid http://data.iana.org/TLD/tlds-alpha-by-domain.txt
      new ResultPair(".aaa", false),//Not valid http://data.iana.org/TLD/tlds-alpha-by-domain.txt
      new ResultPair("aaa", false),//Not valid http://data.iana.org/TLD/tlds-alpha-by-domain.txt
      new ResultPair("", false)
    };
    ResultPair[] testUrlPort = {
      new ResultPair(":5555", true), //Should be valid port
      new ResultPair(":5555555555", false), //Too long
      new ResultPair(":65", true), //Valid
      new ResultPair(":65535", true), //Valid
      //new ResultPair(":99999", true), //Should be INVALID, but testing for true anyway
      new ResultPair(":99999", false), //Should be INVALID
      new ResultPair("", true), //null
      new ResultPair(":-1", false), //bad port      
      new ResultPair(":65a", false)
    };
    ResultPair[] testPath = {      
      new ResultPair("", true),      
      new ResultPair("/..", false),      
      new ResultPair("/$99", true),
      new ResultPair("/../", false),
      new ResultPair("/this1", true),
      new ResultPair("/this1/", true),
      new ResultPair("/123this", true),      
      new ResultPair("/..//file", false),
      new ResultPair("/this1/file", true),
      new ResultPair("/this1//file", false)      
    };
    ResultPair[] testUrlQuery = {
      new ResultPair("?test=thisthing&this=thing", true),
      new ResultPair("?test=thisthing", true),            
      new ResultPair("", true)
    };

    /*-------------------------*/
    //Test the sets of URLS
    /*-------------------------*/
    String testString = "";
    String knownValid = "";
    boolean testResult = false;
    boolean testCompare = false;
    //Tests Summaries
    int schT = 0, authT = 0, prtT = 0, patT = 0, qT = 0; 
    int schTot = 0, authTot = 0, prtTot = 0, patTot = 0, qTot = 0; 
    int testFails = 0;
    int testPass = 0;
    UrlValidator urlV = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
    System.out.println("\nBeginning Programmatic Testing...\n");

    System.out.println("------------------\n");
    System.out.println("Testing Schemes...\n");
    System.out.println("------------------\n");

    System.out.println("\n::Pre Test::\n");
    testResult = urlV.isValid("http://www.google.com:80/test1?action=view true");
    System.out.println(String.format("http://www.google.com:80/test1?action=view true Result: %s", testResult));
    testResult = urlV.isValid("http://www.google.com");
    System.out.println(String.format("http://www.google.com Result: %s", testResult));
    testResult = urlV.isValid("www.google.com");
    System.out.println(String.format("www.google.com Result: %s", testResult));

    /*-------------------------*/
    //Test the Schemes
    /*-------------------------*/
    System.out.println("--------------------------SCHEME SECTION--------------------------------------\n");        
    knownValid = "www.google.com";
    for (int i=0; i<testUrlScheme.length; i++) {
      ResultPair testUrl = testUrlScheme[i];                
      testCompare = testUrl.valid; //Set the compare bool
      testString = (testUrl.item + knownValid); //Set the url string
      testResult = urlV.isValid(testString);
      System.out.println(String.format("Url Tested: %s", testString));
      System.out.println(String.format("Should Be: %s      Actual: %s",testCompare,testResult));
      System.out.println("---------------\n");        
      if (testCompare != testResult) {
        schT++;
        testFails++;
        System.out.println(String.format("-------> FAILURE ON: %s Expected was: %s | Actual was: %s\n",testUrl.item, testUrl.valid, testResult));
      }
      else {
        testPass++;
      }
      schTot++;
    }

    /*-------------------------*/
    //Test the Authority
    /*-------------------------*/
    System.out.println("--------------------------AUTH SECTION--------------------------------------\n");        
    knownValid = "http://";
    for (int i=0; i<testUrlAuthority.length; i++) {
      ResultPair testUrl = testUrlAuthority[i];                
      testCompare = testUrl.valid; //Set the compare bool
      testString = (knownValid + testUrl.item); //Set the url string
      testResult = urlV.isValid(testString);
      System.out.println(String.format("Url Tested: %s", testString));
      System.out.println(String.format("Should Be: %s      Actual: %s",testCompare,testResult));
      System.out.println("---------------\n");        
      if (testCompare != testResult) {
        authT++;
        testFails++;
        System.out.println(String.format("-------> FAILURE ON: %s Expected was: %s | Actual was: %s\n",testUrl.item, testUrl.valid, testResult));
      }
      else {
        testPass++;
      }
      authTot++;
    }

    /*-------------------------*/
    //Test the Port
    /*-------------------------*/
    System.out.println("--------------------------PORT SECTION--------------------------------------\n");        
    knownValid = "http://www.google.com";
    for (int i=0; i<testUrlPort.length; i++) {
      ResultPair testUrl = testUrlPort[i];                
      testCompare = testUrl.valid; //Set the compare bool
      testString = (knownValid + testUrl.item); //Set the url string
      testResult = urlV.isValid(testString);
      System.out.println(String.format("Url Tested: %s", testString));
      System.out.println(String.format("Should Be: %s      Actual: %s",testCompare,testResult));
      System.out.println("---------------\n");        
      if (testCompare != testResult) {
        prtT++;
        testFails++;
        System.out.println(String.format("-------> FAILURE ON: %s Expected was: %s | Actual was: %s\n",testUrl.item, testUrl.valid, testResult));
      }
      else {
        testPass++;
      }
      prtTot++;
    }

    /*-------------------------*/
    //Test the Path
    /*-------------------------*/
    System.out.println("--------------------------PATH SECTION--------------------------------------\n");        
    knownValid = "http://www.google.com:80";
    for (int i=0; i<testPath.length; i++) {
      ResultPair testUrl = testPath[i];                
      testCompare = testUrl.valid; //Set the compare bool
      testString = (knownValid + testUrl.item); //Set the url string
      testResult = urlV.isValid(testString);
      System.out.println(String.format("Url Tested: %s", testString));
      System.out.println(String.format("Should Be: %s      Actual: %s",testCompare,testResult));
      System.out.println("---------------\n");        
      if (testCompare != testResult) {
        patT++;
        testFails++;
        System.out.println(String.format("-------> FAILURE ON: %s Expected was: %s | Actual was: %s\n",testUrl.item, testUrl.valid, testResult));
      }
      else {
        testPass++;
      }
      patTot++;
    }

    /*-------------------------*/
    //Test the Query
    /*-------------------------*/
    System.out.println("--------------------------QUERY SECTION--------------------------------------\n");        
    knownValid = "http://www.google.com:80";
    for (int i=0; i<testUrlQuery.length; i++) {
      ResultPair testUrl = testUrlQuery[i];                
      testCompare = testUrl.valid; //Set the compare bool
      testString = (knownValid + testUrl.item); //Set the url string
      testResult = urlV.isValid(testString);
      System.out.println(String.format("Url Tested: %s", testString));
      System.out.println(String.format("Should Be: %s      Actual: %s",testCompare,testResult));
      System.out.println("---------------\n");        
      if (testCompare != testResult) {
        qT++;
        testFails++;
        System.out.println(String.format("-------> FAILURE ON: %s Expected was: %s | Actual was: %s\n",testUrl.item, testUrl.valid, testResult));
      }
      else {
        testPass++;
      }
      qTot++;
    }

    /*-------------------------*/
    //Summary Printout
    /*-------------------------*/
    System.out.println("---------------------------------------\n");        
    System.out.println(String.format("Scheme Failed: %s of %s total",schT, schTot));
    System.out.println(String.format("Auth Failed  : %s of %s total",authT, authTot));    
    System.out.println(String.format("Port Failed  : %s of %s total",prtT, prtTot));
    System.out.println(String.format("Path Failed  : %s of %s total",patT,patTot));
    System.out.println(String.format("Query Failed : %s of %s total",qT,qTot));
    System.out.println(String.format("Total Pass: %s  Total Fail: %s",testPass,testFails));
    System.out.println("---------------------------------------\n");        
  }

}