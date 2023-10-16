package boj.gold.boj1019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static long start;
	private static long end;
	private static long mul;
	
	private static long[] numbers;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//1 <= N <= 1_000_000_000
		int N = Integer.parseInt(br.readLine());
		
		start = 1;
		end = N;
		mul = 1;
		
		numbers = new long[10]; //0~9까지의 개수를 센다.
		
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
			
			//수를 변형시키면서 발생한 차를 저장한다. => 각 자리마다 공통적으로 나타난 수의 개수를 뜻한다.
			long gap = (end / 10) - (start / 10) + 1;
			
			for(int i = 0; i < 10; i++) {
				numbers[i] += (gap * mul);
			}
			
			//다음 자릿수를 확인한다.
			mul *= 10; //원본 수의 자리를 커팅했으므로 원복해주기 위함
			start /= 10;
			end /= 10;
		}
		
		StringBuilder sb = new StringBuilder();
		for(long n : numbers) {
			sb.append(n).append(" ");
		}
		
		System.out.println(sb);
	}
	
	private static void calculatePlaces(long target) {
		for(long i = target; i > 0; i /= 10) {
			String s = Long.toString(i);
			int digit = s.charAt(s.length() - 1) - '0';
			
			numbers[digit] += mul;
		}
	}
}
