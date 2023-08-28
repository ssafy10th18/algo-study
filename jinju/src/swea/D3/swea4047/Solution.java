package swea.D3.swea4047;

import java.io.*;

class Solution
{
	private static final int MAX = 'Z';
	private static final int NUM = 13;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		int[] types = {'S', 'D', 'H', 'C'};
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
			String input = br.readLine();
			
			boolean[][] cards = new boolean[MAX][NUM + 1];
			
			sb.append("#").append(test_case).append(" ");
			
			boolean isError = false;
			int len = input.length();
			for(int i = 0; i < len; i = i + 3) {
				char type = input.charAt(i);
				int digit1 = input.charAt(i + 1) - '0';
				int digit2 = input.charAt(i + 2) - '0';
				
				int num = digit1 * 10 + digit2;
				
				if(!cards[type][num]) {
					cards[type][num] = true;
				}
				else {
					isError = true;
					sb.append("ERROR").append("\n");
					break;
				}
			}
			
			if(isError) {
				continue;
			}
			
			for(int type : types) {
				int count = NUM;
				for(boolean b : cards[type]) {
					if(b == true) {
						count--;
					}
				}
				sb.append(count).append(" ");
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}