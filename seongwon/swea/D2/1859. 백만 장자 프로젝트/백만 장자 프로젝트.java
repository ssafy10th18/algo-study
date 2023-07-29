import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		int[] days;
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			days = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				days[i] = Integer.parseInt(st.nextToken());
			}
			int max = -1;
			long sum = 0;
			for(int i = N - 1; i >= 0; i--) {
				if(max < days[i])max = days[i];
				else {
					sum += max - days[i];
				}
			}
			System.out.println("#" + tc + " " + sum);
		}
	}
}
