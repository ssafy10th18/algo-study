package com.ssafy.algoStudy.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 크기가 N*N인 R(빨강), G(초록), B(파랑)으로 이루어진 그래프가 제시될 때
 * 적록색약 - R과 G를 같은 색으로 봄
 * 같은 색의 구역을 1개로 했을 때 그래프를 이루는 구역을 일반사람과 적록색약이 보는 개수로 표현해라
 * @author SSAFY
 *
 */

public class Main_10026_적록색약 {
	static int N, normal, colorWeakness;
	//상,하,좌,우
	static int[] dir_r = {-1, 1, 0, 0};
	static int[] dir_c = {0, 0, -1, 1};
	static char[][] draw;
	static boolean[][] isSelected;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		/**
		 * [입력]
		 * 1. N (1 <= N <= 100)
		 * 2~N. 그래프
		 */
		N = Integer.parseInt(br.readLine());
		draw = new char[N][N];
		normal = 0;
		isSelected = new boolean[N][N];
		for(int r = 0; r < N; r++) {
			String row = br.readLine();
			for(int c = 0 ; c < N; c++) {
				draw[r][c] = row.charAt(c);
			}
		}
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				//방문 전이면
				if(!isSelected[r][c]) {
					//새로운 구역
					++normal;
					//같은 구역 탐색
					search_n(r, c, draw[r][c]);
				}
			}
		}
		
		isSelected = new boolean[N][N];
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				//방문 전이면
				if(!isSelected[r][c]) {
					//새로운 구역
					++colorWeakness;
					//같은 구역 탐색
					search_c(r, c, draw[r][c]);
				}
			}
		}
		
		/**
		 * [출력]
		 * 일반사람의 구역 수와 적록색약의 구역 수 공백으로 구분해 출력
		 */
		sb.append(normal).append(" ").append(colorWeakness);
		System.out.println(sb);
	}
	
	public static void search_n(int nowRow, int nowColumn, char nowColor){
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				//탐색할 다음 위치
				int nextRow = nowRow + dir_r[i];
				int nextColumn = nowColumn + dir_c[i];
				//범위 넘어가면 continue
				if(nextRow < 0 || nextRow >= N || nextColumn < 0 || nextColumn >= N)
					continue;
				//방문하지 않은 곳이고 같은 색을 가지고 있으면 방문처리
				if(!isSelected[nextRow][nextColumn] && draw[nextRow][nextColumn] == nowColor) {
					isSelected[nextRow][nextColumn] = true;
					search_n(nextRow, nextColumn, draw[nextRow][nextColumn]);
				}
			}
		}
	}
	
	public static void search_c(int nowRow, int nowColumn, char nowColor){
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				//탐색할 다음 위치
				int nextRow = nowRow + dir_r[i];
				int nextColumn = nowColumn + dir_c[i];
				//범위 넘어가면 continue
				if(nextRow < 0 || nextRow >= N || nextColumn < 0 || nextColumn >= N)
					continue;
				//방문하지 않은 곳이고 
				if(!isSelected[nextRow][nextColumn]) {
					//현재 색이 R이나 G이면 
					if(nowColor == 'R' || nowColor == 'G') {
						if(draw[nextRow][nextColumn] == 'R' || draw[nextRow][nextColumn] == 'G') {
							isSelected[nextRow][nextColumn] = true;
							search_c(nextRow, nextColumn, draw[nextRow][nextColumn]);
						}
					}
					//현재 색이 B이면
					else {
						if(draw[nextRow][nextColumn] == 'B') {
							isSelected[nextRow][nextColumn] = true;
							search_c(nextRow, nextColumn, draw[nextRow][nextColumn]);
						}
					}
				}
			}
		}
	}
}
