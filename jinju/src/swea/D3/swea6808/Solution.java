package swea.D3.swea6808;

import java.io.*;
import java.util.*;

public class Solution {

	private static final int SIZE = 9;
	private static final int MAX = 362_880;

	private static int[] kyuCards;
	private static int[] inCards;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {

			String[] input = br.readLine().split(" ");
			
			kyuCards = new int[SIZE];
			inCards = new int[SIZE];

			boolean[] used = new boolean[SIZE * 2 + 1];
			
			for (int i = 0; i < SIZE; i++) {
				kyuCards[i] = Integer.parseInt(input[i]);
				used[kyuCards[i]] = true;
			}
			
			int idx = 0;
			for(int i = 1; i <= SIZE * 2; i++) {
				if(!used[i]) {
					inCards[idx++] = i;
				}
			}
			
			Arrays.sort(inCards);
			
			//초기 상태 계산
			int count = 0;
			count += calculate();

			//마지막 순열이 될 때까지 반복
			while(nextPermutation(inCards)) {
				count += calculate();
			}

			sb.append("#").append(test_case).append(" ").append(count).append(" ")
				.append(MAX - count).append("\n");
		}

		System.out.print(sb);
	}

	private static int calculate() {
		int kScore = 0;
		int iScore = 0;
		
		for(int i = 0; i < SIZE; i++) {
			if(kyuCards[i] > inCards[i]) {
				kScore += (kyuCards[i] + inCards[i]);
			}
			else {
				iScore += (kyuCards[i] + inCards[i]);
			}
		}
		
		if(kScore > iScore) {
			return 1;
		}
		
		return 0;
	}

	public static boolean nextPermutation(int[] target) {

		int swapIdx = -1;
		for (int i = 1; i < target.length; i++) {
			if (target[i - 1] < target[i])
				swapIdx = i - 1;
		}

		if (swapIdx == -1)
			return false;

		int largerIdx = -1;
		for (int j = 1; j < target.length; j++) {
			if (target[swapIdx] < target[j])
				largerIdx = j;
		}

		swap(target, swapIdx, largerIdx);

		int j = target.length - 1;
		for (int i = swapIdx + 1; i < j; i++) {
			swap(target, i, j);
			j--;
		}
		
		inCards = target;

		return true;
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
