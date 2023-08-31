package boj.silver.boj1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {

			String[] temp = br.readLine().split(" ");

			int N = Integer.parseInt(temp[0]);
			int M = Integer.parseInt(temp[1]);

			int[][] DP = new int[N][M];

			for (int i = 0; i < M; i++) {
				DP[0][i] = 1;
			}
			
			for (int i = 1; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(i > j) { continue; }
					
					for (int k = 0; k < j; k++) {
						DP[i][j] += DP[i - 1][k]; //i번 사이트가 j번 사이트를 선택했을 때 모든 경우의 수
					}
				}
			}
			
			int result = 0;
			for(int i = 0; i < M; i++) {
				result += DP[N - 1][i];
			}
			
			sb.append(result).append("\n");
		}

		System.out.println(sb);
	}

}
