package dailyChallenge;

/*
 * 
 * Given an integer array arr, remove a subarray (can be empty) from arr such that the remaining elements in arr are non-decreasing.

Return the length of the shortest subarray to remove.

A subarray is a contiguous subsequence of the array.

 

Example 1:

Input: arr = [1,2,3,10,4,2,3,5]
Output: 3
Explanation: The shortest subarray we can remove is [10,4,2] of length 3. The remaining elements after that will be [1,2,3,3,5] which are sorted.
Another correct solution is to remove the subarray [3,10,4].
Example 2:

Input: arr = [5,4,3,2,1]
Output: 4
Explanation: Since the array is strictly decreasing, we can only keep a single element. Therefore we need to remove a subarray of length 4, either [5,4,3,2] or [4,3,2,1].
Example 3:

Input: arr = [1,2,3]
Output: 0
Explanation: The array is already non-decreasing. We do not need to remove any elements.
 

Constraints:

1 <= arr.length <= 105
0 <= arr[i] <= 109

 */
public class Solution15_Nov_24 {
	public int findLengthOfShortestSubarray(int[] arr) {
	int n = arr.length;
    
    // Step 1: Find the longest non-decreasing subarray from the start.
    int left = 0;
    while (left < n - 1 && arr[left] <= arr[left + 1]) {
        left++;
    }
    
    // If the whole array is already sorted
    if (left == n - 1) {
        return 0;
    }
    
    // Step 2: Find the longest non-decreasing subarray from the end.
    int right = n - 1;
    while (right > 0 && arr[right - 1] <= arr[right]) {
        right--;
    }
    
    // Step 3: Initialize the minimum length to remove as either removing from the beginning or end
    int minToRemove = Math.min(n - left - 1, right);
    
    // Step 4: Try to merge the left and right sorted segments
    int i = 0, j = right;
    while (i <= left && j < n) {
        if (arr[i] <= arr[j]) {
            // Calculate the length to keep (from start of left to end of right)
            minToRemove = Math.min(minToRemove, j - i - 1);
            i++;
        } else {
            j++;
        }
    }
    
    return minToRemove;
}

}
