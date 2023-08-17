package boj.gold.boj20187;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static final int NONE = -1;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = 1;

		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {

			int K = Integer.parseInt(br.readLine());
			char[] dirs = new char[2 * K];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 2 * K; i++) {
				dirs[i] = st.nextToken().charAt(0);
			}

			int H = Integer.parseInt(br.readLine());

			// 구멍이 뚫린 손수건 정보를 담을 배열
			int[][] result = new int[2 * K][2 * K];
			
			for(int i = 0; i < 2 * K; i++) {
				for(int j = 0; j < 2 * K; j++) {
					result[i][j] = NONE; //아무 구멍도 뚫리지 않았음을 표시
				}
			}

			// 순서에 따라 손수건을 접는다. 접힌 곳의 row와 col을 구한다.
			int startR = 0; // 처음 구멍을 뚫을 위치의 row
			int startC = 0; // 처음 구멍을 뚫을 위치의 col
			if (H == 1) {
				startR = 0;
				startC = 1;
			} else if (H == 2) {
				startR = 1;
				startC = 0;
			} else if (H == 3) {
				startR = 1;
				startC = 1;
			}

			for (char d : dirs) {
				if (d == 'R') {
					startC = startC + (2 * K - 1)- startC;
				} else if (d == 'L') {
					startC = 0 + (2 * K - 1 - startC);
				} else if (d == 'U') {
					startR = 0 + (2 * K - 1 - startR);
				} else if (d == 'D') {
					startR = startR + (2 * K - 1) - startR;
				}
			}
			
			result[startR][startC] = H; //가장 처음 위치에 H번 구멍을 뚫는다.
			
			//역순으로 펼치면서, 규칙에 맞게 구멍이 뚫린 곳을 표시한다.
			for (int i = 2 * K - 1; i >= 0; i--) {
				System.out.println(startR + ", " + startC);
				char d = dirs[i];
				if (d == 'R') {
					startC = 0 + (2 * K - 1 - startC);
				} else if (d == 'L') {
					startC = startC + (2 * K - 1)- startC;
				} else if (d == 'U') {
					startR = startR + (2 * K - 1) - startR;
				} else if (d == 'D') {
					startR = startR - (2 * K - 1 - startR);
				}
				result[startR][startC] = H;
			}
			
			sb.append("#").append(tc).append(" ");
		}

		System.out.println(sb);
	}

}

