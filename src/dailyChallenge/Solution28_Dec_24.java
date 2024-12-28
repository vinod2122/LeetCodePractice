package dailyChallenge;
/*
 * 
 * Given an integer array nums and an integer k, find three non-overlapping subarrays of length k with maximum sum and return them.

Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

 

Example 1:

Input: nums = [1,2,1,2,6,7,5,1], k = 2
Output: [0,3,5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
Example 2:

Input: nums = [1,2,1,2,1,2,1,2,1], k = 2
Output: [0,2,4]
 

Constraints:

1 <= nums.length <= 2 * 104
1 <= nums[i] < 216
1 <= k <= floor(nums.length / 3)
 */
public class Solution28_Dec_24 {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;

        // Step 1: Calculate the sum of each window of size k
        int[] windowSums = new int[n - k + 1];
        int currentSum = 0;
        for (int i = 0; i < n; i++) {
            currentSum += nums[i];
            if (i >= k - 1) {
                windowSums[i - k + 1] = currentSum;
                currentSum -= nums[i - k + 1];
            }
        }

        // Step 2: Calculate the left and right max sum indices
        int[] left = new int[windowSums.length];
        int[] right = new int[windowSums.length];

        // Fill left array
        int maxIndex = 0;
        for (int i = 0; i < windowSums.length; i++) {
            if (windowSums[i] > windowSums[maxIndex]) {
                maxIndex = i;
            }
            left[i] = maxIndex;
        }

        // Fill right array
        maxIndex = windowSums.length - 1;
        for (int i = windowSums.length - 1; i >= 0; i--) {
            if (windowSums[i] >= windowSums[maxIndex]) {
                maxIndex = i;
            }
            right[i] = maxIndex;
        }

        // Step 3: Find the best middle index and calculate the result
        int[] result = new int[3];
        int maxSum = 0;

        for (int mid = k; mid <= windowSums.length - k - 1; mid++) {
            int l = left[mid - k];          // Best left index
            int r = right[mid + k];        // Best right index
            int totalSum = windowSums[l] + windowSums[mid] + windowSums[r];
            if (totalSum > maxSum) {
                maxSum = totalSum;
                result[0] = l;
                result[1] = mid;
                result[2] = r;
            }
        }

        return result;
    }

}
