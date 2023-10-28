package boj.silver.boj4375;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line;
		StringBuilder sb = new StringBuilder();
		while(!(line = br.readLine()).equals("")) {
			int N = Integer.parseInt(line);
			
			int mod = 1;
			int count = 1;
			
			while((mod = mod % N) != 0) {
				count++;
				mod = (mod * 10) + 1;
			}
			
			sb.append(count).append("\n");
		}
		System.out.print(sb);
	}
}
