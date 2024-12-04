package dailyChallenge;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/*
 * 
 * You are given a 0-indexed 2D integer array grid of size m x n. Each cell has one of two values:

0 represents an empty cell,
1 represents an obstacle that may be removed.
You can move up, down, left, or right from and to an empty cell.

Return the minimum number of obstacles to remove so you can move from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1).

 

Example 1:


Input: grid = [[0,1,1],[1,1,0],[1,1,0]]
Output: 2
Explanation: We can remove the obstacles at (0, 1) and (0, 2) to create a path from (0, 0) to (2, 2).
It can be shown that we need to remove at least 2 obstacles, so we return 2.
Note that there may be other ways to remove 2 obstacles to create a path.
Example 2:


Input: grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]]
Output: 0
Explanation: We can move from (0, 0) to (2, 4) without removing any obstacles, so we return 0.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 105
2 <= m * n <= 105
grid[i][j] is either 0 or 1.
grid[0][0] == grid[m - 1][n - 1] == 0
 */
public class Solution28_Nov_24 {
    public int minimumObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Direction vectors for moving in 4 directions
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // Deque for 0-1 BFS
        Deque<int[]> deque = new LinkedList<>();
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Start from the top-left corner
        deque.offerFirst(new int[]{0, 0});
        dist[0][0] = 0;

        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            int x = curr[0], y = curr[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // Check boundaries
                if (nx >= 0 && ny >= 0 && nx < m && ny < n) {
                    int newDist = dist[x][y] + grid[nx][ny];

                    // Update distance if we find a shorter path
                    if (newDist < dist[nx][ny]) {
                        dist[nx][ny] = newDist;
                        if (grid[nx][ny] == 0) {
                            deque.offerFirst(new int[]{nx, ny});
                        } else {
                            deque.offerLast(new int[]{nx, ny});
                        }
                    }
                }
            }
        }

        return dist[m - 1][n - 1];
    }

}
