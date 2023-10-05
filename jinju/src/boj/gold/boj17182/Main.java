package boj.gold.boj17182;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static final int INF = 9_999_999;
	
	private static int[][] graph;
	private static int[][] D;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] temp = br.readLine().split(" ");
		
		int N = Integer.parseInt(temp[0]);
		int K = Integer.parseInt(temp[1]);
		
		graph = new int[N][N];
		D = new int[N][N];
		
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				
				if(i == j) D[i][j] = 0;
				else D[i][j] = INF; 
			}
		}
				
		//플로이드 워샬
		for(int k = 0; k < N; k++) { //경
			for(int i = 0; i < N; i++) { //출
				for(int j = 0; j < N; j++) { //도
					D[i][j] = Math.min(D[i][k] + graph[k][j], D[i][j]);
				}
			}
		}
		
		System.out.println();
		
		//시작점 : K
	}
}
