package boj.gold.boj15683;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	private static final char SCOPE = '#';
	
	private static int N;
	private static int M;
	
	private static int min = Integer.MAX_VALUE;
	private static int count;
	
	private static char[][] matrix;
	private static Map<Integer, int[][]> deltas; 
	private static List<Point> cameras = new ArrayList<>();
	
	private static int[] dr = {1, 0, -1, 0};
	private static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");
		
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);

		matrix = new char[N][M];
		
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				matrix[i][j] = st.nextToken().charAt(0);
				
				if(matrix[i][j] != 0 && matrix[i][j] != 5) {
					cameras.add(new Point(j, i));
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(matrix[i][j] == 5) { //회전시킬 필요 없는 카메라는 미리 세팅한다.
					for(int dir = 0; dir < dr.length; dir++) {
						setScope(matrix, new Point(j, i), dir, '#');
					}
				}
			}
		}
		
		count = cameras.size();

		DFS(matrix, 0);
		
		System.out.println(min);
	}
	
	private static void DFS(char[][] currMatrix, int depth) {
		if(depth == count) {
			int result = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(currMatrix[i][j] == '0') result += 1;
				}
			}
			min = Math.min(result, min);
		}
		
		Point curr = cameras.get(depth);
		int r = curr.y;
		int c = curr.x;
		
		switch(matrix[r][c]) { //카메라 방향 별 탐색
			case 1: {
				for(int dir = 0; dir < dr.length; dir++) {
					setScope(currMatrix, curr, dir, '#');
					DFS(currMatrix, depth + 1);
					setScope(currMatrix, curr, dir, '0'); //원복
				}
				break;
			}
			case 2: {
				for(int dir = 0; dir < dr.length / 2; dir++) {
					setScope(currMatrix, curr, dir, '#');
					setScope(currMatrix, curr, dir + 2, '#'); //dir과 대칭 방향
					DFS(currMatrix, depth + 1);
					setScope(currMatrix, curr, dir, '0'); //원복
					setScope(currMatrix, curr, dir + 2, '0');
				}
				break;
			}
			case 3: {
				//TODO: 
				break;
			}
			case 4: {
				for(int k = 0; k < dr.length; k++) {
					for(int dir = 0; dir < dr.length; dir++) {
						if(k == dir) { continue; } //k번째 방향을 제외한 세 방향 탐색
						setScope(currMatrix, curr, dir, '#');
					}
					
					DFS(currMatrix, depth + 1);
					
					for(int dir = 0; dir < dr.length; dir++) {
						if(k == dir) { continue; } //k번째 방향을 제외한 세 방향 탐색
						setScope(currMatrix, curr, dir, '0'); //원복
					}
				}
				break;
			}
		}
		
	}
	
	private static void setScope(char[][] matrix, Point start, int dir, char target) {
		int nr = start.y;
		int nc = start.x;
		while(true) {
			nr += dr[dir];
			nc += dc[dir];
			
			//벽이라면 탐색을 멈춘다.
			if(!isValid(nr, nc) || matrix[nr][nc] == 6) { return; }
			
			//다른 숫자라면 계속 탐색한다. 0인 경우에만 target으로 덮어씌운다.
			if(matrix[nr][nc] == 0) { matrix[nr][nc] = target; }
		}
	}

	private static boolean isValid(int nr, int nc) {
		return (nr > -1 && nc > -1 && nr < N && nc < M);
	}

}
