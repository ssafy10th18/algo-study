import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static int R;
	private static int answer = Integer.MAX_VALUE;
	
	private static List<Point> houses = new ArrayList<>();
	private static List<Point> stores = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				int input = Integer.parseInt(st.nextToken()); 
				
				if(input == 1) {
					houses.add(new Point(j, i));
				}
				
				if(input == 2) {
					stores.add(new Point(j, i));
				}
			}
		}
		
		int len = stores.size();
		int[] p = new int[len];
		
		for(int i = 0; i < R; i++) {
			p[len - 1 - i] = 1;
		}
		
		do {
			for(int i = 0; i < len; i++) {
				if(p[i] == 0) continue;
				
				answer = Math.min(calculateDist(p), answer);
			}
		} while(np(p));
		
		System.out.println(answer);
	}

	private static int calculateDist(int[] p) {
		int sum = 0;
		for(Point house : houses) {
				int minDist = Integer.MAX_VALUE;
				for(int k = 0; k < p.length; k++) {
					if(p[k] == 0) continue;
					
					Point store = stores.get(k);
					
					int d = Math.abs(house.y - store.y) + Math.abs(house.x - store.x);
					minDist = Math.min(d, minDist);
				}
				sum += minDist;
		}
		
		return sum;
	}

	private static boolean np(int[] p) {
		int N = p.length;
		
		int i = N - 1; //맨 뒤에서 출발
		while(i > 0 && p[i - 1] >= p[i]) --i;
		
		if(i == 0) //이미 마지막 순열임
			return false;
		
		int j = N - 1; //맨 뒤에서 출발
		while(p[i - 1] >= p[j]) --j;
		
		swap(p, i - 1, j);
		
		int k = N - 1; //맨 뒤에서 출발
		while(i < k) { //i, k가 양 끝의 수를 가리킴
			swap(p, i++, k--);
		}
		
		return true;
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}