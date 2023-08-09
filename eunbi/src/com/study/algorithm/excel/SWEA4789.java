package com.study.algorithm.excel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA4789 {
	static String str;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			str = br.readLine();
			
			//전체 인원수
			int total = str.length();
			//전체가 1명인데 0명일 때 박수치는 사람이 0명이면 1명 고용, 1명이면 0명 고용
			int result = str.charAt(0) - '0' == 0 ? 1 : 0;
			//박수치는 사람 0명일 때 박수치는 사람.
			int clapper = str.charAt(0) - '0' + result;
			
			if(str.length() == 1) {}
			else {
				for(int i = 1; i < str.length(); i++) {
					//박수치는 사람이 전체 인원보다 많거나 같으면 for문 종료
					if(clapper >= total) {
						break;
					}
					//박수치는 사람이 i명 이상이면 해당 수만큼의 사람이 박수를 침
					if(clapper >= i) {
						clapper += (str.charAt(i) - '0');
					}
					//박수치는 사람이 i명 미만이면 모자라는 수만큼 고용 후 박수치는 사람 수에 합해주기
					else {
						result += (i - clapper);
						clapper += (result + (str.charAt(i) - '0'));
					}
				}
			}
			if(clapper < total)
				result += (total - clapper);
			sb.append(result).append('\n');
		}
		System.out.println(sb);
	}

}
