
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    int mostCitiesVisited = 1;

    public int solution(int[] A, int[] X) {
        for (int i = 0; i < X.length; i++){
            boolean[] visited = new boolean[X.length];
            visited[i] = true;
            recursive(A,X,visited,A[i],i, 1);
        }

        return mostCitiesVisited;
    }

    public void recursive(int[] A, int[] X, boolean[] visited, int currFuel, int currCity, int visitedCities){
        for (int i = 0; i < X.length; i++){
            int cf = currFuel;
            int cc = currCity;
            int vc = visitedCities;
            boolean[] visCopy = visited.clone();
            int distance = Math.abs(X[cc] - X[i]);
            if (!visCopy[i] && cf >= distance){
                visCopy[i] = true;
                cf = cf - distance + A[i];
                cc = i;
                vc++;
                if (vc > mostCitiesVisited){
                    mostCitiesVisited = vc;
                }
                recursive(A,X,visCopy,cf,cc, vc);
            }
        }
    }
}

