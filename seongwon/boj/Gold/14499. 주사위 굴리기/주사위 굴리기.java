import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dice;
    static int[][] map;
    static int N, M, x, y;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dice = new int[6];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++){
            int roll = Integer.parseInt(st.nextToken());
            if(!moveDice(roll)) continue;
            rollDice(roll);
            System.out.println(dice[1]);
        }
    }

    private static void rollDice(int roll) {
        switch (roll){
            case 1 :
                swap(1, 5);
                swap(1, 4);
                swap(3, 4);
                break;
            case 2 :
                swap(1, 4);
                swap(1, 5);
                swap(3, 5);
                break;
            case 3 :
                swap(0, 1);
                swap(1, 2);
                swap(2, 3);
                break;
            case 4 :
                swap(2, 3);
                swap(1, 2);
                swap(0, 1);
                break;
        }

        if(map[x][y] == 0){
            map[x][y] = dice[3];
        }
        else {
            dice[3] = map[x][y];
            map[x][y] = 0;
        }
    }

    private static void swap(int i, int j) {
        int temp = dice[i];
        dice[i] = dice[j];
        dice[j] = temp;
    }

    private static boolean moveDice(int roll) {
        int nx = x + dx[roll-1];
        int ny = y + dy[roll-1];
        if(nx < 0 || ny < 0 || nx >= N || ny >= M)return false;
        x = nx;
        y = ny;
        return true;
    }
}
