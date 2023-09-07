package com.ssafy.algoStudy.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 민호는 N개의 박스 갖고 있다.
 * 1. 박스 x의 크기를 V[x], 박스 y의 크기를 Y[y]라 할 때 V[y]는 적어도 V[x]의 두 배는 되어야지 x를 y에 넣을 수 있다.
 * 2. 박스 x를 박스 y에 넣었다면 y는 다른 박스에 넣지 못한다. 한 박스 안에 들어있는 모든 박스는 최대 1개이다.
 * 최적의 경우 (눈에 보이는 박스의 개수가 최소가 되는 경우를 의미) 구하기
 * @author SSAFY
 *
 */
public class Main_12945_재미있는박스정리 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, pair, result;
	//idx : boxsize, data : 해당 size 상자의 개수
	static int[] boxes = new int[100001];
	
	public static void main(String[] args) throws IOException{
		/**
		 * [입력]
		 * 1. 박스의 개수 N (1 <= N <= 500,000)
		 * 2~N. 박스들의 크기 V (1 <= V <= 100,000)
		/**
		 * [출력]
		 * 최적의 경우
		 */
		result = N - pair*2 + pair;
		System.out.println(result);
	}

}
