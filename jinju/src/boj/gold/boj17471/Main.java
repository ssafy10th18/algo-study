package boj.gold.boj17471;

import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static int min = Integer.MAX_VALUE;
	
	private static int[] populations; // 전체 구역의 인구수 기록
	private static int[] parents;
	private static boolean[] visited;
	private static boolean[] selected;
	
	private static List<Integer>[] graph;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		populations = new int[N + 1]; // 0은 사용 안 함
		selected = new boolean[N + 1]; // 0은 사용 안 함
		graph = new LinkedList[N + 1]; // 0은 사용 안 함

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			populations[i] = Integer.parseInt(st.nextToken());
			graph[i] = new LinkedList<>();
		}

		for (int v = 1; v <= N; v++) {
			st = new StringTokenizer(br.readLine(), " ");

			int M = Integer.parseInt(st.nextToken());

			for (int j = 0; j < M; j++) {
				int u = Integer.parseInt(st.nextToken());
				graph[v].add(u);
				graph[u].add(v);
			}
		}
	
		subSet(1);

		if(min == Integer.MAX_VALUE) { min = -1; }
		
		System.out.println(min);
	}
	
	private static void subSet(int depth) {
		if(depth == N + 1) {
			int sum1 = 0; int sum2 = 0;
			
			ArrayList<Integer> selectedSpace = new ArrayList<>();
			ArrayList<Integer> unselectedSpace = new ArrayList<>();
			
			makeSet();
			
			for(int i = 1; i <= N; i++) {
				if(selected[i]) {
					selectedSpace.add(i);
					sum1 += populations[i];
				}
				else {
					unselectedSpace.add(i);
					sum2 += populations[i];
				}
			}
			
			if(selectedSpace.size() == 0 || unselectedSpace.size() == 0) {
				return;
			}
			
			for(int i = 0; i < selectedSpace.size() - 1; i++) {
				union(selectedSpace.get(i), selectedSpace.get(i + 1));
			}
			
			for(int i = 0; i < unselectedSpace.size() - 1; i++) {
				union(unselectedSpace.get(i), unselectedSpace.get(i + 1));
			}
			
			if(BFS(selectedSpace) && BFS(unselectedSpace)) {
				min = Math.min(min, Math.abs(sum1 - sum2));
			}
			
			return;
		}
		
		selected[depth] = true;
		subSet(depth + 1);
		selected[depth] = false;
		subSet(depth + 1);
	}
	
	private static boolean BFS(ArrayList<Integer> space) {
		Queue<Integer> queue = new ArrayDeque<>();
		visited = new boolean[N + 1]; // 0은 사용 안 함
		
		queue.add(space.get(0));
		visited[space.get(0)] = true;
		
		while(!queue.isEmpty()) {
			int v = queue.poll();
			for(int u : graph[v]) {
				if(visited[u] || parents[u] != parents[v]) {
					continue; //이미 방문했거나 부모가 같지 않다면 유효하지 않음
				}
				
				visited[u] = true;
				queue.add(u);
			}
		}
		
		for (int v : space) { //방문되지 않은 정점이 남아있다면
			if(!visited[v]) return false;
		}
		
		return true;
	}

	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return;

		parents[bRoot] = aRoot;
	}

	private static int find(int a) {
		if (a == parents[a])
			return a;

		parents[a] = find(parents[a]);
		return parents[a];
	}

	private static void makeSet() {
		parents = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}
	}
}
