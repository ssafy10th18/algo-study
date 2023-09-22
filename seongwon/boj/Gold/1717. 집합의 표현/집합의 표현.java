import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(c == 0) union(a, b);
			if(c == 1) {
				if(find(a) == find(b))System.out.println("YES");
				else System.out.println("NO");
			}
		}
	}
	
	public static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot)return;
		
		parents[bRoot] = parents[aRoot]; 
	}

	private static int find(int a) {
		if(a == parents[a])return a;
		return parents[a] = find(parents[a]);
	}
}
