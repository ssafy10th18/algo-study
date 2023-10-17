package boj.gold.boj2458;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Memo {

	private static int N;
	private static int M;

	private static int[][] adj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");

		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);

		adj = new int[N + 1][N + 1]; // 0행, 0열에 메모 => 0행은 자신보다 작은 개수, 0열은 자신보다 큰 개수

		for (int i = 0; i <= N; i++)
			adj[i][0] = -1;

		for (int i = 0; i < M; i++) {
			temp = br.readLine().split(" ");

			int u = Integer.parseInt(temp[0]) - 1;
			int v = Integer.parseInt(temp[1]) - 1;

			adj[u][v] = 1; // u보다 v의 키가 크다.
		}
		
		int answer = 0;
		for(int k = 1; k <= N; k++) {
			if(adj[k][0] == -1) gtDFS(k); //탐색된 적 없는 학생만 탐색한다.
		}
		
		//메모된 인접 행렬의 상태를 이용하여 자신보다 작은 학생 수를 출력한다.
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				adj[0][j] += adj[i][j];
			}
		}
		
		for(int i = 1; i <= N; i++) { //합계가 N - 1인지 확인한다.
			if(adj[i][0] + adj[0][i] == N - 1) answer++;
		}
		
		System.out.print(answer);
	}

	private static void gtDFS(int k) {
		for (int i = 1; i <= N; i++) {
			if (adj[k][i] == 1) {
				if (adj[i][0] == -1) { // 아직 탐색이 되지 않은 상태라면
					gtDFS(i);
				}

				// 탐색을 완료했거나 이미 탐색된 대상이어서 탐색하지 않고 내려온 경우.
				// i보다 큰 대상이 1명 이상이라면, k보다 큰 간접 대상이 존재 가능한 상황이다.
				// ==> 새롭게 알게 된 간접 관계를 나와의 직접 관계로 반영해 인접 행렬에 표기한다.
				if (adj[i][0] > 0) {
					for (int j = 1; j <= N; j++) {
						if (adj[i][j] == 1) { // i보다 큰 j가 존재한다면
							adj[k][j] = 1;
						}
					}
				}
			}
		}
		
		//자신의 인접 행렬을 모두 처리 ==> 직/간접적 관계가 인접 행렬에 모두 반영된 상태.
		//자신보다 큰 학생 수를 세서 메모하고 종료한다.
		
		int count = 0;
		for(int j = 1; j <= N; j++) {
			count += adj[k][j];
		}
		
		adj[k][0] = count;
	}

}
