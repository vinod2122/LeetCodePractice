package dailyPractice;

import java.util.HashSet;

/*
 * 
 * Design a data structure that is initialized with a list of different words. Provided a string, you should determine if you can change exactly one character in this string to match any word in the data structure.

Implement the MagicDictionary class:

MagicDictionary() Initializes the object.
void buildDict(String[] dictionary) Sets the data structure with an array of distinct strings dictionary.
bool search(String searchWord) Returns true if you can change exactly one character in searchWord to match any string in the data structure, otherwise returns false.
 

Example 1:

Input
["MagicDictionary", "buildDict", "search", "search", "search", "search"]
[[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
Output
[null, null, false, true, false, false]

Explanation
MagicDictionary magicDictionary = new MagicDictionary();
magicDictionary.buildDict(["hello", "leetcode"]);
magicDictionary.search("hello"); // return False
magicDictionary.search("hhllo"); // We can change the second 'h' to 'e' to match "hello" so we return True
magicDictionary.search("hell"); // return False
magicDictionary.search("leetcoded"); // return False
 

Constraints:

1 <= dictionary.length <= 100
1 <= dictionary[i].length <= 100
dictionary[i] consists of only lower-case English letters.
All the strings in dictionary are distinct.
1 <= searchWord.length <= 100
searchWord consists of only lower-case English letters.
buildDict will be called only once before search.
At most 100 calls will be made to search.
 */
public class Solution8 {
    private HashSet<String> dictionary;

    /** Initialize your data structure here. 
     * @return */
    public void MagicDictionary() {
        dictionary = new HashSet<>();
    }
    
    /** Build a dictionary through a list of words. */
    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            this.dictionary.add(word);
        }
    }
    
    /** Returns true if there is any word in the dictionary that differs by exactly one character. */
    public boolean search(String searchWord) {
        // Check each word in the dictionary
        for (String word : dictionary) {
            // If the word is of the same length as searchWord
            if (word.length() == searchWord.length()) {
                int diffCount = 0;
                // Count the number of differing characters
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) != searchWord.charAt(i)) {
                        diffCount++;
                    }
                    if (diffCount > 1) {
                        break; // No need to check further if more than 1 character is different
                    }
                }
                // If exactly one character is different, return true
                if (diffCount == 1) {
                    return true;
                }
            }
        }
        return false;
    }

}
