package math;

public class KeysKeyboard {
	/*
	 * Q: Imagine you have a special keyboard with the following keys:
	 * Key 1: (A): Print one 'A' on screen.
	 * Key 2: (Ctrl-A): Select the whole screen.
	 * Key 3: (Ctrl-C): Copy selection to buffer.
	 * Key 4: (Ctrl-V): Print buffer on screen appending it after what has already been printed.
	 * Now, you can only press the keyboard for N times (with the above four keys), find out the 
	 * maximum numbers of 'A' you can print on screen.
	 * 
	 * 1. 1 <= N <= 50
	 * 2. Answers will be in the range of 32-bit signed integer.
	 * */
	
	/*
	 * Algorithm: Dynamic programming  !!! we can press ctrl-v multiple times.
	 *  dp[i] represents the most number of A that we can print.
	 * since ctrl-A,ctrl-C,ctrl-V 3 steps are reserved, and after i steps, we can make i 'A's. then if
	 * we paste, we can get (n-i-1) copies of those As. 
	 * Say i = 3, we have AAA, then n = 7, we have n  - i - 1 = 3 copies: AAA AAA AAA.
	 * So what max number of A I can get is how many copies I make and where I do copy-paste.
	 * induction rule : dp[i] = max(dp[i],dp[j]*(i-j-1)) j is within (1, i-3), i is within 0-N
	 *  
	 * Complexity Analysis:
	 * T: O(n^2)
	 * S: O(n)
	 * */
	
	public int maxA(int N) {
		int[] dp = new int[N + 1];
		for (int i = 0; i <= N; ++i) {
			dp[i] = i;
			for (int j = 1; j < i - 3; ++j) {
				dp[i] = Math.max(dp[i], dp[j] * (i - j - 1));
			}
		}
		return dp[N];
	}
}
