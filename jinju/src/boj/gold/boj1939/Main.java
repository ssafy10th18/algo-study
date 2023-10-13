package boj.gold.boj1939;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

	private static List<Node>[] graph;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");

		int N = Integer.parseInt(temp[0]); // 섬의 개수
		int M = Integer.parseInt(temp[1]); // 다리의 개수

		graph = new LinkedList[N + 1];
		visited = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			graph[i] = new LinkedList<>();
		}

		for (int i = 1; i <= M; i++) {
			temp = br.readLine().split(" ");

			int A = Integer.parseInt(temp[0]);
			int B = Integer.parseInt(temp[1]);
			int C = Integer.parseInt(temp[2]);

			graph[A].add(new Node(B, C)); // 양방향
			graph[B].add(new Node(A, C));
		}

		temp = br.readLine().split(" ");
		int start = Integer.parseInt(temp[0]);
		int end = Integer.parseInt(temp[1]);

		int maxWeight = dijkstra(start, end);

		System.out.println(maxWeight);
	}

	private static int dijkstra(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.weight, o1.weight)); // 가중치 큰 간선 우선

		pq.add(new Node(start, Integer.MAX_VALUE));
		visited[start] = true;

		int maxWeight = 0; // Node에 기록된 '최소' 중량 중 최대인 것을 찾는다.
		while (!pq.isEmpty()) {
			Node n = pq.poll();

			if (n.num == end) { // 도착지점에 다다르면
				maxWeight = Math.max(n.weight, maxWeight);
				continue;
			}

			for (Node next : graph[n.num]) {
				if (visited[next.num]) {
					continue;
				}

				visited[next.num] = true;
				if (next.weight >= maxWeight) { // 현재까지의 최대 하중보다 작으면 가지치기
					pq.add(new Node(next.num, Math.min(next.weight, n.weight)));
				}
			}
		}

		return maxWeight;
	}

}

class Node {
	int num; // 현재 노드 번호
	int weight; // 해당 경로까지의 '최소' 중량

	public Node(int num, int weight) {
		this.num = num;
		this.weight = weight;
	}
}
