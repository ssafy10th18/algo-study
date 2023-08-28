package swea.D2.swea1979;

import java.util.*;
import java.io.*;

import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
            String[] temp = br.readLine().split(" ");
			
            int N = Integer.parseInt(temp[0]);
            int K = Integer.parseInt(temp[1]);
            
            int[][] matrix = new int[N][N];
           	 
            StringTokenizer st;
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
            	for(int j = 0; j < N; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            int answer = 0;
            
            int curr = 0;
            int prev = 0;
            int len = 0;
            
            for(int i = 0; i < N; i++) {
            	curr = 0;
            	prev = 0;
            	len = 1;
            	for(int j = 0; j < N; j++) {
            		curr = matrix[i][j];
                    if(curr == 1) {
                    	if(prev == 1) {
                    		len++;
                    	}
                    	else { //prev == 1
                    		prev = 1;
                    	}
                    }
                    else if(curr != 1) {
                    	if(len == K) {
                    		answer++;
                    	}
                    	
                    	prev = 0;
                    	len = 1;
                    }
                }
            	
            	if(len == K) {
            		answer++;
            	}
            }
            
            for(int j = 0; j < N; j++) {
            	curr = 0;
            	prev = 0;
            	len = 1;
            	for(int i = 0; i < N; i++) {
            		curr = matrix[i][j];
                    if(curr == 1) {
                    	if(prev == 1) {
                    		len++;
                    	}
                    	else { //prev == 1
                    		prev = 1;
                    	}
                    }
                    else if(curr != 1) {
                    	if(len == K) {
                    		answer++;
                    	}
                    	
                    	prev = 0;
                    	len = 1;
                    }
                }
            	
            	if(len == K) {
            		answer++;
            	}
            }
            
            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
        
        System.out.print(sb);
	}
}