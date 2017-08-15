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
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */

public class UrlValidatorTest extends TestCase {

	public UrlValidatorTest(String testName) {
		super(testName);
	}

	private boolean printStatus = false;
	private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.
  
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
}
