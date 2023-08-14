package swea.D3.swea5215;

import java.io.*;

public class Solution2 {

	private static final int SIZE = 9;
	private static final int MAX = 362_880;

	private static int count = 0;
	
	private static int[] kyuCards;
	private static int[] inCards;
	private static int[] remains;
	private static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			count = 0;
			
			String[] input = br.readLine().split(" ");
			
			kyuCards = new int[SIZE];
			inCards = new int[SIZE];
			remains = new int[SIZE];
			visited = new boolean[SIZE];
			
			boolean[] used = new boolean[SIZE * 2 + 1];

			for (int i = 0; i < SIZE; i++) {
				kyuCards[i] = Integer.parseInt(input[i]);
				used[kyuCards[i]] = true;
			}
			
			int idx = 0;
			for(int i = 1; i <= SIZE * 2; i++) {
				if(!used[i]) {
					remains[idx++] = i;
				}
			}

			permutation(0);
			
			sb.append("#").append(test_case).append(" ").append(count).append(" ")
				.append(MAX - count).append("\n");
		}

		System.out.print(sb);
	}
	
	private static void permutation(int depth) {
		if(SIZE == depth) {
			int kScore = 0;
			int iScore = 0;
			
			for(int i = 0; i < SIZE; i++) {
				if(kyuCards[i] > inCards[i]) {
					kScore += (kyuCards[i] + inCards[i]);
				}
				else { //if(kyuCards[i] < inCards[i]) {
					iScore += (kyuCards[i] + inCards[i]);
				}
			}
			
			if(kScore > iScore) {
				count++;
			}
			
			return;
		} 
		
		for(int i = 0; i < SIZE; i++) {
			if(visited[i]) {
				continue;
			}
			
			inCards[depth] = remains[i];
			
			visited[i] = true;
			permutation(depth + 1);
			visited[i] = false;
		}
	}
}

