package boj.gold.boj2252;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static int N;
	private static int M;
	
	private static int[] degree;
	private static boolean[] visited;
	private static List<Integer>[] graph;
	
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] temp = br.readLine().split(" ");

		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		
		degree = new int[N + 1]; //0은 사용 안 함
		visited = new boolean[N + 1];
		graph = new LinkedList[N + 1];
		
		for(int i = 0; i < N + 1; i++) {
			graph[i] = new LinkedList<>();
		}
		
		for(int i = 0; i < M; i++) {
			temp = br.readLine().split(" ");
			
			int v = Integer.parseInt(temp[0]);
			int u = Integer.parseInt(temp[1]); 
			
			graph[v].add(u);
			degree[u]++;
		}
		
		topologicalSort();
		
		System.out.println(sb);
	}
	
	public static void topologicalSort() { //BFS
		Queue<Integer> queue = new ArrayDeque<>();
		
		for(int i = 1; i < N + 1; i++) {
			if(degree[i] == 0) {
				queue.add(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int v = queue.poll();
			sb.append(v).append(" ");
			
			for(int u : graph[v]) {
				degree[u]--;
				
				if(degree[u] == 0) {
					queue.add(u);
				}
			}
		}
		
		return;
	}

	public static void topologicalSort(int start) { //DFS
		if(!visited[start]) {
			sb.append(start).append(" ");
		}
		
		visited[start] = true;
		
		for(int u : graph[start]) {
			if(!visited[u]) {
				degree[u]--;
				
				if(degree[u] == 0) {
					topologicalSort(u);
				}
			}
		}
		
		return;
	}
}
