package boj.gold.boj2458;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DFS2 {
	private static int N;
	private static int M;

	private static int count = 0;

	private static int[][] adj;
	private static int[][] radj;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");

		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);

		adj = new int[N][N];
		radj = new int[N][N]; //역 인접행렬
		visited = new boolean[N];

		for (int i = 0; i < M; i++) {
			temp = br.readLine().split(" ");

			int u = Integer.parseInt(temp[0]);
			int v = Integer.parseInt(temp[1]);

			radj[v][u] = adj[u][v] = 1;
		}

		int result = 0;
		for (int k = 1; k <= N; k++) {
			DFS(k, adj);
			DFS(k, radj);

			if (count == N - 1)
				result++;
		}

		System.out.println(result);
	}

	//adj : 인접행렬이 주어지면 큰 키를 탐색, 역 인접행렬이 주어지면 작은 키를 탐색.
	private static void DFS(int curr, int[][] adj) {
		visited[curr] = true;
		for (int j = 1; j <= N; j++) {
			if (adj[curr][j] == 1 && !visited[j]) {
				count++;
				DFS(j, adj);
			}
		}
	}
}
