package boj.gold.boj17144;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	private static int R;
	private static int C;
	private static int T;

	private static int[][] matrix;

	// 시계방향 delta (아래쪽)
	private static int[] dr = { 1, 0, -1, 0 };
	private static int[] dc = { 0, 1, 0, -1 };

	// 반시계방향 delta (윗쪽)
	private static int[] rdr = { -1, 0, 1, 0 };
	private static int[] rdc = { 0, 1, 0, -1 };

	private static List<Point> airCleaners = new ArrayList<>();
	private static List<Point> dusts = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");

		R = Integer.parseInt(temp[0]);
		C = Integer.parseInt(temp[1]);
		T = Integer.parseInt(temp[2]);

		matrix = new int[R][C];

		StringTokenizer st;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());

				if (matrix[i][j] == -1)
					airCleaners.add(new Point(j, i));
				
				if(matrix[i][j] > 0) 
					dusts.add(new Point(j, i));
			}
		}

		int answer = solution(T, matrix);

		System.out.println(answer);
	}

	private static int solution(int T, int[][] matrix) {
		for (int t = 0; t < T; t++) {
			int[][] newMatrix = new int[R][C];

			// 1.미세먼지 확산
			for (Point d : dusts) {
				int i = d.y; int j = d.x;
				if (matrix[i][j] != 0 && matrix[i][j] != -1) {
					spread(i, j, matrix, newMatrix);
				}
			}

			sumDust(matrix, newMatrix);

			// 2.공기청정기로 미세먼지 날리기
			cleanUp(airCleaners.get(0), airCleaners.get(1), rdr, rdc, matrix);
			cleanUp(airCleaners.get(1), airCleaners.get(0), dr, dc, matrix);
			
			// 3.미세먼지가 있는 곳 다시 수집
			dusts.clear();
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if(matrix[i][j] > 0) dusts.add(new Point(j, i));
				}
			}
		}

		int count = 0; // 미세먼지량 세기
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (matrix[i][j] != 0 && matrix[i][j] != -1) {
					count += matrix[i][j];
				}
			}
		}

		return count;
	}

	private static void sumDust(int[][] matrix, int[][] newMatrix) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				matrix[i][j] += newMatrix[i][j];
			}
		}
	}

	private static void spread(int r, int c, int[][] matrix, int[][] newMatrix) {
		int count = 0;
		int dust = (int) Math.floor(matrix[r][c] / 5);

		int nr = 0;
		int nc = 0;
		for (int i = 0; i < dr.length; i++) {
			nr = r + dr[i];
			nc = c + dc[i];

			if (!isValid(nr, nc) || matrix[nr][nc] == -1) {
				continue;
			}

			newMatrix[nr][nc] += dust;
			count += 1;
		}

		matrix[r][c] -= dust * count;
	}

	// 공기청정기를 중심으로 청소
	private static void cleanUp(Point point, Point anotherPoint, int[] dr, int[] dc, int[][] matrix) {
		int currR = point.y;
		int currC = point.x;
		
		int dir = 0;
		int nextR = currR; 
		int nextC = currC;
		do {
			nextR = currR + dr[dir];
			nextC = currC + dc[dir];
			
			if (!isValid(nextR, nextC)
					|| nextR == anotherPoint.y) { // 벽에 부딪혔다면  or 반대편에 닿았다면 방향 전환
				dir = (dir + 1) % 4;
				continue;
			}
			
			if(matrix[currR][currC] != -1) {
				//다음 칸의 미세먼지가 현재 칸으로 날라온다.
				matrix[currR][currC] = matrix[nextR][nextC];
			}
			
			if(matrix[currR][currC] == -1 && (currR != point.y || currC != point.x))
				matrix[currR][currC] = 0;
			
			currR = nextR; currC = nextC;	
		} while (!(nextR == point.y && nextC == point.x)); // 원점으로 돌아올 때까지 반복
	}

	private static boolean isValid(int nr, int nc) {
		return (nr > -1 && nc > -1 && nr < R && nc < C);
	}
}
