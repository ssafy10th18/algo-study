package boj.gold.boj14499;

import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static int M;
	
	private static int mapR;
	private static int mapC;
	
	private static int[][] map;
	private static int[][] dice;
	private static Map<Point, Point> oppositeDice;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		dice = new int[3][4]; //처음엔 모두 0
		oppositeDice = new HashMap<>();
		
		oppositeDice.put(new Point(1, 0), new Point(1, 2));
		oppositeDice.put(new Point(1, 2), new Point(1, 0));
		
		oppositeDice.put(new Point(0, 1), new Point(2, 1));
		oppositeDice.put(new Point(2, 1), new Point(1, 1));
		
		oppositeDice.put(new Point(1, 1), new Point(1, 3));
		oppositeDice.put(new Point(1, 3), new Point(1, 1));
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//명령어 받기 
		mapR = y;
		mapC = x;
		int diceR = 1;
		int diceC = 1;
		
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < K; i++) {
			int dir = Integer.parseInt(st.nextToken());
			
			Point moved = moveDice(dir, diceR, diceC);
			//row, col 갱신
			if(moved != null) { //주사위를 굴렸다면 좌표 갱신
				diceR = moved.y;
				diceC = moved.x;
			}
			sb.append(dice[diceR][diceC]).append("\n");
		}
		
		System.out.print(sb);
	}

	private static Point moveDice(int dir, int startR, int startC) {
		//주사위를 굴리는 delta
		int dr = 0;
		int dc = 0;
		
		switch(dir) {
			case 1: {
				dc = -1;
				break;
			}
			case 2: {
				dc = 1;
				break;
			}
			case 3: {
				dr = 1;
				break;
			}
			case 4: {
				dr = -1;
				break;
			}
		}
		
		//주사위의 새 좌표
		int ndr = (startR + dr) % N;
		int ndc = (startC + dc) % M;
		//주사위 범위 넘어가지 않도록 나머지 연산
		
		//바닥의 새 좌표 (주사위와 반대)
		int nmr = mapR + dr * -1;
		int nmc = mapC + dc * -1;
		if(!isValid(nmr, nmc)) {
			return null; //범위를 벗어나면 굴리지 않는다
		}
		
		//지도의 새 좌표
		mapR = nmr;
		mapC = nmc;
		
		//이동한 칸에 쓰여 있는 수가 0이면, 
		//주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다. 
		Point opp = oppositeDice.get(new Point(ndc, ndr));
		if(map[mapR][mapC] == 0) {
			map[mapR][mapC] = dice[opp.y][opp.x];
		} else {
			//0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 
			//칸에 쓰여 있는 수는 0이 된다.
			dice[opp.y][opp.x] = map[mapR][mapC];
			map[mapR][mapC] = 0;
		}

		return new Point(ndc, ndr); //주사위의 윗면을 반환한다.
	}

	private static boolean isValid(int r, int c) {
		return (r > -1 && c > -1 && r < N && c < M);
	}
}
