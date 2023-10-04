package swea.D4.swea5643;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

	private static boolean[][] D;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());

			D = new boolean[N][N];

			for(int i = 0; i < M; i++) {
				String[] temp = br.readLine().split(" ");
				
				int u = Integer.parseInt(temp[0]) - 1;
				int v = Integer.parseInt(temp[1]) - 1;
				
				D[u][v] = true;
			}
			
			for(int k = 0; k < N; k++) { //경유지
				for(int i = 0; i < N; i++) { //출발지
					for(int j = 0; j < N; j++) { //도착지
						if(D[i][k] && D[k][j]) { //간접 경로가 존재한다면
							D[i][j] = true; //i, j가 연결된 것으로 본다.
						}
					}
				}
			}
			
			int[] counts = new int[N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(D[i][j] || D[j][i]) { //양방향 중 한 쪽으로 연결되어 있다면
						counts[i]++;
					}
				}
			}
			
			int result = 0; //다른 모든 점들과 연결된 노드의 수
			for(int n : counts) {
				if(n == N - 1) result++;
			}
			
			sb.append("#").append(test_case).append(" ")
				.append(result).append("\n");
		}
		
		System.out.print(sb);
	}
}
