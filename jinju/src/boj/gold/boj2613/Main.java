package boj.gold.boj2613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N;
	private static int M;

	private static int[] beads;
	
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		beads = new int[N];

		int start = 0;
		int end = 0;
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			beads[i] = Integer.parseInt(st.nextToken());
						
			start = Math.max(beads[i], start);
			end += beads[i];
		}

		int answer = Integer.MAX_VALUE;
		
		int mid = 0;
		//parametric search
		while(start <= end) {
			mid = (start + end) / 2;
			
			if(isValidGroup(mid)) { //그룹의 수가 적다면
				end = mid - 1; //최댓값을 더 작게 잡는다.
				answer = Math.min(answer, mid);
			} else { //그룹의 수가 많다면
				start = mid + 1; //최댓값을 더 크게 잡는다.
			}
		}
		
		sb.append(answer).append("\n"); 
		
		printGroup(answer, M);
		
		System.out.println(sb);
	}

	private static void printGroup(int mid, int target) {
		int sum = 0;
		int count = 0;
		
		for(int i = 0; i < beads.length; i++) {
			sum += beads[i];
			
			if(sum > mid) {
				sum = beads[i]; //새로운 구간부터 다시 부분합을 구한다.
				//한 그룹을 모두 묶었으므로 append 해준다.
				sb.append(count).append(" ");
				
				target -= 1; //앞으로 뽑을 그룹 수 감소
				count = 0;
			}
			count += 1;
			
			//앞으로 구할 그룹의 수와 잔여 구슬의 수가 일치한다면 break
			if(N - i == target) break;
		}
		
		//다른 그룹을 모두 묶었는데 target이 0이 아니라면
		while(target-- > 0) { //1개짜리 그룹을 append 해준다.
			sb.append(count).append(" ");
			count = 1;
		}
	}

	private static boolean isValidGroup(int mid) {
		int sum = 0;
		int groupCount = 1;
		
		for(int i = 0; i < beads.length; i++) {
			sum += beads[i];
			
			if(sum > mid) {
				sum = beads[i]; //새로운 구간부터 다시 부분합을 구한다.
				groupCount++;
			}
		}
		
		return (M >= groupCount);
	}

}
