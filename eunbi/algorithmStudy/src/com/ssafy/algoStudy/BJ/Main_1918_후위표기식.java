package com.ssafy.algoStudy.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Main_1918_후위표기식 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static String calc;
	static Queue<Character> str, alpha, operator;
	static Map<Character, Integer> priority = new HashMap<>();
	
	
	public static void main(String[] args) throws IOException {
		init();
	}
	
	static void init() throws IOException{
		calc = br.readLine();
		str = new ArrayDeque<>();
		alpha = new ArrayDeque<>();
		operator = new ArrayDeque<>();
		
		for(int i = 0; i < calc.length(); i++) {
			char c = calc.charAt(i);
			str.add(c);
		}
		
		priority.put('+', 1);
		priority.put('-', 1);
		priority.put('*', 2);
		priority.put('/', 2);
	}
	
	static void run() {
		while(!str.isEmpty()) {
			char c = str.poll(); //뽑은 거
			//알파벳일 때
			if('A' <= c && c <= 'Z') {
				
			}
			//열린괄호일 때
			//재귀로
			
			//닫힌 괄호일 때
			
			//연산자일 때
			else {
				//queue.peek에 담긴 연산자의 우선 순위와 같거나 보다 작으면 담겨있는 거 다 뽑기
				
				//queue.peek에 담긴 연산자의 우선 순위보다 크면 넣기
			}
		}
	}
}