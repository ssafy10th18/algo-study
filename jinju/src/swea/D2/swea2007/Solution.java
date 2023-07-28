package swea.D2.swea2007;

import java.io.*;

class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
			String str = br.readLine();
			
			for(int i = 1; i < str.length() / 2; i++) {
				String s1 = str.substring(0, i);
				String s2 = str.substring(i, 2 * i);
				
				if(s1.equals(s2)) {
					sb.append("#").append(test_case).append(" ")
						.append(s1.length()).append("\n");
					break;
				}
			}
		}

		System.out.println(sb);
	}
}