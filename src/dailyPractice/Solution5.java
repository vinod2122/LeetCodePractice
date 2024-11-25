package dailyPractice;

/*
 * 
 * Nearly everyone has used the Multiplication Table. The multiplication table of size m x n is an integer matrix mat where mat[i][j] == i * j (1-indexed).

Given three integers m, n, and k, return the kth smallest element in the m x n multiplication table.

 

Example 1:


Input: m = 3, n = 3, k = 5
Output: 3
Explanation: The 5th smallest number is 3.
Example 2:


Input: m = 2, n = 3, k = 6
Output: 6
Explanation: The 6th smallest number is 6.
 

Constraints:

1 <= m, n <= 3 * 104
1 <= k <= m * n
 */
public class Solution5 {
    public int findKthNumber(int m, int n, int k) {
        int left = 1, right = m * n;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = 0;
            
            // Count the number of elements <= mid
            for (int i = 1; i <= m; i++) {
                count += Math.min(mid / i, n);  // Add the number of elements <= mid in row i
            }
            
            if (count >= k) {
                right = mid;  // We need a smaller or equal number
            } else {
                left = mid + 1;  // We need a larger number
            }
        }
        
        return left;
    }

}
