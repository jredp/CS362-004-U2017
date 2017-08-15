
import junit.framework.TestCase;


public class UrlValidatorTestJared extends TestCase {
   
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
