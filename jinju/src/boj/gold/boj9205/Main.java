package boj.gold.boj9205;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

	private static final int MAX_BEER = 20;
	private static final int UNIT_DIST = 50;
	
	private static int N;
	
	private static int start = 0;
	private static int destination;
	
	private static boolean[] visited;
	private static List<Node>[] graph;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			N = Integer.parseInt(br.readLine());
			
			graph = new LinkedList[N + 2];
			visited = new boolean[N + 2];
			destination = N + 2 - 1;
			
			for(int i = 0; i < N + 2; i++) {
				graph[i] = new LinkedList<>();
			}
			
			String[] temp;
			Point[] points = new Point[N + 2];
			for(int i = 0; i < N + 2; i++) {
				temp = br.readLine().split(" ");
				
				int x = Integer.parseInt(temp[0]);
				int y = Integer.parseInt(temp[1]);
				
				points[i] = new Point(x, y);
			}
			
			//각 정점간의 거리 계산
			for(int i = 0; i < points.length; i++) {
				Point v = points[i];
				for(int j = 0; j < points.length; j++) {
					if (i == j) continue;
					
					Point u = points[j];
					
					int dist = Math.abs(v.x - u.x) + Math.abs(v.y - u.y);
					
					graph[i].add(new Node(j, dist));
				}
			}
			
			boolean result = BFS();
			
			if(result) {
				sb.append("happy").append("\n");
			} else { sb.append("sad").append("\n"); }
		}
		
		System.out.println(sb.toString());
	}
	
	private static boolean BFS() {
		Queue<Integer> queue = new ArrayDeque<>();
		
		visited[start] = true;
		queue.add(start);
		
		while(!queue.isEmpty()) {
			int v = queue.poll();
			
			if(v == destination) { //종점이라면
				return true; //탐색 성공
			}
			
			for(Node u : graph[v]) {
				int availableDist = MAX_BEER * UNIT_DIST; //보유 맥주로 갈 수 있는 거리
				int dist = u.dist; //앞으로 가야하는 거리
				
				if(visited[u.num]) { continue; }
				
				if(availableDist >= dist) { //잔여 맥주량으로 갈 수 있는 경우만 큐에 넣는다.
					visited[u.num] = true;
					queue.add(u.num);
				}
			}
		}
		
		return false;
	}

}

class Node {
	int num;
	int dist;
	
	public Node(int num, int dist) {
		this.num = num;
		this.dist = dist;
	}
}
