package boj.gold.boj24230;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	private static int N;
	
	private static int[] colors;
	private static int[] targets;
	private static int[] root;
	private static List<Integer>[] tree;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		colors = new int[N + 1];
		targets = new int[N + 1];
		root = new int[N + 1];
		
		tree = new LinkedList[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			colors[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N; i++) {
			String[] temp = br.readLine().split(" ");
			
			//주의 : 트리의 루트가 1이 되도록 형성
			int u = Integer.parseInt(temp[0]);
			int v = Integer.parseInt(temp[1]);
			
			int smaller = Math.min(u, v);
			int larger = Math.max(u, v);
			
			union(smaller, larger);
			tree[smaller].add(larger);
		}
		
		//find로 루트를 찾아 칠한다.
		//중간에 만나는 자식 노드도 칠한다.
		for(int i = 0; i < N; i++) {
			
		}
	}
	
	private static int find(int a) {
		if(a == root[a]) return root[a];
		
		return root[a] = find(root[a]);
	}
	
	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA == rootB) return false;
		
		root[b] = a;
		
		return true;
	}
	
	private static void makeSet() {
		root = new int[N];
		for(int i = 1; i <= N; i++) {
			root[i] = i;
		}
	}
}
