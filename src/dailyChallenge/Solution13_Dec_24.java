package dailyChallenge;

import java.util.Arrays;

/*
 * 
 * You are given an array nums consisting of positive integers.

Starting with score = 0, apply the following algorithm:

Choose the smallest integer of the array that is not marked. If there is a tie, choose the one with the smallest index.
Add the value of the chosen integer to score.
Mark the chosen element and its two adjacent elements if they exist.
Repeat until all the array elements are marked.
Return the score you get after applying the above algorithm.

 

Example 1:

Input: nums = [2,1,3,4,5,2]
Output: 7
Explanation: We mark the elements as follows:
- 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,1,3,4,5,2].
- 2 is the smallest unmarked element, so we mark it and its left adjacent element: [2,1,3,4,5,2].
- 4 is the only remaining unmarked element, so we mark it: [2,1,3,4,5,2].
Our score is 1 + 2 + 4 = 7.
Example 2:

Input: nums = [2,3,5,1,3,2]
Output: 5
Explanation: We mark the elements as follows:
- 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,3,5,1,3,2].
- 2 is the smallest unmarked element, since there are two of them, we choose the left-most one, so we mark the one at index 0 and its right adjacent element: [2,3,5,1,3,2].
- 2 is the only remaining unmarked element, so we mark it: [2,3,5,1,3,2].
Our score is 1 + 2 + 2 = 5.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 106
 */
public class Solution13_Dec_24 {
    public long findScore(int[] nums) {
        int n = nums.length;
        int[][] valueIndexPairs = new int[n][2];

        // Prepare array of value-index pairs
        for (int i = 0; i < n; i++) {
            valueIndexPairs[i][0] = nums[i];
            valueIndexPairs[i][1] = i;
        }

        // Sort the value-index pairs by the value (ascending)
        Arrays.sort(valueIndexPairs, (a, b) -> a[0] - b[0]);

        // Boolean array to track which indices are marked
        boolean[] isMarked = new boolean[n];
        long totalScore = 0;

        // Process the sorted value-index pairs
        for (int[] pair : valueIndexPairs) {
            int value = pair[0];
            int index = pair[1];

            if (!isMarked[index]) {
                // Add the value to the total score
                totalScore += value;

                // Mark the current index and its adjacent indices
                isMarked[index] = true;
                if (index > 0)
                    isMarked[index - 1] = true;
                if (index < n - 1)
                    isMarked[index + 1] = true;
            }
        }

        return totalScore;
    }

}
