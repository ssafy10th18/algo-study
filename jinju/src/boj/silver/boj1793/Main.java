package boj.silver.boj1793;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

	//private static final int DIV = 10_007;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		String line = null;
		while((line = br.readLine()) != null) {
			int N = Integer.parseInt(line);

			BigInteger[] DP = new BigInteger[N + 1];

			DP[0] = new BigInteger("1"); //아무 것도 선택 안 하는 경우의 수 1개
			
			if(N >= 1) {
				DP[1] = new BigInteger("1");
			}
			
			if(N >= 2) {
				DP[2] = new BigInteger("3");
			}

			BigInteger mul = new BigInteger("2");
			for (int i = 3; i <= N; i++) {
				DP[i] = DP[i - 1].add((DP[i - 2].multiply(mul)));
			}
			
			sb.append(DP[N]).append("\n");
		}
		br.close();
		
		System.out.print(sb);
	}

}
