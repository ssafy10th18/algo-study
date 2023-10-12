package com.ssafy.algoStudy.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_14891_톱니바퀴 {

	static ArrayList<Integer>[] whole;
	static boolean[] canSpin;
	static int[] dirSpin;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
			
			whole = new ArrayList[4];
			//0부터 3번자석
			for(int i = 0; i < 4; i++) {
				whole[i] = new ArrayList<Integer>();
			}
			
			String str = br.readLine();
			for(int i = 0; i < 8; i++) {
				whole[0].add(str.charAt(i) - '0');
			}
			String str1 = br.readLine();
			for(int i = 0; i < 8; i++) {
				whole[1].add(str1.charAt(i) - '0');
			}
			String str2 = br.readLine();
			for(int i = 0; i < 8; i++) {
				whole[2].add(str2.charAt(i) - '0');
			}
			String str3 = br.readLine();
			for(int i = 0; i < 8; i++) {
				whole[3].add(str3.charAt(i) - '0');
			}
			
			int K = Integer.parseInt(br.readLine());
			
			for(int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				int numOfMagnet = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				canSpin = new boolean[4];
				dirSpin = new int[4];
				spin(numOfMagnet - 1, dir);
			}
			
			int result = 0;
			result += (whole[0].get(0) == 0 ? 0 : 1);
			result += (whole[1].get(0) == 0 ? 0 : 2);
			result += (whole[2].get(0) == 0 ? 0 : 4);
			result += (whole[3].get(0) == 0 ? 0 : 8);
			
			sb.append(result).append('\n');

		System.out.print(sb);
	}
	
	public static void spin(int numOfMagnet, int dir) {
		canSpin[numOfMagnet] = true;
		dirSpin[numOfMagnet] = dir;
		//앞번호자석 중 연결된 자석 찾기
		for(int i = numOfMagnet - 1; i >= 0; i--) {
			if(whole[i].get(2) != whole[i + 1].get(6)) {
				canSpin[i] = true;
				dirSpin[i] = dirSpin[i + 1] * -1;
			}
		}
		//뒷번호자석 중 연결된 자석 찾기
		for(int i = numOfMagnet + 1; i < 4; i++) {
			if(whole[i].get(6) != whole[i - 1].get(2)) {
				canSpin[i] = true;
				dirSpin[i] = dirSpin[i - 1] * -1;
			}
		}
		
		for(int i = 0; i < 4; i++) {
			if(canSpin[i])
				change(i, dirSpin[i]);
		}
		
		return;
	}
	
	public static void change(int numOfMagnet, int dir) {
		//시계방향
		if(dir == 1) {
			int temp = whole[numOfMagnet].get(7);
			for(int i = 7; i > 0; i--) {
				whole[numOfMagnet].set(i, whole[numOfMagnet].get(i - 1));
			}
			whole[numOfMagnet].set(0, temp);
		}
		//반시계방향
		else if(dir == -1) {
			int temp = whole[numOfMagnet].get(0);
			for(int i = 1; i < 8; i++) {
				whole[numOfMagnet].set(i - 1, whole[numOfMagnet].get(i));
			}
			whole[numOfMagnet].set(7, temp);
		}
		
		return;
	}
}
