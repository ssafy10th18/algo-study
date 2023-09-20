import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[][] magnets;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        magnets = new int[4 + 1][8];

        for(int tc = 1; tc <= T; tc++) {
            int K = Integer.parseInt(br.readLine());

            // 자석 입력
            for(int i = 1; i <= 4; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for(int j = 0; j < 8; j++) {
                    magnets[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 자석 회전 정보 입력
            for(int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                // 자석 회전
                rotate(num, dir, num);
            }

            int sum = 0;
            for(int i=1;i<=4;i++) {
                sum += (magnets[i][0] * Math.pow(2,i-1));
            }

            sb.append("#").append(tc).append(" ").append(sum).append("\n");
        }

        System.out.println(sb);
    }

    // now : 현재돌리는 자석, dir : 방향, org : 처음시작한자석번호
    public static void rotate(int now, int dir, int org) {
        // now 자석이 org 자석의 왼쪽에 있다면 now 자석의 왼쪽 자석으로 넘어간다 (now-1)
        // now 자석의 6번과 왼쪽 자석의 2번이 서로 다르다면 다음 왼쪽 자석도 회전시킨다
        // 현재자석이 가장왼쪽 마지막 자석인지 확인한다
        if (now - 1 >= 1 && now <= org && magnets[now][6] != magnets[now - 1][2]) {
            rotate(now - 1, dir * -1, org);
        }
        // now 자석이 org 자석의 오른쪽에 있다면 now 자석의 오른쪽 자석으로 넘어간다 (now+1)
        // now 자석의 6번과 왼쪽 자석의 2번이 서로 다르다면 다음 왼쪽 자석도 회전시킨다
        // 현재자석이 가장오른쪽 마지막 자석인지 확인한다
        if (now + 1 <= 4 && now >= org && magnets[now][2] != magnets[now + 1][6]) {
            rotate(now + 1, dir * -1, org);
        }

        // 시계 방향으로 회전
        if(dir==1) {
            int tmp = magnets[now][7];
            for (int i = 7; i > 0; i--) {
                magnets[now][i]=magnets[now][i-1];
            }
            magnets[now][0]=tmp;
        }
        // 반시계 방향으로 회전
        else {
            int tmp = magnets[now][0];
            for (int i = 0; i < 7; i++) {
                magnets[now][i]=magnets[now][i+1];
            }
            magnets[now][7]=tmp;
        }
    }

    public static void print(int[][] magnet) {
        for (int r = 1; r <= 4; r++) {
            for (int c = 0; c < 8; c++) {
                System.out.print(magnets[r][c] + " ");
            }
            System.out.println();
        }
    }

}