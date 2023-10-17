package boj.gold.boj20926;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	
	private static final char ROCK = 'R';
	private static final char HOLE = 'H';
	private static final char EXIT = 'E';

	private static int W;
	private static int H;
	
	private static char[][] matrix;
	private static int[][] DP;
	
	private static int[] dr = {0, 1, 0, -1};
	private static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");
		
		W = Integer.parseInt(temp[0]);
		H = Integer.parseInt(temp[1]);

		matrix = new char[H][W];
		DP = new int[H][W];
		
		Point start = null;
		Point end = null;
		for(int i = 0; i < H; i++) {
			String line = br.readLine();
			for(int j = 0; j < W; j++) {
				matrix[i][j] = line.charAt(j);
				DP[i][j] = Integer.MAX_VALUE;
				
				if(matrix[i][j] == 'T') {
					start = new Point(j, i);
				}
				
				if(matrix[i][j] == EXIT) {
					end = new Point(j, i);
				}
			}
		}
		
		dijkstra(start);
		
		int answer = DP[end.y][end.x];
		if(answer == Integer.MAX_VALUE) {
			answer = -1;
		}
		
		System.out.println(answer);
	}

	private static void dijkstra(Point start) {
		PriorityQueue<Node> pq = new PriorityQueue<>(
				(o1, o2) -> Integer.compare(o1.time, o2.time));
		
		//방문 처리는 어떻게 할까? => DP가 갱신된 곳만 방문
		pq.add(new Node(start.y, start.x, 0));
		DP[start.y][start.x] = 0;
		
		while(!pq.isEmpty()) {
			Node n = pq.poll();
			
			//if(matrix[n.row][n.col] == EXIT) {
			//	return n.time;
			//}
			
			int nr = 0;
			int nc = 0;
			//'R'을 만날 때까지 while로 이동
			for(int i = 0; i < dr.length; i++) {
				int idx = 1;
				int currTime = n.time;
				while(true) {
					nr = n.row + (dr[i] * idx);
					nc = n.col + (dc[i] * idx);
					
					//절벽으로 넘어간다면 가지치기
					if(!isValid(nr, nc)) { break; } 
					
					//구멍에 떨어지면 가지치기
					if(matrix[nr][nc] == HOLE) { break; }
					
					if(matrix[nr][nc] >= '0' && matrix[nr][nc] <= '9') {
						currTime += matrix[nr][nc] - '0'; //지나가는 길의 미끌시간을 더한다
					}
					
					if(matrix[nr][nc] == EXIT) { //출구를 만났다면		
						//시간만 갱신 시킨다.
						DP[nr][nc] = Math.min(currTime, DP[nr][nc]);
					}
					
					if(matrix[nr][nc] == ROCK) { //돌을 만났다면
						//직전 좌표에서 멈추고 큐에 넣어준다.
						nr = n.row + (dr[i] * (idx - 1));
						nc = n.col + (dc[i] * (idx - 1));
						
						if(DP[nr][nc] > currTime) {
							DP[nr][nc] = currTime;
							pq.add(new Node(nr, nc, DP[nr][nc]));
						}
						
						break; //이 뒤로 나아가지 않는다.
					}
					
					idx++;
				}
			}
		}
		
		return;
	}

	private static boolean isValid(int nr, int nc) {
		return (nr > -1 && nc > -1 && nr < H && nc < W);
	}

}

class Node {
	int row;
	int col;
	int time;
	
	public Node(int row, int col, int time) {
		this.row = row;
		this.col = col;
		this.time = time;
	}
}
