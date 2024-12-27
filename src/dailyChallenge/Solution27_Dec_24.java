package dailyChallenge;
/*
 * 
 * You are given an integer array values where values[i] represents the value of the ith sightseeing spot. Two sightseeing spots i and j have a distance j - i between them.

The score of a pair (i < j) of sightseeing spots is values[i] + values[j] + i - j: the sum of the values of the sightseeing spots, minus the distance between them.

Return the maximum score of a pair of sightseeing spots.

 

Example 1:

Input: values = [8,1,5,2,6]
Output: 11
Explanation: i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11
Example 2:

Input: values = [1,2]
Output: 2
 

Constraints:

2 <= values.length <= 5 * 104
1 <= values[i] <= 1000
 */
public class Solution27_Dec_24 {
    public int maxScoreSightseeingPair(int[] values) {
        int maxScore = 0;  // Stores the maximum score
        int maxI = values[0];  // Keeps track of the maximum value of values[i] + i

        for (int j = 1; j < values.length; j++) {
            // Calculate the score for the current pair (i, j)
            maxScore = Math.max(maxScore, maxI + values[j] - j);

            // Update maxI to include the current j
            maxI = Math.max(maxI, values[j] + j);
        }

        return maxScore;
    }

}
