package math;

public class ComplexNumberMultiplication {
	/*
	 * Q: Given two complex numbers all in the form of "a+bi". Return the result of two complex numbers'
	 * multiplications.
	 * */
	
	/*
	 * Algorithm:
	 * 1. Traverse two given string to find the index of the sign.
	 * 2. We get the real part by convert the substring to integer. -> a1, b1
	 * 3. we continue to traverse to find the index of the 'i'
	 * 4. we get the complex part by converting the substring to integer. -> a2, b2
	 * 5. we do multiplication by a1 * b1 + a2 * b2 * (-1) + (a1 * b2 + b1 * a2) i
	 * 
	 * Performance Analysis:
	 * T: O(m + n) m, n is the length of the two given string.
	 * S: O(1)
	 * */
	
	public static String complexNumMultiply(String a, String b) {
		// assumption: a and b are not null
		int M = a.length(), N = b.length();
		int idxOfSignA = findIndex(a);
		int idxOfSignB = findIndex(b);
		int a1 = Integer.valueOf(a.substring(0, idxOfSignA));
		int b1 = Integer.valueOf(b.substring(0, idxOfSignB));
		int a2 = Integer.valueOf(a.substring(idxOfSignA + 1, M - 1));
		int b2 = Integer.valueOf(b.substring(idxOfSignB + 1, N - 1));
		String res = "";
		res = res + (a1 * b1 - a2 *b2) + "+" + (a1* b2 + b1 * a2) + "i";
		return res;
	}
	
	private static int findIndex(String s) {
		char[] ch = s.toCharArray();
		for (int i = 1; i < ch.length; ++i) {
			if (ch[i] == '+' || ch[i] == '-') {
				//System.out.println(i);
				return i;
			}
		}
		return -1;
	}
	
	/*
	 * Algorithm 2: use regular expression to split two given string by "+".
	 * 	Since there can only be one "+" appearing in the right middle of real and complex part.
	 * 
	 * Performance Analysis:
	 * not change
	 * */
	public static String complexNumMultiplyII(String a, String b) {
		String[] s1 = a.split("\\+");
		String[] s2 = b.split("\\+");
		int a1 = Integer.valueOf(s1[0]);
		int b1 = Integer.valueOf(s2[0]);
		int a2 = Integer.valueOf(s1[1].substring(0, s1[1].length() - 1));
		int b2 = Integer.valueOf(s2[1].substring(0, s2[1].length() - 1));
		int t1 = a1 * b1 - a2 *b2;
		int t2 = a1 *b2 +b1 * a2;
		return t1 + "+" + t2 + "i";
	}
	
	public static void main(String[] args) {
		String a = "78+-76i";
		// 1+1i 78+-76i 1+-1i
		String b = "-86+72i";
		// 1+1i -86+72i 1+-1i
		System.out.println("Results of multiplication: " + complexNumMultiplyII(a,b));
	}
}
