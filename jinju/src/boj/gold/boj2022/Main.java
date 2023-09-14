package boj.gold.boj2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		
		double x = Double.parseDouble(input[0]);
		double y = Double.parseDouble(input[1]);
		double c = Double.parseDouble(input[2]);
		
		double left = 1;
		double right = Math.max(x, y);
		
		while(left + 0.001 <= right) {
			double w = (left + right) / 2;
			
			double h1 = Math.sqrt(Math.pow(x, 2) + Math.pow(w, 2));
			double h2 = Math.sqrt(Math.pow(y, 2) + Math.pow(w, 2));
			
			double result_c = h1 * h2 / (h1 + h2);
			
			//근사치 찾기
			if(result_c < c) { //
				right = w;
			} else if(result_c >= c) { 
				left = w;
			}
		}
		
		System.out.print(left);
	}

}
