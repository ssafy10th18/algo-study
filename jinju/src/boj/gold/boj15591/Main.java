package boj.gold.boj15591;

import java.io.*;
import java.util.*;

public class Main {

	private static List<Integer>[] graph;
	private static int[][] usado;
	private static boolean[][] selected;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");

		int N = Integer.parseInt(input[0]);
		int Q = Integer.parseInt(input[1]);

		graph = new LinkedList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new LinkedList<>();
		}
		
		usado = new int[N + 1][N + 1];
		selected = new boolean[N + 1][N + 1];
		visited = new boolean[N + 1];
		
		StringTokenizer st;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());

			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());

			graph[p].add(q);
			graph[q].add(p);

			usado[p][q] = r;
			usado[q][p] = r;
		}

		// 1.인접하지 않은 두 정점의 usado를 구한다.
		// 인접하지 않는다면
		// 2.v번 간선과 연결된 모든 정점 중 usado가 k 이하인 것의 수 세기
		StringBuilder sb = new StringBuilder();
		for(int q = 0; q < Q; q++) { //O(N)
			st = new StringTokenizer(br.readLine());
			
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			BFS(v); // O(V + E);
			
			//생성된 결과에서 k 이상인 것만 추린다
			int count = 0;
			for (int i = 1; i <= N; i++) { //O(N^2) ==> 밖으로 빼기
				if(selected[v][i] || selected[i][v]) {
					continue;
				}
				
				if(usado[v][i] >= k || usado[i][v] >= k) {
					count += 1;
					selected[v][i] = true;
					selected[i][v] = true;
				}
			}
			
			sb.append(count).append("\n");
			
			selected = new boolean[N + 1][N + 1];
			visited = new boolean[N + 1];
		}
		
		System.out.println(sb);
		
	}

	private static void BFS(int from) {
		Queue<Node> queue = new ArrayDeque<>();
		
		queue.add(new Node(from, Integer.MAX_VALUE));
		visited[from] = true;

		while (!queue.isEmpty()) {
			Node v = queue.poll();
			
			//모든 점을 방문하면서 currStart과 연결되지 않은 정점을 발견하면 무조건 min을 넣어준다.
			for(int u : graph[v.num]) {
				int currMin = Math.min(v.currW, usado[v.num][u]);
				
				if(!visited[u]) {
					//start와 연결되지 않았다면
					if(!graph[from].contains(u)) { 
						usado[from][u] = currMin;
						usado[u][from] = currMin;
					}
					
					visited[u] = true;
					queue.add(new Node(u, currMin));
				}
			}
		}
		
		return;
	}

}

class Node {
	int num;
	int currW;
	
	public Node(int num, int currW) {
		this.num = num;
		this.currW = currW;
	}
}
