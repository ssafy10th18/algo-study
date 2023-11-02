package com.ssafy.algoStudy.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14945_불장난 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n;
	static int[][] lab;
	public static void main(String[] args) throws IOException{
		
	}

	static void init() throws IOException{
		n = Integer.parseInt(br.readLine());
		lab = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j <= i; j++) {
				lab[i][j] = 1; //랩실
			}
		}
	}
}
