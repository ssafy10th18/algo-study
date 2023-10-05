import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static int N;
    static int start = 0;
    static int[] arr;
    static boolean[] visited;
    static ArrayList<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        visited = new boolean[N + 1];
        list = new ArrayList<>();

        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for(int i = 1; i <= N; i++) {
            visited[i] = true;
            start = i;
            dfs(i);
            visited[i] = false;
        }

        sb.append(list.size()).append("\n");
        for(int x : list) {
            sb.append(x).append("\n");
        }

        System.out.println(sb.toString());

    }

    private static void dfs(int i) {
        if(arr[i] == start) {
            list.add(start);
        }

        if(!visited[arr[i]]) {
            visited[arr[i]] = true;
            dfs(arr[i]);
            visited[arr[i]] = false;
        }

    }

}
