package swea.D3.swea1220;

import java.util.*;
import java.io.*;

class Solution
{
	private static final int SIZE = 100;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		
		int[][] matrix = new int[SIZE][SIZE];
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
            
			for(int i = 0; i < SIZE; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < SIZE; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int answer = 0;
			StringBuilder temp;
			for(int j = 0; j < SIZE; j++) {
				temp = new StringBuilder();
				for(int i = 0; i < SIZE; i++) {
					if(matrix[i][j] != 0) {
						temp.append(matrix[i][j]);
					}
				}
				
				String result = temp.toString();
				result = result.replaceAll("12", "*");
				for(int i = 0; i < result.length(); i++) {
					if(result.charAt(i) == '*') {
						answer++;
					}
				}
			}
			
			sb.append("#").append(test_case).append(" ")
			.append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
}