import java.io.*;
import java.util.*;

public class Solution {
    static int[] dr = {-1,-1,-1, 0, 0, 1, 1, 1}; // 좌상, 상, 우상, 좌, 우 , 좌하, 하, 우하
    static int[] dc = {-1, 0, 1,-1, 1, -1, 0, 1};
    static boolean[][] visited;
    static int[][] cnt;
    static char[][] m;

    static Queue<int[]> q = new LinkedList<int[]>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            m = new char[N+2][N+2]; // 외곽을 한줄씩 더 두름
            cnt = new int[N+2][N+2]; // 폭탄의 개수, 0~8

            for (int i = 1; i <= N; i++) {
                String s = br.readLine();
                for (int j = 1; j <= N; j++) {
                    m[i][j] = s.charAt(j-1);

                    if (m[i][j] == '*') { // 폭탄이면, 인접칸에 폭탄개수 카운팅
                        cnt[i][j] = '*'; // 사용하지 않는 숫자로 폭탄 표시
                        for (int k = 0; k < dr.length; k++) { // 폭탄인 경우 주변 인접칸에 카운팅
                            int nr = i + dr[k];
                            int nc = j + dc[k];
                            if (m[nr][nc] != '*') { // 폭탄이 아닌곳만 숫자 기록
                                cnt[nr][nc]++;
                            }
                        }
                    }
                }
            }

            visited = new boolean[N+2][N+2];

            for (int i = 0; i < visited.length; i++) { // 외곽은 넘어가지 않도록 방문표시
                visited[0][i] = true;
                visited[i][N+1] = true;
                visited[i][0] = true;
                visited[N+1][i] = true;
            }

            int result = 0; // 클릭 횟수

            // 0인 지점을 찾아서 BFS로 탐색
            // 인접한 0은 모두 열기, 0이 아니면 한번만 열기
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (!visited[i][j] && cnt[i][j] == 0) { // 미방문, 0인 지점만 클릭
                        bfs(i,j);
                        result++;
                    }
                }
            }

            // 남은 숫자들 개수만 카운팅
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (!visited[i][j] && m[i][j] != '*') { // 미방문, 폭탄이 아닌 지점만 클릭
                        result++;
                    }
                }
            }
            sb.append('#').append(tc).append(' ').append(result).append('\n');
        }
        System.out.print(sb);
    }


    public static void bfs(int r, int c) {
        visited[r][c] = true;
        q.offer(new int[] {r,c});

        while(!q.isEmpty()) {
            int[] temp = q.poll();
            r = temp[0];
            c = temp[1];
            for (int i = 0; i < dr.length; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (visited[nr][nc]) continue;
                visited[nr][nc] = true; // 0이 아니면 방문 표시

                if (cnt[nr][nc] == 0) { // 0이면 큐에 삽입
                    q.offer(new int[] {nr,nc});
                }
            }
        }
    }
}