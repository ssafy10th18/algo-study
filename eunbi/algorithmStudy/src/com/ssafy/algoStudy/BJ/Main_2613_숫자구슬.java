package com.ssafy.algoStudy.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N개의 숫자 구슬. 위치 변경 불가능
 * M개의 그룹으로 나누었을 때 각각의 그룹의 합 중 최댓값이 최소가 되도록 해야 한다.
 * @author dnpfk
 *
 */
public class Main_2613_숫자구슬 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, min, max, result;
	static int[] ball, numOfBall;

	public static void main(String[] args) throws IOException{
		/**
		 * [입력]
		 * 1. 구슬의 개수 N, 그룹의 수 M (1<=M<=N<=300)
		 * 2. 구슬의 숫자 (100이하 자연수)
		 */
		init();
		/**
		 * [출력]
		 * 1. 그룹의 합 중 최댓값이 최소일 때의 값
		 * 2. 각 그룹을 구성하는 구슬의 개수를 왼쪽부터 순서대로 출력
		 * 둘 이상이면 하나만 출력
		 */
		System.out.println(sb);
	}
	
	static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		min = Integer.MAX_VALUE;
		ball = new int[N];
		numOfBall = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int n = 0; n < N; n++) {
			ball[n] = Integer.parseInt(st.nextToken());
			min = Math.min(min, ball[n]);
			max += ball[n];
		}
		Binarysearch();
	}
	
	static void Binarysearch() {
		int start = min;//9
		int end = max;//44
		int mid = start + (end - start)/2;//기준최대값
		
		while(true) {
			int group = 0;
			int sum = 0;
			int idx = 0;
			for(int i = 0; i < N; i++) {
				sum += ball[i];
				//최댓값을 넘어가면
				if(sum > mid) {
					group++;//그룹개수++
					if(idx < M) {//범위 내일 때만 저장해줌
						numOfBall[idx] = group;
					}
					sum = 0;
					--i;
				}
			}
			//그룹 개수가 M개보다 작으면 == 그룹개수를 늘려야 하기 때문에 mid값을 낮춘다.
			//그룹 개수가 M개이면 더 작은 최댓값이 있을 수 있으므로 mid값을 낮춘다.
			if(group <= M) {
				end = mid;
				mid = start + (end - start)/2;
				//만약 값이 같으면 최댓값 중 최솟값을 찾은 것이므로 값 저장 후 break
				if(start == mid) {
					result = mid;
					sb.append(result).append('\n');
					getResult();
					break;
				}
			}
			//그룹 개수가 M개보다 많으면 == 그룹 개수를 줄여야 하기 때문에 mid값을 올린다.
			else if(group > M){
				start = mid;
				mid = start + (end - start)/2;
				//만약 값이 같으면 최댓값 중 최솟값을 찾은 것이므로 값 저장 후 break
				if(end == mid) {
					result = mid;
					sb.append(result).append('\n');
					getResult();
					break;
				}
			}
		}
	}
	
	static void getResult() {
		for(int a : numOfBall) {
			sb.append(a).append(" ");
		}
	}
}
