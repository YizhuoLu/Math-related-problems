package math;

public class ExcelSheetColumnNumber {
	/*
	 * Q: Given a column title as appear in an Excel sheet, return its corresponding column number.
	 * */
	
	/*
	 * Algorithm: 
	 * 	It's just 26 base number. Say the given string is k bits. so, it equals to 26^(k-1)*ch[0] + 26^(k-2)
	 * 	*ch[1]+...+ 26^0*ch[k-1].
	 * 
	 * Complexity Analysis:
	 * T: O(n) n is the length of the given string.
	 * S: O(1)
	 * */
	
	public static int titleToNumber(String s) {
		// corner case
		if (s == null || s.length() == 0) {
			return 0;
		}
		int k = s.length();
		int exp = k - 1;
		int res = 0;
		for (char ch : s.toCharArray()) {
			res += (int)Math.pow(26, exp) * (ch - 'A' + 1);
			exp--;
		}
		return res;
	}
	
	public static void main(String[] args) {
		String s = "ZY";
		System.out.println("Number is: " + titleToNumber(s));
	}
}
