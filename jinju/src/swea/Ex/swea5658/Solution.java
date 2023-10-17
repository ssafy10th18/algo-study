package swea.Ex.swea5658;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.TreeSet;

public class Solution {

	private static int N;
	private static int K;
	
	private static TreeSet<Integer> set;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
			String[] temp = br.readLine().split(" ");
			set = new TreeSet<>(Collections.reverseOrder());
			
			N = Integer.parseInt(temp[0]);
			K = Integer.parseInt(temp[1]);
			
			String input = br.readLine();
			makePassword(input);

			Object[] result = set.toArray();
			int answer = (int) result[K - 1];
			
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}

	private static void makePassword(String input) {
		int digit = (N / 4);
		
		int pos = 0; //원점에서 시작
		while(true) {
			int pwdCount = 0;
			for(int i = 0; i < 4; i++) { //네 변을 조사
				String pwd = "";
				int start = i * digit;
				for(int j = start ; j < start + digit; j++) { //(N / 4)자리 비밀번호 생성
					int idx = (pos + j) % N;
					pwd += input.charAt(idx);
				}
				int password = Integer.parseInt(pwd, 16);
				
				if(set.contains(password)) pwdCount++;
				
				set.add(password);
			}
			
			pos = ((pos - 1) + N) % N; //회전 후 탐색
			
			if(pwdCount == 4) { break; } // 네 변의 비밀번호 모두 이미 생성된 적 있다면
		}
	}

}
