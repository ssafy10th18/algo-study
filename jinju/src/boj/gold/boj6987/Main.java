package boj.gold.boj6987;

import java.io.*;
import java.util.*;

public class Main {

	private static final int TEAM = 6;
	private static final int COUNT = 3;

	private static final int WIN = 0;
	private static final int DRAW = 1;
	private static final int LOSE = 2;

	private static final int MATCH_COUNT = 15;
	
	private static int result;
	private static List<String> games;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < 4; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			//games에 대결 정보를 미리 생성한다.
			games = new ArrayList<>();
			
			for(int i = 0; i < TEAM; i++) {
				for(int j = i + 1; j < TEAM; j++) {
					games.add(i + "" + j);
				}
			}
			
			int[][] matches = new int[TEAM][COUNT];
			for (int i = 0; i < TEAM; i++) {
				for (int j = 0; j < COUNT; j++) {
					matches[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			result = 0;
			backTracking(matches, 0);

			sb.append(result).append(" ");
		}

		System.out.println(sb);
	}

	private static void backTracking(int[][] matches, int depth) {
		if (MATCH_COUNT == depth) {
			if(!isValidResult(matches)) {
				return;
			}
			result = 1;
			return;
		}
		
		int curr = games.get(depth).charAt(0) - '0';
		int rival = games.get(depth).charAt(1) - '0';

		// 승부 결과 생성
		if(matches[curr][WIN] > 0 && matches[rival][LOSE] > 0) {
			// 1.승 & 패
			matches[curr][WIN]--;
			matches[rival][LOSE]--;
			backTracking(matches, depth + 1);
			matches[curr][WIN]++;
			matches[rival][LOSE]++;
		}

		if(matches[curr][DRAW] > 0 && matches[rival][DRAW] > 0) {
			// 2.무 & 무
			matches[curr][DRAW]--;
			matches[rival][DRAW]--;
			backTracking(matches, depth + 1);
			matches[curr][DRAW]++;
			matches[rival][DRAW]++;
		}

		if(matches[curr][LOSE] > 0 && matches[rival][WIN] > 0) {
			// 3.패 & 승
			matches[curr][LOSE]--;
			matches[rival][WIN]--;
			backTracking(matches, depth + 1);
			matches[curr][LOSE]++;
			matches[rival][WIN]++;
		}

		return;
	}

	private static boolean isValidResult(int[][] matches) {
		for (int i = 0; i < TEAM; i++) {
			for (int j = 0; j < COUNT; j++) {
				if(matches[i][j] != 0) return false;
			}
		}
		
		return true;
	}

}
