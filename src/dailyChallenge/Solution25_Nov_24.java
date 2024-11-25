package dailyChallenge;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * 
 * On an 2 x 3 board, there are five tiles labeled from 1 to 5, and an empty square represented by 0. A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given the puzzle board board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.

 

Example 1:


Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.
Example 2:


Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.
Example 3:


Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]
 

Constraints:

board.length == 2
board[i].length == 3
0 <= board[i][j] <= 5
Each value board[i][j] is unique.

 */
public class Solution25_Nov_24 {
    public int slidingPuzzle(int[][] board) {
        // Convert the board to a string for easy processing
        StringBuilder start = new StringBuilder();
        for (int[] row : board) {
            for (int num : row) {
                start.append(num);
            }
        }
        
        String target = "123450";
        int[][] neighbors = {
            {1, 3}, // Neighbors for index 0
            {0, 2, 4}, // Neighbors for index 1
            {1, 5}, // Neighbors for index 2
            {0, 4}, // Neighbors for index 3
            {1, 3, 5}, // Neighbors for index 4
            {2, 4} // Neighbors for index 5
        };
        
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        queue.offer(start.toString());
        visited.add(start.toString());
        int moves = 0;
        
        // BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                if (current.equals(target)) {
                    return moves;
                }
                
                // Find the position of '0'
                int zeroIndex = current.indexOf('0');
                
                // Generate new states by swapping '0' with neighbors
                for (int neighbor : neighbors[zeroIndex]) {
                    StringBuilder nextState = new StringBuilder(current);
                    // Swap
                    nextState.setCharAt(zeroIndex, current.charAt(neighbor));
                    nextState.setCharAt(neighbor, '0');
                    
                    String nextString = nextState.toString();
                    if (!visited.contains(nextString)) {
                        queue.offer(nextString);
                        visited.add(nextString);
                    }
                }
            }
            moves++;
        }
        
        return -1; // If the target state is not reachable
    }

}
