import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_16441 {
    static final int MAX = 100;
    static char[][] map = new char[MAX][MAX];
    static int[][] visited = new int[MAX][MAx];
    static int N, M;
    static int[] di = { 0, 1, 0, -1 };
    static int[] dj = { 1, 0, -1, 0 };
    static ArrayList<Axis> wolf = new ArrayList<>();

    public static void main(String[] args) {
        run();
    }

    static class Axis {
        int x, y;

        public Axis(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void run() {
        input();
        for (int i = 0; i < wolf.size(); i++)
            if (visited[wolf.get(i).x][wolf.get(i).y] == 0)
                dfs(wolf.get(i));
        print();
    }

    static void input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < N; i++) {
            StringBuffer sb = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = sb.charAt(j);
                if (map[i][j] == 'W')
                    wolf.add(new Axis(i, j));
                else if (arr[i][j] == '.')
                    map[i][j] = 'P';
            }
        }
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static void dfs(Axis start) {
        initVisited();
        int ci, cj;
    }

    static void initVisited() {
        for (int[] a : visited)
            Arrays.fill(visited, 0);
    }
}
