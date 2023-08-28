package boj.gold.boj17281;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	private static final int NUM = 9;
	private static final int FIRST = -1;
	
	private static int lastIndex = 0;
	private static int maxScore = -1;
	
	private static boolean[] base; //0은 사용 안 함
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] scores = new int[N][NUM]; //N이닝, 첫 번째를 제외한 8명의 선수 득점 예상표
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			for(int j = 0; j < NUM; j++) {
				scores[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int inning = 0;
		int currScore = 0;
		int[] fixedSequence = new int[NUM];
		do {
			base = new boolean[4]; //베이스 초기화
			currScore = playGame(inning, scores[inning]);
			
			if(currScore > maxScore) {
				maxScore = currScore;
				fixedSequence = Arrays.copyOf(scores[inning], scores[inning].length); //타순을 고정
			}
		} while(np(scores[inning]));
		
		inning++; //한 이닝이 끝났다면 증가
		
		//확정된 타순으로 남은 이닝들을 진행시킴
		for(int i = inning; i < N; i++) {
			maxScore += playGame(inning, fixedSequence);
		}
		
		System.out.println(maxScore);
	}
	
	private static int playGame(int inning, int[] scores) {
		int outs = 0;
		
		int total = 0;
		int currScore = -1;
		int idx = lastIndex;
		while(true) {
			if(idx >= NUM) { idx = idx % (NUM); }
			
			if(idx == 3) { //4번 타자 : 1번 선수를 이용
				currScore = scores[0];
			} else { //다른 타자 : 기존 선수를 이용
				currScore = scores[idx];
			}
			
			if(currScore == 0) { 
				outs++;
				idx += 1;
				continue;
			}
			
			if(outs >= 3) {
				lastIndex = idx; //몇 번 투수까지 쳤는지 기록
				break; //이번 이닝 종료
			}
			
			if(currScore == 1) {
				if(base[currScore]) { //1루에 사람이 있다면
					base[currScore + 1] = true; //다음 칸으로 민다.
				}
				base[currScore] = true; //1루에 사람을 채운다.
			} else if(currScore == 2) {
				if(base[currScore]) { 
					base[currScore + 1] = true;
				}
				base[currScore] = true;
			} else if(currScore == 3) {
				if(base[currScore]) { //3루에 사람이 있다면
					total += 1; //홈으로 옮기고 득점
				}
				base[currScore] = true;
			} else { //홈런이라면
				total += 1; //득점
				
				boolean homerun = true;
				for (boolean b : base) {
					if(!b) {
						homerun = false;
						break;
					}
				}
				
				if(homerun) { total += 3; } //만루홈런 계산
			}
			
			idx += 1;
		}
		
		return total;
	}

	private static boolean np(int[] arr) {
		int swapIdx = -1;
		for(int i = 2; i < arr.length; i++) { //첫 번째 원소 제외
			if(arr[i - 1] < arr[i]) {
				swapIdx = i - 1;
			}
		}
		
		if(swapIdx == -1) return false;
		
		int largerIdx = -1;
		for(int j = 2; j < arr.length; j++) {
			if(arr[swapIdx] < arr[j]) {
				largerIdx = j;
			}
		}
		
		swap(arr, swapIdx, largerIdx);
		
		int j = arr.length - 1;
		for(int i = swapIdx + 1; i < j; i++) {
			swap(arr, i, j);
			j--;
		}
		
		return true;
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
