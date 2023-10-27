package com.ssafy.algoStudy.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12100_2048 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, board[][];

	public static void main(String[] args) throws IOException{
		

	}
	
	static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
