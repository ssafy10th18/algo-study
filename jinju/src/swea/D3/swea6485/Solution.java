package swea.D3.swea6485;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

	private static final int MAX_SIZE = 5000;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[] temp = new int[MAX_SIZE + 1];

			String[] input;
			for (int i = 0; i < N; i++) {
				input = br.readLine().split(" ");

				int start = Integer.parseInt(input[0]);
				int end = Integer.parseInt(input[1]);

				for (int k = start; k <= end; k++) {
					temp[k]++;
				}
			}

			int P = Integer.parseInt(br.readLine());

			sb.append("#").append(test_case).append(" ");

			int[] result = new int[P]; // 0은 사용하지 않음
			for (int i = 0; i < P; i++) {
				int target = Integer.parseInt(br.readLine());
				result[i] = temp[target];
				sb.append(result[i]).append(" ");
			}

			sb.append("\n");
		}

		System.out.println(sb);
	}

}