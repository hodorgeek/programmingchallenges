
package com.hodor.simple;

/**
 * 
 * A Program to check if strings are rotations of each other or not Given a
 * string s1 and a string s2, write a snippet to say whether s2 is a rotation of
 * s1 using only one call to strstr routine? (eg given s1 = ABCD and s2 = CDAB,
 * return true, given s1 = ABCD, and s2 = ACBD , return false). Write a program
 * to check if the two strings are rotationaly equals. The rotaionaly equals
 * string can be defined as follows
 * 
 * areRotaionalyEquals("nullpointer", "ternullpoin") -> true
 * areRotaionalyEquals("nullpointer", "interponull") -> false
 * 
 * areRotaionalyEquals("nullpointer", "ternullpoin") -> true
 * areRotaionalyEquals("nullpointer", "ternull") -> false
 * 
 * areRotaionalyEquals("", "") -> true | areRotaionalyEquals("", "ternullpoin")
 * -> false
 * 
 * 
 * The two strings are rotationaly equals then return true else false.
 * 
 * 
 * 
 * @author hodorgeek
 *
 */
public class CheckStringAreRotationalyEquals {

	private static boolean areRotaionalyEquals(String s1, String s2) {
		boolean areRotaionalyEquals = Boolean.FALSE;
		int i = 0, j = 0;
		if ((s1 == null && s2 == null)) {
			areRotaionalyEquals = Boolean.TRUE;
		} else {
			if (s1 != null && s2 != null) {
				// if both the strings are not null then check for
				// are they rotaionaly equals
				if (s1.isEmpty() && s2.isEmpty()) {
					areRotaionalyEquals = Boolean.TRUE;
				} else if (s1.length() == s2.length()) {
					char ch2 = s2.charAt(j);
					boolean match = Boolean.TRUE;
					int count = 0;
					for (i = 0; i < s1.length(); i++) {
						char ch1 = s1.charAt(i);
						if (ch1 == ch2) {
							j++;
							count++;
							if (j != s2.length()) {
								ch2 = s2.charAt(j);
							}
						}
					}
					// check for the remaining character present in the s2 and
					// start it matched with the s1
					if (j != s2.length()) {

						ch2 = s2.charAt(j);

						for (int k = 0; k < i - j; k++) {
							char ch1 = s1.charAt(k);
							if (ch1 == ch2) {
								j++;
								count++;
								if (j != s2.length()) {
									ch2 = s2.charAt(j);
								}
							} else {
								match = Boolean.FALSE;
								break;
							}
						}

					}

					// if all the character matches of both the strings
					// sequentially then return TRUE else FALSE
					if (j == count && match) {
						areRotaionalyEquals = Boolean.TRUE;
					} else {
						areRotaionalyEquals = Boolean.FALSE;
					}
				}
			}
		}

		return areRotaionalyEquals;
	}

	public static void main(String[] args) {
		System.out.println("The two strings are rotionaly equals => ");
		String s1 = "nullpointer";
		String s2 = "ternullpoin";
		boolean result = areRotaionalyEquals(s1, s2);

		System.out.println(result);

		s1 = "nullpointer";
		s2 = "interponull";
		result = areRotaionalyEquals(s1, s2);
		System.out.println(result);

		s1 = "nullpointer";
		s2 = "rnullpointe";
		result = areRotaionalyEquals(s1, s2);
		System.out.println(result);

		result = areRotaionalyEquals("", "");
		System.out.println(result);

		result = areRotaionalyEquals("", "ternullpoin");
		System.out.println(result);

		result = areRotaionalyEquals(null, "");
		System.out.println(result);

		result = areRotaionalyEquals("", null);
		System.out.println(result);

		result = areRotaionalyEquals(null, null);
		System.out.println(result);

		result = areRotaionalyEquals("hodor", "orhod");
		System.out.println(result);

		result = areRotaionalyEquals("sh", "hs");
		System.out.println(result);

		result = areRotaionalyEquals("persistent", "tentpersis");
		System.out.println(result);

		result = areRotaionalyEquals("persistent", "tentsisper");
		System.out.println(result);

		result = areRotaionalyEquals("ternullpoin", "ternullpoin");
		System.out.println(result);
	}
}
