package swea.D4.swea5604;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	private static long start;
	private static long end;
	private static long mul;
	
	private static long[] numbers;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			
			String[] temp = br.readLine().split(" ");
			
			//구간 A부터 B까지의 부분합을 구한다.
			start = Long.parseLong(temp[0]);
			end = Long.parseLong(temp[1]);
			
			numbers = new long[10]; //0~9까지의 개수를 센다.
			
			long result = 0;
			
			mul = 1;
			if(start == 0) start = 1; //0부터 합은 1부터 합이나 마찬가지이다.
			while(start <= end) {
				//1.시작 지점을 0으로 끝나는 수로 변형시킨다.
				while(start % 10 != 0 && start <= end) {
					calculatePlaces(start);
					start++; //끝자리가 0이 될 때까지 더한다.
				}
				
				if(start > end) break;
				//2.종료 지점을 9로 끝나는 수로 변형시킨다.
				while(end % 10 != 9 && start <= end) {
					calculatePlaces(end);
					end--; //끝자리가 9가 될 때까지 뺀다.
				}
				
				//수를 변형시키면서 발생한 차를 저장한다.
				long gap = (end / 10) - (start / 10) + 1;
				
				for(int i = 0; i < 10; i++) {
					numbers[i] += (gap * mul);
				}
				
				//다음 자릿수를 확인한다.
				mul *= 10;
				start /= 10;
				end /= 10;
			}
			
			for(int i = 1; i < 10; i++) {
				result += (i * numbers[i]);
			}
			
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		
		System.out.print(sb);
	}
	
	private static void calculatePlaces(long target) {
		for(long i = target; i > 0; i /= 10) {
			String s = Long.toString(i);
			int digit = s.charAt(s.length() - 1) - '0';
			
			numbers[digit] += mul;
		}
	}

}
