package dailyPractice;
/*
 * 
 * Given two strings a and b, return the minimum number of times you should repeat string a so that string b is a substring of it. If it is impossible for b​​​​​​ to be a substring of a after repeating it, return -1.

Notice: string "abc" repeated 0 times is "", repeated 1 time is "abc" and repeated 2 times is "abcabc".

 

Example 1:

Input: a = "abcd", b = "cdabcdab"
Output: 3
Explanation: We return 3 because by repeating a three times "abcdabcdabcd", b is a substring of it.
Example 2:

Input: a = "a", b = "aa"
Output: 2
 

Constraints:

1 <= a.length, b.length <= 104
a and b consist of lowercase English letters.
 */
public class Solution15 {
    public int repeatedStringMatch(String a, String b) {
        int aLen = a.length();
        int bLen = b.length();

        // Minimum repetitions needed
        int repeats = (bLen + aLen - 1) / aLen; // Equivalent to ceil(bLen / aLen)

        // Create a string by repeating a
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < repeats; i++) {
            sb.append(a);
        }

        // Check if b is a substring of the current string
        if (sb.toString().contains(b)) {
            return repeats;
        }

        // Add one more repetition and check again
        sb.append(a);
        if (sb.toString().contains(b)) {
            return repeats + 1;
        }

        // If b is still not a substring, return -1
        return -1;
    }

}
