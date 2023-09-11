package com.ssafy.algoStudy.excel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA1859 {
	static int N;
	static int[] price;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			
			N = Integer.parseInt(br.readLine());
			price = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			sb.append(getMoney()).append('\n');
		}
		System.out.println(sb);
	}
	
	public static long getMoney() {
		int max = 0;
		long sum = 0;
		
		for(int i = N - 1; i >= 0; i--) {
			if(max < price[i]) {
				max = price[i];
				continue;
			}else if(max > price[i]) {
				sum += (max - price[i]);
			}
		}
		
		return sum;
	}

}
