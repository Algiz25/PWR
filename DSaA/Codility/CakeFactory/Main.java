public class Main {
    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        int[] A = {1, 1, 4, 1, 4};
        int[] B = {5, 2, 5, 5, 4};
        int[] C = {1, 2, 2, 3, 3};

//        int[] A = {1, 2, 1, 1};
//        int[] B = {3, 3, 6, 6};
//        int[] C = {1, 2, 3, 4};

        int res = solution.solution(5,3, A, B, C);
        System.out.println(res);
    }
}