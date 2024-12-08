package contest;

import java.util.HashSet;
import java.util.Set;

/*
 * 
 * You are given an array points where points[i] = [xi, yi] represents the coordinates of a point on an infinite plane.

Your task is to find the maximum area of a rectangle that:

Can be formed using four of these points as its corners.
Does not contain any other point inside or on its border.
Has its edges parallel to the axes.
Return the maximum area that you can obtain or -1 if no such rectangle is possible.

 

Example 1:

Input: points = [[1,1],[1,3],[3,1],[3,3]]

Output: 4

Explanation:



We can make a rectangle with these 4 points as corners and there is no other point that lies inside or on the border. Hence, the maximum possible area would be 4.

Example 2:

Input: points = [[1,1],[1,3],[3,1],[3,3],[2,2]]

Output: -1

Explanation:



There is only one rectangle possible is with points [1,1], [1,3], [3,1] and [3,3] but [2,2] will always lie inside it. Hence, returning -1.

Example 3:

Input: points = [[1,1],[1,3],[3,1],[3,3],[1,2],[3,2]]

Output: 2

Explanation:



The maximum area rectangle is formed by the points [1,3], [1,2], [3,2], [3,3], which has an area of 2. Additionally, the points [1,1], [1,2], [3,1], [3,2] also form a valid rectangle with the same area.

 

Constraints:

1 <= points.length <= 10
points[i].length == 2
0 <= xi, yi <= 100
All the given points are unique.

Note: Please do not copy the description during the contest to maintain the integrity of your submissions.©leetcode

 */
public class Contest08_Dec_24_2 {
	public int maxRectangleArea(int[][] points) {
        // Use a set for fast lookup
        Set<String> pointSet = new HashSet<>();
        for (int[] point : points) {
            pointSet.add(point[0] + "," + point[1]);
        }

        int maxArea = -1;

        // Iterate through all pairs of points to act as opposite corners
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];

                // Ensure points form a rectangle with non-equal x and y
                if (x1 != x2 && y1 != y2) {
                    // Calculate the other two corners
                    String corner1 = x1 + "," + y2;
                    String corner2 = x2 + "," + y1;

                    // Check if these corners exist
                    if (pointSet.contains(corner1) && pointSet.contains(corner2)) {
                        // Validate no other points lie inside or on the border
                        boolean isValid = true;
                        for (int[] point : points) {
                            int px = point[0], py = point[1];
                            // Ignore the four corners of the rectangle
                            if ((px == x1 && py == y1) || (px == x2 && py == y2) || 
                                (px == x1 && py == y2) || (px == x2 && py == y1)) {
                                continue;
                            }
                            // Check if point lies inside or on the border
                            if (px >= Math.min(x1, x2) && px <= Math.max(x1, x2) &&
                                py >= Math.min(y1, y2) && py <= Math.max(y1, y2)) {
                                isValid = false;
                                break;
                            }
                        }
                        // Calculate area if valid
                        if (isValid) {
                            int area = Math.abs(x1 - x2) * Math.abs(y1 - y2);
                            maxArea = Math.max(maxArea, area);
                        }
                    }
                }
            }
        }

        return maxArea;
    }

}
