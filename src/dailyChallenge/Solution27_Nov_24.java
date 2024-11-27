package dailyChallenge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 
 * You are given an integer n and a 2D integer array queries.

There are n cities numbered from 0 to n - 1. Initially, there is a unidirectional road from city i to city i + 1 for all 0 <= i < n - 1.

queries[i] = [ui, vi] represents the addition of a new unidirectional road from city ui to city vi. After each query, you need to find the length of the shortest path from city 0 to city n - 1.

Return an array answer where for each i in the range [0, queries.length - 1], answer[i] is the length of the shortest path from city 0 to city n - 1 after processing the first i + 1 queries.

 

Example 1:

Input: n = 5, queries = [[2,4],[0,2],[0,4]]

Output: [3,2,1]

Explanation:



After the addition of the road from 2 to 4, the length of the shortest path from 0 to 4 is 3.



After the addition of the road from 0 to 2, the length of the shortest path from 0 to 4 is 2.



After the addition of the road from 0 to 4, the length of the shortest path from 0 to 4 is 1.

Example 2:

Input: n = 4, queries = [[0,3],[0,2]]

Output: [1,1]

Explanation:



After the addition of the road from 0 to 3, the length of the shortest path from 0 to 3 is 1.



After the addition of the road from 0 to 2, the length of the shortest path remains 1.

 

Constraints:

3 <= n <= 500
1 <= queries.length <= 500
queries[i].length == 2
0 <= queries[i][0] < queries[i][1] < n
1 < queries[i][1] - queries[i][0]
There are no repeated roads among the queries.
 */
public class Solution27_Nov_24 {
	public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        // Initialize the graph with default roads (i -> i+1)
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            if (i < n - 1) {
                graph.get(i).add(i + 1);
            }
        }

        // Result array to store shortest path after each query
        int[] result = new int[queries.length];

        // Process each query
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            // Add the new road to the graph
            graph.get(u).add(v);

            // Find shortest path from 0 to n-1
            result[i] = bfsShortestPath(graph, n);
        }

        return result;
    }

    private int bfsShortestPath(List<List<Integer>> graph, int n) {
        // BFS to find the shortest path from node 0 to node n-1
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        int[] distance = new int[n];

        queue.offer(0);
        visited[0] = true;
        distance[0] = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    distance[neighbor] = distance[node] + 1;
                    queue.offer(neighbor);

                    // Stop early if we reach the destination
                    if (neighbor == n - 1) {
                        return distance[neighbor];
                    }
                }
            }
        }

        return -1; // In case there's no path (should not happen in this problem)
    }

}
