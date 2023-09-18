package boj.gold.boj2263;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	private static int[] root;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		makeSet(N);
	}
	
	private static int find(int a) {
		if(a == root[a]) return a;
		
		return root[a] = find(root[a]);
	}
	
	private static void union(int a, int b) {
		if(find(a) == find(b)) return;
		
		
	}
	
	private static void makeSet(int N) {
		root = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			root[i] = i;
		}
	}

}
