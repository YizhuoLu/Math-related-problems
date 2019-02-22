package math;

public class SimilarRGB {
	/*
	 * Q: In the following, every capital letter represents some hexadecimal digit from 0 to f. The 
	 * red-green-blue color "#AABBCC" can be written as "#ABC" in shorthand.  For example, "#15c" is 
	 * shorthand for the color "#1155cc". Now, say the similarity between two colors "#ABCDEF" and 
	 * "#UVWXYZ" is -(AB - UV)^2 - (CD - WX)^2 - (EF - YZ)^2. Given the color "#ABCDEF", return 
	 * a 7 character color that is most similar to #ABCDEF, and has a shorthand (that is, it can 
	 * be represented as some "#XYZ"
	 * */
	
	/*
	 * Algorithm:
	 * since same two consecutive hex digits are all the multiples of 17, (0x00->0, 0x11->17, 0x22->34...) 
	 * given the two digits we have say 'x' and 'y', we get decimal sum of it (x * 16 + y). We first use
	 * sum / 17 to get the index, then we use 'sum % 17' to tell if we should choose the larger one or leave
	 * at the original index that we obtain. if (remainder>17/2) we choose the larger one. else: stay.
	 * 
	 * T: O(1)
	 * S: O(1)
	 * */
	public static String similarRGB(String color) {
		StringBuilder sb = new StringBuilder();
		sb.append('#');
		char[] ch = color.toCharArray();
		for (int i = 1; i < ch.length; i += 2) {
			sb.append(getSimilar(ch[i], ch[i+1]));
		}
		return sb.toString();
	}
	
	private static String getSimilar(char a, char b) {
		int first = a >= '0' && a <= '9' ? a  - '0' : a - 'a' + 10;
		int second = b >= '0' && b <= '9' ? b - '0' : b - 'a' + 10;
		int sum = first * 16 + second;
		int index = sum / 17;
		int remainder = sum % 17;
		if (remainder > 17 / 2) {
			index++;
		}
		char c = index >= 0 && index <= 9 ? (char) ('0'+ index) : (char) (index - 10 + 'a');
		return String.valueOf(c) + String.valueOf(c);
	}
	
	public static void main(String[] args) {
//		char x = 'j';
//		char y  = (char) (x + 1);
		String color = "#09f166";
		System.out.println(similarRGB(color));
	}
}
