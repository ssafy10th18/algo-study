package boj.gold.boj14945;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] DP = new int[N][N]; //[N][dir]
		
		DP[0][0] = 0;
		DP[1][0] = 0;
		DP[2][1] = 2;
		
		
	}

}
