import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static char[][] map = new char[20][20];
	static int[] dx = {0,1,1,1};
	static int[] dy = {1,1,0,-1};
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				String line = st.nextToken();
				for(int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j);
				}
			}
			
			boolean isFive = false;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == 'o') {
						isFive = fiveCheck(i, j);
					}
					if(isFive)break;
				}
				if(isFive)break;
			}
			
			if(isFive)System.out.println("#"+tc+" "+"YES");
			else System.out.println("#"+tc+" "+"NO");
		}

	}
	
	public static boolean fiveCheck(int x, int y) {
		for(int k = 0; k < 4; k++) {
			for(int cnt = 1; cnt < 5; cnt++) {
				int nx = x + dx[k] * cnt;
				int ny = y + dy[k] * cnt;
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == 'o') {
					if(cnt == 4)return true;
				}
				else break;
			}
		}
		
		return false;
	}

}