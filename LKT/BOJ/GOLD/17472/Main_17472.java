import java.io.*;
import java.util.*;

public class Main_17472 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int MAX_I = 7;

    static int N, M;
    static int[][] map;
    static int[] parent;
    static List<Axis>[] islands = new ArrayList[MAX_I];

    static int[] di = { 0, 1, 0, -1 };
    static int[] dj = { 1, 0, -1, 0 };

    static class Axis {
        int x, y;

        public Axis(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int s, e, c;

        public Edge(int s, int e, int c) {
            this.s = s;
            this.e = e;
            this.c = c;
        }

        @Override
        public int compareTo(Edge o) {
            return this.c - o.c;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        run();
    }

    static void run() throws Exception {
        getIslands();
        getEdges();
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()) - 1;
            }
        }
    }

    static void getIslands() {
        int num = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] = 0) {
                    islands[num] = new ArrayList<>();
                    bfs(i, j, num);
                    num++;
                }
            }
        }
    }

    static void bfs(int si, int sj, int num) {
        Deque<Axis> dq = new ArrayDeque<>();
        map[si][sj] = num;
        islands[num].add(new Axis(si, sj));

        dq.add(new Axis(si, sj));
        while (!dq.isEmpty()) {
            Axis cur = dq.poll();

            for (int d = 0; d < 4; d++) {
                int ni = cur.x + di[d];
                int nj = cur.y + dj[d];

                if (isNotValid(ni, nj))
                    continue;

                map[ni][nj] = num;
                if()
                dq.add(new Axis(ni, nj));
            }
        }
    }

    static boolean isNotValid(int i, int j) {
        return (i < 0 || i >= N || j < 0 || j >= M || map[i][j] != 0);
    }

    static void getEdges() {

    }

    static void print() throws Exception {

    }
}
