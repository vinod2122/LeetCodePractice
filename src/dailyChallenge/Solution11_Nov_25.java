package dailyChallenge;

/* 
You are given a 2D integer array items where items[i] = [pricei, beautyi] denotes the price and beauty of an item respectively.
You are also given a 0-indexed integer array queries. For each queries[j], you want to determine the maximum beauty of an item whose price is less than or equal to queries[j]. If no such item exists, then the answer to this query is 0.
Return an array answer of the same length as queries where answer[j] is the answer to the jth query.


Example 1:

Input: items = [[1,2],[3,2],[2,4],[5,6],[3,5]], queries = [1,2,3,4,5,6]
Output: [2,4,5,5,6,6]
Explanation:
- For queries[0]=1, [1,2] is the only item which has price <= 1. Hence, the answer for this query is 2.
- For queries[1]=2, the items which can be considered are [1,2] and [2,4]. 
  The maximum beauty among them is 4.
- For queries[2]=3 and queries[3]=4, the items which can be considered are [1,2], [3,2], [2,4], and [3,5].
  The maximum beauty among them is 5.
- For queries[4]=5 and queries[5]=6, all items can be considered.
  Hence, the answer for them is the maximum beauty of all items, i.e., 6.
Example 2:

Input: items = [[1,2],[1,2],[1,3],[1,4]], queries = [1]
Output: [4]
Explanation: 
The price of every item is equal to 1, so we choose the item with the maximum beauty 4. 
Note that multiple items can have the same price and/or beauty.  
Example 3:

Input: items = [[10,1000]], queries = [5]
Output: [0]
Explanation:
No item has a price less than or equal to 5, so no item can be chosen.
Hence, the answer to the query is 0.
 

Constraints:

1 <= items.length, queries.length <= 105
items[i].length == 2
1 <= pricei, beautyi, queries[j] <= 109
 */

import java.util.Arrays;

public class Solution11_Nov_25 {
	public int[] maximumBeauty(int[][] items, int[] queries) {
        // Sort items by price and track the max beauty for each price level
        Arrays.sort(items, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Prepare max beauty values at each price level
        int n = items.length;
        int[] maxBeautyUpToPrice = new int[n];
        maxBeautyUpToPrice[0] = items[0][1];
        
        for (int i = 1; i < n; i++) {
            maxBeautyUpToPrice[i] = Math.max(maxBeautyUpToPrice[i - 1], items[i][1]);
        }
        
        // Create pairs of query values with their original indices for sorting
        int q = queries.length;
        int[][] queryWithIndex = new int[q][2];
        for (int i = 0; i < q; i++) {
            queryWithIndex[i][0] = queries[i];
            queryWithIndex[i][1] = i;
        }
        Arrays.sort(queryWithIndex, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Process each query and find the max beauty within the price range
        int[] result = new int[q];
        int itemIndex = 0;

        for (int[] query : queryWithIndex) {
            int maxAffordablePrice = query[0];
            int queryIndex = query[1];
            
            // Move itemIndex to the largest price that is <= maxAffordablePrice
            while (itemIndex < n && items[itemIndex][0] <= maxAffordablePrice) {
                itemIndex++;
            }
            
            // If we have valid items within the price limit, record the max beauty
            result[queryIndex] = (itemIndex > 0) ? maxBeautyUpToPrice[itemIndex - 1] : 0;
        }
        
        return result;
    }
}



