package com.hodor.simple;

/**
 * Write a program to find the sum of Integers present in the string. The
 * integer present in the string are separated by the one or more space symbol
 * 
 * For example: for the input string = "12 text   my sham 7  3   "
 * 
 * The sum of integer in the above input string would become 22
 * 
 * @author hodorgeek
 *
 */
public class SumOfIntegersFromString {

	private static final String NUMBER = "0123456789";

	/** It matches one or many white spaces. **/
	private static final String REG_EXPR_MATCH_ONE_OR_MORE_SPACES = "\\s+";

	// Logic 1
	private static int sumOfIntegersFromString(final String input) {
		int count = 0;
		String splits[] = input.split(REG_EXPR_MATCH_ONE_OR_MORE_SPACES);
		for (String s : splits) {
			if (isNumber(s.trim())) {
				Integer no = new Integer(s);
				count = count + no;
			}
		}
		return count;
	}

	private static boolean isNumber(String str) {
		boolean isNumber = Boolean.FALSE;
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			String s = Character.toString(str.charAt(i));
			if (NUMBER.contains(s)) {
				count = count + 1;
			}
		}
		if (count == str.length()) {
			isNumber = Boolean.TRUE;
		}
		return isNumber;
	}

	public static void main(String[] args) {
		String text = "12 Test 3   7 text123   1 2   -/+25 ";
		System.out.println(sumOfIntegersFromString(text));
	}
}
