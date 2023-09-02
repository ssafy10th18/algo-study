package swea.Ad.swea1767;

import java.io.*;
import java.util.*;

public class Solution {
	
	private static int N;
	private static int answer;
	private static int maxConnections = 0;
	
	private static int[] directions;
	private static int[][] board;
	private static boolean[][] visited;
	
	private static List<Core> cores;
	
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, 1, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			answer = Integer.MAX_VALUE;
			
			board = new int[N][N];
			visited = new boolean[N][N];
			cores = new ArrayList<>();
			
			StringTokenizer st;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					
					if(board[i][j] == 1 && !isBorder(i, j)) {
						cores.add(new Core(i, j));
					}
				}
			}
			
			directions = new int[cores.size()];
			
			permutation(0);
			
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}

	private static boolean isBorder(int r, int c) {
		return (r - 1 < 0 || c - 1 < 0 || r + 1 == N || c + 1 == N);
	}

	private static void permutation(int depth) {
		if(depth == directions.length) {
			//각 코어가 지금까지 만들어진 방향을 따라 전선을 연결할 수 있는지 확인	
			checkConnection();
			return;
		}
		
		Core core = cores.get(depth);
		for(int i = 0; i < 4; i++) {
			//나아갈 수 없는 방향이라면 아예 뽑지 않는다.
			int nr = core.row + dr[i];
			int nc = core.col + dc[i];
			
			if(!isValid(nr, nc)) {
				continue;
			}
			
			directions[depth] = i; 
			permutation(depth + 1);
		}
	}

	private static void checkConnection() {
		visited = new boolean[N][N];
		
		int connections = 0;
		int result = 0;
		for(int i = 0; i < directions.length; i++) {
			Core core = cores.get(i);

			int nr = 0;
			int nc = 0;
			
			int currLength = 0;
			boolean isPossible = true;
			for(int k = 1; k < N; k++) {
				nr = core.row + dr[directions[i]] * k;
				nc = core.col + dc[directions[i]] * k;
				
				if(!isValid(nr, nc)) {
					break; //탐색 종료
				}
				
				//전선이 교차되는 경우 + 다른 프로세서를 지나는 경우
				if(visited[nr][nc] || board[nr][nc] == 1) {
					isPossible = false; //이 전선은 합산하지 않는다.
					break; //탐색 종료
				}
				
				visited[nr][nc] = true;
				currLength += 1;
			}
			
			//벽에 다다를 수 있다면(전선 교차 X,다른 프로세서 지나지 않음) 합산
			if(isPossible) {
				connections++; //연결된 프로세서의 수 증가
				result += currLength;
			}
		}
		
		if(result > 0) {
			if(maxConnections < connections) {
				maxConnections = connections;
				answer = result;
			} else if(maxConnections == connections) {
				answer = Math.min(result, answer);
			}
		}
	}

	private static boolean isValid(int r, int c) {
		return (r > -1 && c > -1 && r < N && c < N);
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
