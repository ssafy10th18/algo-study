import java.io.*;
import java.util.*;

public class Main_2098 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int MAX = 1 << 16;
    static final int INF = 123456789;

    static int N;
    static long[][] dp;
    static long[][] cost;

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(TSP(0, 0));
    }

    static long TSP(int start, int visit) {
        if (visit == (1 << N) - 1) {
            if (cost[start][0] == 0)
                return dp[start][visit] = INF;

            return dp[start][visit] = cost[start][0];
        }

        if (dp[start][visit] != INF)
            return dp[start][visit];

        dp[start][visit] = INF;
        for (int i = 0; i < N; i++) {
            if ((visit & (1 << i)) == 0) {
                dp[start][visit] = Math.min(dp[start][visit], TSP(i, visit | (1 << i)) + cost[start][i]);
            }
        }

        return dp[start][visit];
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());

        dp = new long[N][1 << N];
        for (long[] row : dp)
            Arrays.fill(row, INF);

        cost = new long[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
