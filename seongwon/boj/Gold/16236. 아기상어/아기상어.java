import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	/* 물고기와 상어가 있는 공간 */
	static int[][] map;
	/* 상어 중복 이동 방지 check */
	static boolean[][] visited;
	static int[] fishArr;
	/* 상 하 이동 */
	static int[] dx = { -1, 0, 0, 1 };
	/* 좌 우 이동 */
	static int[] dy = { 0, -1, 1, 0 };
	/*
	 * N : 공간의 사이즈, sx , sy : 상어의 좌표, sSize : 상어의 사이즈, eat : 상어가 먹은 먹이의 갯수 fish : 현재
	 * 먹을 수 있는 먹이의 수, answer : 답 (진행된 시간)
	 */
	static int N, sx, sy, sSize, eat, fish, answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		fishArr = new int[7];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {// 상어 좌표 세팅
					sx = i;
					sy = j;
				} else if (map[i][j] > 0)
					fishArr[map[i][j]]++;// 먹을 수 있는 먹이의 갯수 카운트
			}
		}

		answer = 0;
		sSize = 2;
		eat = 0;
		fish = fishArr[1];
		while (fish > 0) { // 더이상 먹을 수 있는 먹이가 없을 때 까지 반복
			int x = sx;
			int y = sy;
			moveShark(); // 상어가 이동하며 먹이 활동
			if(sx == x && sy == y)break;
			fishCheck();
		}

		System.out.println(answer);
	}

	public static void moveShark() {
		PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
			if (o1[2] == o2[2]) {
				if (o1[0] == o2[0])
					return Integer.compare(o1[1], o2[1]);
				return Integer.compare(o1[0], o2[0]);
			}
			return Integer.compare(o1[2], o2[2]);
		});
		visited = new boolean[N][N];
		int x = sx;
		int y = sy;
		visited[x][y] = true;
		queue.add(new int[] { x, y, 0 });
		while (!queue.isEmpty()) {
			int[] loc = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = loc[0] + dx[i];
				int ny = loc[1] + dy[i];
				if (nx >= 0 && ny >= 0 && nx < N && ny < N && map[nx][ny] <= sSize && !visited[nx][ny]) {
					visited[nx][ny] = true;
					queue.add(new int[] { nx, ny, loc[2] + 1 });
				}
			}

			if (map[loc[0]][loc[1]] != 0 && map[loc[0]][loc[1]] < sSize && map[loc[0]][loc[1]] != 9) {
				map[sx][sy] = 0;
				sx = loc[0];
				sy = loc[1];
				fishArr[map[sx][sy]]--;
				answer += loc[2];
				eat++;
				if (eat == sSize) {
					eat = 0;
					sSize++;
				}
				map[sx][sy] = 9;
				break;
			}

		}

	}

	public static void fishCheck() {
		fish = 0;
		for (int i = 1; i < 7; i++) {
			if (i < sSize)
				fish += fishArr[i];
		}
	}
}
