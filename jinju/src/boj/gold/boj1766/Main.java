package boj.gold.boj1766;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	private static int N;
	private static int M;
	
	private static List<Integer>[] graph;
	private static int[] indegree;
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		indegree = new int[N + 1];
		graph = new LinkedList[N + 1];
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new LinkedList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			graph[u].add(v);
			indegree[v]++;
		}
		
		topological();
		
		System.out.println(sb.toString());
	}

	private static void topological() {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i = 1; i <= N; i++) {
			if(indegree[i] == 0) pq.add(i);
		}
		
		while(!pq.isEmpty()) {
			int u = pq.poll();
			sb.append(u).append(" ");
			
			for(int v : graph[u]) {
				indegree[v]--;
				
				if(indegree[v] == 0) {
					pq.add(v);
				}
			}
		}
		return;
	}

}
