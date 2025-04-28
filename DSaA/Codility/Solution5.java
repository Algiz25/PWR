public class Solution5 {
    int mostCitiesVisited = 1;
    int[] A, X;

    public int solution(int[] A, int[] X) {
        int bitmask = 0;
        this.A = A;
        this.X = X;
        for (int i = 0; i < A.length; i++){
            if (A[i] != 0) {
                dfs(i, 0, bitmask, 0);
            }
        }
        return mostCitiesVisited;
    }

    public void dfs(int currCity, int visitedCities, int bitmask, int fuel) {
        int cf = fuel + A[currCity];
        int vc = visitedCities+1;

        if (cf == 0){
            mostCitiesVisited = Math.max(mostCitiesVisited, vc);
            return;
        }

        int bm = bitmask;
        int mask = 1 << currCity;
        bm = bm | mask;

        for (int i = currCity+1; i < A.length; i++){
            int distance = Math.abs(X[currCity] - X[i]);
            if (cf >= distance) {
                mask = 1 << i;
                if ((bm & mask) == 0) {
                    dfs(i, vc, bm, cf - distance);
                }
            }
            else{break;}
        }
        //left side
        for (int i = currCity-1; i >= 0; i--){
            int distance = Math.abs(X[currCity] - X[i]);
            if (cf >= distance) {
                mask = 1 << i;
                if ((bm & mask) == 0) {
                    dfs(i, vc, bm, cf - distance);
                }
            }
            else{break;}
        }

        if (vc > mostCitiesVisited){
            mostCitiesVisited = vc;
        }

    }
}
