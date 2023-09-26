package boj.gold.boj2668;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	private static int N;
	private static List<Integer>[] graph;

	private static boolean isCycle;
	
	private static boolean[] visited;
	private static boolean[] selected;
	private static boolean[] finished;

	private static List<Integer> result = new ArrayList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		graph = new ArrayList[N + 1];
		selected = new boolean[N + 1];
		finished = new boolean[N + 1];

		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		int target = 0;
		for (int i = 1; i <= N; i++) {
			target = Integer.parseInt(br.readLine());
			graph[i].add(target); // 주의 : 단방향
		}

		for (int u = 1; u <= N; u++) {
			isCycle = false;
			visited = new boolean[N + 1];
			
			findCycle(u); // u를 시작점으로 하는 사이클을 찾는다.
			if (!isCycle) { continue; }

			System.out.println("사이클 형성: " + u);

			// 사이클을 형성한다면 결과물에 집계한다.
			result.add(u);
			for (int v : graph[u]) {
				if (!selected[v]) {
					selected[v] = true;
					result.add(v);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int n : result) {
			sb.append(n).append("\n");
		}

		System.out.println(result.size());
		System.out.println(sb.toString());
	}

	private static void findCycle(int curr) {
		visited[curr] = true;
		for (int next : graph[curr]) {
			if (!visited[next]) {
				findCycle(next);
			} else if (!finished[next]) {
				isCycle = true;
			}
		}
		finished[curr] = true; //해당 점을 포함하는 경로 탐색 완료.
	}
}
