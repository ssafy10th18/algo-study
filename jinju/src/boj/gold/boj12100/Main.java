package boj.gold.boj12100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 2048 (Easy)

	private static final int RIGHT = 0;
	private static final int DOWN = 1;
	private static final int LEFT = 2;
	private static final int UP = 3;
	
	private static int[] dr = { 0, 1, 0, -1 }; // 오른쪽, 아래, 왼쪽, 위쪽
	private static int[] dc = { 1, 0, -1, 0 };
	private static int[][] matrix;
	
	private static int N;
	private static int maxDepth = 5;
	private static int max = -1;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		matrix = new int[N][N];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		DFS(0, matrix);

		System.out.println(max);
	}

	private static void DFS(int depth, int[][] matrix) {
		if (depth == maxDepth) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (max < matrix[i][j])
						max = matrix[i][j];
				}
			}
			return;
		}

		for (int i = 0; i < dr.length; i++) {
			int[][] newMatrix = new int[N][N];
			
			copyMatrix(matrix, newMatrix);
			sumBlocks(i, newMatrix);
			moveBlocks(i, newMatrix);

			if (isSameMatrix(matrix, newMatrix)) {// 밀어도 변화가 없다면
				continue; // 방향을 바꾼다.
			}

			DFS(depth + 1, newMatrix);
		}
	}

	private static void sumBlocks(int dir, int[][] matrix) {
		if (dir == RIGHT) { // 인덱스가 오른쪽 끝부터 감소하면서 탐색
			for(int i = 0; i < N; i++) {
				int j = N - 1;
				while(j > 0) {
					if(matrix[i][j - dc[dir]] == matrix[i][j]) {
						matrix[i][j] = matrix[i][j] * 2;
						matrix[i][j - dc[dir]] = 0;
					}
					j -= dc[dir];
				}
			}
		} else if (dir == DOWN) { // 인덱스가 아래쪽 끝부터 감소하면서 탐색
			for(int j = 0; j < N; j++) {
				int i = N - 1;
				while(i > 0) {
					if(matrix[i - dr[dir]][j] == matrix[i][j]) {
						matrix[i][j] = matrix[i][j] * 2;
						matrix[i - dr[dir]][j] = 0;
					}
					i -= dr[dir];
				}
			}
		} else if (dir == LEFT) {// 인덱스 0부터 오른쪽으로 증가하면서 탐색
			for(int i = 0; i < N; i++) {
				int j = 0;
				while(j < N) {
					if(matrix[i][j - dc[dir]] == matrix[i][j]) {
						matrix[i][j] = matrix[i][j] * 2;
						matrix[i][j - dc[dir]] = 0;
					}
					j -= dc[dir];
				}
			}
		} else if (dir == UP) { // 인덱스 0부터 아래쪽으로 증가하면서 탐색
			for(int j = 0; j < N; j++) {
				int i = 0;
				while(i < N) {
					if(matrix[i - dr[dir]][j] == matrix[i][j]) {
						matrix[i][j] = matrix[i][j] * 2;
						matrix[i - dr[dir]][j] = 0;
					}
					i -= dr[dir];
				}
			}
		}
	}
	
	private static void moveBlocks(int dir, int[][] matrix) {
		if (dir == RIGHT) { 

		} else if (dir == DOWN) {
			for (int col = 0; col < N; col++) {
				int row = N - 1; 
				int nr = -1;
				
				while(row > 0) {
					if(matrix[row][col] == 0) { //빈칸이라면 내릴 벽돌을 찾는다.
						nr = row - 1;
						while(nr > 0 && matrix[nr][col] == 0) --nr; //행을 위로 올린다.
						
						matrix[row][col] = matrix[nr][col]; //새로 찾은 벽돌을 빈 칸에 넣는다.
						matrix[nr][col] = 0;
					}
					if(nr == 0) break; //해당 행의 탐색 종료
					--row; //아직 해당 행을 다 못 봤다면 계속 탐색
				}
			}
		} else if (dir == LEFT) {

		} else if (dir == UP) {
			
		}
	}
	
	private static void copyMatrix(int[][] matrix, int[][] newMatrix) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newMatrix[i][j] = matrix[i][j];
			}
		}
	}

	private static boolean isSameMatrix(int[][] matrix, int[][] newMatrix) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (matrix[i][j] == newMatrix[i][j])
					return false;
			}
		}

		return true;
	}
}
