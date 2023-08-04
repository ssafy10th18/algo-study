package boj.gold.boj2023;

import java.io.*;

public class Main {

	private static int N;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		int[] result = new int[N];

		DFS(0, result);

		System.out.println(sb);
	}

	private static void DFS(int depth, int[] result) {
		if(depth == N) {
			int rslt = 0;
			for(int i = 0; i < result.length; i++) {
				rslt *= 10;
				rslt += result[i];

				if(!isPrime(rslt)) {
					return;
				}
			}

			sb.append(rslt).append("\n");
			return;
		}

		for(int i = 1; i < 10; i++) {
			result[depth] = i;
			DFS(depth + 1, result);
			result[depth] = 0;
		}

		return;
	}

	private static boolean isPrime(int rslt) {
		if(rslt == 1) {
			return false;
		}

		for(int i = 2; i <= Math.sqrt(rslt); i++) {
			if(rslt % i == 0) {
				return false;
			}
		}

		return true;
	}

}