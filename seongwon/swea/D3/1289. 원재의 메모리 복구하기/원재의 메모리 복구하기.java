import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			String p = st.nextToken();
			int answer = 0;
			for(int i = 1; i < p.length(); i++){
				if(p.charAt(i) != p.charAt(i-1))answer++;
			}
			if(p.charAt(0) == '1')answer++;
			System.out.println("#"+ tc +" "+answer);
		}
	}

}
