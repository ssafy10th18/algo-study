package swea.Ad.swea1767;

import java.awt.Point;
import java.io.*;
import java.util.*;

public class Solution {

	private static final int UP = 0;
	private static final int DOWN = 1;
	private static final int RIGHT = 2;
	private static final int LEFT = 3;
		
	private static Point[] deltas = {new Point(0, 1), new Point(0, -1), 
			new Point(1, 0), new Point(-1, 0)};
	
	private static int[] directions;
	private static int[][] board;
	private static List<Core> cores;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			
			directions = new int[N];
			board = new int[N][N];
			cores = new ArrayList<>();
			
			StringTokenizer st;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					
					if(board[i][j] == 1) {
						cores.add(new Core(i, j, 0, 0));
					}
				}
			}
			
			permutation(0);
			
			sb.append("#");
		}
		
		System.out.println(sb);
	}
	
	public static void DFS(int startR, int startC, int dir) {
		
	}

	private static void permutation(int depth) {
		if(depth == directions.length) {
			//지금까지 만들어진 방향에 따라 전선을 연결할 수 있는지 확인
		}
		
		for(int i = 0; i < 4; i++) {
			directions[depth] = i; //depth번 코어의 방향 결정 (중복 순열)
			permutation(depth + 1);
		}
	}
}

class Core {
	int row;
	int col;
	int dir;
	int length;
	
	public Core(int row, int col, int dir, int length) {
		this.row = row;
		this.col = col;
		this.dir = dir;
		this.length = length;
	}
}
