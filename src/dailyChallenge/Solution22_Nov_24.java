package dailyChallenge;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*
 * 
 * You are given an m x n binary matrix matrix.

You can choose any number of columns in the matrix and flip every cell in that column (i.e., Change the value of the cell from 0 to 1 or vice versa).

Return the maximum number of rows that have all values equal after some number of flips.

 

Example 1:

Input: matrix = [[0,1],[1,1]]
Output: 1
Explanation: After flipping no values, 1 row has all values equal.
Example 2:

Input: matrix = [[0,1],[1,0]]
Output: 2
Explanation: After flipping values in the first column, both rows have equal values.
Example 3:

Input: matrix = [[0,0,0],[0,0,1],[1,1,0]]
Output: 2
Explanation: After flipping values in the first two columns, the last two rows have equal values.
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 300
matrix[i][j] is either 0 or 1.
 */
public class Solution22_Nov_24 {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<String, Integer> patternFrequency = new HashMap<>();
        for (int[] row : matrix) {
            StringBuilder normalizedPattern = new StringBuilder();
            // Normalize the row by flipping if the first element is 1
            if (row[0] == 0) {
                for (int cell : row) {
                    normalizedPattern.append(cell);
                }
            } else {
                for (int cell : row) {
                    normalizedPattern.append(cell ^ 1);
                }
            }
            // Update frequency of the normalized pattern
            patternFrequency.merge(normalizedPattern.toString(), 1, Integer::sum);
        }
        // Return the maximum frequency among all patterns
        return Collections.max(patternFrequency.values());
    }

}
