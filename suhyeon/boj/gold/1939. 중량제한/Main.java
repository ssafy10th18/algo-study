import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Bridge{
    int island;
    int weight;
    public Bridge(int island, int weight) {
        this.island = island;
        this.weight = weight;
    }
}

public class Main {
    public static int N;
    public static int M;
    public static ArrayList<ArrayList<Bridge>> bridge = new ArrayList<>();
    public static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i=0;i<=N;i++) {
            bridge.add(new ArrayList<>());
        }
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            bridge.get(a).add(new Bridge(b,c));
            bridge.get(b).add(new Bridge(a,c));

            if(max < c) {
                max = c;
            }
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int left = 0;
        int right = max;

        while(left <= right) {
            int mid = (left + right)/2;
            if(bfs(start,end,mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(right);
    }
    public static boolean bfs(int start, int end, int mid) {
        boolean[] visit = new boolean[N+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;

        while(!q.isEmpty()) {
            int p = q.poll();

            if(p == end) {
                return true;
            }

            for(Bridge i: bridge.get(p)) {
                if(!visit[i.island] && mid <= i.weight) {
                    visit[i.island] = true;
                    q.add(i.island);
                }
            }
        }
        return false;
    }
}