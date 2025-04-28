import java.util.LinkedList;
import java.util.Queue;

public class Solution4 {
    int mostCitiesVisited = 1;

    public int solution(int[] A, int[] X) {
        boolean[] visited = new boolean[A.length];
        for (int i = 0; i < A.length; i++){
            dfs(A,X,i,0,visited,0);
        }
        return mostCitiesVisited;
    }

    public void dfs(int[] A, int[] X, int currCity, int visitedCities, boolean[] vis, int fuel) {
        boolean[] visit = vis.clone();
        visit[currCity] = true;
        int vc = visitedCities+1;
        int cf = fuel + A[currCity];

        for (int i = currCity+1; i < A.length; i++){
            int distance = Math.abs(X[currCity] - X[i]);
            if (cf >= distance) {
                if (!visit[i]) {
                    dfs(A, X, i, vc, visit, cf - distance);
                }
            }
            else{break;}
        }
        //left side
        for (int i = currCity-1; i >= 0; i--){
            int distance = Math.abs(X[currCity] - X[i]);
            if (cf >= distance) {
                if (!visit[i]) {
                    dfs(A, X, i, vc, visit, cf - distance);
                }
            }
            else{break;}
        }

        if (vc > mostCitiesVisited){
            mostCitiesVisited = vc;
        }

    }
}
