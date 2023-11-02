package com.ssafy.algoStudy.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1976 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, routine[];
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		init();
		
		if(isPossible()) {
			System.out.println("YES");
		}
		else {
			System.out.println("NO");
		}
	}
	
	static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parent = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 1)
					union(r+1, c+1);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		routine = new int[M];
		for(int i = 0; i < M; i++) {
			routine[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	static boolean isPossible(){
		int num = find(parent[routine[0]]);
		
		for(int i = 1; i < routine.length; i++) {
			if(find(parent[routine[i]]) != find(num))
				return false;
		}
		return true;
	}
	
	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		
		//이미 같은 그래프
		if(x == y) return false;
		
		if(x <= y) parent[y] = x;
		else parent[x] = y;
		return true;
	}
	
	static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
}
