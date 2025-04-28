class Solution1 {
    int mostCitiesVisited = 1;

    public int solution(int[] A, int[] X) {
        for (int i = 0; i < A.length; i++){
            boolean[] visited = new boolean[A.length];
            visited[i] = true;
            chooseFurthestCity(A,X,visited,A[i],i,1);
        }
        return mostCitiesVisited;
    }

    public void chooseFurthestCity(int[] A, int[] X, boolean[] visited, int currFuel,int currCity, int cityVisited) {
        int leftMostCity = currCity;
        int leftDistance = 0;
        int leftFuelAlong = 0;
        int leftCitiesAlong = 0;

        int rightMostCity = currCity;
        int rightDistance = 0;
        int rightFuelAlong = 0;
        int rightCitiesAlong = 0;

        // left
        for (int i = currCity-1; i >= 0; i--){
            int distance = Math.abs(X[currCity] - X[i]);
            if (currFuel >= distance) {
                if(!visited[i]) {
                    leftDistance = distance;
                    leftMostCity = i;
                    leftFuelAlong += A[i];
                    leftCitiesAlong += 1;
                }
            }
            else{
                break;
            }
        }
        //right
        for (int i = currCity+1; i < X.length; i++){
            int distance = Math.abs(X[currCity] - X[i]);
            if (currFuel >= distance) {
                if(!visited[i]) {
                    rightDistance = distance;
                    rightMostCity = i;
                    rightFuelAlong += A[i];
                    rightCitiesAlong += 1;
                }
            }
            else{
                break;
            }
        }

        if (leftCitiesAlong == rightCitiesAlong){
            if (leftCitiesAlong == 0){
                return;
            }
        }
        else if (leftCitiesAlong > rightCitiesAlong){
            for(int i = currCity-1; i >= leftMostCity; i--){
                visited[i] = true;
            }

            cityVisited += leftCitiesAlong;
            currFuel += leftFuelAlong - leftDistance;
            currCity = leftMostCity;

            if (cityVisited > mostCitiesVisited){
                mostCitiesVisited = cityVisited;
            }
            chooseFurthestCity(A, X, visited, currFuel, currCity, cityVisited);
        }
        else {
            for(int i = currCity+1; i <= rightMostCity; i++){
                visited[i] = true;
            }

            cityVisited += rightCitiesAlong;
            currFuel += rightFuelAlong - rightDistance;
            currCity = rightMostCity;

            if (cityVisited > mostCitiesVisited){
                mostCitiesVisited = cityVisited;
            }
            chooseFurthestCity(A, X, visited, currFuel, currCity, cityVisited);
        }
    }
}

