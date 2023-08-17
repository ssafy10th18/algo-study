package swea.D5.swea1247;

import java.awt.Point;
import java.io.*;
import java.util.*;

public class Solution {

	private static int N;
	private static int destX;
	private static int destY;
	private static int answer;
	
	private static Point[] customers;
	private static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			
			destX = Integer.parseInt(st.nextToken());
			destY = Integer.parseInt(st.nextToken());
			
			customers = new Point[N];
			visited = new boolean[N];
			
			for(int i = 0; i < N; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				customers[i] = new Point(x, y);
			}
			
			answer = Integer.MAX_VALUE;
			DFS(0, startX, startY, 0);
			
			sb.append("#").append(test_case).append(" ")
				.append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void DFS(int depth, int prevX, int prevY, int dist) {
		if(N == depth) {
			int d = Math.abs(prevX - destX) + Math.abs(prevY - destY);
			answer = Math.min(answer, dist + d);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(visited[i]) { continue; }
			
			int currX = customers[i].x;
			int currY = customers[i].y;
			
			visited[i] = true;
			DFS(depth + 1, currX, currY, dist + Math.abs(prevX - currX) 
				+ Math.abs(prevY - currY));
			visited[i] = false;
		}
	}

}
