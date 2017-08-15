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