package swea.D3.swea4698;

import java.util.*;
import java.io.*;

class Solution
{
	private static final int MAX_BOUND = 1_000_000;
	private static boolean[] isPrime = new boolean[MAX_BOUND + 1];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		Arrays.fill(isPrime, true);
		
		makePrime();
		
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
			String[] temp = br.readLine().split(" ");
			
			String D = temp[0];
			int A = Integer.parseInt(temp[1]);
			int B = Integer.parseInt(temp[2]);

			int answer = 0;
			for(int i = A; i <= B; i++) {
				if(!isPrime[i]) {
					continue;
				}
				
				if(String.valueOf(i).contains(D)) {
					answer++;
				}
			}
			
			sb.append("#").append(test_case).append(" ")
				.append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void makePrime() {
		isPrime[0] = false;
		isPrime[1] = false;
		
		for(int i = 2; i <= Math.sqrt(MAX_BOUND); i++) {
			if(isPrime[i] == false) {
				continue;
			}
			
			for(int j = i * i; j < isPrime.length; j = j + i) {
				isPrime[j] = false;
			}
		}
		
	}
}
