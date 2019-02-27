package math;

public class LargestTriangleArea {
	/*
	 * Q: You have a list of points in the plane. Return the area of the largest triangle 
	 * that can be formed by any 3 of the points.
	 * */
	
	/*
	 * Algorithm: get all three points combination and calculate the area using formula:
	 * --> line: ax+by+c=0, (x0,y0), d= abs(a*x0+b*y0+c) / (sqrt(a^2+b^2)).
	 * compare with globalMax each time and return globalMax eventually.
	 * 
	 * 
	 * Formula: abs(Ax(By − Cy) + Bx(Cy − Ay ) 	+Cx (Ay −By) / 2.0)
	 * 
	 * Complexity Analysis:
	 * T: O(n^3)
	 * S: O(1)
	 * */
	
	public double largestTriangleArea(int[][] points) {
		double globalMax = Double.MIN_VALUE;
		int N=points.length;
		for (int i = 0; i <N -2; ++i) {
			for (int j = i + 1;j < N-1; ++j) {
				for (int k = j + 1; k < N; ++k) {
					globalMax = Math.max(globalMax, calArea(points[i], points[j], points[k]));
				}
			}
		}
		return  globalMax;
	}
	
	private double calArea(int[] pt1, int[] pt2, int[] pt3) {
		return Math.abs(pt1[0] * (pt2[1] - pt3[1]) + pt2[0] * (pt3[1] - pt1[1]) + pt3[0] * (pt1[1] - pt2[1])) / 2.0; 
	}
}
