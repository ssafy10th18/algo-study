package com.ssafy.algoStudy.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16234_인구이동 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
	static StringTokenizer st;
	static int N, L, R, result;
	static boolean isUnion = true;
	static int[][] A; //나이
	static boolean[][] isVisited; //이동 가능 표시 배열
	static List<Location> team;
	//상, 하, 좌, 우
	static int[] dir_x = {-1, 1, 0, 0}; //column
	static int[] dir_y = {0, 0, -1, 1}; //row
	
	static class Location{
		int row, column;

		public Location(int row, int column) {
			super();
			this.row = row;
			this.column = column;
		}

		public int getRow() {
			return row;
		}

		public void setRow(int row) {
			this.row = row;
		}

		public int getColumn() {
			return column;
		}

		public void setColumn(int column) {
			this.column = column;
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		//초기화
		init();
		//이동 가능한 국가 찾기
		System.out.println(result);
	}
	
	static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		result = 0;
		
		A = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(isUnion) {
			isUnion = false;
			search();
			if(isUnion) result++;
		}
		
	}
	
	static void search() {
		isVisited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!isVisited[i][j]) {
					team = new ArrayList<>();
					isVisited[i][j] = true;
					BFS(i, j);
				}
			}
		}
	}
	
	static void BFS(int row, int column) {
		Location loc = new Location(row, column);
		Queue<Location> queue = new LinkedList<>();
		queue.add(loc);
		int totalPeople = A[row][column];
		int numOfCountry = 1;
		team.add(loc);
		
		while(!queue.isEmpty()) {
			Location location = queue.poll();
			//상, 하, 좌, 우 탐색
			for(int idx = 0; idx < 4; idx++) {
				int nextRow = location.getRow() + dir_y[idx];
				int nextColumn = location.getColumn() + dir_x[idx];
				//범위 벗어나거나 이미 방문했으면 continue
				if(nextRow < 0 || nextRow >= N || nextColumn < 0 || nextColumn >= N || isVisited[nextRow][nextColumn]) {
					continue;
				}
				//둘의 차이
				int minus = Math.abs(A[location.getRow()][location.getColumn()] - A[nextRow][nextColumn]);
				//국경선을 열 수 있으면
				if(L <= minus && minus <= R) {
					//연합이 존재함을 표시
					isUnion = true;
					//방문처리 해주고
					isVisited[nextRow][nextColumn] = true;
					//전체 인구에 더해주기
					totalPeople += A[nextRow][nextColumn];
					//연합국 개수 더해주기
					numOfCountry++;
					//team에 해당 국가 넣어주기
					team.add(new Location(nextRow, nextColumn));
					//queue에 넣어주기
					queue.add(new Location(nextRow, nextColumn));
				}
			}
		}
		change(totalPeople, numOfCountry);
	}
	
	//연합국 인구 조절
	static void change(int totalPeople, int numOfCountry) {
		int changeNum = totalPeople / numOfCountry;
		for(int i = 0; i < team.size(); i++) {
			Location location = team.get(i);
			A[location.getRow()][location.getColumn()] = changeNum;
		}
	}
}
