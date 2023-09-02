package com.study.algorithm.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * AC언어
 * 1. R(뒤집기) : 배열에 있는 수의 순서를 뒤집는 함수
 * 2. D(버리기) : 첫 번째 수를 버리는 함수, 배열이 비어있을 때 D를 사용한 경우에는 에러가 발생.
 * 함수는 조합해서 한 번에 사용할 수 있다.
 * 배열의 초깃값과 수행할 함수가 주어졌을 때, 최종 결과를 구하는 프로그램 작성
 * @author SSAFY
 *
 */

public class Main_5430_AC {
	static int n;
	static String function;
	static List<Integer> list;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		/**
		 * [입력]
		 * 1. 테스트케이스 T (최대 100)
		 * 2. 함수 p (1 <= p의 길이 <= 100,000)
		 * 3. 배열에 들어있는 수의 개수 n (0 <= n <= 100,000)
		 * 4. [x1,...,xn]의 형태로 배열에 들어있는 정수가 주어진다. (1 <= x <= 100)
		 * p의 길이의 합과 n의 합은 70만을 넘지 않는다.
		 */
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; ++t) {
			//함수 p
			function = br.readLine();
			//배열에 들어있는 수
			n = Integer.parseInt(br.readLine());
			list = new ArrayList<>();

			//입력받는 string에서 숫자만 추출
			String[] array = br.readLine().replace("[", "").replace("]", "").split(",");
			//list
			for(int i = 0; i < n; ++i) {
				list.add(Integer.parseInt(array[i]));
			}
			
			boolean error = false;
			int cntR = 0;
			//함수
			for(int i = 0; i < function.length(); i++) {
				//R이면 수의 순서 뒤집기
				//R 짝수개가 붙어있으면 뒤집기를 수행하지 않아도 됨.
				if(function.charAt(i) == 'R') {
					//R의 개수 세기
					cntR++;
				}
				//D이면 첫 번째 수 버리기
				else {
					//R이 홀수이면 뒤집기 수행
					if(cntR % 2 == 1) {
						int time = list.size();
						for(int j = 0; j < time; ++j) {
							int last = list.remove(list.size() - 1);
							list.add(j, last);
						}
						//뒤집기 수행 후 초기화
						cntR = 0;
					}else if(cntR % 2 == 0){cntR = 0;}
					
					//배열이 비어있으면
					if(list.isEmpty()) {
						//에러 출력 후 빠져나가기
						sb.append("error").append('\n');
						error = true;
						break;
					}
					//비어있지 않으면
					//첫 번째 요소 삭제
					list.remove(0);
				}
			}
			if(!error) {
				for(int i = 0; i < list.size(); ++i) {
					//처음요소
					if(i == 0) {
						sb.append('[').append(list.get(i));
					}
					//마지막 요소
					else if(i == list.size() - 1) {
						sb.append(',').append(list.get(i)).append(']').append('\n');
					}
					//나머지
					else {
						sb.append(',').append(list.get(i));
					}
				}
			}
		}
		/**
		 * [출력]
		 * 수행한 결과 출력. 에러 발생 시 error 출력
		 */
		System.out.println(sb);
	}
}
