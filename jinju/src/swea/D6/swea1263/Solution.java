package swea.D6.swea1263;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	
	static final int INF = 9999999;
	static int N, dist[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		//
		for (int k = 0; k < N; ++k) {
			for (int i = 0; i < N; ++i) {
				if (i == k)
					continue; // 출발지와 경유지가 같다면 다음 출발지
				for (int j = 0; j < N; ++j) {
					if (i == j || k == j)
						continue; // 경유지와 목적지가 같거나 출발지가 곧 목적지라면 패스
					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}

	}

}
