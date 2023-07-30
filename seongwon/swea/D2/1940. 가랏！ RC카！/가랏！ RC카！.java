import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		 
		int T = Integer.parseInt(st.nextToken());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int speed = 0;
			int answer = 0;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				
				if(c == 0)answer += speed;
				else {
					int s = Integer.parseInt(st.nextToken());
					if (c == 1) {
						speed += s;
						answer += speed;
					}
					else if(c == 2) {
						speed -= speed > s ? s : speed;
						answer += speed;
					}
				}					
			}
			System.out.println("#" + tc + " " + answer);
		}
	}

}
