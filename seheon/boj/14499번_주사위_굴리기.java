import java.io.*;
import java.util.*;

public class Main {
	
	static int x, y, n, m, k;
	static int[][] M;
	static int[] oper;
	static int[][] dxy = {{}, {1, 0}, {-1, 0}, {0, -1}, {0, 1}};
	static int[] dice = {0, 0, 0, 0, 0, 0}; // 하, 상, 뒤, 앞, 좌, 우

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		M = new int[n][m];
		oper = new int[k];
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = 0;
			while (st.hasMoreTokens()) {
				M[i][idx++] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int idx = 0;
		while (st.hasMoreTokens()) {
			oper[idx++] = Integer.parseInt(st.nextToken());
		}

		for (int dir : oper) {
			if (move(dir)) {
				System.out.println(dice[1]);
			}
		}
	}
	
	
	static boolean move(int dir) {
		
		int dx = x + dxy[dir][0];
		int dy = y + dxy[dir][1];
		
		if (dx<0 || dx>m-1 || dy<0 || dy>n-1) return false;
		
		x = dx;
		y = dy;
		
		int[] temp = new int[6];
		if (dir == 1) {
			temp[0] = dice[5];
			temp[1] = dice[4];
			temp[2] = dice[2];
			temp[3] = dice[3];
			temp[4] = dice[0];
			temp[5] = dice[1];
			dice = Arrays.copyOf(temp, 6);
		}
		else if (dir == 2) {
			temp[0] = dice[4];
			temp[1] = dice[5];
			temp[2] = dice[2];
			temp[3] = dice[3];
			temp[4] = dice[1];
			temp[5] = dice[0];
			dice = Arrays.copyOf(temp, 6);
		}
		else if (dir == 3) {
			temp[0] = dice[2];
			temp[1] = dice[3];
			temp[2] = dice[1];
			temp[3] = dice[0];
			temp[4] = dice[4];
			temp[5] = dice[5];
			dice = Arrays.copyOf(temp, 6);
		}
		else if (dir == 4) {
			temp[0] = dice[3];
			temp[1] = dice[2];
			temp[2] = dice[0];
			temp[3] = dice[1];
			temp[4] = dice[4];
			temp[5] = dice[5];
			dice = Arrays.copyOf(temp, 6);
		}
		
		if (M[y][x] == 0) {
			M[y][x] = dice[0];
		}
		else {
			dice[0] = M[y][x];
			M[y][x] = 0;
		}
		
		return true;
	}
	
}