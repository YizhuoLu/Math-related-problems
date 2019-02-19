package math;

public class SpiralMatrixIII {
	/*
	 * Q: On a 2 dimensional grid with R rows and C columns, we start at (r0, c0) facing east. Here, the 
	 * north-west corner of the grid is at the first row and column, and the south-east corner of the grid 
	 * is at the last row and column. Now, we walk in a clockwise spiral shape to visit every position 
	 * in this grid. Whenever we would move outside the boundary of the grid, we continue our walk 
	 * outside the grid (but may return to the grid boundary later.) Eventually, we reach all R * C spaces 
	 * of the grid.
	 * Return a list of coordinates representing the positions of the grid in the order they were visited.
	 * */
	
	/*
	 * Algorithm: 
	 *  Intuitively, we don't need to care about whether we are within the boundary of grid or not, as long as we
	 *  continue walking in a spiral way, we can ensure that we will reach all the grids.
	 *  So first we need to the length that we walk each time:
	 *  	each time we go east 1, south 1, west 2, north 2, east 3, south 3, west 4, north 4....
	 *  in the process, we just need to record the number of valid grid that we visited and each time we
	 *  do count++, at last, we check if count == R * C, we return  result;
	 *  
	 * Complexity Analysis:
	 * T: O((max(R, C))^2) since we must go in current direction at least R or C before spiral.
	 * S: O(R*C) the space is used by our answer.
	 * */
	
	public static int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
		// define east, south, west, north four directions.
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		
		int[][] res = new int[R * C][2];
		int t = 0;
		res[t++] = new int[] {r0, c0};
		// corner case
		if (R * C == 1) {
			return res;
		}
		
		for (int k = 1; k < 2 * R * C; k += 2) {
			// k represents the base each base we do increment 1 + (i/2) = 1, 2 --- 3 + (i/2) = 3, 4
			for (int i = 0; i < 4; ++i) {
				// i represents the direction we go currently
				int step = k + (i / 2); // step is the number of steps we should walk at current direction
				for (int s = 0; s < step; ++s) {
					r0 += dr[i];
					c0 += dc[i];
					if (r0 >= 0 && r0 < R && c0 >= 0 && c0 < C)  {
						res[t++] = new int[] {r0, c0};
						if (t == R * C) return res;
					}
				}
			}
		}
		throw null;
	}
	
	public static void main(String[] args) {
		int[][] res = spiralMatrixIII(5, 6, 1, 4);
		for (int i = 0; i <  res.length; ++i) {
			System.out.println(res[i][0] + " " + res[i][1]);
		}
	}
}
