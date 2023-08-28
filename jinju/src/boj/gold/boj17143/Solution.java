package boj.gold.boj17143;

import java.awt.Point;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Solution {

	private static final int UP = 1;
	private static final int DOWN = 2;
	private static final int RIGHT = 3;
	private static final int LEFT = 4;

	private static int R;
	private static int C;

	//TODO: Shark[][] 타입으로 바꿔보기 
	private static Map<Point, Shark> sharks = new HashMap<>();
	private static int[][] placed; // r, c에 상어가 몇 마리 있는지 기록
	private static List<Shark>[][] duplications;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");

		R = Integer.parseInt(temp[0]);
		C = Integer.parseInt(temp[1]);
		int M = Integer.parseInt(temp[2]);

		placed = new int[R][C]; // 0은 사용하지 않음
		duplications = new ArrayList[R][C];
		
		init();

		StringTokenizer st;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			sharks.put(new Point(c, r), new Shark(r, c, s, d, z));
			placed[r][c] += 1;

		}

		int total = 0;
		for (int t = 0; t < C; t++) {
			// 1. (t-1) 열의 가장 가까운 상어를 잡는다.
			for (int row = 0; row < R; row++) {
				Point p = new Point(t, row); // 현재 위치에 존재하는 상어 정보를 읽어온다.
				Shark s = sharks.get(p);

				if (s != null) { // 가장 가까운 상어를 찾았다면
					sharks.remove(p); // 맵에서 제거하고 크기를 더한다.
					total += s.size;
					placed[s.row][s.col] -= 1;
					System.out.println("t : " + (t + 1) + "초 / " + s.size);
					break;
				}
			}

			// 2. 상어를 이동시킨다.
			moveSharks();
			
			System.out.println();
			
			// 3. 이동시킨 상태에서 겹치는 칸에 존재(placed가 1 이상)한다면 한 상어가 다른 상어를 잡아먹는다.
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					if(placed[i][j] > 1) {
						Shark maxShark = preyOn(i, j);
						
						for(Shark s : duplications[i][j]) {
							if(!s.equals(maxShark)) {
								sharks.remove(s); //잡아먹힌 상어 제거
							}
						}
						
						placed[i][j] = 1; //잡아먹은 후로 초기화
					}
				}
			}
			
			init(); //중복 집계를 초기화
		}

		System.out.println(total);
	}

	private static void init() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				duplications[i][j] = new ArrayList<>();
			}
		}
	}

	private static void moveSharks() {
		// 모든 상어를 이동시킨다. (r, c)에서 d를 따라 z만큼 움직인다.
		Map<Point, Shark> moved = new HashMap<>();
		for (Entry<Point, Shark> shark : sharks.entrySet()) {
			Shark s = shark.getValue();
			int d = s.dir;
			int z = s.speed;

			placed[s.row][s.col] -= 1;

			int deltaR = 0;
			int deltaC = 0;
			if (d == UP) {
				deltaR = -1;
			} else if (d == DOWN) {
				deltaR = 1;
			} else if (d == LEFT) {
				deltaC = -1;
			} else if (d == RIGHT) {
				deltaC = 1;
			}

			int nr = 0;
			int nc = 0;
			while (z-- > 0) {
				nr = s.row + deltaR;
				nc = s.col + deltaC;
				
				//TODO : 변량 미리 구해서 변경
				if (!isValid(nr, nc)) { // 방향전환
					deltaR *= -1;
					deltaC *= -1;
					
					nr = s.row + deltaR; //재계산
					nc = s.col + deltaC;
				}
				
				s.row = nr;
				s.col = nc;
			}

			moved.put(new Point(s.col, s.row), s); //새 상어 정보 저장
			
			// 이동이 끝나면 placed 증가
			placed[s.row][s.col] += 1;
			if(placed[s.row][s.col] >= 1) {
				duplications[s.row][s.col].add(s);
			}
		}
		
		sharks = moved; //레퍼런스 변경
	}

	private static Shark preyOn(int row, int col) {
		int maxSize = 0;
		Shark maxShark = null; //가장 큰 상어
		for(Shark shark : duplications[row][col]) {
			if(shark.size > maxSize) {
				maxSize = shark.size;
				maxShark = shark;
			}
		}
		
		return maxShark;
	}

	private static boolean isValid(int nr, int nc) {
		return (nr > -1 && nc > -1 && nr < R && nc < C);
	}

}

class Shark {
	int row;
	int col;
	int speed;
	int dir;
	int size;

	public Shark(int row, int col, int speed, int dir, int size) {
		this.row = row;
		this.col = col;
		this.speed = speed;
		this.dir = dir;
		this.size = size;
	}
}
