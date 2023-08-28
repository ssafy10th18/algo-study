package boj.gold.boj2493;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] towers = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			towers[i] = Integer.parseInt(st.nextToken());
		}

		int[] DP = new int[N];
		DP[0] = 0;
		for (int i = 1; i < N; i++) {
			if(towers[i] >= towers[i - 1]) {
				int start = DP[i - 1];

				for(int j = start; j >= 0; j--) {
					if(towers[j] > towers[i]) {
						DP[i] = j + 1;
						break;
					}
				}
			}
			else { //towers[i] < towers[i - 1]
				DP[i] = i;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int dp : DP) {
			sb.append(dp).append(" ");
		}

		System.out.println(sb);
	}
}