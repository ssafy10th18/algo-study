package boj.gold.boj3055;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 매 분마다 고슴도치는 현재 있는 칸과 인접한 네 칸 중 하나로 이동할 수 있다. (위, 아래, 오른쪽, 왼쪽) 물도 매 분마다 비어있는
 * 칸으로 확장한다. 물이 있는 칸과 인접해있는 비어있는 칸(적어도 한 변을 공유)은 물이 차게 된다. 물과 고슴도치는 돌을 통과할 수 없다.
 * 또, 고슴도치는 물로 차있는 구역으로 이동할 수 없고, 물도 비버의 소굴로 이동할 수 없다.
 * 
 * 티떱숲의 지도가 주어졌을 때, 고슴도치가 안전하게 비버의 굴로 이동하기 위해 필요한 최소 시간을 구하는 프로그램을 작성하시오.
 * 
 * 고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다. 즉, 다음 시간에 물이 찰 예정인 칸으로 고슴도치는 이동할 수 없다.
 */
public class Main {
	private static final char DEST = 'D';
	private static final char ROCK = 'X';
	private static final char WATER = '*';

	private static int R;
	private static int C;

	private static Point start;
	private static List<Point> fountainhead;

	private static char[][] matrix;
	private static boolean[][][] visited;

	private static int[] dr = { 0, 1, 0, -1 };
	private static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");

		R = Integer.parseInt(temp[0]);
		C = Integer.parseInt(temp[1]);

		fountainhead = new ArrayList<>();
		matrix = new char[R][C];
		visited = new boolean[50 * 50][R][C]; //첫 번째 인덱스 : 시간

		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				matrix[i][j] = line.charAt(j);

				if (matrix[i][j] == 'S') {
					start = new Point(j, i);
				}

				if (matrix[i][j] == WATER) {
					fountainhead.add(new Point(j, i));
				}
			}
		}

		int result = BFS();

		if(result == -1) {
			System.out.println("KAKTUS");
		} else System.out.println(result);
	}

	private static int BFS() {
		Queue<Point> queue = new ArrayDeque<>();
		Queue<Point> waters = new ArrayDeque<>();

		queue.add(start);
		visited[0][start.y][start.x] = true; // 고슴도치의 방문 위치

		for(Point f : fountainhead) {
			waters.add(f);
			visited[0][f.y][f.x] = true; // 수원의 위치
		}
		
		int time = 0;
		while(!queue.isEmpty()) { //수원이 없어도 진행 가능
			time++; //time : 미래 시점 / time - 1 : 현재 시점
			
			// 1.물을 범람시킨다
			int size = waters.size();
			while (size-- > 0) {
				Point w = waters.poll();

				int nr = 0;
				int nc = 0;
				for (int i = 0; i < dr.length; i++) {
					nr = w.y + dr[i];
					nc = w.x + dc[i];

					if (!isValid(nr, nc)) {
						continue;
					}
					
					if(visited[time][nr][nc] || matrix[nr][nc] == DEST 
							|| matrix[nr][nc] == ROCK) {
						continue; //이미 범람 or 도착지 or 바위이면 범람되지 않는다.
					}

					visited[time][nr][nc] = true;
					waters.add(new Point(nc, nr));
				}
			}

			// 2.고슴도치를 이동시킨다.
			size = queue.size();
			while (size-- > 0) {
				Point p = queue.poll();

				if (!(p.y == start.y && p.x == start.x) &&
						visited[time - 1][p.y][p.x]) { // 이미 범람된 지점을 뽑았다면 가지치기
					continue;
				}
				
				if(matrix[p.y][p.x] == DEST) {
					return time - 1; //목적지에 다다랐다면 종료
				}

				int nr = 0;
				int nc = 0;
				for (int i = 0; i < dr.length; i++) {
					nr = p.y + dr[i];
					nc = p.x + dc[i];

					if (!isValid(nr, nc)) {
						continue;
					}
					
					if(visited[time - 1][nr][nc] || visited[time][nr][nc] 
							|| matrix[nr][nc] == ROCK) {
						continue; //이미 방문 or 범람될 곳 or 바위이면 가지 않는다.
					}

					visited[time - 1][nr][nc] = true;
					queue.add(new Point(nc, nr));
				}
			}
		}
		
		return -1;
	}

	private static boolean isValid(int nr, int nc) {
		return (nr > -1 && nc > -1 && nr < R && nc < C);
	}

}
