package swea.D3.swea5356;

import java.util.*;
import java.io.*;

class Solution
{
	private static final int N = 5;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {

			int maxLen = 0;
			String[] lines = new String[N];
			for(int i = 0; i < N; i++) {
				lines[i] = br.readLine();
				maxLen = Math.max(maxLen, lines[i].length());
			}
			
			char[][] matrix = new char[N][maxLen];
			for(int i = 0; i < N; i++) {
				Arrays.fill(matrix[i], '*');
			}

			for(int i = 0; i < N; i++) {
				for(int j = 0; j < lines[i].length(); j++) {
					matrix[i][j] = lines[i].charAt(j);
				}
			}
			
			sb.append("#").append(test_case).append(" ");
			for(int j = 0; j < maxLen; j++) {
				for(int i = 0; i < N; i++) {
					if(matrix[i][j] != '*') {
						sb.append(matrix[i][j]);
					}
				}
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}