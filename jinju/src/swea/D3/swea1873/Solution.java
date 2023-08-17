package swea.D3.swea1873;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	//Map에 담아서 key로 참조해도 좋음.
	private static final char UP = 'U';
	private static final char DOWN = 'D';
	private static final char LEFT = 'L';
	private static final char RIGHT = 'R';
	private static final char SHOOT = 'S';
	
	private static final char TANK_UP = '^';
	private static final char TANK_DOWN = 'v';
	private static final char TANK_LEFT = '<';
	private static final char TANK_RIGHT = '>';
	
	private static final char GROUND = '.';
	private static final char WALL = '*';
	private static final char STEEL = '#';
	
	private static int H;
	private static int W;
	private static char[][] field;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		String[] temp;
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
			temp = br.readLine().split(" ");
			
			H = Integer.parseInt(temp[0]);
			W = Integer.parseInt(temp[1]);
			
			field = new char[H][W];
			
			int currR = 0;
			int currC = 0;
			char lastDir = ' ';
			
			for(int i = 0; i < H; i++) {
				String line = br.readLine();
				for(int j = 0; j < W; j++) {
					field[i][j] = line.charAt(j);
					
					if("^v<>".contains(String.valueOf(field[i][j]))) {
						currR = i;
						currC = j;
						lastDir = field[i][j];
					}
				}
			}
			
			int len = Integer.parseInt(br.readLine());
			
			String commands = br.readLine();
			for(int i = 0; i < len; i++) {
				char c = commands.charAt(i);
				
				if(c == SHOOT) {
					shoot(lastDir, currR, currC);
					continue;
				}
				
				int nr = currR;
				int nc = currC;
				if(c == UP) { //다음 이동 방향과 탱크 머리를 결정한다.
					lastDir = TANK_UP;
					nr = currR - 1;
				} else if(c == DOWN) {
					lastDir = TANK_DOWN;
					nr = currR + 1;
				} else if(c == LEFT) {
					lastDir = TANK_LEFT;
					nc = currC - 1;
				} else if(c == RIGHT) {
					lastDir = TANK_RIGHT;
					nc = currC + 1;
				}
				
				//탱크의 머리를 돌린다.
				field[currR][currC] = lastDir;
				
				//다음 위치로 이동할 수 있다면 탱크를 이동시킨다.
				if(isValid(nr, nc)
						&& field[nr][nc] == GROUND) {
					field[currR][currC] = GROUND;
					field[nr][nc] = lastDir;
					currR = nr;
					currC = nc;
				}
			}
			
			sb.append("#").append(test_case).append(" ");
			for(char[] f : field) {
				for(char c : f) {
					sb.append(c);
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}

	private static void shoot(char lastDir, int currR, int currC) {
		int dr = 0;
		int dc = 0;
		
		if(lastDir == TANK_UP) {
			dr = -1;
		} else if (lastDir == TANK_DOWN) {
			dr = 1;
		} else if (lastDir == TANK_LEFT) {
			dc = -1;
		} else if (lastDir == TANK_RIGHT) {
			dc = 1;
		}
		
		int nr = currR;
		int nc = currC;
		while(nr < H && nc < W) {
			if(!isValid(nr + dr, nc + dc)) {
				break;
			}
			
			nr += dr;
			nc += dc;
			
			if(field[nr][nc] == STEEL) {
				break; //
			}
			
			if(field[nr][nc] == WALL) { //벽돌하나만 부술 수 있다.
				field[nr][nc] = GROUND;
				break;
			}
		}

		return;
	}

	private static boolean isValid(int r, int c) {
		return (r > -1 && c > -1 && r < H && c < W);
	}

}
