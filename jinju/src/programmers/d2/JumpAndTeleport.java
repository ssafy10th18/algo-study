package programmers.d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JumpAndTeleport {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());

		int result = 0;
		while(n > 0) {
			if(n % 2 == 0) {
				n = n / 2;
			} else {
				n -= 1;
				result++;
			}
		}
		
		System.out.println(result);
	}

}
