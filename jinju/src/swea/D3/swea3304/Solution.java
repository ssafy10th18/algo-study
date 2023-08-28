package swea.D3.swea3304;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		String[] temp;
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
			temp = br.readLine().split(" ");
			
			String s1 = temp[0];
			String s2 = temp[1];
			
			int slen1 = s1.length();
			int slen2 = s2.length();
			
			int[][] DP = new int[slen1 + 1][slen2 + 1]; //0은 사용 안함
			
			sb.append("#").append(test_case).append(" ");
			
			for(int i = 1; i <= slen1; i++) {
				for(int j = 1; j <= slen2; j++) {
					char c1 = s1.charAt(i - 1); //row
					char c2 = s2.charAt(j - 1); //col
					
					if(c1 == c2) {
						DP[i][j] = DP[i - 1][j - 1] + 1; //왼쪽, 위쪽에 더해버리면 중복 경우 생김
					} else {
						DP[i][j] = Math.max(DP[i - 1][j], DP[i][j - 1]);
					}
				}
			}
			
			sb.append(DP[slen1][slen2]).append("\n");
		}
		
		System.out.println(sb);
	}
}
