package boj.gold.boj1753;

import java.io.*;
import java.util.*;

public class Main {

	static class Node implements Comparable<Node> {
		int num; // 정점 번호
		int weight; // 트리 정점과 연결했을 때 간선 비용

		public Node(int num, int weight) {
			this.num = num;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	private static final int INF = Integer.MAX_VALUE;

	private static int V, E;

	private static int[] dist;
	private static List<Node>[] adjList; // 인접 행렬
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		int start = Integer.parseInt(br.readLine());

		dist = new int[V + 1]; // 0은 사용하지 않음

		adjList = new ArrayList[V + 1]; // 0은 사용하지 않음
		for (int i = 0; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine().trim());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adjList[from].add(new Node(to, weight));
		}

		Arrays.fill(dist, INF);

		dijkstra(start);

		for (int end = 1; end <= V; end++) {
			if (dist[end] != INF) {
				sb.append(dist[end]).append("\n");
			} else
				sb.append("INF").append("\n");
		}

		System.out.print(sb);
	}

	private static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V + 1]; // 0은 사용하지 않음

		dist[start] = 0; // 트리의 처음 시작 정점을 세팅
		pq.offer(new Node(start, dist[start]));

		int count = 0;
		while (!pq.isEmpty()) {
			Node current = pq.poll(); // 경유지 정점의 인덱스 저장

			if (visited[current.num])
				continue;

			// step2 : 방문 처리
			visited[current.num] = true;
			if (++count == V)
				break;

			// step3 : 경유지를 이용하여 미방문 정점들의 출발지에서 자신으로의 최소비용 고려
			for (Node v : adjList[current.num]) {
				// 방문한 적 없고, 현재 정점에서 갈 수 있는 경우
				if (!visited[v.num] 
						&& dist[v.num] > dist[current.num] + v.weight) {
					dist[v.num] = dist[current.num] + v.weight; // 더 짧은 거리로 갱신
					pq.offer(new Node(v.num, dist[v.num]));
				}
			}
		}

	}

}
