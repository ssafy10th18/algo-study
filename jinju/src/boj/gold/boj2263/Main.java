package boj.gold.boj2263;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	private static int[] parent;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		makeSet(N);
		
		//findTree();
	}
	
	private static int find(int a) {
		if(a == parent[a]) return a;
		
		return parent[a] = find(parent[a]);
	}
	
	private boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return false;
		
		if(a <= b) parent[b] = a;
		else parent[a] = b;
		
		return true;
	}
	
	private static void makeSet(int N) {
		parent = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}

}
