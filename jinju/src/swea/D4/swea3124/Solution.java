package swea.D4.swea3124;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	private static Edge[] edgeList;
	private static int V, E;
	private static int[] parents;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			String[] temp = br.readLine().split(" ");
			
			V = Integer.parseInt(temp[0]);
			E = Integer.parseInt(temp[1]);
			
			edgeList = new Edge[E];
			
			StringTokenizer st;
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				edgeList[i] = new Edge(from, to, weight);
			}

			Arrays.sort(edgeList);
			
			makeSet();
			
			sb.append("#").append(test_case).append(" ");
			
			int count = 0;
			long result = 0;
			for(Edge edge : edgeList) { //비용이 작은 간선 순으로 꺼내서 처리
				if(union(edge.from, edge.to)) { 
					//사이클이 형성되지 않았다면
					result += edge.weight;
					if(++count == V - 1) break;
				}
			}
			
			sb.append(result).append("\n");
		}

		System.out.println(sb);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false; //두 요소가 같은 루트에 속해있다면 사이클 형성
		
		//bRoot 이하의 집합을 aRoot 아래로 붙인다.
		parents[bRoot] = aRoot;
		
		return true;
	}
	
	private static int find(int a) {
		if(a == parents[a]) return a; //루트에 다다랐다면 반환
		
		return parents[a] = find(parents[a]); //그렇지 않으면 parent를 계속 찾는다.
	}

	private static void makeSet() {
		parents = new int[V + 1];
		for(int i = 0; i <= V; i++) {
			parents[i] = i;
		}
	}
}
