package boj.gold.boj2613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N;
	private static int M;
	
	private static int[] DP;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] beads = new int[N];
		DP = new int[N];
		
		int start = 0;
		int end = 0;
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			beads[i] = Integer.parseInt(st.nextToken());
						
			start = Math.max(beads[i], start);
			end += beads[i];
		}
		
		DP[0] = beads[0];
		for(int i = 1; i < N; i++) {
			DP[i] = DP[i - 1] + beads[i];
		}

		int answer = Integer.MAX_VALUE;
		
		//이분탐색
		while(start <= end) {
			int mid = (start + end) / 2;
			
			if(isValidGroup(mid)) { //그룹의 수가 적다면
				end = mid - 1; //최댓값을 더 작게 잡는다.
				answer = Math.min(answer, mid);
			} else { //그룹의 수가 많다면
				start = mid + 1; //최댓값을 더 크게 잡는다.
			}
		}
		
		System.out.println(answer);
	}

	private static boolean isValidGroup(int mid) {
		int groupCount = 1;
		
		int left = -1; int right = 1;
		while(left <= right && right < N) {
			int sum = 0;
			if(left > 0) {
				sum = DP[right] - DP[left];
			} else sum += DP[right];
			
			if(sum >= mid) { 
				left = right; //새 구간부터 다시 부분합을 구한다.
				right = left + 1;
				groupCount++;
			} else {
				right++;
			}
		}
		
		return (M >= groupCount);
	}

}
