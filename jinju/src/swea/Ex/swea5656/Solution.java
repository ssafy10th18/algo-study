package swea.Ex.swea5656;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	private static int N;
	private static int W;
	private static int H;
	
	private static int matrix[][];
	
	private static List<Point> blocks;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
			String[] temp = br.readLine().split(" ");
			
			N = Integer.parseInt(temp[0]);
			W = Integer.parseInt(temp[1]);
			H = Integer.parseInt(temp[2]);
			
			matrix = new int[W][H];
			
			StringTokenizer st;
			for(int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < H; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			setBlocks();
			
			
		}
		
		System.out.print(sb);
	}
	
	private static void DFS(int depth) {
		if(depth == N) {
			//잔여 블록의 수 카운트
			return;
		}
		
		//블록 하나 선정해서 깨트리기 
	}
	
	private static void setBlocks() {
		blocks = new ArrayList<>();
		
		all : for(int i = 0; i < W; i++) {
			for(int j = 0; j < H; j++) {
				if(matrix[i - 1][j] == 0 && matrix[i + 1][j] != 0) { //경계면이라면
					blocks.add(new Point(j, i));
					continue all;
				}
			}
		}
	}

}
