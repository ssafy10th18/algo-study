package boj.bronze.boj3985;

import java.io.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());

		int[] cakes = new int[L + 1]; //0은 사용하지 않음

		int people = Integer.parseInt(br.readLine());
		int[] pieces = new int[people + 1]; //0은 사용하지 않음

		int max = 0;
		int expected = 0;
		int actual = 0;
		for(int i = 1; i <= people; i++) {
			String[] temp = br.readLine().split(" ");

			int P = Integer.parseInt(temp[0]);
			int K = Integer.parseInt(temp[1]);

			if(K - P > max) {
				max = K - P;
				expected = i;
			}

			for(int j = P; j <= K; j++) {
				if(cakes[j] != 0) { //선점된 적 있다면
					continue;
				}
				cakes[j] = i;
				pieces[i]++;
			}
		}

		max = 0;
		for(int i = 1; i <= people; i++) {
			if(pieces[i] > max) {
				max = pieces[i];
				actual = i;
			}
		}

		System.out.println(expected);
		System.out.println(actual);
	}
}