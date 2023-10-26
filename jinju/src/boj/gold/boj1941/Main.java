package boj.gold.boj1941;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	private static final int SIZE = 5;
	private static final int MAX_DEPTH = 7;

	private static char[][] matrix;
	private static boolean[][] visited;

	private static int[] dr = { 1, 0, -1, 0 };
	private static int[] dc = { 0, 1, 0, -1 };

	private static int count = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		matrix = new char[SIZE][SIZE];
		
		for (int i = 0; i < SIZE; i++) {
			String lines = br.readLine();
			for (int j = 0; j < SIZE; j++) {
				matrix[i][j] = lines.charAt(j);
			}
		}

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				visited = new boolean[SIZE][SIZE];
				
				int startY = 0;
				if(matrix[i][j] == 'Y') startY++;
				
				DFS(0, startY, i, j); // 시작점은 매번 달라야 한다.
			}
		}

		System.out.println(count);
	}

	private static void DFS(int depth, int countY, int row, int col) {
		if(countY > 3) {
			return;
		}
		
		if(depth == MAX_DEPTH) {
			count++;
			return;
		}
		
		for(int i = 0; i < dr.length; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];

			if (!isValid(nr, nc) || visited[nr][nc]) {
				continue;
			}

			int nextY = countY;
			if (matrix[nr][nc] == 'Y') { // 다음에 포섭할 학생이 임도연파라면
				nextY += 1; // Y의 수를 1 늘린다.
			}
			
			visited[nr][nc] = true;
			DFS(depth + 1, nextY, nr, nc);
			visited[nr][nc] = false;
		}
	}

	private static boolean isValid(int nr, int nc) {
		return (nr > -1 && nc > -1 && nr < SIZE && nc < SIZE);
	}
}

class Node {
	int row;
	int col;
	int width;
	int countY;
	
	public Node(int row, int col, int width, int countY) {
		this.row = row;
		this.col = col;
		this.width = width;
		this.countY = countY;
	}
}
