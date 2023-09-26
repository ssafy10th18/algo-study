package boj.gold.boj1194;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	private static final char START = '0';
	private static final char END = '1';
	private static final char BLANK = '.';
	private static final char WALL = '#';
	
	private static int N;
	private static int M;
	
	private static char[][] matrix; //출발지, 도착지, 열쇠 등을 기록
	private static boolean[][][] visited; //지나갈 수 있는지/없는지를 기록
	
	private static int[] dr = {0, 1, -1, 0};
	private static int[] dc = {1, 0, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");
		
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		
		matrix = new char[N][M];
		visited = new boolean[64][N][M]; //0: 아무 키도 없음 / 1: a번 키만 가지고 있음 / ...
		
		//('a', 'b', 'c', 'd', 'e', 'f')
		
		int startR = 0;
		int startC = 0;
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < M; j++) {
				matrix[i][j] = line.charAt(j);
				
				if(matrix[i][j] == START) {
					startR = i;
					startC = j;
				}
			}
		}
		
		int result = BFS(startR, startC);
		
		System.out.println(result);
	}
	
	public static int BFS(int startR, int startC) {
		Queue<Node> queue = new ArrayDeque<>();
		
		visited[0][startR][startC] = true;
		queue.add(new Node(startR, startC, 0, 1 >> 6));
		
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			int width = n.width;
			int currKey = n.key;
			
			if(matrix[n.row][n.col] == END) { return width; }
			
			int nr = 0;
			int nc = 0;
			for(int i = 0; i < dr.length; i++) {
				nr = n.row + dr[i];
				nc = n.col + dc[i];
				
				if(!isValid(nr, nc) || visited[currKey][nr][nc]) { continue; }
					
				if(isAvailable(nr, nc)) { //1.지나갈 수 있는 길이라면
					visited[currKey][nr][nc] = true;
					queue.add(new Node(nr, nc, width + 1, currKey));
				}
				else if(isKey(nr, nc)) { //2.열쇠가 있는 곳이라면
					int newKey = 1 << (matrix[nr][nc] - 'a');
					newKey = newKey | currKey; //새 열쇠를 기록한다.
					
					if(visited[newKey][nr][nc]) { continue; }
					
					visited[currKey][nr][nc] = true;
					visited[newKey][nr][nc] = true; //이전 배열 & 현재 배열을 모두 갱신한다.
					queue.add(new Node(nr, nc, width + 1, newKey));
				}
				else if(isDoor(nr, nc)) { //3.문이 있는 곳이라면
					int door = 1 << (matrix[nr][nc] - 'A'); //문의 비트를 계산한다.
					
					if((currKey & door) > 0) { //and 연산 : key와 문의 알파벳이 서로 맞다면
						visited[currKey][nr][nc] = true;
						queue.add(new Node(nr, nc, width + 1, currKey));
					}
				}
			}
		}
		
		return -1;
	}

	private static boolean isAvailable(int nr, int nc) { //지나갈 수 있는 길인지 확인
		return matrix[nr][nc] == BLANK || matrix[nr][nc] == '0' || matrix[nr][nc] == '1';
	}

	private static boolean isKey(int nr, int nc) { //현재 밟은 곳이 열쇠인지 확인
		return matrix[nr][nc] >= 'a' && matrix[nr][nc] <= 'z';
	}
	
	private static boolean isDoor(int nr, int nc) { //현재 밟은 곳이 문인지 확인
		return matrix[nr][nc] >= 'A' && matrix[nr][nc] <= 'Z';
	}

	private static boolean isValid(int nr, int nc) {
		return (nr > -1 && nc > -1 && nr < N && nc < M) && (matrix[nr][nc] != WALL);
	}
}

class Node {
	int row;
	int col;
	int width;
	int key;
	
	public Node(int row, int col, int width, int key) {
		this.row = row;
		this.col = col;
		this.width = width;
		this.key = key;
	}
}
