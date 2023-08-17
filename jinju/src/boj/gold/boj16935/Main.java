package boj.gold.boj16935;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

	private static int N;
	private static int M;
	private static int[][] matrix;
	private static int[][] subMatrix;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");

		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		int R = Integer.parseInt(temp[2]);

		matrix = new int[N][M];
		subMatrix = matrix;

		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < R; i++) {
			int command = Integer.parseInt(st.nextToken());

			switch(command) {
				case 1: {
					subMatrix = flipUpDown(subMatrix);
					break;
				}
				case 2: {
					subMatrix = flipLeftRight(subMatrix);
					break;
				}
				case 3: {
					subMatrix = rotateRight(subMatrix);
					break;
				}
				case 4: {
					subMatrix = rotateLeft(subMatrix);
					break;
				}
				case 5: {
					subMatrix = rotateClockwise(subMatrix);
					break;
				}
				case 6: {
					subMatrix = rotateCounterclockwise(subMatrix);
					break;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < subMatrix.length; i++) {
			for(int j = 0; j < subMatrix[0].length; j++) {
				if(subMatrix[i][j] != 0)
					sb.append(subMatrix[i][j]).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	private static int[][] flipUpDown(int[][] matrix) { //1
		int N = matrix.length;
		int M = matrix[0].length;

		int[][] subMatrix = new int[N][M];
		//col 고정
		for(int j = 0; j < M; j++) {
			for(int i = 0; i < N / 2; i++) {
				subMatrix[(N - 1) - i][j] = matrix[i][j];
				subMatrix[i][j] = matrix[(N - 1) - i][j];
			}
		}

		return subMatrix;
	}

	private static int[][] flipLeftRight(int[][] matrix) { //2
		int N = matrix.length;
		int M = matrix[0].length;

		int[][] subMatrix = new int[N][M];
		//row 고정
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M / 2; j++) {
				subMatrix[i][(M - 1) - j] = matrix[i][j];
				subMatrix[i][j] = matrix[i][(M - 1) - j];
			}
		}

		return subMatrix;
	}

	private static int[][] rotateRight(int[][] matrix) { //3
		int N = matrix.length;
		int M = matrix[0].length;

		int[][] subMatrix = new int[M][N]; //[8][6]

		for(int i = 0; i < N; i++) { //6
			for(int j = 0; j < M; j++) { //8
				subMatrix[j][(N - 1) - i] = matrix[i][j];
			}
		}

		return subMatrix;
	}

	private static int[][] rotateLeft(int[][] matrix) { //4
		int N = matrix.length;
		int M = matrix[0].length;

		int[][] subMatrix = new int[M][N];

		for(int i = 0; i < N; i++) { //6
			for(int j = 0; j < M; j++) { //8
				subMatrix[(M - 1) - j][i] = matrix[i][j];
			}
		}

		return subMatrix;
	}

	private static int[][] rotateClockwise(int[][] matrix) { //5
		int N = matrix.length;
		int M = matrix[0].length;

		int[][] subMatrix = new int[N][M];

		int rMid = (N / 2) - 1;
		int cMid = (M / 2) - 1;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(i <= rMid && j <= cMid) { //왼쪽위
					subMatrix[i][j + (cMid + 1)] = matrix[i][j];
				} else if(i <= rMid && j > cMid) { //오른쪽위
					subMatrix[i + (rMid + 1)][j] = matrix[i][j];
				} else if(i > rMid && j > cMid) { //오른쪽아래
					subMatrix[i][j - (cMid + 1)] = matrix[i][j];
				} else { //왼쪽아래
					subMatrix[i - (rMid + 1)][j] = matrix[i][j];
				}
			}
		}

		return subMatrix;
	}

	private static int[][] rotateCounterclockwise(int[][] matrix) { //6
		int N = matrix.length;
		int M = matrix[0].length;

		int[][] subMatrix = new int[N][M];

		int rMid = (N / 2) - 1;
		int cMid = (M / 2) - 1;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(i <= rMid && j <= cMid) { //왼쪽위
					subMatrix[i + (rMid + 1)][j] = matrix[i][j];
				} else if(i <= rMid && j > cMid) { //오른쪽위
					subMatrix[i][j - (cMid + 1)] = matrix[i][j];
				} else if(i > rMid && j > cMid) { //오른쪽아래
					subMatrix[i - (rMid + 1)][j] = matrix[i][j];
				} else { //왼쪽아래
					subMatrix[i][j + (cMid + 1)] = matrix[i][j];
				}
			}
		}

		return subMatrix;
	}
}