package boj.gold.boj14499;

import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {

	private static final int UPPER = 3;
	private static final int LOWER = 6;
	
	private static int N;
	private static int M;
		
	private static int[] dice;
	private static int[][] map;
	
	private static int[] dr = {0, 0, -1, 1}; //동, 서, 북, 남
	private static int[] dc = {1, -1, 0, 0};
	
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		dice = new int[LOWER + 1]; //인덱스 0은 사용하지 않음, 처음엔 모두 0
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//명령어 받기 (x, y 순서?)
		int mapR = y;
		int mapC = x;
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < K; i++) {
			int dir = Integer.parseInt(st.nextToken());
			
			Point moved = moveDice(dir, mapR, mapC);
			if(moved != null) { //주사위를 굴렸다면 지도 좌표 갱신
				mapR = moved.y;
				mapC = moved.x;
			}
		}
		
		System.out.print(sb);
	}

	private static Point moveDice(int dir, int mapR, int mapC) {
		int nr = mapR + dr[dir - 1];
		int nc = mapC + dc[dir - 1];
		
		if(!isValid(nr, nc)) {
			return null;
		}
		
		//굴릴 수 있다면 주사위의 방향을 바꿔준다.
		int temp = dice[UPPER];
		
		switch(dir) {
		case 1:
			dice[3] = dice[4];
			dice[4] = dice[6];
			dice[6] = dice[2];
			dice[2] = temp;
			break;
		case 2:
			dice[3] = dice[2];
			dice[2] = dice[6];
			dice[6] = dice[4];
			dice[4] = temp;
			break;
		case 3:
			dice[3] = dice[5];
			dice[5] = dice[6];
			dice[6] = dice[1];
			dice[1] = temp;
			break;
		case 4:
			dice[3] = dice[1];
			dice[1] = dice[6];
			dice[6] = dice[5];
			dice[5] = temp;
			break;
		}
		
		if(map[nr][nc] == 0) {
			map[nr][nc] = dice[LOWER];
		} else {
			//0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 
			//칸에 쓰여 있는 수는 0이 된다.
			dice[LOWER] = map[nr][nc];
			map[nr][nc] = 0;
		}
		
		//주의: 굴릴 수 없다면 출력하지 않는다.
		sb.append(dice[UPPER]).append("\n");

		return new Point(nc, nr);
	}

	private static boolean isValid(int r, int c) {
		return (r > -1 && c > -1 && r < N && c < M);
	}
}
