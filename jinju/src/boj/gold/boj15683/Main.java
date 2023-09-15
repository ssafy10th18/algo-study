package boj.gold.boj15683;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	private static final int BLANK = 0;
	private static final int WALL = 6;
	
	private static int N;
	private static int M;
	

	private static int count;
	private static int min = Integer.MAX_VALUE;
	
	private static int[][] matrix;
	private static int[][] scopes;

	private static List<Point> cameras = new ArrayList<>();
	
	private static int[] dr = {1, 0, -1, 0};
	private static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");
		
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);

		matrix = new int[N][M];
		scopes = new int[N][M];
		
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
				
				//5번 카메라는 제외하고 add
				if(matrix[i][j] != BLANK && matrix[i][j] != WALL 
						&& matrix[i][j] != 5) {
					cameras.add(new Point(j, i));
				}
				
				if(matrix[i][j] != BLANK) {
					scopes[i][j] = -1; //사각지대에서 제외시킬 곳 기록
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(matrix[i][j] == 5) { //회전시킬 필요 없는 카메라는 미리 세팅한다.
					for(int dir = 0; dir < dr.length; dir++) {
						setScope(scopes, new Point(j, i), dir, 1);
					}
				}
			}
		}
		
		count = cameras.size(); //5번 카메라를 제외한 카메라의 수

		DFS(scopes, 0);
		
		System.out.println(min);
	}
	
	private static void DFS(int[][] scopes, int depth) {
		if(depth == count) {
			int result = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(scopes[i][j] == BLANK) result += 1;
				}
			}

			min = Math.min(result, min);
			
			return;
		}
		
		Point curr = cameras.get(depth);
		int r = curr.y;
		int c = curr.x;
		
		switch(matrix[r][c]) { //카메라 방향 별 시야 기록
			case 1: {
				for(int dir = 0; dir < dr.length; dir++) {
					setScope(scopes, curr, dir, 1);
					DFS(scopes, depth + 1);
					setScope(scopes, curr, dir, -1); //원복
				}
				break;
			}
			case 2: {
				for(int dir = 0; dir < dr.length / 2; dir++) {
					setScope(scopes, curr, dir, 1);
					setScope(scopes, curr, dir + 2, 1); //dir과 대칭 방향
					DFS(scopes, depth + 1);
					setScope(scopes, curr, dir, -1); //원복
					setScope(scopes, curr, dir + 2, -1);
				}
				break;
			}
			case 3: {
				for(int i = 0; i < dr.length; i++) {
					for(int j = i + 1; j < dr.length; j++) { //서로 다른 두 방향 i, j를 탐색 (대칭X)
						if(i == j || Math.abs(i - j) == 2) { continue; }
						setScope(scopes, curr, i, 1);
						setScope(scopes, curr, j, 1); //dir과 대칭 방향
						DFS(scopes, depth + 1);
						setScope(scopes, curr, i, -1); //원복
						setScope(scopes, curr, j, -1);
					}
				}
				break;
			}
			case 4: {
				for(int k = 0; k < dr.length; k++) {
					for(int dir = 0; dir < dr.length; dir++) {
						if(k == dir) { continue; } //k번째 방향을 제외한 세 방향 탐색
						setScope(scopes, curr, dir, 1);
					}
					
					DFS(scopes, depth + 1);
					
					for(int dir = 0; dir < dr.length; dir++) {
						if(k == dir) { continue; } //k번째 방향을 제외한 세 방향 탐색
						setScope(scopes, curr, dir, -1); //원복
					}
				}
				break;
			}
		}
		
	}
	
	private static void setScope(int[][] scope, Point start, int dir, int target) {
		int nr = start.y;
		int nc = start.x;
		while(true) {
			nr += dr[dir];
			nc += dc[dir];
			
			//벽이라면 탐색을 멈춘다.
			if(!isValid(nr, nc) || matrix[nr][nc] == WALL) { return; }
			
			//다른 숫자라면 계속 탐색한다. 0인 경우에만 target으로 덮어씌운다.
			if(matrix[nr][nc] == BLANK) { scope[nr][nc] += target; }
		}
	}

	private static boolean isValid(int nr, int nc) {
		return (nr > -1 && nc > -1 && nr < N && nc < M);
	}

}
