import java.util.Arrays;

public class Solution1 {
    public int solution(String R) {
        int n = R.length();
        int[] asphalt = new int[n];
        int[] sand = new int[n];

        if (R.charAt(0) == 'A') {
            asphalt[0] = 1;
            sand[0] = 0;
        }
        else{
            asphalt[0] = 0;
            sand[0] = 1;
        }


        for (int i = 1; i < n; i++) {
            if (R.charAt(i) == 'A') {
                asphalt[i] = asphalt[i-1] + 1;
                sand[i] = sand[i-1];
            }
            else{
                asphalt[i] = asphalt[i-1];
                sand[i] = sand[i-1] + 1;
            }
        }

//        System.out.println("A: " + Arrays.toString(asphalt));
//        System.out.println("S: " + Arrays.toString(sand));

        int timeOnScooter;
        int timeOnFoot;
        // border cases - all time on foot or all time on scooter
        int totalTime = Math.min(asphalt[n-1] * 20 + sand[n-1] * 30,asphalt[n-1] * 5 + sand[n-1] * 40);

        // calculate time for every point - we get of scooter on index i + 1 (i is counted as on scooter)
        for (int i = 0; i < n-1; i++) {
            timeOnScooter = asphalt[i] * 5 + sand[i] * 40;
            timeOnFoot = (asphalt[n-1] - asphalt[i]) * 20 + (sand[n-1] - sand[i]) * 30;
            totalTime = Math.min(totalTime, timeOnScooter + timeOnFoot);
//            System.out.println(totalTime);
        }
        return totalTime;
    }
}
