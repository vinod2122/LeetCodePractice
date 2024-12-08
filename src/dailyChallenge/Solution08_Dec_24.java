package dailyChallenge;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/*
 * 
 * You are given a 0-indexed 2D integer array of events where events[i] = [startTimei, endTimei, valuei]. The ith event starts at startTimei and ends at endTimei, and if you attend this event, you will receive a value of valuei. You can choose at most two non-overlapping events to attend such that the sum of their values is maximized.

Return this maximum sum.

Note that the start time and end time is inclusive: that is, you cannot attend two events where one of them starts and the other ends at the same time. More specifically, if you attend an event with end time t, the next event must start at or after t + 1.

 

Example 1:


Input: events = [[1,3,2],[4,5,2],[2,4,3]]
Output: 4
Explanation: Choose the green events, 0 and 1 for a sum of 2 + 2 = 4.
Example 2:

Example 1 Diagram
Input: events = [[1,3,2],[4,5,2],[1,5,5]]
Output: 5
Explanation: Choose event 2 for a sum of 5.
Example 3:


Input: events = [[1,5,3],[1,5,1],[6,6,5]]
Output: 8
Explanation: Choose events 0 and 2 for a sum of 3 + 5 = 8.
 

Constraints:

2 <= events.length <= 105
events[i].length == 3
1 <= startTimei <= endTimei <= 109
1 <= valuei <= 106

 */
public class Solution08_Dec_24 {
    public int maxTwoEvents(int[][] events) {
        // Sort events by end time
        Arrays.sort(events, Comparator.comparingInt(e -> e[1]));

        // Store maximum value up to a point
        int maxValue = 0;
        int maxSum = 0;

        // To perform binary search, store event end times and their maximum value up to that point
        TreeMap<Integer, Integer> endTimeToMaxValue = new TreeMap<>();

        for (int[] event : events) {
            int start = event[0];
            int end = event[1];
            int value = event[2];

            // Find the best non-overlapping event
            Integer lastEndTime = endTimeToMaxValue.floorKey(start - 1);
            int bestNonOverlapValue = lastEndTime == null ? 0 : endTimeToMaxValue.get(lastEndTime);

            // Update max sum by considering the current event and the best non-overlapping event
            maxSum = Math.max(maxSum, value + bestNonOverlapValue);

            // Update the running maximum value up to the current event
            maxValue = Math.max(maxValue, value);
            endTimeToMaxValue.put(end, maxValue);
        }

        return maxSum;
    }

}
