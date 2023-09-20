import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    static int[] dice = {0, 0, 0, 0, 0, 0};
    static int[][] map;
    static int n, m, x, y, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++) {
            int dir = Integer.parseInt(st.nextToken());

            int nx = x + dirs[dir-1][0];
            int ny = y + dirs[dir-1][1];

            // 이동할 좌표가 지도 범위 안에 있는지 확인
            if(!isValid(nx, ny)) continue;

            // 주사위 굴리기
            if(dir == 1) moveToEast();
            else if(dir == 2) moveToWest();
            else if(dir == 3) moveToNorth();
            else if(dir == 4) moveToSouth();

            // 칸에 쓰여 있는 수가 0이면
            if(map[nx][ny] == 0) {
                map[nx][ny] = dice[3]; // 주사위에 바닥면에 있는 수를 칸에 복사
            }
            // 칸에 쓰여 있는 수가 0이 아니면
            else {
                dice[3] = map[nx][ny]; // 칸에 있는 수를 주사위 바닥면으로 복사
                map[nx][ny] = 0; // 칸의 수를 0으로 변경
            }

            // 주사위 윗 면에 쓰여 있는 수 저장
            sb.append(dice[1]).append("\n");

            x = nx;
            y = ny;
        }

        System.out.println(sb);
    }

    static boolean isValid(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }

    static void moveToNorth() {
        int temp = dice[0];
        for(int i = 0; i < 3; i++) {
            dice[i] = dice[i+1];
        }
        dice[3] = temp;
    }

    static void moveToSouth() {
        int temp = dice[3];
        for(int i = 3; i >= 1; i--) {
            dice[i] = dice[i-1];
        }
        dice[0] = temp;
    }

    static void moveToEast() {
        int temp = dice[1];
        dice[1] = dice[4];
        dice[4] = dice[3];
        dice[3] = dice[5];
        dice[5] = temp;
    }

    static void moveToWest() {
        int temp = dice[1];
        dice[1] = dice[5];
        dice[5] = dice[3];
        dice[3] = dice[4];
        dice[4] = temp;
    }
}
