 import java.io.*;
    import java.util.*;

    class Scholar implements Comparable<Scholar>{
        int d;
        int p;

        Scholar(int p, int d){
            this.d = d;
            this.p = p;
        }

        @Override
        public int compareTo(Scholar o) {
            if (this.p < o.p) return 1;
            else if (this.p > o.p) return -1;
            else {
                if (this.d > o.d) return 1;
                else if (this.d < o.d) return -1;
                else return 0;
            }
        }
    }

    public class Main {

        static int[] visited;
        static PriorityQueue<Scholar> Q = new PriorityQueue<>();


        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            visited = new int[10001];

            for (int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                Q.add(new Scholar(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            int ans = 0;
            while (!Q.isEmpty()){
                Scholar s = Q.poll();
                for (int i = s.d; i>0; i--){
                    if (visited[i] == 0) {
                        visited[i] = 1;
                        ans += s.p;
                        break;
                    }
                }
            }
            System.out.println(ans);
        }
    }