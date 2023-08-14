package boj.silver.boj11660;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

	private static int[][] matrix;
	private static int[][] sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");

		int N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);

		matrix = new int[N + 1][N + 1];
		sum = new int[N + 1][N + 1];

		StringTokenizer st;
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j <= N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
				sum[i][j] = sum[i][j - 1] + matrix[i][j];
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			temp = br.readLine().split(" ");
			int x1 = Integer.parseInt(temp[0]);
			int y1 = Integer.parseInt(temp[1]);
			int x2 = Integer.parseInt(temp[2]);
			int y2 = Integer.parseInt(temp[3]);

			int result = calculate(x1, y1, x2, y2);

			sb.append(result).append("\n");
		}

		System.out.println(sb);
	}

	private static int calculate(int r1, int c1, int r2, int c2) {
		int rslt = 0;
		for(int i = r1; i <= r2; i++) {
			rslt += (sum[i][c2] - sum[i][c1 - 1]);
		}

		return rslt;
	}

}
