package swea.D3.swea5607;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			
			String[] temp = br.readLine().split(" ");
			
			int N = Integer.parseInt(temp[0]);
			int R = Integer.parseInt(temp[1]);
			
			
			
			sb.append("#").append(test_case).append(" ").append("").append("\n");
		}
		
		System.out.print(sb);
	}
	
	private static long nCr(int n, int r, int p) {
		if(r == 0) return 1L;
		
		long[] factorials = new long[n + 1]; //팩토리얼 값 기록
		factorials[0] = 1;
		
		for(int i = 1; i <= n; i++) 
			factorials[i] = factorials[i - 1] * i % p;
		
		long temp1 = power(factorials[r], p - 2, p) % p;
		long temp2 = power(factorials[n - r], p - 2, p) % p;
		
		return (factorials[n] * temp1 * temp2) % p;
	}

	private static long power(long x, int y, long p) {
		long res = 1L;
		
		x = x % p;
		
		while(y > 0) {
			if(y % 2 == 1) 
				res = (res * x) % p;
			y = y >> 1; // `y = y / 2` 보다 빠르다.
			x = (x * x) % p;
		}
		return res;
	}

}