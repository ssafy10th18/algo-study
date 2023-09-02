package boj.gold.boj1600;

import java.io.*;
import java.util.*;

public class Main {

	private static int K;
	private static int W;
	private static int H;

	private static int[] hdr = { 2, 2, -2, -2, 1, -1, 1, -1 };
	private static int[] hdc = { 1, -1, 1, -1, 2, 2, -2, -2 };

	private static int[] mdr = { 0, 1, 0, -1 };
	private static int[] mdc = { 1, 0, -1, 0 };

	private static int[][] board;
	private static int[][][] DP;
	private static boolean[][][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		K = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		board = new int[H][W];
		DP = new int[H][W][K + 1]; //(r, c)에 다다를 때까지, K번만큼 말 처럼 움직인 결과를 기록
		visited = new boolean[H][W][K + 1];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < W; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				
				for(int k = 0; k <= K; k++) {
					DP[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}

		BFS(0, 0);

		int result = Integer.MAX_VALUE;
		for(int k = 0; k <= K; k++) { //k를 쓴 횟수마다 최소값을 따야 함
			result = Math.min(result, DP[H - 1][W - 1][k]);
		}
		
		if (W - 1 == 0 && H - 1 == 0) {
			result = 0;
		}
		else if (result == Integer.MAX_VALUE) {
			result = -1;
		}
		
		System.out.println(result);
	}

	private static void BFS(int startR, int startC) {
		Queue<Node> queue = new ArrayDeque<>();

		visited[startC][startR][0] = true;
		queue.add(new Node(startC, startR, 0));
		
		while (!queue.isEmpty()) {
			Node n = queue.poll();

			int nr = 0;
			int nc = 0;
			if (n.k < K) {
				// 1.말처럼 움직여보기 ==> K번만 움직일 수 있다.
				for (int i = 0; i < hdr.length; i++) {
					nr = n.row + hdr[i];
					nc = n.col + hdc[i];

					if (nr == 0 && nc == 0) {
						continue;
					}

					if (!isValid(nr, nc, n.k + 1)) { //k + 1번째 이동을 이미 처리했다면 continue
						continue;
					}

					// DP 갱신을 위해 방문체크 하지 않음
					if (DP[nr][nc][n.k + 1] >= DP[n.row][n.col][n.k] + 1) { // 이동횟수가 작거나 같다면
						DP[nr][nc][n.k + 1] = (DP[n.row][n.col][n.k] == Integer.MAX_VALUE) 
								? 1 : DP[n.row][n.col][n.k] + 1;
												
						if (nr == H - 1 && nc == W - 1) { return; }
						
						visited[nr][nc][n.k + 1] = true;
						queue.add(new Node(nr, nc, n.k + 1));
					}
				}
			}

			// 2.상하좌우만 이동 가능
			for (int i = 0; i < mdr.length; i++) {
				nr = n.row + mdr[i];
				nc = n.col + mdc[i];

				if (nr == 0 && nc == 0) {
					continue;
				}

				if (!isValid(nr, nc, n.k)) {
					continue;
				}

				// DP 갱신을 위해 방문체크 하지 않음
				if (DP[nr][nc][n.k] >= DP[n.row][n.col][n.k] + 1) { // 이동횟수가 작거나 같다면
					DP[nr][nc][n.k] = (DP[n.row][n.col][n.k] == Integer.MAX_VALUE) 
							? 1 : DP[n.row][n.col][n.k] + 1;
					
					if (nr == H - 1 && nc == W - 1) { return; }
					
					visited[nr][nc][n.k] = true;
					queue.add(new Node(nr, nc, n.k)); // count 변수 유지
				}
			}

		}

		return;
	}

	private static boolean isValid(int nr, int nc, int k) {
		return (nr > -1 && nc > -1 && nr < H && nc < W && !visited[nr][nc][k] && board[nr][nc] != 1);
	}
}

class Node {
	int row;
	int col;
	int k; // 말처럼 움직인 횟수

	public Node(int row, int col, int k) {
		this.row = row;
		this.col = col;
		this.k = k;
	}
}