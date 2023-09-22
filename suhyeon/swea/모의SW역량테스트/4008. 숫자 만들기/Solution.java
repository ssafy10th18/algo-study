import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int[] num, op;
    static int max, min, N;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), " ");
            op = new int[4];
            for(int i = 0; i < op.length; i++) {
                op[i] = Integer.parseInt(st.nextToken());
            }

            num = new int[N];
            st = new StringTokenizer(br.readLine(), " ");

            for(int i = 0; i < num.length; i++) {
                num[i] = Integer.parseInt(st.nextToken());
            }

            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            dfs(1, num[0]);

            sb.append("#").append(tc).append(" ").append(max - min).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void dfs(int step, int val) {
        if(step == N) {
            if(max < val) max = val;
            if(min > val) min = val;
            return;
        }

        for(int i = 0; i < op.length; i++) {
            if(op[i] == 0) continue; // 연산자 개수가 남아있지 않으면, 재귀호출 안함
            op[i]--; // 연산 개수 감소

            switch(i) {
                case 0: dfs(step + 1, val + num[step]); break;
                case 1: dfs(step + 1, val - num[step]); break;
                case 2: dfs(step + 1, val * num[step]); break;
                case 3: dfs(step + 1, val / num[step]); break;
            }
            op[i]++;
        }
    }
}