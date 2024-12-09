package dailyChallenge;
/*
 * 
 * An array is considered special if every pair of its adjacent elements contains two numbers with different parity.

You are given an array of integer nums and a 2D integer matrix queries, where for queries[i] = [fromi, toi] your task is to check that 
subarray
 nums[fromi..toi] is special or not.

Return an array of booleans answer such that answer[i] is true if nums[fromi..toi] is special.

 

Example 1:

Input: nums = [3,4,1,2,6], queries = [[0,4]]

Output: [false]

Explanation:

The subarray is [3,4,1,2,6]. 2 and 6 are both even.

Example 2:

Input: nums = [4,3,1,6], queries = [[0,2],[2,3]]

Output: [false,true]

Explanation:

The subarray is [4,3,1]. 3 and 1 are both odd. So the answer to this query is false.
The subarray is [1,6]. There is only one pair: (1,6) and it contains numbers with different parity. So the answer to this query is true.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 105
1 <= queries.length <= 105
queries[i].length == 2
0 <= queries[i][0] <= queries[i][1] <= nums.length - 1
 */
public class Solution09_Dec_24 {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = nums.length;
        // Initialize prefixBad array with n+1 elements
        int[] prefixBad = new int[n + 1];
        prefixBad[0] = 0;
        
        for(int i = 0; i < n; i++) {
            // If i < n-1 and nums[i] and nums[i+1] have same parity
            if(i < n-1 && (nums[i] % 2) == (nums[i+1] % 2)) {
                prefixBad[i + 1] = prefixBad[i] + 1;
            } else {
                prefixBad[i + 1] = prefixBad[i];
            }
        }
        // Handle the last element
        // prefixBad[n] = prefixBad[n-1] since there's no i=n-1 to i=n
        // Already handled in the loop since i < n in the loop
        // So prefixBad[n] = prefixBad[n-1] if n >=1
        // But the loop already sets prefixBad[n] correctly when i=n-1
        // So no need to set it again
        
        int q = queries.length;
        boolean[] answer = new boolean[q];
        
        for(int i = 0; i < q; i++) {
            int from = queries[i][0];
            int to = queries[i][1];
            if(from == to) {
                // Single element, automatically special
                answer[i] = true;
            } else {
                // Check if number of bad pairs in [from, to-1] is zero
                // which means prefixBad[to] - prefixBad[from] ==0
                if(prefixBad[to] - prefixBad[from] == 0) {
                    answer[i] = true;
                } else {
                    answer[i] = false;
                }
            }
        }
        
        return answer;
    }

}
