import java.io.*;
import java.util.*;

public class Main {

    static int x;
    static int y;
    static int d;
    static int[][] M;
    static int[][] dxy = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}}; // 북 동 남 서

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        M = new int[n][m];

        st = new StringTokenizer(br.readLine());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int idx = 0;
            while (st.hasMoreTokens()){
                M[i][idx++] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt=0;
        while (true){
            // #1
            if (M[y][x] == 0) cnt += 1;
            M[y][x] = -1;

            int flag = 0;
            for (int[] xy : dxy){
                // #3
                if (M[y+xy[1]][x+xy[0]] == 0) {
                    // #2
                    d -= 1;
                    if (d < 0) d=3;
                    if (M[y+dxy[d][1]][x+dxy[d][0]] == 0) {
                        x += dxy[d][0];
                        y += dxy[d][1];
                    }
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) continue;

            int td = d+2;
            if (td > 3) td-=4;
            if (M[y+dxy[td][1]][x+dxy[td][0]] != 1){
                x += dxy[td][0];
                y += dxy[td][1];
            }
            else {
                break;
            }
        }
        System.out.println(cnt);

    }
}
