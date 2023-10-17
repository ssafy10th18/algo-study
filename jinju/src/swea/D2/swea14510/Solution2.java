package swea.D2.swea14510;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution2 {

	private static int[] trees;
	private static int[] remains;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			
			trees = new int[N];
			remains = new int[N];
			
			int maxHeight = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(trees[i], maxHeight);
			}
			
			for(int i = 0; i < N; i++) {
				remains[i] = maxHeight - trees[i];
			}
			
			int answer = 0; //1. 몫을 조사
			for(int i = 0; i < N; i++) {
				if(remains[i] == 0) continue;
					
				//나무의 높이가 2부터이기 때문에 3으로 나눌 수 없을 수도 있음 
				if(remains[i] == 1 || remains[i] == 2) {
					answer += 1;
				} else if (remains[i] == 3) {
					answer += 2;
					remains[i] = 0;
				}
				else {
					int r = (remains[i] / 3) * 2; //키가 1m + 2m 자라는 경우의 몫
					answer += r; //몫의 물을 주는 횟수
					remains[i] = remains[i] % 3;
				}
			}
			
			//2.나머지를 조사
			int oddCount = 0;
			int evenCount = 0;
			for(int i = 0; i < N; i++) {
				if(remains[i] == 0) continue; //높이가 가장 높은 나무는 무시
				
				if(remains[i] % 2 == 0) { evenCount++; }
				else oddCount++;
			}
			
			if(evenCount > oddCount) { //홀수번째 날에 쉬었던 전적이 있다면
				answer += (evenCount - oddCount - 1); //쉬었던 홀수번째 날을 더해준다.
			} else if (evenCount < oddCount) {
				answer += (oddCount - evenCount - 1); //쉬었던 짝수번째 날을 더해준다.
			}
			
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}

}
