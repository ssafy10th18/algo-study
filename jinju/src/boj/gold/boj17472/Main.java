package boj.gold.boj17472;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static final int SEA = 0;
	private static final int LAND = 1;
	private static final int BRIDGE = -1;

	private static int N;
	private static int M;

	private static int borderIdx = 1;
	private static int minLength = Integer.MAX_VALUE;

	private static int[] dr = { 1, 0, -1, 0 };
	private static int[] dc = { 0, 1, 0, -1 };

	private static boolean[] selected;
	private static int[][] matrix;
	private static int[][] field;
	private static boolean[][] visited;

	private static boolean[][] isBorder;
	private static List<Point>[] borders; // i번째 섬의 경계면을 모두 담는다.

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");

		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);

		matrix = new int[N][M];
		field = new int[N][M];
		visited = new boolean[N][M];
		isBorder = new boolean[N][M];

		StringTokenizer st;
		List<Point> islands = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());

				if (matrix[i][j] == LAND) {
					islands.add(new Point(j, i));
				}
			}
		}

		// 1. 섬의 번호를 매긴다 (BFS)
		for (Point island : islands) {
			if (!visited[island.y][island.x]) {
				setBorder(island.y, island.x);
				borderIdx++;
			}
		}
		
		borderIdx -= 1;
		selected = new boolean[borderIdx];
		
		// 2. 경계면의 좌표를 모은다.
		collectBorders();

		// 3. 경계 면에서 다리를 놓는다. (DFS)
		DFS(field, 0, 0);

		if(minLength == Integer.MAX_VALUE) minLength = -1;
		System.out.println(minLength);
	}

	private static void DFS(int[][] field, int depth, int length) {
		if (depth == borderIdx) {
			minLength = Math.min(length, minLength);
			return;
		}

		//이미 다리가 놓였다면
		if(selected[depth]) {
			DFS(field, depth + 1, length);
		}
		
		List<Point> curr = borders[depth]; // 다리를 놓아야 하는 섬
		
		for (Point p : curr) {
			for (int dir = 0; dir < dr.length; dir++) {
				int len = setBridge(field, p, dir, BRIDGE);
				if(len > 1) { //길이가 1 이상인 것만 선정
					DFS(field, depth + 1, length + len);
				}
				setBridge(field, p, dir, SEA); //원복
			}
		}
	}

	private static int setBridge(int[][] field, Point start, int dir, int target) {
		int length = 0;
		
		int nr = start.y;
		int nc = start.x;
		while(true) {
			nr += dr[dir];
			nc += dc[dir];
			
			//범위 밖이라면 탐색을 멈춘다.
			if(!isValid(nr, nc)) { return 0; }
			
			//다른 섬에 다다랐다면 (0보다 큼) 길이를 반환한다.
			if(matrix[nr][nc] > SEA) {
				if(matrix[nr][nc] == matrix[start.y][start.x]) { return 0; }
					
				if(selected[matrix[nr][nc] - 1]) {
					selected[matrix[nr][nc] - 1] = false;
				} else selected[matrix[nr][nc] - 1] = true;
				
				return length; 
			}
			
			//바다라면 계속 탐색한다. 이 경우에만 target으로 덮어씌운다.
			if(matrix[nr][nc] == SEA) {
				field[nr][nc] = target;
				length += 1;
			}
		}
	}

	private static void setBorder(int startR, int startC) {
		Queue<Point> queue = new ArrayDeque<>();

		matrix[startR][startC] = borderIdx;
		visited[startR][startC] = true;
		queue.add(new Point(startC, startR));

		while (!queue.isEmpty()) {
			Point p = queue.poll();

			int nr = 0;
			int nc = 0;
			for (int i = 0; i < dr.length; i++) {
				nr = p.y + dr[i];
				nc = p.x + dc[i];

				// 가지치기
				if (!isValid(nr, nc) || visited[nr][nc]) {
					continue;
				}

				if (matrix[nr][nc] == SEA) { // 바다와 인접해 있다면
					isBorder[p.y][p.x] = true;
					continue;
				}

				matrix[nr][nc] = borderIdx;
				visited[nr][nc] = true;
				queue.add(new Point(nc, nr));
			}
		}
		return;
	}

	private static void collectBorders() {
		borders = new ArrayList[borderIdx];
		
		for (int i = 0; i < borderIdx; i++) {
			borders[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (isBorder[i][j]) { //경계면의 좌표를 취합한다.
					int islandNum = matrix[i][j];
					borders[islandNum - 1].add(new Point(j, i));
				}
			}
		}
	}

	private static boolean isValid(int nr, int nc) {
		return (nr > -1 && nc > -1 && nr < N && nc < M);
	}

}
