package swea.D2.swea1940;

import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			
			int speed = 0;
			int dist = 0;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				
				int command = Integer.parseInt(st.nextToken());
				if(command != 0) {
					int delta = Integer.parseInt(st.nextToken());
					
					if(command == 1) {
						speed += delta;
					}
					else if (command == 2) {
						speed = (speed - delta < 0) ? 0 : (speed - delta);
					}
				}
				
				dist += speed;
			}
			
			sb.append("#").append(test_case).append(" ")
				.append(dist).append("\n");
		}
		
		System.out.println(sb);
	}
}
