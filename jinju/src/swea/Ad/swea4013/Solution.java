package swea.Ad.swea4013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	private static int[] pointers;
	private static int[][] magnets;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {

			int K = Integer.parseInt(br.readLine());

			pointers = new int[4]; // 빨간 화살표가 가리키는 날의 번호
			magnets = new int[4][8];

			StringTokenizer st;
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 8; j++) {
					magnets[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < K; i++) {
				String[] temp = br.readLine().split(" ");

				int num = Integer.parseInt(temp[0]) - 1;
				int dir = Integer.parseInt(temp[1]);

				rotate(num, dir);
			}

			int answer = 0;
			int[] score = {1, 2, 4, 8};
			for (int i = 0; i < magnets.length; i++) {
				answer += magnets[i][pointers[i]] * score[i];
			}

			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}

		System.out.print(sb);
	}

	private static void rotate(int num, int dir) {
		int[] newPointers = new int[4];
		
		for(int n = num; n > 0; n--) { //num보다 번호가 작은 톱니바퀴를 돌린다.
			int thisPos = (pointers[n] + 2) % 8;
			int nextPos = (8 - thisPos) % 8;
			
			if (magnets[n][thisPos] != magnets[n - 1][nextPos]) { // 자성이 다르다면
				// 다음 자석도 dir과 반대 방향으로 회전한다.
				newPointers[n - 1] = (pointers[n - 1] + dir + 8) % 8;
			} else break;
		}
		
		for(int n = num; n < 3; n++) { //num보다 번호가 큰 톱니바퀴를 돌린다.
			int thisPos = (pointers[n] + 2) % 8;
			int nextPos = (8 - thisPos) % 8;
			
			if (magnets[n][thisPos] != magnets[n + 1][nextPos]) { // 자성이 다르다면
				// 다음 자석도 dir과 반대 방향으로 회전한다.
				newPointers[n + 1] = (pointers[n + 1] + dir + 8) % 8;
			} else break;
		}

		newPointers[num] = (pointers[num] - dir + 8) % 8;
		for(int i = 0; i < 4; i++) {
			pointers[i] = newPointers[i];
		}
	}

}
