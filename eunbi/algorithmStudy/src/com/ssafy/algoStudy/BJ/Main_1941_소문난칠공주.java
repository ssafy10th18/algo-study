package com.ssafy.algoStudy.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1941_소문난칠공주 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int result;
	static char[][] graph;
	static boolean[][] visited;
	//상 하 좌 우
	static int[] dir_x = {};
	static int[] dir_y = {};

	public static void main(String[] args) throws IOException {
		init();
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(!visited[i][j]) {
					visited[i][j] = true;
					int Y = graph[i][j] == 'Y' ? 1 : 0;
					DFS(i, j, Y, 0);
					visited[i][j] = false;
				}
			}
		}
	}
	
	static void init() throws IOException{
		graph = new char[5][5];
		visited = new boolean[5][5];
		result = 0;
		
		for(int r = 0; r < 5; r++) {
			String row = br.readLine();
			for(int c = 0; c < 5; c++) {
				graph[r][c] = row.charAt(c);
			}
		}
	}
	
	static void DFS(int row, int column, int Y, int depth) {
		//임도연파가 4명 이상이면 실패
		if(Y > 3) {
			return;
		}
		//7명 모으면
		if(depth == 7) {
			result++;
		}
		
	}

}
