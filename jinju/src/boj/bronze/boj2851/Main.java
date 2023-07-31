package boj.bronze.boj2851;

import java.io.*;

public class Main {

	private static final int CRITERIA = 100;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = 10;

		int sum = 0;
		int minDiff = Integer.MAX_VALUE;
		int answer = 0;
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());

			sum += num;

			int diff = Math.abs(CRITERIA - sum);

			if(minDiff >= diff) {
				minDiff = diff;
				answer = sum;
			}

			if(answer > CRITERIA) {
				break;
			}
		}

		System.out.println(answer);
	}
}
