import java.util.ArrayList;

import static java.lang.Math.abs;

class Solution3 {
    int mostCitiesVisited = 1;
    ArrayList<Integer> sequence;
    ArrayList<Integer> tempSequence;

    public int solution(int[] A, int[] X) {
        for (int i = 0; i < A.length; i++){
            boolean[] visited = new boolean[A.length];
            tempSequence = new ArrayList<>();
            visited[i] = true;
            tempSequence.add(i);
            chooseFurthestCity(A,X,visited,A[i],i,1);
        }
        System.out.println(sequence);
        return mostCitiesVisited;
    }

    public void chooseFurthestCity(int[] A, int[] X, boolean[] visited, int currFuel,int currCity, int cityVisited) {
        int leftMostCity = currCity;
        int leftMostCityDistance = 0;
        ArrayList<Integer> leftCityPositions = new ArrayList<>();
        ArrayList<Integer> leftFuels = new ArrayList<>();
        int leftFuelAlong = 0;
        int leftCitiesAlong = 0;

        int rightMostCity = currCity;
        int rightMostCityDistance = 0;
        ArrayList<Integer> rightCityPositions = new ArrayList<>();
        ArrayList<Integer> rightFuels = new ArrayList<>();
        int rightFuelAlong = 0;
        int rightCitiesAlong = 0;

        // find the furthest possible to the left
        for (int i = currCity - 1; i >= 0; i--) {
            int distance = abs(X[currCity] - X[i]);
            if (currFuel >= distance) {
                if (!visited[i]) {
                    leftCityPositions.add(distance);

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
        //find the furthest possible to the right
        for (int i = currCity + 1; i < X.length; i++) {
            int distance = abs(X[currCity] - X[i]);
            if (currFuel >= distance) {
                if (!visited[i]) {
                    rightCityPositions.add(X[i]);

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

        //no options or only one option
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
            //calculate how much we can go back
            int goLeftCitiesCount = 0;
            int goRightCitiesCount = 0;
            for (int i = 0; i < leftCityPositions.size(); i++) {
                if (2*Math.abs(X[currCity] - leftCityPositions.get(i)) + rightMostCityDistance <= currFuel + leftFuels.get(i)) {
                    goLeftCitiesCount++;
                }
            }
            for (int i = 0; i < rightCityPositions.size(); i++) {
                if (2*Math.abs(X[currCity] - rightCityPositions.get(i)) + leftMostCityDistance <= currFuel + rightFuels.get(i)) {
                    goRightCitiesCount++;
                }
            }

            if (goLeftCitiesCount > 0 && goLeftCitiesCount >= goRightCitiesCount) {
                goLeft(A,X,currFuel,currCity,visited,cityVisited,currCity-goLeftCitiesCount,goLeftCitiesCount, leftFuels.get(goLeftCitiesCount-1), leftCityPositions.get(goLeftCitiesCount-1));
            }
            else if (goRightCitiesCount > 0) {
                goRight(A,X,currFuel,currCity,visited,cityVisited,currCity + goRightCitiesCount, goRightCitiesCount,rightFuels.get(goRightCitiesCount-1), rightCityPositions.get(goRightCitiesCount-1));
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
            if (!visited[i]) {
                visited[i] = true;
                tempSequence.add(i);
            }
        }

        cityVisited += rightCitiesAlong;
        currFuel += rightFuelAlong - rightDistance;
        currCity = rightMostCity;

        if (cityVisited > mostCitiesVisited){
            mostCitiesVisited = cityVisited;
            sequence = (ArrayList<Integer>) tempSequence.clone();
        }
        chooseFurthestCity(A, X, visited, currFuel, currCity, cityVisited);
    }

    public void goLeft(int[] A, int[] X,int currFuel, int currCity,boolean[] visited, int cityVisited, int leftMostCity, int leftCitiesAlong, int leftFuelAlong, int leftDistance){
        for(int i = currCity-1; i >= leftMostCity; i--){
            if (!visited[i]) {
                visited[i] = true;
                tempSequence.add(i);
            }
        }

        cityVisited += leftCitiesAlong;
        currFuel += leftFuelAlong - leftDistance;
        currCity = leftMostCity;

        if (cityVisited > mostCitiesVisited){
            mostCitiesVisited = cityVisited;
            sequence = (ArrayList<Integer>) tempSequence.clone();
        }
        chooseFurthestCity(A, X, visited, currFuel, currCity, cityVisited);
    }
}

