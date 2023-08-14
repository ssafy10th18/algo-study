package swea.D2.swea2001;

import java.util.*;
import java.io.*;

class Solution
{
	private static int N;
	private static int M;
	
	private static int[][] matrix;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			String[] temp = br.readLine().split(" ");

			N = Integer.parseInt(temp[0]);
			M = Integer.parseInt(temp[1]);

			matrix = new int[N][N];

			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int max = Integer.MIN_VALUE;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					max = Math.max(max, sumArea(i, j));
				}
			}
			
			sb.append("#").append(test_case).append(" ").append(max).append("\n");
		}

		System.out.println(sb);
	}

	private static int sumArea(int row, int col) {
		int sum = 0;
		for(int i = row; i < row + M; i++) {
			for(int j = col; j < col + M; j++) {
				if(!isValid(i, j)) {
					return 0;
				}
				sum += matrix[i][j];
			}
		}

		return sum;
	}

	private static boolean isValid(int row, int col) {
		return (row > -1 && col > -1 && row < N && col < N);
	}
}
