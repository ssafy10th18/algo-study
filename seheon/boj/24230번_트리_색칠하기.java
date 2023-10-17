import java.io.*;
import java.util.*;


public class Main {

    static int ans=0;
    static int[] visited;
    static int[] C;
    static List<Integer>[] M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        C = new int[n+1];
        M = new ArrayList[n+1];
        visited = new int[n+1];
        for (int i=1; i<=n; i++) M[i] = new ArrayList<>();

        int idx=1;
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()){
            C[idx++]=Integer.parseInt(st.nextToken());
        }
        for (int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            M[a].add(b);
            M[b].add(a);
        }

        visited[1] = 1;
        if (C[1] != 0) ans += 1;
        dfs(1, C[1]);

        System.out.println(ans);
    }

    static void dfs(int cur, int color){
        for (int child : M[cur]){
            if (visited[child] == 1) continue;
            visited[child] = 1;
            if (color != C[child]) ans+=1;
            dfs(child, C[child]);
        }
    }
}