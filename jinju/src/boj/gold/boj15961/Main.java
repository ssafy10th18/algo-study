package boj.gold.boj15961;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");

		int N = Integer.parseInt(temp[0]); // 벨트에 놓인 접시의 수
		int d = Integer.parseInt(temp[1]); // 초밥의 가짓수
		int k = Integer.parseInt(temp[2]); // 연속해서 먹는 접시 수
		int c = Integer.parseInt(temp[3]); // 쿠폰번호

		int[] dishes = new int[N]; // 벨트 위에 놓인 모든 접시
		int[] selections = new int[d + 1]; //중복 접시 세기, 0은 사용하지 않음
		Set<Integer> set = new HashSet<>(); // 선택된 접시의 가짓수를 모은다.

		for (int i = 0; i < N; i++) {
			int v = Integer.parseInt(br.readLine());
			dishes[i] = v;
		}

		int p = 0;
		int q = p + k - 1;

		for (int i = 0; i <= q; i++) {
			set.add(dishes[i]); // 초기 상태
			selections[dishes[i]]++;
		}

		int initP = p;
		int initQ = q;

		int currMax = -1;
		do {
			// 현재까지의 최대 가짓수 계산
			currMax = Math.max(set.size(), currMax);

			if (!set.contains(c)) {
				currMax = Math.max(set.size() + 1, currMax); //레일에 초밥이 없어도 지급됨
			}
			
			// 지나간 접시 제거
			selections[dishes[p]]--;
			if(selections[dishes[p]] == 0) { //중복 접시를 모두 제거했다면
				set.remove(dishes[p]);
			}
			
			if (p + 1 >= N) { //인덱스 밀기
				p = 0;
			} else p++;

			// 새 접시 추가
			if (q + 1 >= N) {
				q = 0;
			} else q++;
			
			selections[dishes[q]]++;
			set.add(dishes[q]);
		} while (p != initP || q != initQ);
		// 원점 회귀할 때까지 반복한다.
		
		System.out.println(currMax);
	}

}
