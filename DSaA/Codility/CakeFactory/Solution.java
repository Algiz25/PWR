import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int solution(int N, int K, int[] A, int[] B, int[] C){
        int M = A.length;
        boolean[] isStillViable = new boolean[N];
        boolean[] isPerfect = new boolean[N];
        int perfectCount = 0;
        Arrays.fill(isStillViable, true);

        List<List<Integer>> cakes = new ArrayList<>();
        for (int i = 0; i < N ; i++) {
            List<Integer> cake = new ArrayList<>();
            cakes.add(cake);
        }

        for (int i = 0; i < M; i++){
            for (int j = A[i]-1; j <= B[i]-1; j++){
                if (isStillViable[j]){
                    if (C[i] != cakes.get(j).size() + 1 || cakes.get(j).size() == K){
                        isStillViable[j] = false;
                        if (isPerfect[j]) {
                            perfectCount--;
                            isPerfect[j] = false;
                        }
                    }
                    else {
                        cakes.get(j).add(C[i]);
                        if (cakes.get(j).size() == K){
                            perfectCount++;
                            isPerfect[j] = true;
                        }
                    }
                }
            }
        }
        return perfectCount;
    }

}