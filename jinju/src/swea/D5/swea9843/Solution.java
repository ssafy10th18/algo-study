import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
			long answer = -1;
			
			long N = Long.parseLong(br.readLine());
			
			if(N == 1) {
				sb.append("#").append(test_case).append(" ").append(1).append("\n");
				continue;
			}
			
			long i = (long) Math.sqrt(2 * N);
			if(i * (i + 1) / 2 == N) {
					answer = i;
			}
            
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}

}