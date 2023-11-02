package com.ssafy.algoStudy.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 두 개의 섬에 공장을 세워 두고 물품을 생산하는 일을 하고 있다.
 * 다리로 물품을 수송할 수 있다.
 * 다리마다 중량 제한이 있다.
 * 한 번의 이동에서 옮길 수 있는 물품들의 중량의 최댓값을 구하라.
 *
 */
public class Main_1939_중량제한 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] DP;//a번 섬에서 b섬으로 갈 때 가장 큰 중량
	static int[][] map;
	static int N, M, factory1, factory2;

	public static void main(String[] args) throws IOException{
		/*
		 * [입력]
		 * 1. 섬 개수 N, 다리 개수 M (2<=N<=10,000)(1<=M<=100,000)
		 * 2~M. 다리 정보 A, B, C [A번 섬과 B번 섬 사이의 중량 제한 C]
		 * (1<=A,B<=N)(1<=C<=1,000,000,000)
		 * 마지막 줄에는 공장이 위치해 있는 섬의 번호를 나타내는 서로 다른 두 정수가 주어진다.
		 */
		
		/*
		 * [출력]
		 * 답 출력
		 */
	}
	
	static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N]; //섬 사이를 잇는 다리들 중 중량제한이 가장 큰 걸 저장
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int island_A = Integer.parseInt(st.nextToken());
			int island_B = Integer.parseInt(st.nextToken());
			int maxWeight = Integer.parseInt(st.nextToken());
			//최대 중량 저장
			map[island_A][island_B] = map[island_A][island_B] < maxWeight ? maxWeight : map[island_A][island_B];
		}
		
		st = new StringTokenizer(br.readLine());
		factory1 = Integer.parseInt(st.nextToken());
		factory2 = Integer.parseInt(st.nextToken());
	}
	
	//DFS
	static void DFS(int row, int column, int a) {
		
	}
}
//1번 섬과 2번 섬 사이에 중량이 3,4,5인 다리가 3개 있으면