package boj.silver.boj1156;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");

		int N = Integer.parseInt(temp[0]);
		int K = Integer.parseInt(temp[1]);

		Queue<Integer> queue = new LinkedList<>();

		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			queue.add(i);
		}

		int cnt = 1;
		while(!queue.isEmpty()) {
			for(int i = 0; i < queue.size(); i++) {
				if(cnt == K) {
					result.add(queue.poll());
					cnt = 0;
				}
				else {
					queue.add(queue.poll());
				}
				cnt++;
			}	
		}

		StringBuilder sb = new StringBuilder();
		sb.append("<");
		for(int n : result) {
			sb.append(n).append(", ");
		}
		sb.replace(sb.length() - 2, sb.length() - 1, ">");

		System.out.println(sb);

	}

}