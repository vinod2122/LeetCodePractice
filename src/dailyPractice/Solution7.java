package dailyPractice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 
 * You are asked to cut off all the trees in a forest for a golf event. The forest is represented as an m x n matrix. In this matrix:

0 means the cell cannot be walked through.
1 represents an empty cell that can be walked through.
A number greater than 1 represents a tree in a cell that can be walked through, and this number is the tree's height.
In one step, you can walk in any of the four directions: north, east, south, and west. If you are standing in a cell with a tree, you can choose whether to cut it off.

You must cut off the trees in order from shortest to tallest. When you cut off a tree, the value at its cell becomes 1 (an empty cell).

Starting from the point (0, 0), return the minimum steps you need to walk to cut off all the trees. If you cannot cut off all the trees, return -1.

Note: The input is generated such that no two trees have the same height, and there is at least one tree needs to be cut off.

 

Example 1:


Input: forest = [[1,2,3],[0,0,4],[7,6,5]]
Output: 6
Explanation: Following the path above allows you to cut off the trees from shortest to tallest in 6 steps.
Example 2:


Input: forest = [[1,2,3],[0,0,0],[7,6,5]]
Output: -1
Explanation: The trees in the bottom row cannot be accessed as the middle row is blocked.
Example 3:

Input: forest = [[2,3,4],[0,0,5],[8,7,6]]
Output: 6
Explanation: You can follow the same path as Example 1 to cut off all the trees.
Note that you can cut off the first tree at (0, 0) before making any steps.
 

Constraints:

m == forest.length
n == forest[i].length
1 <= m, n <= 50
0 <= forest[i][j] <= 109
Heights of all trees are distinct.
 */
public class Solution7 {
    // Directions for moving north, east, south, and west
    private static final int[] DIRECTIONS = {-1, 0, 1, 0, 0, -1, 0, 1};
    
    public int cutOffTree(List<List<Integer>> forest) {
        int m = forest.size();
        int n = forest.get(0).size();
        
        // Step 1: Extract all trees (positions and heights) and sort them by height.
        List<int[]> trees = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (forest.get(i).get(j) > 1) {
                    trees.add(new int[]{i, j, forest.get(i).get(j)}); // (row, col, height)
                }
            }
        }
        // Sort trees by height
        trees.sort(Comparator.comparingInt(a -> a[2]));
        
        // Step 2: Start BFS to go from one tree to the next
        int totalSteps = 0;
        int[] start = {0, 0}; // Start position is (0, 0)
        
        for (int[] tree : trees) {
            int steps = bfs(forest, start[0], start[1], tree[0], tree[1]);
            if (steps == -1) {
                return -1; // If any tree is unreachable, return -1
            }
            totalSteps += steps;
            // Update the starting position to the current tree's position after cutting it
            start[0] = tree[0];
            start[1] = tree[1];
            forest.get(tree[0]).set(tree[1], 1); // Cut the tree by setting the cell value to 1
        }
        
        return totalSteps;
    }

    private int bfs(List<List<Integer>> forest, int startX, int startY, int targetX, int targetY) {
        int m = forest.size();
        int n = forest.get(0).size();
        
        // If start or target is blocked, return -1
        if (forest.get(startX).get(startY) == 0 || forest.get(targetX).get(targetY) == 0) {
            return -1;
        }
        
        // BFS setup
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY, 0}); // {x, y, steps}
        visited[startX][startY] = true;
        
        // BFS loop
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1], steps = current[2];
            
            // If we reach the target tree, return the number of steps
            if (x == targetX && y == targetY) {
                return steps;
            }
            
            // Explore the four directions (north, east, south, west)
            for (int i = 0; i < 4; i++) {
                int newX = x + DIRECTIONS[i * 2];
                int newY = y + DIRECTIONS[i * 2 + 1];
                
                // Check if the new position is within bounds and not visited
                if (newX >= 0 && newX < m && newY >= 0 && newY < n && !visited[newX][newY] && forest.get(newX).get(newY) != 0) {
                    visited[newX][newY] = true;
                    queue.offer(new int[]{newX, newY, steps + 1});
                }
            }
        }
        
        // If we cannot reach the target tree, return -1
        return -1;
    }

}
