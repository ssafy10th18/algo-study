package boj.silver.boj1074;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int R;
	private static int C;
	private static int SIZE;

	private static int count = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		SIZE = (int) Math.pow(2, N); //한 변의 크기

		recursion(SIZE, R, C);
		
		System.out.println(count);
	}
	
	private static void recursion(int size, int r, int c) {		
		if(size == 1) {
			return;
		}

		int half = size / 2;
		if(r < half && c < half) { //1사분면: row와 col이 모두 key보다 크면 호출
			recursion(half, r, c);
		} else if(r < half && c >= half) { //2사분면: R보다 row가 크면 호출
			count += size * size / 4; //1사분면의 수 skip
			recursion(half, r, c - half);
		} else if(r >= half && c < half) { //3사분면: C보다 col이 크면 호출
			count += (size * size / 4) * 2; //2사분면의 수 skip
			recursion(half, r - half, c);
		} else { //4사분면: row와 col이 모두 key보다 작거나 같으면 호출
			count += (size * size / 4) * 3; //3사분면의 수 skip
			recursion(half, r - half, c - half);
		}
	}

}
