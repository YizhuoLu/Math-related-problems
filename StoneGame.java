package math;

public class StoneGame {
	/*
	 * Q: Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a row, 
	 * and each pile has a positive integer number of stones piles[i]. The objective of the game is to end 
	 * with the most stones.  The total number of stones is odd, so there are no ties. Alex and Lee take turns, 
	 * with Alex starting first.  Each turn, a player takes the entire pile of stones from either the beginning 
	 * or the end of the row.  This continues until there are no more piles left, at which point the person 
	 * with the most stones wins.
	 *  Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.
	 * */
	
	/*
	 * Algorithm 1: Dynamic Programming
	 * (Let dp[i][j] be the max score that Alex can get in the range of pile[i], pile[i+1], ..., pilee[j].
	 * 	The turns can be ensured by (i + j + N) %2)
	 * 
	 * dp[i][j] represents the biggest number of stones that current player can get more than opponent picking 
	 * in piles[i]~piles[j].
	 * 
	 * --> Under this definition, we don't need to care about who is the turn to play each time.
	 * base case: dp[i][i] = p[i]
	 * induction rule: dp[i][j] = max(p[i] - dp[i+1][j], p[j] - dp[i][j-1]).
	 * 
	 * Complexity Analysis:
	 * T: O(n^2)
	 * S: O(n^2)
	 * */
	
	public static boolean stoneGame(int[] piles) {
		int n = piles.length;
		int[][] dp = new int[n][n];
		for (int i = 0; i < n; ++i) dp[i][i] = piles[i];
		for (int size = 1; size < n; ++size) {
			for (int i = 0; i < n - size; ++i) {
				int j = i + size;
				dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
			}
		}
		return dp[0][n-1] > 0;
	}
	
	/*
	 * Mathematical optimization:
	 * As long as Alex play initially, he can always win the game since he can either get the all odd-index 
	 * piles or even-index piles which at least one is more than another one.
	 * 
	 * So just return true.
	 * 
	 * T: O(1)
	 * S: O(1)
	 * */
	
	public static void main(String[] args) {
		int[] piles = {5, 3, 4, 5};
		System.out.println("Whether Alex can win? " + stoneGame(piles));
	}
}
