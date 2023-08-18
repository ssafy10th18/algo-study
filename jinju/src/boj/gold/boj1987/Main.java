package boj.gold.boj1987;

import java.io.*;

public class Main {

	private static int R;
	private static int C;
	
	private static char[][] board;
	
	private static int[] dr = {0, 1, 0, -1};
	private static int[] dc = {1, 0, -1, 0};
	
	private static int maxDepth = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");

		R = Integer.parseInt(temp[0]);
		C = Integer.parseInt(temp[1]);
		
		board = new char[R][C];
		
		boolean[] alphabets = new boolean['Z' - 'A' + 1];
		
		for(int i = 0; i < R; i++) {
			String line = br.readLine();
			for(int j = 0; j < C; j++) {
				board[i][j] = line.charAt(j);
			}
		}
		
		alphabets[board[0][0] - 'A'] = true;
		DFS(0, 0, alphabets);
		
		System.out.println(maxDepth);
	}

	private static void DFS(int r, int c, boolean[] alphabets) {
		if(r == R && c == C) {
			return;
		}
		
		int sum = 0; //maxDepth 계산
		for(boolean b : alphabets) {
			if(b) sum += 1;
		}
		maxDepth = Math.max(maxDepth, sum);
		
		int nr = 0;
		int nc = 0;
		for(int i = 0; i < dr.length; i++) {
			nr = r + dr[i];
			nc = c + dc[i];
			
			if(!isValid(nr, nc)) {
				continue;
			}
			
			if(alphabets[board[nr][nc] - 'A']) {
				continue;
			}
			
			alphabets[board[nr][nc] - 'A'] = true;
			DFS(nr, nc, alphabets);
			alphabets[board[nr][nc] - 'A'] = false;
		}
		
	}

	private static boolean isValid(int nr, int nc) {
		return (nr > -1 && nc > -1 && nr < R && nc < C);
	}

}
