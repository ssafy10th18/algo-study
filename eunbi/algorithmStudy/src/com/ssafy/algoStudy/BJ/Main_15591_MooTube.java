package com.ssafy.algoStudy.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1번 ~ N번의 동영상이 이미 올려져 있음.(1 <= N <= 5,000)
 * 모든 동영상에 대해 연관 동영상 리스트를 만들기로 했다.
 * USADO = 두 동영상이 얼마나 가까운 지를 측정하는 단위
 * N-1개의 동영상 쌍을 골라서 직접 두 쌍의 USADO 계산
 * 각 동영상을 정점으로 나타내기로 했다.
 * 연결구조를 서로 연결되어 있는 N-1개의 동영상 쌍으로 나타내었다.(경로가 반드시 1개)
 * 임의의 두 쌍 사이의 동영상의 USADO를 그 경로의 모든 연결들의 USADO중 최솟값으로 하기로 했따.
 * 값 k를 정해서 USADO가 K 이상인 모든 동영상이 추천되도록 할 것이다.
 * k를 적절한 값으로 결정하려고 한다.
 * @author dnpfk
 *
 */
public class Main_15591_MooTube {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, Q;
	static int[] usado;
	static List[] list;

	static class Edge{
		int p, q;

		public Edge(int p, int q) {
			super();
			this.p = p;
			this.q = q;
		}

		public int getP() {
			return p;
		}

		public int getQ() {
			return q;
		}
	}
	
	public static void main(String[] args) throws IOException{
		/**
		 * [입력]
		 * 1. N, Q (1 <= Q <= 5,000)
		 * 2~N-1.두 동영상의 쌍의 USADO. p, q, r (1<=p,q<=N, 1<=r<=10억)
		 * 3~Q. k, v (1<=k<=10억, 1<=v<=N)
		 */
		
		/**
		 * [출력]
		 * 질문에 대한 답
		 */
	}
	
	static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		list = new ArrayList[N];
		for(int n = 0; n < N; n++) {
			list[n] = new ArrayList<Integer>();
		}
		
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
		}
	}

}
