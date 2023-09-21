package boj.gold.boj1717;

import java.io.*;

public class Main {
	
	private static int[] root;
	private static int[] rank; 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] temp = br.readLine().split(" ");
		int n = Integer.parseInt(temp[0]);
		int m = Integer.parseInt(temp[1]); 
		
		root = new int[n + 1];
		rank = new int[n + 1];
		for(int i = 0; i <= n; i++) {
			root[i] = i;
			rank[i] = 0;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < m; i++) {
			temp = br.readLine().split(" ");
			
			int operation = Integer.parseInt(temp[0]);
			int a = Integer.parseInt(temp[1]);
			int b = Integer.parseInt(temp[2]);
			
			if(operation == 0) {
				union(a, b);
			}
			else if(operation == 1) {
				boolean result = isSameSet(a, b);
				
				if(result) {
					sb.append("YES").append('\n');
				}
				else {
					sb.append("NO").append('\n');
				}
			}
			else {
				continue;
			}
		}
		
		System.out.print(sb);
	}
	
	public static int find(int x) {
		if(root[x] == x) {
			return x;
		}
		else {
			return root[x] = find(root[x]);
		}
	}
	
	public static void union(int x, int y) {
		x = find(x); 
		y = find(y);
		
		if(x == y) {
			return;
		}
		
		if(rank[x] < rank[y]) {
			root[x] = y;
		}
		else if(rank[x] >= rank[y]) {
			root[y] = x;
		}
		
		if(rank[x] == rank[y]) {
			rank[x]++;
		}
	}
	
	public static boolean isSameSet(int x, int y) {
		x = find(x);
		y = find(y);

		return (x == y);
	}
}
