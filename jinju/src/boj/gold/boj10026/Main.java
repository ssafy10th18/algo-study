package boj.gold.boj10026;

import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {

	private static int[] dr = {0, 1, 0, -1};
	private static int[] dc = {1, 0, -1, 0};
	
	private static int N;
	
	private static char[][] matrix;
	private static boolean[][] visited;
	
	private static int area1 = 0;
	private static int area2 = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		matrix = new char[N][N];
		visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			String lines = br.readLine();
			for(int j = 0; j < N; j++) {
				matrix[i][j] = lines.charAt(j);
			}
		}
		
		//색약이 아닌 사람이 보는 영역 세기
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					area1 += BFS(i, j, matrix[i][j]);
				}
			}
		}
		
		//visited 초기화
		visited = new boolean[N][N];
		
		//G를 R로 체크하고 세기
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					area2 += BFS(i, j, matrix[i][j]);
				}
			}
		}
		
		System.out.println(area1 + " " + area2);
	}

	private static int BFS(int startR, int startC, char color) {
		Queue<Point> queue = new ArrayDeque<>();
		
		visited[startR][startC] = true;
		queue.add(new Point(startC, startR));
		if(matrix[startR][startC] == 'R') {
			matrix[startR][startC] = 'G';
		}
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			int nr = 0;
			int nc = 0;
			for(int i = 0; i < dr.length; i++) {
				nr = p.y + dr[i];
				nc = p.x + dc[i];
				
				if(!isValid(nr, nc)) { continue; }
				
				if(visited[nr][nc]) { continue; }
				
				if(matrix[nr][nc] == color) {
					visited[nr][nc] = true;
					queue.add(new Point(nc, nr));
					if(matrix[nr][nc] == 'R') {
						matrix[nr][nc] = 'G';
					}
				}
			}
		}
		
		return 1;
	}

	private static boolean isValid(int nr, int nc) {
		return (nr > -1 && nc > -1 && nr < N && nc < N);
	}

}
