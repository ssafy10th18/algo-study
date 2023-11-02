package com.ssafy.algoStudy.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

/*
 * 
 */
public class Main_7662_이중우선순위큐 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int k, calcCnt;
	static TreeMap<Integer, Integer> map;

	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			init();
			run();
		}
		System.out.println(sb);
	}
	
	static void init() throws IOException{
		calcCnt = Integer.parseInt(br.readLine());
		map = new TreeMap<>();
	}
	
	static void run() throws IOException{
		for(int i = 0; i < calcCnt; i++) {
			st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			int num = Integer.parseInt(st.nextToken());
			
			if(order.equals("I")) {
				//map.getOrDefault(num, 0) :num이 없다면 0을 반환
				map.put(num, map.getOrDefault(num, 0) + 1); //이미 있는 key이면 값을 +1 해서 넣어준다.
			}
			else {
				//size가 0이면 연산 무시
				if(map.size() == 0) continue;
				int key;
				//1일 땐 최댓값 삭제
				if(num == 1) {
					key = map.lastKey();
				}
				//0일 땐 최솟값 삭제
				else {
					key = map.firstKey();
				}

				//해당 key의 값 -1 한 후 갱신
				//-1 하기 이전의 값이 1이라면
				if( map.put(key, map.get(key)-1) == 1) {
					//아예 제거해주기
					map.remove(key);
				}
			}
		}
		
		if(map.size() == 0) {
			sb.append("EMPTY" + '\n');
		}else {
			sb.append(map.lastKey() + " " + map.firstKey() + '\n');
		}
	}

}
