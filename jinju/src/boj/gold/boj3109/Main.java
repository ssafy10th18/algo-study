package boj.gold.boj3109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static int R;
	private static int C;
	
	private static int answer = 0;
	private static boolean finished;
	
	private static final char BUILDING = 'x';
	private static final char ROAD = '.';
	
	private static int[] dy = {-1, 0, 1};
	private static int[] dx = {1, 1, 1};
	
	private static char[][] map;
	private static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		
		map = new char[R][C];
		visited = new boolean[R][C];
		
		for(int i = 0; i < R; i++) {
			String line = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		for(int i = 0; i < R; i++) {
			finished = false; //새 루트를 찾을 때 초기화
			DFS(i, 0);
		}

		System.out.println(answer);
	}
	
	public static void DFS(int row, int col) {
		if(col == C - 1) {
			answer++;
			finished = true; //한 루트를 발견했으므로 체크
			return;
		}
		
		for(int i = 0; i < dy.length; i++) {
			int nr = row + dy[i];
			int nc = col + dx[i];
			
			if(!isValid(nr, nc)) {
				continue;
			}
			
			if(map[nr][nc] == BUILDING) {
				continue;
			}
			
			if(visited[nr][nc]) {
				continue;
			}
			
			if(finished) continue; //이미 최적 경로를 찾았다면 중단
			
			visited[nr][nc] = true;
			DFS(nr, nc);
		}
		
		return;
	}

	private static boolean isValid(int nr, int nc) {
		return (nr > -1 && nc > -1 && nr < R && nc < C);
	}

}
