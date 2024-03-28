import java.util.*;

class Solution {
   public boolean canFinish(int numCourses, int[][] prerequisites) {
  
       Map> graph = new HashMap<>();
       int[] indegree = new int[numCourses];
       for (int[] prereq : prerequisites) {
           int course = prereq[0];
           int prereqCourse = prereq[1];
           graph.computeIfAbsent(prereqCourse, k -> new ArrayList<>()).add(course);
           indegree[course]++;
       }

       // DFS to detect cycles
       boolean[] visited = new boolean[numCourses];
       boolean[] stack = new boolean[numCourses];

       for (int node = 0; node < numCourses; node++) {
           if (indegree[node] == 0 && !dfs(graph, node, visited, stack)) {
               return false;
           }
       }

       return true;
   }

   private boolean dfs(Map> graph, int node, boolean[] visited, boolean[] stack) {
       visited[node] = true;
       stack[node] = true;

       for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
           if (!visited[neighbor]) {
               if (!dfs(graph, neighbor, visited, stack)) {
                   return false;
               }
           } else if (stack[neighbor]) {
               return false;
           }
       }

       stack[node] = false;
       return true;
   }
}
