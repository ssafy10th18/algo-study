package swea.D3.swea3499;

import java.io.*;

class Solution
{
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			sb.append("#").append(test_case).append(" ");
			
			String[] cards = br.readLine().split(" ");
			
			int mid = (int)Math.round((double)N / 2);
			int p = 0;
			int q = mid;
			while(p < mid || q < N) {
				if(p < mid) {
					sb.append(cards[p++]).append(" ");
				}
				
				if(q < N) {
					sb.append(cards[q++]).append(" ");
				}
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}