import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[] colors;
    static ArrayList<ArrayList<Integer>> list;
    static boolean[] visited;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        colors = new int[N+1];
        visited = new boolean[N+1];
        list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++) {
            colors[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for(int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            list.get(from).add(to);
            list.get(to).add(from);
        }

        changeColor(1, 0);

        System.out.println(answer);

    }

    static void changeColor(int idx, int color) {
        visited[idx] = true;
        if (colors[idx] != color) {
            answer += 1;
            color = colors[idx];
        }
        for (int next : list.get(idx)) {
            if (!visited[next]) {
                changeColor(next, color);
            }
        }
    }

}
