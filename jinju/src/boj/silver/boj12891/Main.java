package boj.silver.boj12891;

import java.io.*;
import java.util.*;

public class Main {

	private static int count = 0;

	private static Map<Character, Integer> map = new HashMap<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");

		map.put('A', 0);
		map.put('C', 1);
		map.put('G', 2);
		map.put('T', 3);

		int S = Integer.parseInt(temp[0]);
		int P = Integer.parseInt(temp[1]);

		int[] answer = new int[4];
		int[] result = new int[4];

		String word = br.readLine();

		temp = br.readLine().split(" ");
		for(int i = 0; i < 4; i++) {
			answer[i] = Integer.parseInt(temp[i]);
		}

		for(int i = 0; i < P; i++) { //첫 구간 초기화
			char curr = word.charAt(i);
			result[map.get(curr)]++;
		}

        if(isSameArr(result, answer)) {
			count++;
		}

		int i = -1;
		char prev = ' ';
		char curr = ' ';
		for(int j = P; j < S; j++) { //현재 부분문자열의 끝을 가리킴
			i = j - P; //이전 부분문자열의 시작점

			curr = word.charAt(j);
			prev = word.charAt(i);
			result[map.get(prev)]--;
			result[map.get(curr)]++;

			if(isSameArr(result, answer)) {
				count++;
			}
		}

		System.out.println(count);
	}

	private static boolean isSameArr(int[] result, int[] answer) {
		for(int i = 0; i < result.length; i++) {
			if(result[i] < answer[i]) {
				return false;
			}
		}
		return true;
	}
}