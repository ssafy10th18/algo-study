package com.ssafy.algoStudy.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * [2][N] 의 표가 있다. 첫째 줄은 1,2,...,N이 차례대로 들어 있다. 둘째 줄은 1 이상 N 이하의 정수가 들어 있다. 첫째
 * 줄에서 숫자를 적절히 뽑으면, 뽑힌 정수들이 이루는 집합과 뽑힌 정수들의 바로 밑의 둘째 줄에 들어있는 정수들이 이루는 집합이 일치한다.
 * 이러한 조건을 만족시키도록 정수들을 뽑되, 최대로 많이 뽑는 방법을 찾는 프로그램을 작성하시오.
 * 
 * @author SSAFY
 *
 */
public class Main_2668_숫자고르기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, resultCnt;
	static int[][] graph;
	static boolean[] selected;
	static boolean[] resultArr;

	public static void main(String[] args) throws IOException {
		/**
		 * [입력] 1. N (1<=N<=100) 2~N. 둘째 줄의 정수들
		 */
		init();
		/**
		 * [출력] 1. 뽑힌 정수들의 개수 2~. 뽑힌 수들을 작은 수부터 한 줄에 하나씩 출력
		 */
		System.out.println(sb);
	}

	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		resultCnt = 0;
		graph = new int[2][N + 1];
		selected = new boolean[N + 1];
		resultArr = new boolean[N + 1];

		for (int n = 1; n <= N; n++) {
			int num = Integer.parseInt(br.readLine());
			graph[0][n] = n;
			graph[1][n] = num;
		}

		for (int i = 1; i <= N; i++) {
			
			for (int j = 1; j <= N; j++) {
				findCycle(i);
			}
		}

	}

	static void findCycle(int startIdx) {
		Set<Integer> set = new HashSet<>();
		for (int i = startIdx; i <= N; i++) {
			if(!selected[i]) {
				
			}
		}
	}
}
