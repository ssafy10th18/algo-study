package com.ssafy.algoStudy.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_20926_얼음미로 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int W, H, tRow, tColumn, eRow, eColumn;
	static char[][] maze;
	static int[][] DP;
	//상 하 좌 우
	static int[] dir_x = {0, 0, -1, 1}; //column
	static int[] dir_y = {-1, 1, 0, 0}; //row
	
	static class Location{
		int row, column;

		public Location(int row, int column) {
			this.row = row;
			this.column = column;
		}
	}
	
	static class moveInfo{
		boolean possible;
		int slideTime, row, column;
		
		public moveInfo(boolean possible, int slideTime, int row, int column) {
			this.possible = possible;
			this.slideTime = slideTime;
			this.row = row;
			this.column = column;
		}
		
		public moveInfo(boolean possible) {
			this.possible = possible;
		}
	}

	public static void main(String[] args) throws IOException{
		/*
		 * [입력]
		 * 1. 얼음 미로의 가로 크기 W, 세로 크기 H (2<=W,H<=500)
		 * 2~H. 얼음미로정보
		 * (T : 테라, R : 바위, H : 구멍, E : 출구, 0<=미끌시간<=9)
		 */
		init();
		BFS();
		/*
		 * [출력]
		 * 탈출 가능하면 최단 탈출 시간 출력
		 * 탈출 불가능이면 -1 출력
		 */
		if(DP[eRow][eColumn] == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(DP[eRow][eColumn]);
		}
	}

	static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		DP = new int[H][W];
		
		for(int[] row : DP) {
			Arrays.fill(row, Integer.MAX_VALUE);
		}
		
		maze = new char[H][W];
		for(int h = 0; h < H; h++) {
			String str = br.readLine();
			for(int w = 0; w < W; w++) {
				maze[h][w] = str.charAt(w);
				//테리의 시작 위치 저장
				if(maze[h][w] == 'T') {
					tRow = h;
					tColumn = w;
				}
				if(maze[h][w] == 'E') {
					eRow = h;
					eColumn = w;
				}
			}
		}
	}
	
	static void BFS() {
		Queue<Location> queue = new ArrayDeque<>(); //우선순위 큐?
		queue.add(new Location(tRow, tColumn));
		
		while(!queue.isEmpty()) {
			Location location = queue.poll();
			int row = location.row;
			int column = location.column;
			
			for(int i = 0; i < 4; i++) {
				moveInfo moveinfo = checkRoad(row, column, i);
				//갈 수 있으면
				if(moveinfo.possible) {
					//해당 위치 최단으로 값 갱신
					int arrRow = moveinfo.row;
					int arrColumn = moveinfo.column;
					//해당 위치의 값을 갱신해주어야 하면
					if(DP[arrRow][arrColumn] > moveinfo.slideTime) {
						DP[arrRow][arrColumn] = moveinfo.slideTime;
						//queue에 넣어주기
						queue.add(new Location(arrRow, arrColumn));
					}
				}
				//갈 수 없으면
				else if(!moveinfo.possible) {
					//DP에 저장되는 값 없이 return
					continue;
				}
				
			}
		}
	}
	
	//갈 수 있으면 true 반환
	static moveInfo checkRoad(int row, int column, int dir) {
		int nextRow = row;
		int nextColumn = column;
		int slideTime = 0;
		
		while(true) {
			nextRow += dir_y[dir];
			nextColumn += dir_x[dir];
			
			//절벽으로 가면 return false
			if(nextRow < 0 || nextRow >= H || nextColumn < 0 || nextColumn >= W)
				return new moveInfo(false);
			
			//구멍으로 가면 return false
			if(maze[nextRow][nextColumn] == 'H')
				return new moveInfo(false);
			
			//바위 만나면 지금까지의 미끌시간과 true return
			if(maze[nextRow][nextColumn] == 'R')
				return new moveInfo(true, slideTime, nextRow - dir_y[dir], nextColumn - dir_x[dir]);
			
			//출구 만나면 지금까지의 미끌시간과 true return
			if(maze[nextRow][nextColumn] == 'E')
				return new moveInfo(true, slideTime, nextRow, nextColumn);
			
			//미끌시간 더해주기
			slideTime += (maze[nextRow][nextColumn] - '0');
		}
	}
}

//
