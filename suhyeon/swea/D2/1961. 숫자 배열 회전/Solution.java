import java.util.Scanner;

public class Solution {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int tc=1; tc<=T; tc++) {
            int n = sc.nextInt();
            int[][] arr = new int[n][n];

            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }
            System.out.println("#"+tc);

            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    System.out.print(arr[n-j-1][i]);
                }
                System.out.print(" ");
                for(int j=0; j<n; j++) {
                    System.out.print(arr[n-i-1][n-j-1]);
                }
                System.out.print(" ");
                for(int j=0; j<n; j++) {
                    System.out.print(arr[j][n-i-1]);
                }
                System.out.println();
            }
        }
    }
}