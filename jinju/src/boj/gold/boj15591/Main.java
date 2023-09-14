package boj.gold.boj15591;

import java.io.*;
import java.util.*;

public class Main {

	private static int K;
	private static int count = 0;
	
	private static List<Node>[] graph;
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
		
		visited = new boolean[N + 1];
		
		StringTokenizer st;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());

			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			graph[p].add(new Node(q, r));
			graph[q].add(new Node(p, r));
		}

		StringBuilder sb = new StringBuilder();
		for(int q = 0; q < Q; q++) { //O(N)
			st = new StringTokenizer(br.readLine());
			
			K = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			BFS(v); // O(V + E);
			
			sb.append(count).append("\n");
			
			count = 0;

			visited = new boolean[N + 1];
		}
		
		System.out.println(sb);
		
	}

	private static void BFS(int from) {
		Queue<Integer> queue = new ArrayDeque<>();
		
		queue.add(from);
		visited[from] = true;

		while (!queue.isEmpty()) {
			int v = queue.poll();
						
			for(Node u : graph[v]) {		
				//가지치기 => 어떤 간선의 가중치가 K이상이어야만 큐에 넣는다.
				if(!visited[u.num] && u.usado >= K) {
					count++;

					visited[u.num] = true;
					queue.add(u.num);
				}
			}
		}
		
		return;
	}

}

class Node {
	int num; //연결된 노드
	int usado;
	
	public Node(int num, int usado) {
		this.num = num;
		this.usado = usado;
	}
}
