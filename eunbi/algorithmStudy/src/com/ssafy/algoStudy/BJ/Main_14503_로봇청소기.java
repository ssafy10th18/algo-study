package com.ssafy.algoStudy.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 로봇 청소기와 방의 상태가 주어졌을 때, 청소하는 영역의 개수 구하기
 * 방 크기 : N*M
 * 청소기는 바라보는 방향이 존재
 * 1. 현재 칸 청소
 * 2. 주변 4칸이 다 깨끗하면
 * 	- 방향 유지 + 한 칸 후진 / 벽이 있어 후진 불가면 작동 멈춤
 * 3. 주변 4칸 중 더러운 곳이 있으면
 * 	- 반시계 방향으로 90도 회전
 *  - 회전 후의 앞쪽 칸이 청소되지 않은 곳이면 전진
 */
public class Main_14503_로봇청소기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, r, c, d, result;
	static int[][] room;
	//북 동 남 서 (상 우 하 좌) -> 반시계 90도이려면 -1
	static int[] dir_x = {0, 1, 0, -1}; //column
	static int[] dir_y = {-1, 0, 1, 0}; //row
	
	public static void main(String[] args) throws IOException{
		/*
		 * [입력]
		 * 1. N, M (3<=N,M<=50)
		 * 2. 청소기 위치 r,c 청소기가 바라보는 방향 d
		 * (d : 0-북. 1-동, 2-남, 3-서)
		 * 3~N. 방 상태
		 * (0 : 청소되지 않은 빈 칸, 1 : 벽)
		 */
		init();
		clean(r, c);
		/*
		 * [출력]
		 * 청소한 칸의 개수
		 */
		System.out.println(result);
	}
	
	static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		result = 0;
		
		room = new int[N][M];
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for(int m = 0; m < M; m++) {
				room[n][m] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static void clean(int row, int column) {
		//현재 위치가 빈 칸이면 청소하기
		if(room[row][column] == 0) {
			room[row][column] = 2;
			result++;
//			System.out.println("청소");
//			for(int[] r : room) {
//				System.out.println(Arrays.toString(r));
//			}
		}
		
		//청소가 필요 없으면
		if(!checkDir(row, column)) {
			//방향 유지 + 한 칸 후진 / 벽이 있어 후진 불가면 작동 멈춤
			//후진할 곳
			int backRow = row + dir_y[(d + 2) % 4];
			int backColumn = column + dir_x[(d + 2) % 4];
			//후진한 곳이 벽이면 작동 멈춤
			if(room[backRow][backColumn] == 1) {
				return;
			}
			//후진한 곳에서 또 탐색하기
			clean(backRow, backColumn);
		}else {//청소 필요하면
			//반시계로 90도 회전하면서 빈칸 찾기
			for(int i = 0; i < 4; i++) {
				int rotateDir = d - 1;
				rotateDir = rotateDir == -1 ? 3 : rotateDir;
				int rotateRow = row + dir_y[rotateDir];
				int rotateColumn = column + dir_x[rotateDir];
				
				if(rotateRow < 0 || rotateRow >= N || rotateColumn < 0 || rotateColumn >= M)
					continue;
				//방향 바꿔주기
				d = rotateDir;
				//빈 칸이면
				if(room[rotateRow][rotateColumn] == 0) {
					//청소하러 이동한 후
					clean(rotateRow, rotateColumn);
					//리턴
					return;
				}
			}
		}
	}
	
	//주변이 다 깨끗하면 false 반환
	static boolean checkDir(int row, int column) {
		for(int i = 0; i < 4; i++) {
			int nextRow = row + dir_y[i];
			int nextColumn = column + dir_x[i];
			
			if(nextRow < 0 || nextRow >= N || nextColumn < 0 || nextColumn >= M)
				continue;
			//청소가 필요하면 true 리턴
			if(room[nextRow][nextColumn] == 0) {
				return true;
			}
		}
		return false;
	}
}
