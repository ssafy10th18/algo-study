package boj.gold.boj13023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	private static List<Integer>[] graph;
	private static boolean[] visited;
	
	private static int flag = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] temp = br.readLine().split(" ");
		
		int N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);

		int[] from = {0, 1, 2, 3};
		int[] to = {1, 2, 3, 4};
		
		graph = new ArrayList[N];
		visited = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
			
		for(int i = 0; i < M; i++) {
			temp = br.readLine().split(" ");
			
			int v = Integer.parseInt(temp[0]);
			int u = Integer.parseInt(temp[1]);
			
			graph[v].add(u);
			graph[u].add(v);
		}
		
		for(int i = 0; i < N; i++) {
			visited[i] = true;
			DFS(i, 0);
			visited = new boolean[N];
		}
		
		System.out.println(flag);
	}

	private static boolean DFS(int start, int depth) {		
		if(depth >= 4) {
			flag = 1;
			return true;
		}
		
		for(int u : graph[start]) {
			if(!visited[u]) {
				visited[u] = true;
				if(!DFS(u, depth + 1)) {
					visited[u] = false;
				}
			}
		}
		
		return false;
	}
}
