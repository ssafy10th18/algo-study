import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		String[] temp;
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
			temp = br.readLine().split(" ");

			int N = Integer.parseInt(temp[0]);
			int M = Integer.parseInt(temp[1]);

			int[] items = new int[N];

			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++) {
				int n = Integer.parseInt(st.nextToken());
				items[i] = n;
			}

			int result = -1;

			for(int p = 0; p < N; p++) {
				for(int q = p + 1; q < N; q++) {
					if(items[p] + items[q] <= M) {
						result = Math.max(items[p] + items[q], result);
					}
				}
			}

			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}

		System.out.println(sb);
	}

}