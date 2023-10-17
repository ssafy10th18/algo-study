package boj.gold.boj2458;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DFS1 {
	
	private static int N;
	private static int M;
	
	private static int gtCnt = 0;
	private static int ltCnt = 0;
	
	private static int[][] adj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");
		
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		
		adj = new int[N + 1][N + 1];
		
		for(int i = 0; i < M; i++) {
			temp = br.readLine().split(" ");
			
			int u = Integer.parseInt(temp[0]);
			int v = Integer.parseInt(temp[1]);
			
			adj[u][v] = 1; //u보다 v의 키가 크다.
		}
		
		int result = 0;
		for(int k = 1; k <= N; k++) {
			gtCnt = ltCnt = 0;
			
			gtDFS(k, new boolean[N + 1]);
			ltDFS(k, new boolean[N + 1]);
			
			if(gtCnt + ltCnt == N - 1) result++;
		}
		
		System.out.println(result);
	}

	private static void gtDFS(int curr, boolean[] visited) {
		visited[curr] = true;
		for(int j = 1; j <= N; j++) {
			if(adj[curr][j] == 1  && !visited[j]) {
				gtCnt++;
				gtDFS(j, visited);
			}
		}
	}
	
	private static void ltDFS(int curr, boolean[] visited) {
		visited[curr] = true;
		for(int i = 1; i <= N; i++) {
			if(adj[i][curr] == 1  && !visited[i]) {
				gtCnt++;
				gtDFS(i, visited);
			}
		}
	}
}
