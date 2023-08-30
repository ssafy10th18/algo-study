package boj.gold.boj17070;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

	private static final int HOR = 0;
	private static final int DIAG = 1;
	private static final int VERT = 2;

	private static int N;

	private static int[][] matrix;
	private static int[][][] DP;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		matrix = new int[N][N];
		DP = new int[3][N][N]; // 파이프의 방향: 0, 1, 2

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		DP[HOR][0][0] = 1;
		DP[HOR][0][1] = 1;

		for (int i = 0; i < N; i++) {
			for (int j = 2; j < N; j++) {
				if(matrix[i][j] == 1) {
					continue;
				}
				
				if (i - 1 >= 0 && matrix[i - 1][j] != 1) { // 윗쪽에서 밀 수 있다면
					DP[VERT][i][j] += (DP[VERT][i - 1][j] + DP[DIAG][i - 1][j]);
				}

				if (j - 1 >= 0 && matrix[i][j - 1] != 1) { // 왼쪽에서 밀 수 있다면
					DP[HOR][i][j] += (DP[HOR][i][j - 1] + DP[DIAG][i][j - 1]);
				}

				if (i - 1 >= 0 && j - 1 >= 0 && matrix[i - 1][j] != 1 && matrix[i][j - 1] != 1 && matrix[i - 1][j - 1] != 1) { // 대각선에서 밀 수 있다면
					DP[DIAG][i][j] += (DP[HOR][i - 1][j - 1] + DP[VERT][i - 1][j - 1] + DP[DIAG][i - 1][j - 1]);
				}
			}

		}

		int result = 0;
		for (int k = 0; k < 3; k++) {
			result += DP[k][N - 1][N - 1];
		}

		System.out.println(result);
	}
}
