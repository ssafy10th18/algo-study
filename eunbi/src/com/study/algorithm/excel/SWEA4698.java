package com.study.algorithm.excel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * D가 주어질 때 D를 포함한 소수가 특별한 소수
 * A이상 B이하의 수 중에서 특별한 소수인 것들의 개수를 구하기
 * @author SSAFY
 *
 */
public class SWEA4698 {
	static int D, result;
	static String A, B;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			A = st.nextToken();
			B = st.nextToken();
			result = 0;
			
			System.out.println(strToInt(A));
		}
	}
	
	public void getSpecial(int D, int A, int B) {
		for(int i = A; i <= B; i++) {
			boolean isPrime = false;
			for(int j = 2; j <= Math.sqrt(i); j++) {
				if(i % j == 0)
					isPrime = true;
				if(isPrime) {
					//String 변환
				}
			}
			
		}
	}
	// 소수면 Stirng 변환 후 해당 숫자 가지고 있는지 확인
	
	public boolean hasD(int num) {
		
		return true;
	}
	
	public static int strToInt(String str) {
		int length = str.length() - 1;
		int num = 0;
		for(int i = 0; i <= length; i++) {
			num += (str.charAt(i) - '0')*Math.pow(10, length - i);
		}
		return num;
	}

}
