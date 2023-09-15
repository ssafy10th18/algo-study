package com.ssafy.algoStudy.excel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA4047 {
	static int[] S, D, H, C;
	static int error;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			
			S = new int[14];
			D = new int[14];
			H = new int[14];
			C = new int[14];
			error = 0;
			
			String information = br.readLine();
			for(int i = 0; i < information.length(); i += 3) {
				char nowChar = information.charAt(i);
				char firstNum = information.charAt(i + 1);
				char secondNum = information.charAt(i + 2);
				
				Card(nowChar, firstNum, secondNum);
				
				if(error == 1) {
					sb.append("ERROR").append('\n');
					break;
				}
			}
			if(error != 1) {
				sb.append(cntCard(S)).append(" ")
				  .append(cntCard(D)).append(" ")
				  .append(cntCard(H)).append(" ")
				  .append(cntCard(C)).append('\n');
			}
		}
		System.out.println(sb);
	}
	
	public static void Card(char nowChar, char firstNum, char secondNum) {
		int cardNum;
		if(firstNum - '0' == 0)
			cardNum = secondNum - '0';
		else
			cardNum = 10 + (secondNum - '0');
		
		switch(nowChar) {
		case 'S':
			if(S[cardNum] == 1)
				error = 1;
			else
				S[cardNum] = 1;
			break;
		case 'D':
			if(D[cardNum] == 1)
				error = 1;
			else
				D[cardNum] = 1;
			break;
		case 'H':
			if(H[cardNum] == 1)
				error = 1;
			else
				H[cardNum] = 1;
			break;
		case 'C':
			if(C[cardNum] == 1)
				error = 1;
			else
				C[cardNum] = 1;
			break;
		}
	}

	public static int cntCard(int[] arr) {
		int cnt = 0;
		for(int i = 1; i < 14; i++) {
			if(arr[i] == 0)
				cnt++;
		}
		return cnt;
	}
}

/**
 * 1. 한 덱의 카드 == 스페이드, 다이아몬드, 하트, 클로버 별로 A, 2~10, J, Q, K 총 13장씩 모두 52장
 * 2. A = 1, J = 11, Q = 12, K = 13
 * 3. 1~13의 카드
 * 4. 몇 개의 카드가 더 필요한지 알고 싶음
 * 5. 겹치는 카드가 있다면 오류를 출력
 * 
 * [입력]
 * T
 * S(1 <= |S| <= 1000) - S는 각각 3자리로 표현되는 카드들의 정보를 붙여서 만든 하나의 문자열, 각 카드는 TXY 꼴로 표현됨.
 * T는 카드의 무늬(S, D, H, C), XY는 카드의 숫자 (01 ~ 13)
 * 
 * [출력]
 * S, D, H, C 순소러 몇 장의 카드가 부족한지 출력
 * 겹치는 카드가 있다면 문자열 ERROR를 출력한다
 * 
 *  
*/