package com.ssafy.algoStudy.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2812_크게만들기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder number = new StringBuilder();
	static int N, K;
	
	public static void main(String[] args) throws IOException{
		
	}
	
	static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		number.append(br.readLine());
	}

	static void run() {
		String str = number.substring(0, K);
		int max = Integer.MIN_VALUE;
		int index;
		for(int i = 0; i < str.length(); i++) {
			int num = str.charAt(i) - '0';
			if(max < num)
				index = i;
		}
	}
}
