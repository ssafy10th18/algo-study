package swea.D2.swea1984;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Solution
{
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

        int size = 10;
        StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
			int[] arr = new int[size];
			
            String[] splited = br.readLine().split(" ");
            for(int i = 0; i < size; i++) {
                arr[i] = Integer.parseInt(splited[i]);
            }
            
            Arrays.sort(arr);
            
            double sum = 0;
            for(int i = 1; i < size - 1; i++) {
                sum += arr[i];
            }
            
            int result = (int) Math.round(sum / (size - 2));
            
            sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
        
        System.out.print(sb);
	}
}