package boj.gold.boj17182;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N;
	private static int K;
	
	private static final int INF = 9_999_999;
	
	private static boolean[] visited;
	private static int[][] D;
	
	private static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] temp = br.readLine().split(" ");
		
		N = Integer.parseInt(temp[0]);
		K = Integer.parseInt(temp[1]);
		
		visited = new boolean[N];
		D = new int[N][N];
		
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				D[i][j] = Integer.parseInt(st.nextToken());
			}
		}
				
		//플로이드 워샬
		for(int k = 0; k < N; k++) { //경
			for(int i = 0; i < N; i++) { //출
				for(int j = 0; j < N; j++) { //도
					D[i][j] = Math.min(D[i][k] + D[k][j], D[i][j]);
				}
			}
		}
		
		visited[K] = true; //시작점을 체크하고 진입한다.
		DFS(1, K, 0); //주의: depth가 1부터 시작
		
		System.out.println(min);
	}

	private static void DFS(int depth, int start, int dist) {
		if(depth == N) {
			min = Math.min(dist, min);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(i == start) { continue; }
			
			if(!visited[i]) {
				visited[i] = true;
				DFS(depth + 1, i, dist + D[start][i]);
				visited[i] = false;
			}
		}
	}
}
