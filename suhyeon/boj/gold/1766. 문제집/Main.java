import java.io.*;
import java.util.*;

public class Main{

    static int N, M;
    static int[] inDegree;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        inDegree = new int[N+1];
        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0 ; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            inDegree[b] += 1;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                pq.offer(i);
            }
        }

        while (!pq.isEmpty()) {
            int now = pq.poll();
            sb.append(now).append(" ");
            ArrayList<Integer> nodes = graph.get(now);
            for(int node : nodes) {
                inDegree[node] -= 1;

                if(inDegree[node] == 0) {
                    pq.offer(node);
                }
            }
        }
        System.out.println(sb.toString());
    }
}