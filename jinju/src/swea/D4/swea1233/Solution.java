package swea.D4.swea1233;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	private static int N;
	private static int result = 1;
	private static char[] tree;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;

		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			result = 1;

			tree = new char[N + 1]; // 0은 사용 안 함

			String[] temp;
			for (int i = 1; i <= N; i++) {
				temp = br.readLine().split(" ");

				int n = Integer.parseInt(temp[0]);
				char operator = temp[1].charAt(0);

				tree[n] = operator;
			}

			inorder(1);

			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}

		System.out.println(sb);
	}

	public static void inorder(int index) {
		if (index * 2 > N && index * 2 + 1 > N) {
			if (tree[index] < '0' || tree[index] > '9') {
				result = 0;
			}
			return;
		}

		if (index * 2 <= N) {
			inorder(index * 2);
		}

		if (tree[index] >= '0' && tree[index] <= '9') {
			result = 0;
			return;
		}

		if (index * 2 + 1 <= N) {
			inorder(index * 2 + 1);
		}
	}

}