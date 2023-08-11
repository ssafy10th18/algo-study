package boj.bronze.boj3052;

import java.io.*;
import java.util.*;

public class Main {

	private static final int DIV = 42;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		
		Set<Integer> set = new HashSet<>();
		for(int i = 0; i < T; i++) {
			int n = Integer.parseInt(br.readLine());

			set.add(n % DIV);
		}
		
		System.out.println(set.size());
	}

}
