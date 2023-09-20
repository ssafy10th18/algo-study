import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
        boolean[] visited = new boolean[10001];
        int answer = 0;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        // 비용을 기준으로 내림차순 정렬
        Arrays.sort(arr, (o1, o2) -> {
            // 비용이 같으면 날짜를 기준으로 내림차순 정렬
            if(o1[0] == o2[0]){
                return Integer.compare(o2[1],o1[1]);
            }
            else{
                return Integer.compare(o2[0],o1[0]);
            }
        });

        for(int i = 0; i < N; i++) {
            // 강연 날짜부터 1일까지 역순으로 강연 가능한 날짜가 있는지 확인
            for(int j = arr[i][1]; j >= 1; j--) {
                if(!visited[j]) {
                    visited[j] = true;
                    answer += arr[i][0];
                    break;
                }
            }
        }

        System.out.println(answer);
    }

}
