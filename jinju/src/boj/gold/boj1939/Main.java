package boj.gold.boj1939;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

	private static List<Node>[] graph;
	private static int[] maxWeight;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");

		int N = Integer.parseInt(temp[0]); // 섬의 개수
		int M = Integer.parseInt(temp[1]); // 다리의 개수

		graph = new LinkedList[N + 1];
		maxWeight = new int[N + 1]; //dist 배열

		for (int i = 1; i <= N; i++) {
			graph[i] = new LinkedList<>();
			maxWeight[i] = -1;
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

		maxWeight[start] = Integer.MAX_VALUE;
		pq.add(new Node(start, maxWeight[start]));
		
		// Node에 기록된 '최소' 중량 중 최대인 것을 찾는다.
		while (!pq.isEmpty()) {
			Node n = pq.poll();

			// 현재까지 구해진 최대값보다 작으면,
			// 즉 n까지 도달하는 데 소모되는 weight가 유망하지 않으면 가지치기
			if(n.weight < maxWeight[n.num]) {
				continue;
			}

			for (Node next : graph[n.num]) {
				int currWeight = Math.min(next.weight, n.weight); //이 경로의 최소하중
				if (currWeight > maxWeight[next.num]) { //더 큰 것으로 교체될 수 있다면 add
					maxWeight[next.num] = currWeight;
					pq.add(new Node(next.num, maxWeight[next.num]));
				}
			}
		}

		return maxWeight[end];
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
