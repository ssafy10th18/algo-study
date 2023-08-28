package swea.D4.swea1251;

import java.io.*;
import java.util.*;

public class Solution {

	static class Vertex implements Comparable<Vertex> {
		int num; // 정점 번호
		long weight; // 트리 정점과 연결했을 때 간선 비용

		public Vertex(int num, long weight) {
			this.num = num;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			return Long.compare(this.weight, o.weight);
		}
	}

	private static List<Vertex>[] adjList; // 인접 리스트

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());

			adjList = new LinkedList[N];
			for (int i = 0; i < N; i++) {
				adjList[i] = new LinkedList<>();
			}
			
			boolean[] visited = new boolean[N];
			PriorityQueue<Vertex> pq = new PriorityQueue<>();

			int[] px = new int[N];
			int[] py = new int[N];

			StringTokenizer st;
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				px[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				py[i] = Integer.parseInt(st.nextToken());
			}

			double E = Double.parseDouble(br.readLine());
			
			for(int v = 0; v < N; v++) {
				for(int u = 0; u < N; u++) {
					if(v == u) continue;
					
					long distX = Math.abs(px[v] - px[u]);
					long distY = Math.abs(py[v] - py[u]);
					
					long dist = distX * distX + distY * distY;
					
					adjList[v].add(new Vertex(u, dist));
				}
			}
			
			pq.offer(new Vertex(0, 0));
			
			sb.append("#").append(test_case).append(" ");
			
			int count = 0;
			long result = 0; // 최소 신장 트리 비용
			long minWeight = 0;
			int minVertex = 0;
			
			while(!pq.isEmpty()) {
				Vertex curr = pq.poll();

				minWeight = curr.weight;
				minVertex = curr.num;
				if (visited[minVertex]) {
					continue;
				}

				visited[minVertex] = true;
				result += minWeight;
				if (++count == N) {
					break;
				}

				int size = adjList[minVertex].size();
				for (int i = 0; i < size; i++) {
					Vertex next = adjList[minVertex].get(i);
					if (!visited[next.num]) { //연결된 다음 노드와 연결되었는지 확인
						pq.offer(next);
					}
				}
			}
			
			sb.append(Math.round(result * E)).append("\n");
		}

		System.out.println(sb);
	}

}
