package boj.bronze.boj8320;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int count = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= i; j++) {
				if(i * j <= N) {
					count++;
				}
			}
		}

		System.out.println(count);
	}
}