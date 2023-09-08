package boj.silver.boj16401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[] snacks = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			snacks[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(snacks);
		
		int result = 0;
		int maxSize = snacks[N - 1];
		
		int count = 0;
		for(int s : snacks) {
			count += s / maxSize; //초기 길이로 몇 명에게 줄 수 있는지 센다.
		}
		
		if(count >= M) {
			System.out.print(maxSize);
			return;
		}
		
		int left = 1;
		int right = maxSize;
		while(left <= right) {
			int mid = (left + right) / 2;
			
			count = 0;
			for(int s : snacks) {
				count += s / mid; //현재 길이로 몇 명에게 줄 수 있는지 센다.
			}
			
			if(count >= M) {
				result = Math.max(mid, result);
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		System.out.print(result);
	}

}
