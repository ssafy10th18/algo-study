package boj.gold.boj4485;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {

	private static int N;

	private static int[][] matrix;
	private static int[][] DP;

	private static int[] dr = { 0, 1, -1, 0 };
	private static int[] dc = { 1, 0, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

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

		BFS(0, 0);

		System.out.print(DP[N - 1][N - 1]);
	}

	private static void BFS(int startR, int startC) {
		Queue<Point> queue = new ArrayDeque<>(); //다익스트라: DP값을 기준으로 정렬하는 pq 생성

		DP[startR][startC] = matrix[startR][startC];
		queue.add(new Point(startC, startR));

		while (!queue.isEmpty()) {
			Point p = queue.poll();

			if (p.y == N - 1 && p.x == N - 1) {
				return;
			}

			int nr = 0;
			int nc = 0;
			for (int i = 0; i < dr.length; i++) {
				nr = p.y + dr[i];
				nc = p.x + dc[i];

				if (!isValid(nr, nc)) {
					continue;
				}

				// DP가 갱신됐다면 방문 여부에 상관 없이 재방문한다.
				if (DP[p.y][p.x] + matrix[nr][nc] < DP[nr][nc]) {
					DP[nr][nc] = DP[p.y][p.x] + matrix[nr][nc];
					queue.add(new Point(nr, nc));
				}
			}
		}

		return;
	}

	private static boolean isValid(int nr, int nc) {
		return (nr > -1 && nc > -1 && nr < N && nc < N);
	}

}
