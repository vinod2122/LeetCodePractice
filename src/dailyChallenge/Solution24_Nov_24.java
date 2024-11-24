package dailyChallenge;

/*
 * 
 * You are given an n x n integer matrix. You can do the following operation any number of times:

Choose any two adjacent elements of matrix and multiply each of them by -1.
Two elements are considered adjacent if and only if they share a border.

Your goal is to maximize the summation of the matrix's elements. Return the maximum sum of the matrix's elements using the operation mentioned above.

 

Example 1:


Input: matrix = [[1,-1],[-1,1]]
Output: 4
Explanation: We can follow the following steps to reach sum equals 4:
- Multiply the 2 elements in the first row by -1.
- Multiply the 2 elements in the first column by -1.
Example 2:


Input: matrix = [[1,2,3],[-1,-2,-3],[1,2,3]]
Output: 16
Explanation: We can follow the following step to reach sum equals 16:
- Multiply the 2 last elements in the second row by -1.
 

Constraints:

n == matrix.length == matrix[i].length
2 <= n <= 250
-105 <= matrix[i][j] <= 105
 */
public class Solution24_Nov_24 {
    public long maxMatrixSum(int[][] matrix) {
        long totalSum = 0;
        int negativeCount = 0;
        int minAbsoluteValue = Integer.MAX_VALUE;
        
        // Traverse the matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int value = matrix[i][j];
                totalSum += Math.abs(value); // Add absolute value to total sum
                
                // Count negative values
                if (value < 0) {
                    negativeCount++;
                }
                
                // Track smallest absolute value
                minAbsoluteValue = Math.min(minAbsoluteValue, Math.abs(value));
            }
        }
        
        // If the count of negatives is odd, adjust the total sum
        if (negativeCount % 2 != 0) {
            totalSum -= 2 * minAbsoluteValue;
        }
        
        return totalSum;
    }

}
