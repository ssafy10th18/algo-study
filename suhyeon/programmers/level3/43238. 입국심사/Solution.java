import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        long left = 0;
        long right = (long) n * times[times.length - 1]; // 최대 소요 시간

        while(left <= right) {
            long mid = (left + right) / 2;
            long sum = 0; // 심사한 인원

            // 각 심사대 별로 mid분 동안 몇명의 사람을 심사할 수 있는지 확인
            for(int i = 0; i < times.length; i++) {
                sum += mid / times[i];
            }

            // 해당 시간 안에 모든 사람 심사 불가능한 경우
            if(sum < n) {
                // 시간을 늘려 다시 탐색
                left = mid + 1;
            }
            // 모든 사람 심사 가능한 경우
            else {
                // 시간을 줄여 최소 시간 갱신
                right = mid - 1;
                answer = mid;
            }
        }
        return answer;
    }
}