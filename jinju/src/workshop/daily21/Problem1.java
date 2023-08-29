package workshop.daily21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem1 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		int[] DP = new int[N + 1];
		
		DP[0] = 1;
		DP[1] = 2; 
		DP[2] = DP[0] + DP[1];
		
		if(N == 1) {
			System.out.print(DP[1]);
			return;
		}
		
		if(N == 2) {
			System.out.print(DP[2]);
			return;
		}
		
		for(int i = 3; i <= N; i++) {
			DP[i] = DP[i - 1] + DP[i - 2];
		}
		
		System.out.print(DP[N]);
	}
}
