package swea.D4.swea1861;

import java.awt.Point;
import java.io.*;
import java.util.*;

public class Solution {

	private static int[] dx = {1, 0, -1, 0};
	private static int[] dy = {0, 1, 0, -1};

	private static int[][] matrix;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());

			matrix = new int[N][N];

			StringTokenizer st;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int maxValue = Integer.MAX_VALUE;
			int maxCount = 0;
			sb.append("#").append(test_case).append(" ");
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					int cnt = BFS(i, j);
					if(cnt > maxCount) {
						maxCount = cnt;
						maxValue = matrix[i][j];
					}

					if(cnt == maxCount && matrix[i][j] < maxValue) {
						maxValue = matrix[i][j];
					}
				}
			}

			sb.append(maxValue).append(" ").append(maxCount).append("\n");
		}

		System.out.println(sb);
	}

	private static int BFS(int startR, int startC) {
		Queue<Point> queue = new ArrayDeque<>();

		queue.add(new Point(startC, startR));

		int count = 1;
		while(!queue.isEmpty()) {
			Point p = queue.poll();

			int nr = 0;
			int nc = 0;
			for(int i = 0; i < dx.length; i++) {
				nr = p.y + dy[i];
				nc = p.x + dx[i];

				if(!isValid(nr, nc)) {
					continue;
				}

				if(matrix[nr][nc] == matrix[p.y][p.x] + 1) {
					queue.add(new Point(nc, nr));
					count++;
				}
			}
		}

		return count; 
	}

	private static boolean isValid(int nr, int nc) {
		return (nr > -1 && nc > -1 && nr < matrix.length && nc < matrix.length);
	}
}