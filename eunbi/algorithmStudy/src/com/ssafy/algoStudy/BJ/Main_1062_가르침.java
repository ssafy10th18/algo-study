package com.ssafy.algoStudy.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * K개의 글자를 배워서 이것으로만 이루어진 단어만을 읽을 수 있을 때
 * 어떤 K개의 글자를 가르쳐야 학생들이 읽을 수 있는 단어의 개수가 최대가 되는지 구하지
 * 남극언어의 모든 단어는 "anta"로 시작되고, "tica"로 끝난다.
 * @author SSAFY
 *
 */
public class Main_1062_가르침 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, K, result, wholeNum;
	static char[] alphabet = new char[26];
	static List<String> word;
	static boolean[] selectedAlphabet;
	static boolean[] selectWord;

	public static void main(String[] args) throws IOException{
		/**
		 * [입력]
		 * 1. 단어의 개수 N, K (1 <= N <= 50, 0 <= K <= 26)
		 * 2~N. 남극 언어의 단어 (8 <= 길이 <= 15)
		 */
		init();
		/**
		 * [출력]
		 * 단어 개수의 최댓값
		 */
		System.out.println(result);
	}
	
	static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		result = 0;
		word = new ArrayList<>();
		selectedAlphabet = new boolean[26];
		
		int idx = 0;
		for(int i = 0; i < 26; i++) {
			char alpha = (char)(97 + i);
			if(alpha == 'a' || alpha == 'n' || alpha == 't' || alpha == 'i' || alpha == 'c')
				continue;
			alphabet[idx] = (char)(97 + i);
			idx++;
		}
	
		//K가 5보다 작으면 0이 답
		if(K < 5) return;
		
		//실제 필요한 글자 개수
		K -= 5;
		
		//실제 확인해야 할 곳만 저장
		for(int n = 0; n < N; n++) {
			StringBuilder str = new StringBuilder(br.readLine());
			str.delete(0, 4);
			str.delete(str.length() - 4, str.length());
			for(int i = 0; i < str.length(); i++) {
				char temp = str.charAt(i);
				
				if(temp == 'a' || temp == 'n' || temp == 't' || temp == 'i' || temp == 'c'){
					str.deleteCharAt(i);
					i--;
				}	
			}
			word.add(str.toString());
		}
		
		for(int i = 0; i < 21; i++) {
			
			
			selectedAlphabet[i] = true;
			DFS(i, 1);
			selectedAlphabet[i] = false;
		}
	}
	
	static void DFS(int start, int cnt) {
		List<Character> temp = new ArrayList<>();
		
		//전부 다 
		if(cnt == K) {
			search();
			return;
		}
		
		for(int i = start + 1; i < 21; i++) {
			if(!selectedAlphabet[i]) {
				selectedAlphabet[i] = true;
				DFS(i, cnt + 1);
				selectedAlphabet[i] = false;
			}
		}
	}
	
	static void search() {
		int cnt = 0;
		for(int i = 0; i < word.size(); i++) {
			String str = word.get(i);
			for(int j = 0; j < str.length(); j++) {
				boolean have = false;
				for(int x = 0; x < 21; x++) {
					if(selectedAlphabet[x]) {
						//포함하면
						if(alphabet[x] == str.charAt(j))
							have = true;
					}
				}
				if(have)
					cnt++;
			}
		}
		result = Math.max(result, cnt);
	}
}