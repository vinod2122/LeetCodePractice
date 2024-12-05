package dailyChallenge;

import java.util.PriorityQueue;

/*
 * You are given a m x n matrix grid consisting of non-negative integers where grid[row][col] represents the minimum time required to be able to visit the cell (row, col), which means you can visit the cell (row, col) only when the time you visit it is greater than or equal to grid[row][col].

You are standing in the top-left cell of the matrix in the 0th second, and you must move to any adjacent cell in the four directions: up, down, left, and right. Each move you make takes 1 second.

Return the minimum time required in which you can visit the bottom-right cell of the matrix. If you cannot visit the bottom-right cell, then return -1.

 

Example 1:



Input: grid = [[0,1,3,2],[5,1,2,5],[4,3,8,6]]
Output: 7
Explanation: One of the paths that we can take is the following:
- at t = 0, we are on the cell (0,0).
- at t = 1, we move to the cell (0,1). It is possible because grid[0][1] <= 1.
- at t = 2, we move to the cell (1,1). It is possible because grid[1][1] <= 2.
- at t = 3, we move to the cell (1,2). It is possible because grid[1][2] <= 3.
- at t = 4, we move to the cell (1,1). It is possible because grid[1][1] <= 4.
- at t = 5, we move to the cell (1,2). It is possible because grid[1][2] <= 5.
- at t = 6, we move to the cell (1,3). It is possible because grid[1][3] <= 6.
- at t = 7, we move to the cell (2,3). It is possible because grid[2][3] <= 7.
The final time is 7. It can be shown that it is the minimum time possible.
Example 2:



Input: grid = [[0,2,4],[3,2,1],[1,0,4]]
Output: -1
Explanation: There is no path from the top left to the bottom-right cell.
 

Constraints:

m == grid.length
n == grid[i].length
2 <= m, n <= 1000
4 <= m * n <= 105
0 <= grid[i][j] <= 105
grid[0][0] == 0
 
 */
public class Solution29_Nov_24 {
    // Direction vectors for moving in four directions: right, left, down, up
    int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
    int rows, cols;
    boolean[][] visitedCells;

    public int minimumTime(int[][] grid) {
        // Base case: If both adjacent cells from the start are inaccessible, return -1
        if (grid[0][1] > 1 && grid[1][0] > 1) {
            return -1;
        }

        // Initialize dimensions of the grid
        rows = grid.length;
        cols = grid[0].length;

        // Priority queue to implement Dijkstra's algorithm (min-heap based on time)
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        visitedCells = new boolean[rows][cols];

        // Add the starting cell to the priority queue: {row, col, time}
        priorityQueue.offer(new int[]{0, 0, 0});

        // Process the priority queue until empty
        while (!priorityQueue.isEmpty()) {
            int[] currentCell = priorityQueue.poll();
            int currentRow = currentCell[0], currentCol = currentCell[1], currentTime = currentCell[2];

            // If the bottom-right cell is reached, return the time taken
            if (currentRow == rows - 1 && currentCol == cols - 1) return currentTime;

            // Skip if the current cell is already visited
            if (visitedCells[currentRow][currentCol]) continue;

            // Mark the current cell as visited
            visitedCells[currentRow][currentCol] = true;

            // Explore all four possible directions
            for (int[] direction : directions) {
                int nextRow = currentRow + direction[0];
                int nextCol = currentCol + direction[1];

                // Check if the neighboring cell is valid (within bounds and not visited)
                if (!isCellValid(nextRow, nextCol)) continue;

                // Calculate the earliest time to reach the neighboring cell
                // Adjust time based on parity to match the cell's constraint
                int timeAdjustment = (grid[nextRow][nextCol] - currentTime) % 2 == 0 ? 1 : 0;
                int nextTime = Math.max(currentTime + 1, grid[nextRow][nextCol] + timeAdjustment);

                // Add the neighboring cell to the priority queue
                priorityQueue.offer(new int[]{nextRow, nextCol, nextTime});
            }
        }
        // If the bottom-right cell is unreachable, return -1
        return -1;
    }

    // Helper method to check if a cell is within bounds and not visited
    private boolean isCellValid(int row, int col) {
        return row >= 0 && col >= 0 && row < rows && col < cols && !visitedCells[row][col];
    }

}
