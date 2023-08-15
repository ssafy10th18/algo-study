package boj.silver.boj1992;

import java.io.*;

public class Main {

	private static int[][] matrix;
	
	private static StringBuilder sb;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());

		matrix = new int[N][N];
		
		sb = new StringBuilder();
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < N; j++) {
				matrix[i][j] = line.charAt(j) - '0';
			}
		}
		
		quadTree(N, 0, 0);
		
		System.out.println(sb);
	}
	
	/**
	 * @param size : 한 변의 크기
	 * @param r : 시작지점 row
	 * @param c : 시작지점 col
	 */
	private static void quadTree(int size, int r, int c) {
		//모두 같은 비트라면 압축
		if(isAllSameBit(size, r, c)) {
			sb.append(matrix[r][c]);
			return;
		}
		
		if(size == 2) { // 2 * 2 matrix까지 쪼개졌을 때
			sb.append("(");
			for(int i = r; i < r + size; i++) {
				for(int j = c; j < c + size; j++) {
					sb.append(matrix[i][j]);
				}
			}
			sb.append(")");
			
			return;
		}
		
		sb.append("("); //재귀를 시작하며 괄호 열기
		int newSize = size / 2;
		quadTree(newSize, r, c);
		quadTree(newSize, r, c + newSize);
		quadTree(newSize, r + newSize, c);
		quadTree(newSize, r + newSize, c + newSize);
		sb.append(")"); //4등분 재귀가 끝나면 괄호 닫기
	}

	private static boolean isAllSameBit(int size, int r, int c) {
		int initialBit = matrix[r][c];
		
		for(int i = r; i < r + size; i++) {
			for(int j = c; j < c + size; j++) {
				if(matrix[i][j] != initialBit) return false;
			}
		}
		
		return true;
	}
	
}
