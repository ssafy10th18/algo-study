import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    int e;
    int weight;

    public Node(int e, int weight) {
        this.e = e;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return o.weight-this.weight;
    }
}

public class Main {

    static List<Node>[] M;
    static PriorityQueue<Node> Q = new PriorityQueue<>();
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        M = new ArrayList[n+1];
        for (int i=0; i<=n; i++) M[i] = new ArrayList<>();
        dist = new int[n+1];

        for (int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            M[a].add(new Node(b, w));
            M[b].add(new Node(a, w));
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        for (Node node : M[s]) {
            dist[node.e] = node.weight;
            Q.add(node);
        }

        while (!Q.isEmpty()){
            Node node = Q.poll();
            int cur=node.e;
            int weight=node.weight;
            if (weight < dist[cur]) continue;
            if (cur == e) {
                continue;
            }
            for (Node next : M[cur]){
                int nextWeight = Math.min(weight, next.weight);
                if (dist[next.e] >= nextWeight) continue;
                dist[next.e] = nextWeight;
                next.weight = nextWeight;
                Q.add(next);
            }
        }

        System.out.println(dist[e]);
    }
}
