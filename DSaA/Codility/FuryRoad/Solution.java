import java.util.Arrays;

public class Solution {
    public int solution(String R) {
        int n = R.length();
        int[] asphalt = new int[n];
        int[] sand = new int[n];

        if (R.charAt(n-1) == 'A') {
            asphalt[n - 1] = 1;
            sand[n - 1] = 0;
        }
        else{
            asphalt[n - 1] = 0;
            sand[n - 1] = 1;
        }

        for (int i = R.length() - 2; i >= 0; i--) { // skip the last one
            if (R.charAt(i) == 'A') {
                asphalt[i] = asphalt[i+1] + 1;
                sand[i] = sand[i+1];
            }
            else {
                asphalt[i] = asphalt[i+1];
                sand[i] = sand[i+1] + 1;
            }
        }

        System.out.println(Arrays.toString(asphalt));
        System.out.println(Arrays.toString(sand));

        int timeOnScooter;
        int timeOnFoot;
        boolean isOnScooter = true;
        int totalTime = 0;

        for (int i = 0; i < n; i++) {
            if (isOnScooter) {
                timeOnScooter = asphalt[i] * 5 + sand[i] * 40;
                timeOnFoot = asphalt[i] * 20 + sand[i] * 30;
                System.out.println("Scooter: " + timeOnScooter + " foot: " + timeOnFoot);
                if (R.charAt(i) == 'A') {
                    totalTime += 5;
                    System.out.println("Przejechany asfalt");
                }
                else if (timeOnScooter <= timeOnFoot) {
                    totalTime += 40;
                    System.out.println("Przejechany piach");
                }
                else {
                    totalTime += 30;
                    isOnScooter = false;
                    System.out.println("Zsiadamy i idziemy przez piach");
                }
            }
            else{
                if (R.charAt(i) == 'A') {
                    totalTime += 20;
                    System.out.println("Idziemy asfalt");
                }
                else{
                    totalTime += 30;
                    System.out.println("Idziemy piach");
                }
            }
        }
        return totalTime;
    }
}
