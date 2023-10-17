package boj.gold.boj2473;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		long[] sol = new long[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			sol[i] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(sol);

		int ansL = 0;
		int ansR = 0;
		int ansM = 0;
		
		long key = Long.MAX_VALUE;

		for(int mid = 1; mid < N - 1; mid++) {
			int left = 0;
			int right = N - 1;
			while (left <= right) {
				long mixed = sol[left] + sol[right] + sol[mid];
				
				if(left >= mid || right <= mid) { break; }
				
				if(Math.abs(0 - mixed) < Math.abs(0 - key)) {
					key = Math.abs(0 - mixed);
					ansL = left;
					ansR = right;
					ansM = mid;
				}
				
				if (mixed < 0) {
					left++;
				} else if (mixed > 0) {
					right--;
				} else { //(mixed == 0) : 최적해를 찾았다면
					break;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(sol[ansL]).append(" ").append(sol[ansM])
			.append(" ").append(sol[ansR]);
		
		System.out.print(sb);
	} 

}
