import java.util.*;
import java.io.*;

public class Main {
	
	static List<String> W = new ArrayList<>();
	static int[] alph = new int[26];
	static int ans = 0;
	static int n;
	static int k;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] info = br.readLine().split(" ");
		n = Integer.parseInt(info[0]);
		k = Integer.parseInt(info[1])-5;

		alph[(int)'a'-97] = 1;
		alph[(int)'n'-97] = 1;
		alph[(int)'t'-97] = 1;
		alph[(int)'i'-97] = 1;
		alph[(int)'c'-97] = 1;
	
		for (int i=0; i<n; i++) {
			W.add(br.readLine());
		}
		
		dfs(-1, 0);
		System.out.println(ans);
		
	}
	
	static void dfs(int idx, int cnt) {
		if (cnt == k) {
			ans = Math.max(ans, getCount());
			return;
		}
		for (int i=idx+1; i<26; i++) {
			if (alph[i] == 1) continue;
			alph[i] = 1; 
			dfs(i, cnt+1);
			alph[i] = 0;
			
		}
	}
	
	static int getCount() {
		int cnt = 0;
        for (String s : W) {
            for(int i=4; i<s.length()-3; i++){
                if (alph[(int)s.charAt(i)-97] == 0) {
                    cnt -= 1;
                    break;
                }
            }
            cnt += 1;
        }
		return cnt;
	}

}