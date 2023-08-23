package boj.gold.boj2931;

import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {

	private static final char EMPTY = '.';
	private static final char START = 'M';
	private static final char END = 'Z';

	private static int R;
	private static int C;

	private static char[][] matrix;
	private static int[][] emptyArea;
	private static boolean[][] visited;

	private static Map<Character, int[][]> deltaMap = new HashMap<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 각 블럭마다의 델타 값을 초기화
		int[][] block1 = { { -1, 0 }, { 1, 0 } }; // dr, dc 순
		int[][] block2 = { { 0, -1 }, { 0, 1 } };
		int[][] block3 = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		int[][] block4 = { { 0, 1 }, { 1, 0 } };
		int[][] block5 = { { 0, 1 }, { -1, 0 } };
		int[][] block6 = { { 0, -1 }, { -1, 0 } };
		int[][] block7 = { { 0, -1 }, { 1, 0 } };

		// 블럭 문자로 참조할 수 있도록 맵에 설정
		deltaMap.put('|', block1);
		deltaMap.put('-', block2);
		deltaMap.put('+', block3);
		deltaMap.put('1', block4);
		deltaMap.put('2', block5);
		deltaMap.put('3', block6);
		deltaMap.put('4', block7);
		deltaMap.put('M', block3); // 시작점은 4방 탐색 필요
		deltaMap.put('.', block3); // 빈 칸은 4방 탐색 필요

		StringBuilder sb = new StringBuilder();
		String[] temp = br.readLine().split(" ");

		R = Integer.parseInt(temp[0]);
		C = Integer.parseInt(temp[1]);

		matrix = new char[R][C];
		emptyArea = new int[R][C];
		visited = new boolean[R][C];

		int startR = 0;
		int startC = 0;
		for (int i = 0; i < R; i++) {
			String lines = br.readLine();
			for (int j = 0; j < C; j++) {
				matrix[i][j] = lines.charAt(j);

				if (matrix[i][j] == START) {
					startR = i;
					startC = j;
				}
			}
		}

		BFS(startR, startC); // 블럭이 연결되지 않은 칸을 모두 표시한다.

		int tR = 0; // target row
		int tC = 0; // target col
		all: for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (emptyArea[i][j] != 0) {
					tR = i;
					tC = j;
					break all;
				}
			}
		}

		sb.append(tR + 1).append(" ").append(tC + 1).append(" ");

		if (emptyArea[tR][tC] == 4) {
			// 사방의 네 칸과 연결되지 않은 곳이 있을 경우
			sb.append("+");
		} else if (emptyArea[tR][tC] == 2) {
			// 사방의 두 칸과 연결되지 않은 곳이 있을 경우
			if (matrix[tR - 1][tC] != EMPTY && matrix[tR + 1][tC] != EMPTY) {
				sb.append("|");
			} else if (matrix[tR][tC - 1] != EMPTY && matrix[tR][tC + 1] != EMPTY) {
				sb.append("-");
			} else if (matrix[tR][tC + 1] != EMPTY && matrix[tR + 1][tC] != EMPTY) {
				sb.append("1");
			} else if (matrix[tR - 1][tC] != EMPTY && matrix[tR][tC + 1] != EMPTY) {
				sb.append("2");
			} else if (matrix[tR - 1][tC] != EMPTY && matrix[tR][tC - 1] != EMPTY) {
				sb.append("3");
			} else if (matrix[tR + 1][tC] != EMPTY && matrix[tR][tC - 1] != EMPTY) {
				sb.append("4");
			}
		}

		sb.append("\n");

		System.out.println(sb);
	}

	private static void BFS(int startR, int startC) {
		Queue<Point> queue = new ArrayDeque<>();

		visited[startR][startC] = true;
		queue.add(new Point(startC, startR));

		while (!queue.isEmpty()) {
			Point p = queue.poll();

			// 만약 종료지점까지 다다랐다면 BFS 종료
			if (matrix[p.y][p.x] == END) {
				return;
			}

			// 현재 블럭과 맞는 delta 배열을 가져온다.
			int[][] deltas = deltaMap.get(matrix[p.y][p.x]);

			int nr = 0;
			int nc = 0;
			for (int i = 0; i < deltas.length; i++) {
				nr = p.y + deltas[i][0];
				nc = p.x + deltas[i][1];

				// 배열 밖으로 넘어가지 않는지 확인한다.
				if (!isValid(nr, nc)) {
					continue;
				}

				// 해당 통로 너머에 다른 블럭이 없는지 확인한다.
				// 1.빈 공간에서 옆 칸으로 이어지는 통로가 있는지 확인할 필요는 없다.
				// 2.시작점에서 옆 칸으로 이어지는 통로는 반드시 한 군데 존재한다.
				// 3.조사하고 싶은 칸이 반드시 빈칸이어야 한다.
				if (matrix[p.y][p.x] != EMPTY && matrix[p.y][p.x] != START && matrix[nr][nc] == EMPTY) {
					emptyArea[nr][nc] += 1; // 연결되지 않은 수를 센다.
				}

				if (visited[nr][nc]) { // 이미 방문했다면 스킵
					continue;
				}

				visited[nr][nc] = true;
				queue.add(new Point(nc, nr)); // 다음 정점 탐색
			}

		}
	}

	private static boolean isValid(int nr, int nc) {
		return (nr > -1 && nc > -1 && nr < R && nc < C);
	}

}