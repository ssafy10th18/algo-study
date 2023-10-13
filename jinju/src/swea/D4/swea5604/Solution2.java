package swea.D4.swea5604;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution2 {

	private static int A;
	private static int B;
	
	private static long[] tree;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			
			String[] temp = br.readLine().split(" ");
			
			//구간 A부터 B까지의 부분합을 구한다.
			A = Integer.parseInt(temp[0]);
			B = Integer.parseInt(temp[1]);
			
			tree = new long[4 * B];
			
			init(A, B, 1);
			
			long result = tree[1];
			
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		
		System.out.print(sb);
	}

	private static long init(int start, int end, int node) {
		if(start == end) { //리프노드라면
			return tree[node] = sumPlaces(start);
		}
		int mid = (start + end) / 2;
		
		return tree[node] 
				= init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
	}
	
	private static long sumPlaces(int start) {
		int sum = 0;
		while(start > 0) {
			sum += start % 10;
			start /= 10;
		}
		return sum;
	}
}
