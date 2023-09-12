package boj.gold.boj1062;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");

		int N = Integer.parseInt(temp[0]);
		int K = Integer.parseInt(temp[1]);
		
		if(K < 5) { //a, n, t, i, c 을 모두 학습할 수 없다면
			System.out.print(0);
			return;
		}
		
		char[] preset = {'a', 'n', 't', 'i', 'c'};
		
		int[] count = new int['z' - 'a' + 1]; //알파벳이 출현한 횟수를 센다.
		int[] words = new int['z' - 'a' + 1]; //알파벳이 출현한 단어의 가짓수를 센다.
				
		//a, n, t, i, c 을 우선으로 학습한다.
		for(int i = 0; i < 5; i++) {
			words[preset[i]] += 1;
			K--;
		}
		
		String[] lines = new String[N];
		for(int i = 0; i < N; i++) { //0 <= N <= 50
			if(K <= 0) { break; }
			
			lines[i] = br.readLine(); //길이: 8 <= L <= 15
			
			int length = lines[i].length() - 8; //antatica 제외
			boolean[] selected = new boolean['z' - 'a' + 1]; //한 단어마다 출현 여부를 체크
			for(int j = 0; j < length; j++) {
				char curr = lines[i].charAt(j + 4);
				
				count[curr] += 1;
				if(!selected[curr]) {
					selected[curr] = true;
					words[curr] += 1; //curr이라는 알파벳이 출현한 단어의 수
				}
			}
		}
		
		//select랑 words를 같이 정렬해야돼서 Heap<int[2]>으로 만드는 게 좋을듯
		for(int i = 0; i < N; i++) { //0 <= N <= 50
			for(int j = 0; j < lines[i].length() - 4; j++) {
				char curr = lines[i].charAt(j + 4);
				
				//
			}
		}
		
		
	}

}
