package boj.gold.boj18513;

import java.io.*;
import java.util.*;

public class Main {

	private static final int SIZE = 100_000_000;
	//private static final int SIZE = 10;
	
	private static int result = 0;
	private static int N;
	private static int K;
	
	private static int[] houses;
	private static boolean[] visited = new boolean[2 *  SIZE + 1]; //2억 2십만까지 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		K = Integer.parseInt(temp[1]);
		
		houses = new int[N];
		
		temp = br.readLine().split(" ");
		for(int i = 0; i < N; i++) {
			houses[i] = Integer.parseInt(temp[i]) + SIZE; //음수 보정
		}
		
		BFS();
		
		System.out.println(result);
	}
	
	private static void BFS() {
		Queue<Integer> queue = new ArrayDeque<>();
		
		for(int start : houses) {
			queue.add(start);
			visited[start] = true;
		}
		
		int count = 0;
		int width = 1;
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size-- > 0) {
				int p = queue.poll();
				
				//if(p + width <= 2 * SIZE + 1) {
				//	continue;
				//}
				
				if(!visited[p + width]) {
					visited[p + width] = true;
					result += width;
					count += 1;
					queue.add(p + width);
				}
				
				if(count == K) {
					return;
				}
				
				//if(p - width < 0 || visited[p - width]) {
				//	continue; 
				//}
				//만약 건너뛰었다가 width가 의도치않게 커진다면? (한쪽에만 설치된다면?)
				
				if(!visited[p - width]) {
					visited[p - width] = true;
					result += width;
					count += 1;
					queue.add(p - width);
				}
				
				if(count == K) {
					return;
				}
			}
			width++;
		}
		
		return;
	}
}
