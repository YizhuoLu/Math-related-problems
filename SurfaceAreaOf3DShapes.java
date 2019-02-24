package math;

public class SurfaceAreaOf3DShapes {
	/*
	 * Q: On a N * N grid, we place some 1 * 1 * 1 cubes. Each value v = grid[i][j] represents 
	 * a tower of v cubes placed on top of grid cell (i, j). Return the total surface area of 
	 * the resulting shapes.
	 * */
	
	/*
	 * Algorithm: 
	 * Intuitively, for each grid[i][j] = v, the surface area = 4 * v + 2.
	 * but if there are neighboring tower, we need to deduct the hidden area which is:
	 * 	min(v1, v2) * 2.
	 * 
	 * Complexity Analysis:
	 * T: O(n^2)
	 * S: O(1)
	 * */
	
	public static int surfaceArea(int[][] grid) {
		// assumption: grid is not null and its rows and columns are both not 0.
		int N = grid.length;
		int res = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (grid[i][j] > 0) res += grid[i][j] * 4 + 2;
				if (i > 0) res -= Math.min(grid[i][j], grid[i - 1][j]) * 2;
				if (j > 0) res -= Math.min(grid[i][j], grid[i][j - 1]) * 2;
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		int[][] grid = {{1, 0}, {0, 2}};
		// 
		System.out.println(surfaceArea(grid));
	}
}
