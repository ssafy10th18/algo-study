package boj.bronze.boj10163;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

	private static final int SIZE = 1001;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][] board = new int[SIZE][SIZE];

		StringTokenizer st;
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int col = Integer.parseInt(st.nextToken());
			int row = Integer.parseInt(st.nextToken());

			int width = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());

			for(int r = row; r < row + height; r++) {
				for(int c = col; c < col + width; c++) {
					board[r][c] = i;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int k = 1; k <= N; k++) {
			int result = 0;
			for(int r = 0; r < SIZE; r++) {
				for(int c = 0; c < SIZE; c++) {
					if(board[r][c] == k)
						result++;
				}
			}
			sb.append(result).append("\n");
		}

		System.out.println(sb);
	}

}