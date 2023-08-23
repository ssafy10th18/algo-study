package boj.silver.boj2563;

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][] board = new int[100][100];

		String[] temp;
		for(int k = 0; k < N; k++) {
			temp = br.readLine().split(" ");

			int w = Integer.parseInt(temp[0]);
			int h = Integer.parseInt(temp[1]);

			for(int i = h; i < h + 10; i++) {
				for(int j = w; j < w + 10; j++) {
					board[i][j] = 1;
				}
			}
		}

		int answer = 0;
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(board[i][j] == 1) {
					answer++;
				}
			}
		}

		System.out.println(answer);

	}
}