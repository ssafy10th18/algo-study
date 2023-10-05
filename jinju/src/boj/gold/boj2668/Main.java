package boj.gold.boj2668;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	private static int N;
	private static int[] graph;

	private static boolean[] visited;

	private static List<Integer> result = new ArrayList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		graph = new int[N + 1];
		visited = new boolean[N + 1];

		int target = 0;
		for (int i = 1; i <= N; i++) {
			target = Integer.parseInt(br.readLine());
			graph[i] = target; //한 노드에 다른 하나의 노드만 대응 => 배열로 정의 가능
		}

		for (int u = 1; u <= N; u++) {
			visited[u] = true; //시작점
			findCycle(u, u);
			visited[u] = false;
		}

		Collections.sort(result);
		StringBuilder sb = new StringBuilder();
		for (int n : result) {
			sb.append(n).append("\n");
		}

		System.out.println(result.size());
		System.out.println(sb.toString());
	}

	private static void findCycle(int start, int curr) {
		if(!visited[graph[curr]]) {
			visited[graph[curr]] = true;
			findCycle(start, graph[curr]);
			visited[graph[curr]] = false;
		}
		if(start == graph[curr]) { //시작점으로 다시 되돌아왔다면
			result.add(start); //사이클 발생 => 결과 리스트에 추가
		}
	}
}
