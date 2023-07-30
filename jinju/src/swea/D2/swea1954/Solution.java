package swea.D2.swea1954;

import java.io.*;

class Solution
{
	private static int[] dx = {1, 0, -1, 0};
	private static int[] dy = {0, 1, 0, -1};
	
	private static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            
            int[][] matrix = new int[N][N];
            
            int idx = 0;
            int r = 0;
            int c = 0;
            for(int k = 1; k <= N * N;) {  
            	int nr = 0;
                int nc = 0;
            	if(matrix[r][c] != 0) {
            		nr = r + dy[idx];
            		nc = c + dx[idx];
            	}   
            	
            	if(!isValidIndex(nr, nc) || matrix[nr][nc] != 0) {
            		idx++;
            		if(idx >= dx.length) {
            			idx = idx % dx.length;
            		}
            		continue;
            	}
            	
            	matrix[nr][nc] = k++;
            	r = nr;
            	c = nc;
            }
            
            sb.append("#").append(test_case).append("\n");
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					sb.append(matrix[i][j]).append(" ");
	            }
				sb.append("\n");
            }
		}
		
		System.out.print(sb.toString());
	}

	private static boolean isValidIndex(int nr, int nc) {
		return (nr > -1 && nc > -1 && nr < N && nc < N);
	}
}
