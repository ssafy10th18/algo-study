package com.ssafy.algoStudy.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N개의 강의가 있다. 시작하는 시간과 끝나는 시간을 알고 있다.
 * 최대한 적은 수의 강의실을 사용하여 모든 강의가 이루어지게 하자.
 * 한 강의실에서 동시에 2개 이상 진행 안됨.
 * @author SSAFY
 *
 */
public class Main_1374_강의실 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	
	static class Lesson{
		int startTime, endTime;
	}

	public static void main(String[] args) throws IOException{
		/**
		 * [입력]
		 * 1. 강의의 개수 N (1 <= N <= 100,000)
		 * 2~N. 강의 번호, 시작 시간, 강의 종료 시간 (1~N, 0 <= <= 10억)
		 */
		
		/**
		 * [출력]
		 * 최소 강의실 개수
		 */

	}
	
	static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
	}

}
