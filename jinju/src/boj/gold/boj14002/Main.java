package boj.gold.boj14002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		int[] DP = new int[N];
		String[] rslt = new String[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			rslt[i] = String.valueOf(arr[i]) + " ";
			DP[i] = 1;
		}
		
		for(int i = 0; i < N; i++) {
			int pivot = arr[i];
			for(int j = i + 1; j < N; j++) {
				if(pivot < arr[j]) {
					if(DP[i] + 1 > DP[j]) {
						DP[j] = DP[i] + 1;
						rslt[j] = rslt[i] + arr[j] + " ";
					}
				}
			}
		}
		
		int maxIdx = N - 1;
		for(int i = 0; i < N; i++)
			maxIdx = (DP[i] > DP[maxIdx]) ? i : maxIdx;
		
		System.out.println(DP[maxIdx]);
		System.out.println(rslt[maxIdx]);
	}

}
