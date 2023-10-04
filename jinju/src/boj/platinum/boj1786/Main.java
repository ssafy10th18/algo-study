package boj.platinum.boj1786;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] text = br.readLine().toCharArray();
		char[] pattern = br.readLine().toCharArray();
		
		int tLen = text.length;
		int pLen = pattern.length;
		
		//1. 패턴 문자열에 대해서 부분 일치 테이블 생성
		int[] pi = new int[pLen];
		for(int i = 1, j = 0; i < pLen; i++) { //i : 접미사 포인터 (1부터 시작)
			while(j > 0 && (pattern[i] != pattern[j])) {
				j = pi[j - 1]; //pi값을 따라 패턴 포인터 밀기
			}
			
			if(pattern[i] == pattern[j]) { pi[i] = ++j; } //대칭을 이룰 경우
			else pi[i] = 0; //대칭이 아닌 경우
		}
		
		int count = 0;
		List<Integer> list = new ArrayList<>();
		//i : 텍스트 포인터, j : 패턴 포인터
		for(int i = 0, j = 0; i < tLen; i++) {
			while(j > 0 && (text[i] != pattern[j])) {
				j = pi[j - 1]; //pi값을 따라 패턴 포인터 밀기
			}
			
			if(text[i] == pattern[j]) { //패턴과 원본 문자가 일치한다면
				if(j == pLen - 1) { //j가 패턴의 마지막 인덱스라면
					count++;
					list.add(i - j);
					j = pi[j]; //pi값을 따라 패턴 포인터 밀기
				}
				else { j++; } //패턴의 다음 문자 조사
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(count).append("\n");
		if(count > 0) {
			for(int i : list) {
				sb.append(i + 1).append(" ");
			}
		}
		
		System.out.print(sb);
	}

}
