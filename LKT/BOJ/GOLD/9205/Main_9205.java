import java.io.*;
import java.util.*;

public class Main_9205 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static String ans;
    static int N;
    static List<Axis> list;
    static Axis home, fest;

    static int[] di = { 0, 1, 0, -1 };
    static int[] dj = { 1, 0, -1, 0 };

    static class Axis {
        int x, y;

        public Axis(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getDist(Axis b) {
            return Math.abs(this.x - b.x) + Math.abs(this.y - b.y);
        }

        public boolean calc(Axis b) {
            return getDist(b) <= 1000;
        }
    }
    

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            run();
        }
        System.out.println(sb);
    }
    
    static void run() throws Exception {
        input();

        ans = "sad";

        if (home.calc(fest))
            ans = "happy";
        else {
            for (int i = 0; i < list.size(); i++) {
                Axis conv = list.get(i);
                if (home.calc(conv)) {
                    if (bfs(i, conv)) {
                        ans = "happy";
                        break;
                    }
                }
            }
        }

        sb.append(ans + "\n");
    }

    static void bfs(int idx, Axis start) {
        Deque<Axis> dq = new ArrayDeque<>();
        boolean[] visited = new boolean[N];
        visited[i] = true;
        dq.add(start);

        while (!dq.isEmpty()) {
            Axis cur = dq.poll();

            for () {
                

                
            }
        }
    }
    
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        
        list = new ArrayList<>();
        for (int i = 0; i < N + 2; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (i == 0) {
                home = new Axis(x, y);
            } else if (i == N - 1) {
                fest = new Axis(x, y);
            } else {
                list.add(new Axis(x, y));
            }
        }
    }
}