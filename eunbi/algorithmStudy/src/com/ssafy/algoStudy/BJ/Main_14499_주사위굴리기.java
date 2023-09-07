package com.ssafy.algoStudy.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N*M인 지도. 오른쪽이 동쪽 위쪽이 북쪽.
 * 	 2
 * 4 1 3
 * 	 5
 * 	 6  
 * 놓여져 있는 곳의 좌표 (x, y)
 * 가장 처음에 주사위에는 모든 면에 0이 적혀져 있음.
 * 지도의 각 칸에는 정수가 쓰여 있고 주사위를 굴렸을 때
 * 1. 이동한 칸에 있는 수가 0이면 - 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다.
 * 2. 이동한 칸에 있는 수가 0이 아니면 - 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며 칸의 수가 0이 된다.
 * 주사위를 놓은 곳의 좌표와 이동시키는 명령이 주어졌을 때, 주사위 이동 때마다 상단에 쓰여 있는 값을 구하자.
 * 주사위는 지도 바깥으로 이동할 수 없다.
 * @author SSAFY
 *
 */
public class Main_14499_주사위굴리기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, x, y, K;
	static int[][] map;
	//바닥, 위, 앞, 왼, 오, 뒤
	static int dice[] = new int[6];
	
	public static void main(String[] args) throws IOException{
		/**
		 * [입력]
		 * 1. 지도의 세로 크기 N, 가로 크기 M (1 <= N,M <= 20), 주사위를 놓은 곳의 좌표 x, y(0 <= x <= N-1, 0 <= y <= M-1), 명령의 개수 K(1 <= K <= 1,000)
		 * *주사위를 놓은 칸에 쓰여 있는 수는 항상 0이다. 
		 * 2~N. 그래프식으로 입력
		 * (동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4)
		 */
		init();
		/**
		 * [출력]
		 * 이동 때마다 주사위의 뮛 면에 쓰여 있는 수를 출력.
		 * *바깥으로 이동하는 경우 해당 명령 무시, 출력하면 안됨.
		 */
	}
	
	static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for(int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			moveDice(Integer.parseInt(st.nextToken()));
		}
	}
	
	static void moveDice(int dir) {
		switch(dir) {
		//동
		case(1):
			//굴린 후의 위치가 범위를 벗어나면 return
			//아니면 주사위 위치의 숫자들 바꿔줌
			//이동 위치에 숫자가 0이면 주사위 바닥의 숫자를 지도에 복사 
			//0이 아니면 지도 숫자가 주사위 바닥에 복사, 지도 숫자는 0으로 바뀜
			break;
		//서
		case(2):
			break;
		//북
		case(3):
			break;
		//남
		case(4):
			break;
		}
	}
}
