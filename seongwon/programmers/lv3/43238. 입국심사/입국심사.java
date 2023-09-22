import java.util.Arrays;
class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times); // 가장 적은 시간이 드는 심사관 부터
		long left = times[0]; // 가장 적은 시간으로 처리하는 경우
		long right = (long)times[times.length-1] * n;
		long answer = 0;
		
		while(left <= right) {
			long mid = (left + right) / 2;
			long possible = 0; // 해당 시간내에 심사가능한 사람의 수
			
			for(int i = 0; i < times.length; i++) {
				possible += mid / times[i];
			}
			
			if(possible < n) { // 심사가능한 인원이 총인원 보다 적은 경우
				left = mid + 1; // 최저시간을 증가하여 심사시간을 증가
			}
			else if(possible >= n) { // 심사 가능인원이 총인원 보다 많거나 같은 경우
				right = mid - 1; // 심사시간을 줄여도 가능한지 체크
				answer = mid; // 제일 적은 시간 갱신
			}
		}
		
		return answer;
    }
}