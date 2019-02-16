package math;

public class ProjectionAreaOf3DShapes {
	/*
	 * Q: On a N * N grid, we place some 1 * 1 * 1 cubes that are axis-aligned with the x, y, and z axes. 
	 * Each value v = grid[i][j] represents a tower of v cubes placed on top of grid cell (i, j). Now 
	 * we view the projection of these cubes onto the xy, yz, and zx planes. A projection is like a 
	 * shadow, that maps our 3 dimensional figure to a 2 dimensional plane. Here, we are viewing 
	 * the "shadow" when looking at the cubes from the top, the front, and the side. Return the total 
	 * area of all three projections.
	 * */
	
	/*
	 * Algorithm: 
	 * 1. Area for xy is the number of nonzero values in the 2D array.
	 * 2. Area for xz is the sum of each column's max value.
	 * 3. Area for yz is the sum of each row's max value.
	 * Area = A(xy) + A(xz) + A(yz)
	 * 
	 * Performance Analysis:
	 * T: O(mn) m is the number of rows, n is the number of columns.
	 * S: O(1)
	 * */
	
	public int projectArea(int[][] grid) {
		// corner case
		if (grid == null || grid.length == 0 || grid[0].length ==  0) {
			return 0;
		}
		int rows = grid.length, cols = grid[0].length;
		int sum = 0;
		for (int i = 0; i < rows; ++i) {
			int Ayz = 0;
			for (int j = 0; j < cols; ++j) {
				sum = grid[i][j] == 0 ? sum : sum + 1;
				Ayz = Math.max(Ayz, grid[i][j]);
			}
			sum += Ayz;
		}
		for (int i = 0; i < cols; ++i) {
			int Axz = 0;
			for (int j =  0; j < rows; ++j) {
				Axz = Math.max(Axz, grid[j][i]);
			}
			sum += Axz;
		}
		return sum;
	}
}
