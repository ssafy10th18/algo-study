package swea.D3.swea4789;

import java.io.*;

class Solution
{
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
			String input = br.readLine();
			
			int len = input.length();
			
			int count = 0;
			int sum = input.charAt(0) - '0';
			for(int i = 1; i < len;) {
				if(sum < i) {
					count+= 1;
					sum += 1;
				} else {
					sum += input.charAt(i) - '0';
					i++;
				}
			}
			
			sb.append("#").append(test_case).append(" ")
				.append(count).append("\n");
		}
		
		System.out.println(sb);
	}
}
