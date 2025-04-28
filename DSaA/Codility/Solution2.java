import java.util.ArrayList;

class Solution2 {
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
        int leftMostCityDistance = 0;
        ArrayList<Integer> leftDistances = new ArrayList<>();
        ArrayList<Integer> leftFuels = new ArrayList<>();
        int leftFuelAlong = 0;
        int leftCitiesAlong = 0;

        int rightMostCity = currCity;
        int rightMostCityDistance = 0;
        ArrayList<Integer> rightDistances = new ArrayList<>();
        ArrayList<Integer> rightFuels = new ArrayList<>();
        int rightFuelAlong = 0;
        int rightCitiesAlong = 0;

        // left
        for (int i = currCity - 1; i >= 0; i--) {
            int distance = Math.abs(X[currCity] - X[i]);
            if (currFuel >= distance) {
                if (!visited[i]) {
                    leftDistances.add(distance);

                    leftMostCityDistance = distance;
                    leftMostCity = i;

                    leftFuelAlong += A[i];
                    leftFuels.add(leftFuelAlong);

                    leftCitiesAlong += 1;
                }
            } else {
                break;
            }
        }
        //right
        for (int i = currCity + 1; i < X.length; i++) {
            int distance = Math.abs(X[currCity] - X[i]);
            if (currFuel >= distance) {
                if (!visited[i]) {
                    rightDistances.add(distance);

                    rightMostCityDistance = distance;
                    rightMostCity = i;

                    rightFuelAlong += A[i];
                    rightFuels.add(rightFuelAlong);

                    rightCitiesAlong += 1;
                }
            } else {
                break;
            }
        }

        if (leftCitiesAlong == 0 || rightCitiesAlong == 0) {
            if (leftCitiesAlong == rightCitiesAlong) {
                return;
            }

            if (leftCitiesAlong == 0) {
                goRight(A, X, currFuel, currCity, visited, cityVisited, rightMostCity, rightCitiesAlong, rightFuelAlong, rightMostCityDistance);
            } else {
                goLeft(A, X, currFuel, currCity, visited, cityVisited, leftMostCity, leftCitiesAlong, leftFuelAlong, leftMostCityDistance);
            }
        }
        else {
            //calculate for going left and then right to the end
            int goLeftCitiesCount = 0;
            int goRightCitiesCount = 0;
            for (int i = 0; i < leftDistances.size(); i++) {
                if (2*leftDistances.get(i) + rightMostCityDistance <= currFuel + leftFuels.get(i)) {
                    goLeftCitiesCount++;
                }
            }
            for (int i = 0; i < rightDistances.size(); i++) {
                if (2*rightDistances.get(i) + leftMostCityDistance <= currFuel + rightFuels.get(i)) {
                    goRightCitiesCount++;
                }
            }

            if (goLeftCitiesCount > 0 && goLeftCitiesCount > goRightCitiesCount) {
                for (int i = 0; i < goLeftCitiesCount; i++) {
                    visited[currCity - i - 1] = true;
                    cityVisited++;
                }
                currFuel += leftFuels.get(goLeftCitiesCount-1) - 2*leftDistances.get(goLeftCitiesCount-1);
                goRight(A, X, currFuel, currCity, visited, cityVisited, rightMostCity, rightCitiesAlong, rightFuelAlong, rightMostCityDistance);
            }
            else if (goRightCitiesCount > 0 && goRightCitiesCount > goLeftCitiesCount) {
                for (int i = 0; i < goRightCitiesCount; i++) {
                    visited[currCity + i + 1] = true;
                    cityVisited++;
                }
                currFuel += rightFuels.get(goRightCitiesCount-1) - 2*rightDistances.get(goRightCitiesCount-1);
                goLeft(A, X, currFuel, currCity, visited, cityVisited, leftMostCity, leftCitiesAlong, leftFuelAlong, leftMostCityDistance);
            }
            else if ((2 * leftMostCityDistance + rightMostCityDistance <= 2 * rightMostCityDistance + leftMostCityDistance && currFuel + leftFuelAlong >= 2 * leftMostCityDistance + rightMostCityDistance)
                    || (leftCitiesAlong > rightCitiesAlong && currFuel + rightFuelAlong < 2 * rightMostCityDistance + leftMostCityDistance)) {
                goLeft(A, X, currFuel, currCity, visited, cityVisited, leftMostCity, leftCitiesAlong, leftFuelAlong, leftMostCityDistance);
            } else {
                goRight(A, X, currFuel, currCity, visited, cityVisited, rightMostCity, rightCitiesAlong, rightFuelAlong, rightMostCityDistance);
            }
        }
    }

    public void goRight(int[] A, int[] X,int currFuel, int currCity,boolean[] visited, int cityVisited, int rightMostCity, int rightCitiesAlong, int rightFuelAlong, int rightDistance) {
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

    public void goLeft(int[] A, int[] X,int currFuel, int currCity,boolean[] visited, int cityVisited, int leftMostCity, int leftCitiesAlong, int leftFuelAlong, int leftDistance){
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
}

