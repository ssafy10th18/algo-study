package swea.D4.swea3289;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	private static int N;
	private static int M;
	
	private static int[] parents;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			
			String[] temp = br.readLine().split(" ");
			
			N = Integer.parseInt(temp[0]);
			M = Integer.parseInt(temp[1]);
			
			makeSet();
			
			sb.append("#").append(test_case).append(" ");
			StringTokenizer st;
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				
				int ope = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(ope == 0) {
					union(a, b);
				} else if (ope == 1) {
					if(find(a) == find(b)) {
						sb.append(1);
					} else sb.append(0);
				}
			}
			
			sb.append("\n");
		}

		System.out.println(sb);
	}
	
	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return; //두 요소가 같은 집합에 속해있다면
		
		//bRoot 이하의 집합을 aRoot 아래로 붙인다.
		parents[bRoot] = aRoot;
	}
	
	private static int find(int a) {
		if(a == parents[a]) return a;
		
		parents[a] = find(parents[a]);
		
		return parents[a];
	}
	
	// 모든 요소는 자기만을 원소로 갖는 단위 서로소 집합임.
	private static void makeSet() {
		parents = new int[N + 1];
		for(int i = 0; i <= N; i++) {
			parents[i] = i;
		}
	}

}
