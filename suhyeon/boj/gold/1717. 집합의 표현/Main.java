import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parents = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int option = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(option == 0) {
                union(a, b);
            } else {
                int aRoot = find(a);
                int bRoot = find(b);

                if(aRoot == bRoot) {
                    sb.append("YES\n");
                }else {
                    sb.append("NO\n");
                }
            }
        }
        System.out.println(sb.toString());
    }

    public static int find(int a) {
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    public static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot != bRoot) parents[aRoot] = bRoot;
    }

}
