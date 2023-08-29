package swea.Ad.swea1767;

import java.io.*;
import java.util.*;

public class Solution { //라이브 답안

	private static int N, totalCount;
	private static int maxCore, minLength;
	
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};
	
	private static int[][] board;
	private static List<Core> cores;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			
			totalCount = 0;
			maxCore = 0;
			minLength = Integer.MAX_VALUE;
			
			board = new int[N][N];
			cores = new ArrayList<>();
			
			StringTokenizer st;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					
					if(isBorder(i, j) && board[i][j] == 1) { continue; }
					
					if(board[i][j] == 1) {
						cores.add(new Core(i, j));
						totalCount++;
					}
				}
			}
			
			permutation(0, 0);
			
			sb.append("#").append(test_case).append(" ").append(minLength).append("\n");
		}
		
		System.out.println(sb);
	}

	private static boolean isBorder(int r, int c) {
		return r == 0 || r == N - 1 || c == 0 || c == N - 1;
	}

	/**
	 * @param depth : 재귀의 깊이
	 * @param index : 고려해야 할 코어의 번호
	 * @param coreCount : 지금까지 연결된 코어의 수
	 */
	private static void permutation(int depth, int coreCount) {
		//가지치기: 현재까지 연결된 코어 수 + 남은 코어 수 < 임시 최대 코어 수라면
		//예: 이미 7개짜리 답을 구해놨는데 6개짜리 답밖에 못 구하는 상황이면 가지치기
		if(coreCount + (totalCount - depth) < maxCore) {
			return; 
		}
		
		if(depth == totalCount) {
			int res = getLength();
			
			if(maxCore < coreCount) {
				maxCore = coreCount;
				minLength = res;
			} else if(maxCore == coreCount) {
				minLength = Math.min(res, minLength);
			}
			return;
		}
		
		Core core = cores.get(depth);
		int r = core.row;
		int c = core.col;
		
		//1.현재 코어를 선택
		for(int dir = 0; dir < 4; dir++) {
			if(!isAvailable(r, c, dir)) { continue; }
			
			setStatus(r, c, dir, 2); //전선 놓기
			
			permutation(depth + 1, coreCount + 1);
			
			setStatus(r, c, dir, 0); //전선 지우기
		}
		
		//2.현재 코어를 선택하지 않음
		permutation(depth + 1, coreCount);
	}

	private static int getLength() {
		int len = 0; //전선을 센다.
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(board[i][j] == 2) {
					len++;
				}
			}
		}
		
		return len;
	}

	private static void setStatus(int r, int c, int dir, int status) {
		int nr = r;
		int nc = c;
		
		while(true) {
			nr += dr[dir];
			nc += dc[dir];
			
			if(nr < 0 || nc >= N || nc < 0 || nc >= 0) {
				break;
			}
			
			board[nr][nc] = status;
		}
	}

	private static boolean isAvailable(int r, int c, int dir) {
		int nr = r;
		int nc = c;
		
		while(true) {
			nr += dr[dir];
			nc += dc[dir];
			
			if(nr < 0 || nc >= N || nc < 0 || nc >= 0) { break; }
			
			if(board[nr][nc] != 0) { return false; } //다른 코어를 만나거나 전선이 있다면
		}
		
		return true;
	}
}

class Core {
	int row;
	int col;
	
	public Core(int row, int col) {
		this.row = row;
		this.col = col;
	}
}
