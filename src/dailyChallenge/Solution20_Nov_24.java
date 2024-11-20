package dailyChallenge;

/*
 * 
 * You are given a string s consisting of the characters 'a', 'b', and 'c' and a non-negative integer k. Each minute, you may take either the leftmost character of s, or the rightmost character of s.

Return the minimum number of minutes needed for you to take at least k of each character, or return -1 if it is not possible to take k of each character.

 

Example 1:

Input: s = "aabaaaacaabc", k = 2
Output: 8
Explanation: 
Take three characters from the left of s. You now have two 'a' characters, and one 'b' character.
Take five characters from the right of s. You now have four 'a' characters, two 'b' characters, and two 'c' characters.
A total of 3 + 5 = 8 minutes is needed.
It can be proven that 8 is the minimum number of minutes needed.
Example 2:

Input: s = "a", k = 1
Output: -1
Explanation: It is not possible to take one 'b' or 'c' so return -1.
 

Constraints:

1 <= s.length <= 105
s consists of only the letters 'a', 'b', and 'c'.
0 <= k <= s.length
 * 
 */

public class Solution20_Nov_24 {
    public int takeCharacters(String s, int k) {
        // Step 1: Count the total occurrences of 'a', 'b', and 'c' in the string
        int[] count = new int[3];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        
        // Step 2: Check if it's possible to take at least k of each character
        if (count[0] < k || count[1] < k || count[2] < k) {
            return -1; // Not possible
        }

        // Step 3: Sliding window to find the largest valid window
        int n = s.length();
        int[] required = {count[0] - k, count[1] - k, count[2] - k};
        int maxWindowLength = 0;
        int[] windowCount = new int[3];
        int left = 0;

        for (int right = 0; right < n; right++) {
            // Expand the window
            windowCount[s.charAt(right) - 'a']++;

            // Shrink the window if it exceeds the required counts
            while (windowCount[0] > required[0] || 
                   windowCount[1] > required[1] || 
                   windowCount[2] > required[2]) {
                windowCount[s.charAt(left) - 'a']--;
                left++;
            }

            // Update the maximum window length
            maxWindowLength = Math.max(maxWindowLength, right - left + 1);
        }

        // Step 4: Calculate the result
        return n - maxWindowLength;
    }

}
