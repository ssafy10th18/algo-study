package boj.gold.boj4485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	private static int N;

	private static int[][] matrix;
	private static int[][] DP;

	private static int[] dr = { 0, 1, -1, 0 };
	private static int[] dc = { 1, 0, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = 1;
		StringBuilder sb = new StringBuilder();
		while ((N = Integer.parseInt(br.readLine())) != 0) {
			matrix = new int[N][N];
			DP = new int[N][N];

			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());
					DP[i][j] = Integer.MAX_VALUE;
				}
			}

			dijkstra(0, 0);
			
			sb.append("Problem ").append(tc++).append(": ")
			.append(DP[N - 1][N - 1]).append("\n");
		}

		System.out.print(sb);
	}

	private static void dijkstra(int startR, int startC) {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.dist, o2.dist));

		DP[startR][startC] = matrix[startR][startC];
		pq.add(new Node(startR, startC, DP[startR][startC]));

		while (!pq.isEmpty()) {
			Node n = pq.poll();

			if (n.row == N - 1 && n.col == N - 1) {
				return;
			}

			int nr = 0;
			int nc = 0;
			for (int i = 0; i < dr.length; i++) {
				nr = n.row + dr[i];
				nc = n.col + dc[i];

				if (!isValid(nr, nc)) {
					continue;
				}

				//DP값이 갱신됐다면 재탐색 해야하므로 별도의 visit 처리는 하지 않는다.
				if (DP[n.row][n.col] + matrix[nr][nc] < DP[nr][nc]) {
					DP[nr][nc] = DP[n.row][n.col] + matrix[nr][nc];
					pq.add(new Node(nr, nc, DP[nr][nc]));
				}
			}
		}

		return;
	}

	private static boolean isValid(int nr, int nc) {
		return (nr > -1 && nc > -1 && nr < N && nc < N);
	}
}

class Node {
	int row;
	int col;
	int dist;

	public Node(int row, int col, int dist) {
		this.row = row;
		this.col = col;
		this.dist = dist;
	}
}
