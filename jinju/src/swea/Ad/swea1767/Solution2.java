package swea.Ad.swea1767;

import java.io.*;
import java.util.*;

public class Solution2 {

	static class Core {
		int row;
		int col;

		public Core(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	private static final int BLOCKED = -1;
	
	private static int N;
	private static int answer;
	private static int coreCount;
	private static int maxConnections;

	private static int[][] board;

	private static List<Core> cores;

	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			answer = Integer.MAX_VALUE;
			maxConnections = 0;

			board = new int[N][N];
			cores = new ArrayList<>();

			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());

					if (board[i][j] == 1 && !isBorder(i, j)) {
						cores.add(new Core(i, j));
					}
				}
			}

			coreCount = cores.size();

			permutation(board, 0, 0, 0);

			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}

		System.out.println(sb);
	}

	private static boolean isBorder(int r, int c) {
		return (r - 1 < 0 || c - 1 < 0 || r + 1 == N || c + 1 == N);
	}

	private static void permutation(int[][] board, int depth, int sum, int con) {
		if (depth == coreCount) {
			if (maxConnections < con) {
				maxConnections = con;
				answer = sum;
			}

			if (maxConnections == con && sum < answer) {
				answer = Math.min(sum, answer);
			}
			return;
		}

		if(con + (coreCount - depth) < maxConnections) {
			 return; //가지치기
		}
		
		Core core = cores.get(depth);
		for (int i = 0; i < 4; i++) {
			int[][] copied = cloneBoard(board);

			//한 방향이 결정될 때마다 길이를 체크해준다.
			int currLen = checkConnection(copied, core, i);
			if (currLen != BLOCKED) { //연결할 수 있는 경우 연결한 코어의 수(con)를 늘린다
				permutation(copied, depth + 1, sum + currLen, con + 1);
			}

			//연결할 수 없는 경우 다음 코어를 확인한다.
			permutation(board, depth + 1, sum, con);
		}
	}

	private static int checkConnection(int[][] board, Core core, int dir) {
		int len = 0;
		
		int nr = 0;
		int nc = 0;
		for (int k = 1; k < N; k++) {
			nr = core.row + dr[dir] * k;
			nc = core.col + dc[dir] * k;

			if (!isValid(nr, nc)) {
				break; // 이 전선은 합산하지 않는다.
			}

			// 전선이 교차되는 경우 + 다른 프로세서를 지나는 경우
			if (board[nr][nc] > 0) {
				len = BLOCKED; //연결할 수 없음을 나타냄
				break; // 이 전선은 합산하지 않는다.
			}

			len += 1;
		}
		
		//지나온 전선에 대해 방문 처리를 해준다.
		if(len != BLOCKED) {
			nr = 0;
			nc = 0;
			
			for (int k = 1; k < N; k++) {
				nr = core.row + dr[dir] * k;
				nc = core.col + dc[dir] * k;

				if (!isValid(nr, nc)) { break; }

				board[nr][nc] = 2; //visited
			}
		}

		return len;
	}

	private static boolean isValid(int r, int c) {
		return (r > -1 && c > -1 && r < N && c < N);
	}

	private static int[][] cloneBoard(int[][] board) {
		int[][] copied = new int[N][N];
		
		for(int i = 0; i < N; i++) { 
			for(int j = 0; j < N; j++) {
				copied[i][j] = board[i][j];
			}
		}
		
		return copied;
	}
}
