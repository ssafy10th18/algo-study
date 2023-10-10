package swea.Ex.swea5656;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution2 {

	private static int N;
	private static int W;
	private static int H;
	private static int minCount;

	private static int matrix[][];

	private static int[] dr = { 0, 1, -1, 0 };
	private static int[] dc = { 1, 0, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			String[] temp = br.readLine().split(" ");

			N = Integer.parseInt(temp[0]);
			W = Integer.parseInt(temp[1]);
			H = Integer.parseInt(temp[2]);
			minCount = Integer.MAX_VALUE;

			matrix = new int[H][W];

			StringTokenizer st;
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			DFS(0, matrix);
			
			sb.append("#").append(test_case).append(" ").append(minCount).append("\n");
		}

		System.out.print(sb);
	}

	private static void DFS(int depth, int[][] matrix) {
		if (depth == N) { // 잔여 블록의 수를 센다.
			int count = 0;
			for (int j = 0; j < W; j++) {
				for (int i = H - 1; i >= 0; i--) {
					if (matrix[i][j] == 0) {
						break;
					}

					count++;
				}
			}

			minCount = Math.min(count, minCount);
			
			return;
		}
		
		if(isAllBroke(matrix)) { //depth에 다다르지 않아도 모두 부서졌다면 종료
			minCount = 0;
			return;
		}

		for (int col = 0; col < W; col++) { // 중복 순열
			Point block = findUpperBlock(matrix, col); // 블록 선정

			if (block.y > -1 && block.x > -1 && matrix[block.y][block.x] > 0) {
				int[][] result = new int[H][W];
				
				breakBlocks(block, matrix, result);
				resetBlocks(result);
				DFS(depth + 1, result); // 복제를 만들기 때문에 원복할 필요 X
			}
		}
	}

	private static boolean isAllBroke(int[][] matrix) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(matrix[i][j] > 0) return false;
			}
		}
		return true;
	}

	/*
	 * 선정된 블록과 그 영향을 받는 블록을 깨트린다.
	 */
	private static void breakBlocks(Point block, int[][] matrix, int[][] result) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(new Point(block.x, block.y));

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				result[i][j] = matrix[i][j];
			}
		}

		int power;
		while (!queue.isEmpty()) {
			Point p = queue.poll();

			power = result[p.y][p.x] - 1; // 위력 저장
			result[p.y][p.x] = 0; // 자기자신을 부순다.

			int nr = 0;
			int nc = 0;
			for (int dir = 0; dir < dr.length; dir++) {
				for (int k = 1; k <= power; k++) {
					nr = p.y + dr[dir] * k;
					nc = p.x + dc[dir] * k;

					if (!isValid(nr, nc)) {
						break;
					}

					if (result[nr][nc] > 1) { // 위력 1 초과의 다른 블록을 건드렸다면
						queue.add(new Point(nc, nr)); // 후보에 추가
					} else result[nr][nc] = 0; // 1짜리 블록이면 그냥 부순다.
				}
			}
		}

		return;
	}

	/*
	 * 붕 떠있는 블록을 재배열한다.
	 */
	private static void resetBlocks(int[][] matrix) {
		for (int j = 0; j < W; j++) {
			// 첫 번째로 비어있는 칸의 row를 찾는다.
			int blankRow = 0;
			for (int i = H - 1; i >= 0; i--) {
				if (matrix[i][j] == 0) {
					blankRow = i;
					break;
				}
			}

			// 위의 블럭을 모두 아래로 내린다.
			// 주의: 해당 열에 빈 칸이 하나도 없을 수도 있다.
			// 또, 해당 열에서 아무 블럭도 깨지지 않았을 수도 있다.
			for (int i = blankRow; i >= 0; i--) {
				if (matrix[i][j] != 0) { // 위에 떠있는 블럭을 발견했다면
					int temp = matrix[i][j];
					matrix[i][j] = 0;
					matrix[blankRow--][j] = temp; // 블럭을 아래로 옮긴다.
				}
			}
		}
	}

	private static boolean isValid(int nr, int nc) {
		return (nr > -1 && nc > -1 && nr < H && nc < W);
	}

	private static Point findUpperBlock(int[][] matrix, int col) {
		for (int i = 0; i < H - 1; i++) {
			if (matrix[i][col] != 0) {
				if(i == 0 || (i > 0 && matrix[i - 1][col] == 0)) {
					return new Point(col, i);
				}
			}
		}
		return new Point(-1, -1); // 블록을 찾을 수 없다면
	}
}
