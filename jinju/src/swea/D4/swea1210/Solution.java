package swea.D4.swea1210;

import java.util.*;
import java.io.*;

public class Solution
{
	private static final int N = 100;

	private static int[] dr = { 0, 0, -1 };
	private static int[] dc = { -1, 1, 0 };

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;

		int[][] matrix = new int[N][N];

		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			int tc = Integer.parseInt(br.readLine());

			int startR = 0;
			int startC = 0;
			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());

					if (matrix[i][j] == 2) {
						startC = j;
						startR = i;
					}
				}
			}

            int result = move(matrix, startR, startC);
            
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}

		System.out.println(sb);
	}

	private static int move(int[][] matrix, int r, int c) {
		int nr = 0;
		int nc = 0;
		while (nr >= 0 && nc >= 0) {
			for (int i = 0; i < dr.length; i++) {
				nr = r + dr[i];
				nc = c + dc[i];

				if (!isValid(nr, nc)) {
					continue;
				}

				if(matrix[nr][nc] == 1) {
					matrix[nr][nc] = 3;
					r = nr;
					c = nc;
				}
			}
		}
		return nc;
	}
	private static boolean isValid(int nr, int nc) {
		return (nr > -1 && nc > -1 && nr < N && nc < N);
	}
}
