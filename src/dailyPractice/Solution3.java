package dailyPractice;

/*
 * 
 * Given an array nums with n integers, your task is to check if it could become non-decreasing by modifying at most one element.

We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for every i (0-based) such that (0 <= i <= n - 2).

 

Example 1:

Input: nums = [4,2,3]
Output: true
Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
Example 2:

Input: nums = [4,2,1]
Output: false
Explanation: You cannot get a non-decreasing array by modifying at most one element.
 

Constraints:

n == nums.length
1 <= n <= 104
-105 <= nums[i] <= 105
 */
public class Solution3 {
    public boolean checkPossibility(int[] nums) {
        int count = 0; // Count of modifications
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                count++;
                if (count > 1) return false;

                // Modify nums[i-1] or nums[i] to ensure non-decreasing order
                if (i == 1 || nums[i] >= nums[i - 2]) {
                    nums[i - 1] = nums[i]; // Modify nums[i-1]
                } else {
                    nums[i] = nums[i - 1]; // Modify nums[i]
                }
            }
        }
        return true;
    }

}
