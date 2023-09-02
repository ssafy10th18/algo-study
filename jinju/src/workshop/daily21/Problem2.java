package workshop.daily21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] DP = new int[N + 1];
		
		DP[0] = 1;
		DP[1] = 2;
		
		for(int i = 2; i <= N; i++) {
			DP[i] = (DP[i - 1] * 2) + DP[i - 2];
		}
		
		System.out.println(DP[N]);
	}
}
