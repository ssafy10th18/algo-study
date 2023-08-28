package swea.D2.swea2005;

import java.io.*;

class Solution
{
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			
			int[][] DP = new int[N + 1][N + 1];
			
			DP[0][0] = 1;
			
			for(int i = 1; i < N; i++) {
				for(int j = 0; j <= i; j++) {
					if(j == 0) {
						DP[i][j] = DP[i - 1][j];
					}
					else if(j == i) {
						DP[i][j] = DP[i - 1][j - 1];
					}
					else {
						DP[i][j] = DP[i - 1][j] + DP[i - 1][j - 1];
					}
				}
			}
			
			sb.append("#").append(test_case).append("\n");
			for(int i = 0; i < N; i++) {
				for(int j = 0; j <= i; j++) {
					sb.append(DP[i][j]).append(" ");
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}
}
