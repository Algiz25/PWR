//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int[] A = {4, 1, 4, 3, 3};
        int[] X = {8, 10, 11, 13, 100};
        Solution6 solution = new Solution6();
        System.out.println(solution.solution(A,X));

        int[] B = {0, 10, 0};
        int[] Y = {1, 2, 3};
        solution = new Solution6();
        System.out.println(solution.solution(B, Y));

        int[] C = {0, 1, 0, 1, 1, 1, 0};
        int[] Z = {1, 2, 3, 4, 5, 6, 7};
        solution = new Solution6();
        System.out.println(solution.solution(C, Z));
//        int number = 0;          // 00000000
//        int n = 3;               // Np. ustawiamy bit 3 (liczymy od 0)
//        int mask = 1 << n;       // maska: 00001000 (n-ty bit)
//        number = number | mask;  // OR ustawia n-ty bit na 1
//        System.out.println(number);  // wypisze 8 (00001000)
//
//        number = 24; // Przykładowa liczba 10 = 1010 (binarnie)
//
//        mask = 1 << 3; // Maska: 1000 (sprawdzamy 3-ci bit)
//        if ((number & mask) != 0) {
//            System.out.println("3-ci bit jest ustawiony na 1!");
//        } else {
//            System.out.println("3-ci bit jest ustawiony na 0!");
//        }
    }


}