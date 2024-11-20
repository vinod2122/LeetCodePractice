package dailyChallenge;

import java.util.HashSet;
import java.util.Set;

/*
 * 
 * You are given an integer array nums and an integer k. Find the maximum subarray sum of all the subarrays of nums that meet the following conditions:

The length of the subarray is k, and
All the elements of the subarray are distinct.
Return the maximum subarray sum of all the subarrays that meet the conditions. If no subarray meets the conditions, return 0.

A subarray is a contiguous non-empty sequence of elements within an array.

 

Example 1:

Input: nums = [1,5,4,2,9,9,9], k = 3
Output: 15
Explanation: The subarrays of nums with length 3 are:
- [1,5,4] which meets the requirements and has a sum of 10.
- [5,4,2] which meets the requirements and has a sum of 11.
- [4,2,9] which meets the requirements and has a sum of 15.
- [2,9,9] which does not meet the requirements because the element 9 is repeated.
- [9,9,9] which does not meet the requirements because the element 9 is repeated.
We return 15 because it is the maximum subarray sum of all the subarrays that meet the conditions
Example 2:

Input: nums = [4,4,4], k = 3
Output: 0
Explanation: The subarrays of nums with length 3 are:
- [4,4,4] which does not meet the requirements because the element 4 is repeated.
We return 0 because no subarrays meet the conditions.
 

Constraints:

1 <= k <= nums.length <= 105
1 <= nums[i] <= 105

 */

public class Solution19_Nov_24 {
    public long maximumSubarraySum(int[] nums, int k) {
        long maxSum = 0; // To store the maximum sum
        long currentSum = 0; // To store the current sum of the window
        int start = 0; // Start of the window
        Set<Integer> window = new HashSet<>(); // To ensure all elements in the window are distinct

        for (int end = 0; end < nums.length; end++) {
            // If the element is already in the window, shrink it from the left
            while (window.contains(nums[end])) {
                window.remove(nums[start]);
                currentSum -= nums[start];
                start++;
            }

            // Add the current element to the window
            window.add(nums[end]);
            currentSum += nums[end];

            // Check if the window size is exactly k
            if (end - start + 1 == k) {
                // Update the maximum sum
                maxSum = Math.max(maxSum, currentSum);

                // Slide the window by removing the leftmost element
                window.remove(nums[start]);
                currentSum -= nums[start];
                start++;
            }
        }

        return maxSum;
    }

}
