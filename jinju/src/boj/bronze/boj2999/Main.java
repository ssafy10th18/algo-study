package boj.bronze.boj2999;

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();

		int len = input.length();

		int R = 1;
		int C = len;
		all : for(int k = 1; k <= len / 2; k++) {
			if(len % k == 0) {
				if(k <= len / k) {
					R = k;
					C = len / k;
				}
				else break all;
			}
		}

		int idx = 0;
		char[][] board = new char[R][C];
		for(int j = 0; j < C; j++) {
			for(int i = 0; i < R; i++) {
				board[i][j] = input.charAt(idx++);
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				sb.append(board[i][j]);
			}
		}

		System.out.print(sb);
	}
}