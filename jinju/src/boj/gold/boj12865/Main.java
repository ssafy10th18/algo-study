package boj.gold.boj12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] temp = (br.readLine()).split(" ");
		
		int N = Integer.parseInt(temp[0]);
		int K = Integer.parseInt(temp[1]);
		
		int[][] DP = new int[K + 1][K + 1]; //2차원으로 푼다.
		int[] weights = new int[N + 1];
		int[] values = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			temp = (br.readLine()).split(" ");

			weights[i] = Integer.parseInt(temp[0]);
			values[i] = Integer.parseInt(temp[1]);
		}
		
		for(int i = 1; i <= N; i++) {
			int w = weights[i];
			int v = values[i];
			for(int k = 1; k <= K; k++) { //임시 가방의 무게를 늘린다.
				if(w > k) { //i번째 물건 무게(w)보다 임시 가방의 무게가 작다면(못 담음)
					DP[i][w] = DP[i - 1][w];
				} else { //임시가방에 물건을 담을 수 있음
					DP[i][w] = Math.max(v + DP[i - 1][k - w], DP[i - 1][k]);
				}
			}
		}
		
		System.out.print(DP[N][K]);
	}

}
