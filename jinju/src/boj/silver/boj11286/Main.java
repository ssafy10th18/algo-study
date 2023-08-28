package boj.silver.boj11286;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		int curr;
		PriorityQueue<Node> pq = new PriorityQueue<>( new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if(o1.abs > o2.abs) {
					return 1;
				}
				else if (o1.abs < o2.abs) {
					return -1;
				}
				else { //절댓값이 같다면
					if(o1.value > o2.value) {
						return 1; //원본값이 작은 것을 반환한다
					}
					else {
						return -1;
					}
				}
			}
		});

		for(int i = 0; i < N; i++) {
			curr = Integer.parseInt(br.readLine());

			if(curr == 0) {
				if(pq.peek() != null)
					sb.append(pq.poll().value).append("\n");
				else
					sb.append(0).append("\n");
				continue;
			}

			Node n = new Node(curr, Math.abs(curr));
			pq.add(n);
		}

		System.out.println(sb);
	}

}

class Node {
	public int value;
	public int abs;

	public Node(int value, int abs) {
		this.value = value;
		this.abs = abs;
	}
}