package boj.gold.boj14502;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static final int EMPTY = 0;
	private static final int VIRUS = 2;

	private static int N;
	private static int M;

	private static int[] dr = { 1, 0, -1, 0 };
	private static int[] dc = { 0, 1, 0, -1 };

	private static int[][] matrix;

	private static List<Point> points = new ArrayList<>();
	private static List<Point> viruses = new ArrayList<>();

	private static int safeArea = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");

		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);

		matrix = new int[N][M];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());

				if (matrix[i][j] == EMPTY) {
					points.add(new Point(j, i));
				}

				if (matrix[i][j] == VIRUS) {
					viruses.add(new Point(j, i));
				}
			}
		}

		combination(0, 0);

		System.out.print(safeArea);
	}

	private static void combination(int depth, int lastIndex) {
		if (depth == 3) {
			int[][] copied = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					copied[i][j] = matrix[i][j];
				}
			}

			for (Point start : viruses) {
				BFS(start, copied);
			}

			int sum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (copied[i][j] == EMPTY)
						sum += 1;
				}
			}

			safeArea = Math.max(sum, safeArea);
			return;
		}

		for (int i = lastIndex; i < points.size(); i++) {
			Point p = points.get(i);
			matrix[p.y][p.x] = 1;
			combination(depth + 1, i + 1);
			matrix[p.y][p.x] = 0;
		}
	}

	private static void BFS(Point start, int[][] copied) {
		Queue<Point> queue = new ArrayDeque<>();

		queue.add(start);

		while (!queue.isEmpty()) {
			Point p = queue.poll();

			int nr = 0;
			int nc = 0;
			for (int i = 0; i < dr.length; i++) {
				nr = p.y + dr[i];
				nc = p.x + dc[i];

				if (!isValid(nr, nc)) {
					continue;
				}

				if (copied[nr][nc] == EMPTY) {
					copied[nr][nc] = VIRUS;
					queue.add(new Point(nc, nr));
				}
			}
		}

		return;
	}

	private static boolean isValid(int nr, int nc) {
		return (nr > -1 && nc > -1 && nr < N && nc < M);
	}

}
