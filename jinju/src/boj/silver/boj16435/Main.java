package boj.silver.boj16435;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");

		int N = Integer.parseInt(temp[0]);
		int L = Integer.parseInt(temp[1]);

		int[] fruit = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			fruit[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(fruit);

		for(int i = 0; i < N; i++) {
			if(fruit[i] <= L) {
				L += 1;
			} else break;
		}

		System.out.println(L);
	}
}