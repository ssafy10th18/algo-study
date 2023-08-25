package swea.D4.swea1238;

import java.util.*;
import java.io.*;

class Solution {
    private static final int MAX_NUM = 100;
	
    private static Stack<Integer> results;
	private static List<Integer>[] graph;
    private static boolean[] visited;
    
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;

		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
            String[] temp = br.readLine().split(" ");
            
            int N = Integer.parseInt(temp[0]);
            int start = Integer.parseInt(temp[1]);
            
            results = new Stack<>();
            graph = new LinkedList[MAX_NUM + 1];
            visited = new boolean[MAX_NUM + 1];
            
            for(int i = 0; i <= MAX_NUM; i++) {
                graph[i] = new LinkedList<>();
            }
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N / 2; i++) {
                int u = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                
                graph[u].add(w); //단방향
            }
            
            BFS(start);
            
            int max = results.pop();
            sb.append("#").append(test_case).append(" ")
            	.append(max).append("\n");
		}
		
		System.out.print(sb.toString());
	}
	
	public static void BFS(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		
		visited[start] = true;
		queue.add(start);
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			int max = -1;
			
			while(size-- > 0) {
				int v = queue.poll();
				
				for(int u : graph[v]) {
					if(!visited[u]) {
						visited[u] = true;
						queue.add(u);
						max = Math.max(max, u);
					}
				}
			}
			
			if(max != -1) {
				results.push(max);
			}
		}
	}
}