import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int tc = 1; tc <= T; tc++ ) {
            int N = sc.nextInt();
            int[] arr = new int[26];
            int count = 0;

            for(int i = 0; i < N; i++) {
                arr[sc.next().charAt(0)-'A']++;
            }

            for(int i = 0; i < arr.length; i++) {
                if(arr[i] > 0) {
                    count++;
                } else {
                    break;
                }
            }
            System.out.println("#"+tc+" "+count);
        }
    }
}