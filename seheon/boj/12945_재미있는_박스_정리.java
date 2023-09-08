import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[] M;
    static int[] M2;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        M = new int[n];

        for (int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int value = Integer.parseInt(st.nextToken());
            M[i] = value;
        }

        Arrays.sort(M);

        int l = 0, r = n/2;
        int ans = n;
        while (true){
            if (r > n-1 || l >= n/2) break;
            if (M[l]*2 <= M[r]) {
                l += 1;
                ans -= 1;
            }
            r += 1;

        }

        System.out.println(ans);


    }
}
