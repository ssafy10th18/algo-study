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
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			
			// 위치 찾기
			int num = 1;
			int i = 2;
			while(num < p) {
				num += i++;
			}
			int px = i - 1 - (num - p);
			int py = 1 + (num - p);
			
			num = 1;
			i = 2;
			while(num < q) {
				num += i++;
			}
			int qx = i - 1 - (num - q);
			int qy = 1 + (num - q);
			
			// 값 계산
			int answer = 1;
			i = 1;
			while(i < py+qy) {
				answer += i++;
			}
			for(int j = 1; j < px+qx; j++) {
				answer += ++i;
			}
			System.out.println("#"+ tc + " " +answer);
		}
	}

}