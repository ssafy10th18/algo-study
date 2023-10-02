import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, r, c, d;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0}; //북동남서
    static int[] dc = {0, 1, 0, -1};
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        cnt = 1; // 초기 로봇 위치 청소

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(r, c, d);

        System.out.println(cnt);

    }

    public static void dfs(int r, int c, int dir) {

        // 현재 칸 청소
        map[r][c] = 2;

        // 현재 칸 주변 4칸을 현재 방향의 왼쪽방향부터 차례대로 탐색
        for(int i = 0; i < 4; i++) {
            dir = (dir + 3) % 4; // 반시계 방향 90도 회전
            int nr = r + dr[dir];
            int nc = c + dc[dir];

            if(nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0) {
                cnt++;
                dfs(nr, nc, dir);

                return;
            }
        }

        // 주변 4칸이 모두 청소되어 있는 경우, 뒤쪽 칸이 벽이 아니라면 방향 유지한 채 후진
        int nd = (dir + 2) % 4; // 후진 방향
        int nr = r + dr[nd];
        int nc = c + dc[nd];

        if(nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] != 1) {
            dfs(nr, nc, dir);
        }
    }
}
