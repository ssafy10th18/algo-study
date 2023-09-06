import java.io.*;
import java.util.*;

public class Main_1535 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, ans;
    static int[] lose, happy;

    public static void main(String[] args) throws Exception {
        input();
        run();
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        lose = new int[N];
        happy = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            lose[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            happy[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void run() throws Exception {
        find(0, 100, 0);
        System.out.println(ans);
    }

    static void find(int cnt, int life, int sum) {
        if (life <= 0)
            return;

        if (cnt == N) {
            ans = ans > sum ? ans : sum;
            return;
        }

        find(cnt + 1, life, sum);
        find(cnt + 1, life - lose[cnt], sum += happy[cnt]);
    }
}
