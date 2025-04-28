public class Solution6 {
    //todo Prioritize Closest To left and right
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

        //todo choose which way to go
        //find furthest possible cities
//        int rightFCity = currCity;
//        int leftFCity = currCity;
//        for (int i = currCity+1; i < A.length; i++){
//            if (X[i] - X[currCity] > cf){
//                rightFCity = i-1;
//                break;
//            }
//        }
//        for (int i = currCity-1; i >= 0; i--){
//            if (X[currCity] - X[i] > cf){
//                leftFCity = i+1;
//                break;
//            }
//        }


        int closestLeftCity = currCity;
        int closestRightCity = currCity;
        for (int i = currCity-1; i >= 0; i--){
            int distance = Math.abs(X[currCity] - X[i]);
            if (cf >= distance) {
                mask = 1 << i;
                if ((bm & mask) == 0) {
                    closestLeftCity = i;
                    break;
                }
            }
            else{break;}
        }
        for (int i = currCity+1; i < A.length; i++){
            int distance = Math.abs(X[currCity] - X[i]);
            if (cf >= distance) {
                mask = 1 << i;
                if ((bm & mask) == 0) {
                    closestRightCity = i;
                    break;
                }
            }
            else{break;}
        }

        if (closestLeftCity == currCity && closestRightCity == currCity){
            mostCitiesVisited = Math.max(mostCitiesVisited, vc);
            return;
        }
        else if (closestLeftCity == currCity){
            dfs(closestRightCity, vc, bm, cf - (X[closestRightCity] - X[currCity]));
        }
        else if (closestRightCity == currCity){
            dfs(closestLeftCity, vc, bm, cf - (X[currCity] - X[closestLeftCity]));
        }
        else if (cf - (X[currCity] - X[closestLeftCity]) + A[closestLeftCity]  > cf - (X[closestRightCity] - X[currCity]) + A[closestRightCity]){
            dfs(closestLeftCity, vc, bm, cf - (X[currCity] - X[closestLeftCity]));
        }
        else if (cf - (X[currCity] - X[closestLeftCity]) + A[closestLeftCity]  < cf - (X[closestRightCity] - X[currCity]) + A[closestRightCity]){
            dfs(closestRightCity, vc, bm, cf - (X[closestRightCity] - X[currCity]));
        }
        else{
            dfs(closestLeftCity, vc, bm, cf - (X[currCity] - X[closestLeftCity]));
            dfs(closestRightCity, vc, bm, cf - (X[closestRightCity] - X[currCity]));
        }

//        goLeft(currCity,cf,mask,bm,vc);
//        goRight(currCity,cf,mask,bm,vc);



//        for (int i = currCity+1; i < A.length; i++){
//            int distance = Math.abs(X[currCity] - X[i]);
//            if (cf >= distance) {
//                mask = 1 << i;
//                if ((bm & mask) == 0) {
//                    dfs(i, vc, bm, cf - distance);
//                    break;
//                }
//            }
//            else{break;}
//        }
        //left side
//        for (int i = currCity-1; i >= 0; i--){
//            int distance = Math.abs(X[currCity] - X[i]);
//            if (cf >= distance) {
//                mask = 1 << i;
//                if ((bm & mask) == 0) {
//                    dfs(i, vc, bm, cf - distance);
//                    break;
//                }
//            }
//            else{break;}
//        }

        mostCitiesVisited = Math.max(mostCitiesVisited, vc);

    }

    public void goLeft(int currCity, int cf, int mask, int bm, int vc) {
        for (int i = currCity-1; i >= 0; i--){
            int distance = Math.abs(X[currCity] - X[i]);
            if (cf >= distance) {
                mask = 1 << i;
                if ((bm & mask) == 0) {
                    dfs(i, vc, bm, cf - distance);
                    break;
                }
            }
            else{break;}
        }
    }

    public void goRight(int currCity, int cf, int mask, int bm, int vc) {
        for (int i = currCity+1; i < A.length; i++){
            int distance = Math.abs(X[currCity] - X[i]);
            if (cf >= distance) {
                mask = 1 << i;
                if ((bm & mask) == 0) {
                    dfs(i, vc, bm, cf - distance);
                    break;
                }
            }
            else{break;}
        }
    }
}
