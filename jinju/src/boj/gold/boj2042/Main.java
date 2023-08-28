package boj.gold.boj2042;

import java.io.*;

public class Main {

	private static long[] tree;
	private static long[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] temp = br.readLine().split(" ");
		
		int N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);
		int K = Integer.parseInt(temp[2]);
		
		arr = new long[N + 1]; //0은 사용하지 않음
		
		for(int i = 1; i <= N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		tree = new long[4 * N];
		
		init(1, N, 1); //1부터 N까지 부분합을 초기화한다.
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M + K; i++) {
			temp = br.readLine().split(" ");
			
			int a = Integer.parseInt(temp[0]);
			int b = Integer.parseInt(temp[1]);
			long c = Long.parseLong(temp[2]);
			long diff = 0;
			
			if(a == 1) {
				diff = c - arr[b];
				arr[b] = c;
				update(1, N, 1, b, diff);
			} else if(a == 2) {
				long result = sum(1, N, 1, b, (int)c); //1부터 N번 노드까지 탐색, b~c 구간합 계산
				sb.append(result).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	/**
	 * @param start 시작 인덱스
	 * @param end 끝 인덱스
	 * @param node 노드 번호
	 * @return 두 부분으로 나눈 합을 자기 자신의 노드값으로 삼는다.
	 */
	private static long init(int start, int end, int node) {
		if(start == end) {
			return tree[node] = arr[start]; //노드값으로 시작점을 달아준다.
		}
		int mid = (start + end) / 2; //이분 탐색
		
		return tree[node] 
				= init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
	}
	
	/**
	 * @param start 시작 인덱스
	 * @param end 끝 인덱스
	 * @param node 노드 번호
	 * @param left 구간합을 찾고자 하는 시작점
	 * @param right 구간합을 찾고자 하는 끝점
	 * @return
	 */
	private static long sum(int start, int end, int node, int left, int right) {
		if(left > end || right < start) //탐색 범위 밖에 있다면
			return 0;
		
		if(left <= start && end <= right) //탐색 범위 안에 있다면
			return tree[node]; //노드값을 반환
		
		//그렇지 않으면 두 부분으로 나누어 다시 탐색
		int mid = (start + end) / 2;
		
		return sum(start, mid, node * 2, left, right) 
				+ sum(mid + 1, end, node * 2 + 1, left, right);
	}

	/**
	 * @param start 시작 인덱스
	 * @param end 끝 인덱스
	 * @param node 노드 번호
	 * @param index 수정하고자 하는 노드
	 * @param diff 수정할 값
	 * @return
	 */
	private static void update(int start, int end, int node, int index, long diff) {
		if(index < start || index > end) //범위 밖에 있다면 리턴
			return;
		
		//범위 안에 있으면 내려가며 다른 원소도 갱신
		tree[node] += diff;
		
		if(start == end) 
			return;
		
		int mid = (start + end) / 2;
		
		update(start, mid, node * 2, index, diff);
		update(mid + 1, end, node * 2 + 1, index, diff);
	}
}
