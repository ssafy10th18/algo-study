package swea.D2.swea14510;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

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
			
			int result = solution(N, maxHeight);
			
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		
		System.out.print(sb);
	}

	private static int solution(int N, int maxHeight) {
		int oddCount = 0; //홀수 날짜의 갯수
		int evenCount = 0; //짝수 날짜의 갯수
		for(int i = 0; i < N; i++) {
			remains[i] = maxHeight - trees[i];
			
			oddCount += remains[i] % 2;
			evenCount += remains[i] / 2;
		}
		
		int result = oddCount + evenCount;
		if(oddCount < evenCount) { //짝수 일자는 홀수 일자로 압축할 수 있다 => 날짜 수를 더욱 줄여보자.
			int gap = (evenCount - oddCount) * 2; //쉬는 날을 포함한 짝수 날짜의 수 (∅ + 2)
			int remain = (gap / 3) * 2; //짝수 날짜를 홀수 날짜로 압축한다.
			 
			if(gap % 3 == 2) { //압축하고 남은 나머지가 짝수 날짜라면
				remain += 2; //홀수 날짜도 함께 쉰다. (∅ + 2)
			} else if (gap % 3 == 1) remain += 1; //그렇지 않으면 홀수 날짜만 더한다.
			
			result = (oddCount * 2) + remain;
		} 
		else if (oddCount - evenCount > 1){ //홀수가 더 많을 경우 : 1 ∅ 1 ∅ 1 ... => ∅의 개수를 더한다.
			result += (oddCount - evenCount - 1);
		}
		return result;
	}

}
