import java.io.IOException;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb;
        int T = sc.nextInt();

        for(int tc = 1; tc <= T; tc++) {
            sb = new StringBuilder("");
            int[] arr = new int[5001];
            int N = sc.nextInt();

            for(int i = 0; i < N; i++) {
                int A = sc.nextInt();
                int B = sc.nextInt();
                for(int j = A; j <= B; j++) {
                    arr[j]++;
                }
            }

            int P = sc.nextInt();
            sb.append("#"+tc+" ");
            for(int i = 0; i < P; i++) {
                int C = sc.nextInt();
                sb.append(arr[C]+" ");
            }
            System.out.println(sb.toString());
        }
    }
}
