import java.io.*;
import java.util.*;

public class Main {

    static int[] P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        P = new int[n+1];
        for (int i=1; i<=n; i++){
            P[i] = i;
        }

        for (int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int o = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (o == 0) union(a, b);
            else if (o == 1){
                if (parent(a) != parent(b)) System.out.println("no");
                else System.out.println("yes");
            }
        }
    }

    static int parent(int a){
        if (a != P[a]) P[a] = parent(P[a]);
        return P[a];
    }

    static void union(int a, int b){
        a = parent(a);
        b = parent(b);
        if (a<b) P[b] = a;
        else P[a] = b;
    }
}