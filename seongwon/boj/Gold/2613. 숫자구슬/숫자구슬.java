import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] beads;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 구슬의 수
        M = Integer.parseInt(st.nextToken()); // 그룹의 수

        beads = new int[N];
        int left = 0, right = 0; // left = 그룹의 합중 가장 작은 값 , right = 그룹의 합중 가장 큰 값
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            beads[i] = Integer.parseInt(st.nextToken());
            left = Math.max(left, beads[i]); // 1개의 구슬이 1개의 그룹일 때 최댓값
            right += beads[i]; // 모든 구슬이 같은 그룹인 경우의 합
        }
        int mid = 0, groupCnt = 0, answer = Integer.MAX_VALUE;
        while(left <= right){
            mid = (left + right) / 2; // 각 그룹이 갖을 수 있는 합
            groupCnt = countGroup(mid); // mid를 이용해 만들 수 있는 그룹의 수

            if(M < groupCnt) { // 요구되는 그룹 M 보다 만들 수 있는 그룹의 수가 많은 경우
                left = mid + 1; // 각 그룹이 갖을 수 있는 최대합을 높여 그룹의 수 감소
            } else {    // 요구되는 그룹 M 보다 만들 수 있는 그룹의 수가 적은 경우
                right = mid - 1; // 각 그룹이 갖을 수 있는 최대합을 낮춰 그룹의 수 증가
                if(mid < answer){ // 그룹의 최솟값 갱신
                    answer = mid;
                }
            }
        }

        StringBuilder sb = new StringBuilder().append(answer).append("\n");
        int sum = 0, cnt = 0;
        for(int i = 0; i < N; i++){ // 각 그룹별 구슬 갯수 카운트
            sum += beads[i];
            if(sum <= answer)cnt++; // 그룹합이 최댓값을 넘지 않는다면 count
            else if(sum > answer){ // 그룹합이 최댓값을 넘는다면 결과에 구슬수 append
                sb.append(cnt).append(" ");
                cnt = 1;
                sum = beads[i];
                M--;
            }
            if(M == N - i)break; // 남은 그룹의 수와 남은 구슬의 수가 같다면 종료
        }
        for(int i = 0; i < M; i++){ // 남은 구릅의 수만큼 1로 채우기
            sb.append(cnt).append(" ");
            cnt = 1;
        }
        System.out.println(sb.toString());
    }

    /**
     * 각 그룹이 갖을 수 있는 합의 최댓 값 (mid)을 지키며 만들 수 있는 그룹의 수를 카운트
     * @param mid
     * @return 만들 수 있는 그룹의 수
     */
    private static int countGroup(int mid) {
        int sum = 0;
        int cnt = 0;
        for(int i = 0; i < N; i++){
            sum += beads[i]; // 그룹 합
            if(sum > mid){ // 그룹이 갖을 수 있는 최댓값을 넘는 경우 카운트
                sum = beads[i];
                cnt++;
            }
        }
        if(sum > 0)cnt++;
        return cnt;
    }
}
