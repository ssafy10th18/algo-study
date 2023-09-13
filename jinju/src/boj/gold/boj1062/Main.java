package boj.gold.boj1062;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	private static int N;
	private static int K;
	private static int result = 0;

	private static int[] items;
	private static String[] words;
	private static Map<Character, Integer> indexes = new HashMap<>();
	private static Set<Character> set = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");

		N = Integer.parseInt(temp[0]);
		K = Integer.parseInt(temp[1]);
		words = new String[N];

		K = K - 5;
		if (K < 0) { // a, n, t, i, c 을 모두 학습할 수 없다면
			System.out.print(0);
			return;
		} else if (K == 26) {
            System.out.println(N); // 모든 알파벳을 가르칠 수 있는 경우
            return;
        }

		for (int i = 0; i < N; i++) {
			words[i] = br.readLine();
			
			for (int j = 4; j < words[i].length() - 4; j++) {
				char c = words[i].charAt(j);
				
				if (c == 'a' || c == 'n' || c == 't'
						|| c == 'i' || c == 'c') { // a, n, t, i, c 제외
					continue;
				}
				
				set.add(c);
			}
		}
		
		int idx = 0;
		for(Character c : set) {
			indexes.put(c, idx++);
		}

		// a, n, t, i, c은 고려하지 않는다.
		items = new int['z' - 'a' + 1 - 5];

		for (int i = items.length - 1; i >= items.length - K; i--) {
			items[i] = 1; // 뽑아야 하는 수만큼 초기화 시킨다.
		}

		// 뽑은 알파벳에 따라 몇 개의 단어를 읽을 수 있는지 체크한다.
		do {
			result = Math.max(countWords(), result);
		} while (np(items));

		System.out.println(result);
	}

	private static int countWords() {
		// 지금까지 선택한 알파벳으로 읽을 수 있는 단어의 수를 센다.
		int count = 0;
		all : for(String word : words) {
			for (int i = 4; i < word.length() - 4; i++) {
				char c = word.charAt(i);
				
				if (c == 'a' || c == 'n' || c == 't'
						|| c == 'i' || c == 'c') { // a, n, t, i, c 제외
					continue;
				}

				//참조 방식 수정
				if(items[indexes.get(c)] == 0) {
					continue all; //읽을 수 없는 단어
				}
			}
			count++;
		}
		
		return count;
	}

	private static boolean np(int[] count) {
		int swapIdx = -1;
		for (int i = 1; i < count.length; i++) {
			if (count[i - 1] < count[i])
				swapIdx = i - 1;
		}
		if (swapIdx == -1)
			return false;

		int largerIdx = -1;
		for (int j = 1; j < count.length; j++) {
			if (count[swapIdx] < count[j])
				largerIdx = j;
		}

		swap(count, swapIdx, largerIdx);

		int j = count.length - 1;
		for (int i = swapIdx + 1; i < j; i++) {
			swap(count, i, j);
			j--;
		}

		return true;
	}

	private static void swap(int[] count, int swapIdx, int largerIdx) {
		int temp = count[swapIdx];
		count[swapIdx] = count[largerIdx];
		count[largerIdx] = temp;
	}
}
