package boj.platinum.boj1168;

import java.io.*;

public class Main {

	private static final int SIZE = 100_000;
	private static int[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");

		int N = Integer.parseInt(temp[0]);
		int K = Integer.parseInt(temp[1]);

		tree = new int[4 * SIZE];

		init(0, N - 1, 1);

		int rank = K;

		StringBuilder sb = new StringBuilder();
		sb.append("<");
		for(int i = 0; i < N; i++) {
			int result = update(0, N - 1, 1, rank) + 1; //cur, l, r, rank
			sb.append(result);

			if(i < N - 1) {
				rank = (rank + K - 2) % (N - i - 1) + 1;
				sb.append(", ");
			}
		}
		sb.append(">");

		System.out.println(sb);
	}

	private static int init(int start, int end, int node) {
		if(start == end) {
			return tree[node] = 1; 
		}
		int mid = (start + end) / 2; 
		return tree[node] 
				= init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
	}

	private static int update(int start, int end, int node, int rank) {
		if(start == end) {
			tree[node] -= 1;
			return start;
		}

		int mid = (start + end) / 2;

		int result = 0;
		if(rank <= tree[node * 2]) { 
			result = update(start, mid, node * 2, rank); 
		} else { 
			result = update(mid + 1, end, node * 2 + 1, rank - tree[node * 2]);
		}

		tree[node] = tree[node * 2] + tree[node * 2 + 1];

		return result;
	}

}