import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        dp = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int max = 1; // 최대 LIS 값

        // dp 배열 값 채우기
        for(int i = 0; i < N; i++) {
            // 해당 위치를 1로 초기화
            dp[i] = 1;
            // 현재 원소와 이전의 원소 값을 비교
            for(int j = 0; j < i; j++) {
                // 증가하는 부분 수열이 될 수 있는 경우
                if(arr[j] < arr[i]) {
                    // dp[j]+1 의 최댓값으로 dp[i]를 갱신
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            // 최대 LIS 값 갱신
            max = Math.max(max, dp[i]);
        }

        sb.append(max).append("\n");

        // 경로를 역추적 하기 위한 stack
        Stack<Integer> stack = new Stack<>();

        for(int i = N - 1; i >= 0; i--){
            // 현재 찾는 길이와 같은 경우
            if(max == dp[i]) {
                // stack에 값 push
                stack.push(arr[i]);
                // max를 1 감소시켜 다음에 찾을 길이 설정
                max--;
            }
        }

        while (!stack.isEmpty()){
            sb.append(stack.pop() + " ");
        }

        System.out.println(sb.toString());

    }

}