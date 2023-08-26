import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int dis = y - x; // 거리
            double max = Math.round(Math.sqrt(dis)); // 최댓값
            if(dis > max * max){ // 거리 > 최댓값 제곱 이면 최댓값 * 2
                System.out.println((int)max * 2);
            }
            else { // 거리 <= 최댓값 제곱 이면 최댓값 * 2 - 1
                System.out.println((int)max * 2 - 1);
            }
        }

    }
}
