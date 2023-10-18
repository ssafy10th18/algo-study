import java.io.*;
import java.util.*;

public class Main {

    static List<Integer> ansList = new ArrayList<>();;
    static int cnt=0;
    static int[] A;
    static int[] B;
    static int[] visited;
    static int[] visited2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        A = new int[n+1];
        B = new int[n+1];
        visited2 = new int[n+1];

        for (int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            B[i] = Integer.parseInt(st.nextToken());
        }


        for (int i=1; i<=n; i++){
            visited = new int[n+1];
            visited[i] = 1;
            dfs(i, 1, new ArrayList<>());
            visited[i] = 0;
        }

        System.out.println(ansList.size());
        ansList.sort(Comparator.naturalOrder());
        for (Integer a : ansList) System.out.println(a);

    }

    static void dfs(int cur, int cnt, List<Integer> L){
        L.add(cur);
        if (visited[B[cur]] == 1) {
            if (visited2[cur] == 1) return;
            List<Integer> temp = new ArrayList<>();
            for (Integer l : L) temp.add(B[l]);
            temp.sort(Comparator.naturalOrder());
            L.sort(Comparator.naturalOrder());
            if (temp.equals(L)) {
                for (Integer l : L) {
                    ansList.add(l);
                    visited2[l] = 1;
                }
            }
            return;
        }
        visited[B[cur]] = 1;
        dfs(B[cur], cnt+1, L);
    }
}