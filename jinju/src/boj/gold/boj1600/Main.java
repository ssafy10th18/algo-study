package boj.gold.boj1600;

import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {

	private static int W;
	private static int H;
	
	private static int[] dr = {};
	private static int[] dc = {};
	
	private static int[][] board;
	private static boolean[][] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int K = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		board = new int[H][W];
		
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < W; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		//
	}

	private static void BFS(int startR, int startC) {
		Queue<Point> queue = new ArrayDeque<>();
		
		visited[startR][startC] = true;
		queue.add(new Point(startC, startR));
		
		while(!queue.isEmpty()) {
			
		}
	}
}
