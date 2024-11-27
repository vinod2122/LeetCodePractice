package dailyPractice;

/*
 * 
 * Given an unsorted array of integers nums, return the length of the longest continuous increasing subsequence (i.e. subarray). The subsequence must be strictly increasing.

A continuous increasing subsequence is defined by two indices l and r (l < r) such that it is [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] and for each l <= i < r, nums[i] < nums[i + 1].

 

Example 1:

Input: nums = [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5] with length 3.
Even though [1,3,5,7] is an increasing subsequence, it is not continuous as elements 5 and 7 are separated by element
4.
Example 2:

Input: nums = [2,2,2,2,2]
Output: 1
Explanation: The longest continuous increasing subsequence is [2] with length 1. Note that it must be strictly
increasing.
 

Constraints:

1 <= nums.length <= 104
-109 <= nums[i] <= 109
 */
public class Solution6 {
    public int findLengthOfLCIS(int[] nums) {
        // Edge case: If the array is empty, return 0
        if (nums.length == 0) {
            return 0;
        }
        
        int maxLen = 1;  // The minimum LCIS length is 1 (if there's at least one element)
        int currentLen = 1;  // Start counting from the first element
        
        // Iterate over the array starting from the second element
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                // If the current number is greater than the previous one, increase the length of the subsequence
                currentLen++;
            } else {
                // Otherwise, update the maximum length and reset current subsequence length
                maxLen = Math.max(maxLen, currentLen);
                currentLen = 1;  // Reset for a new subsequence
            }
        }
        
        // Final check for the last subsequence
        maxLen = Math.max(maxLen, currentLen);
        
        return maxLen;
    }

}
