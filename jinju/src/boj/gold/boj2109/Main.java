package boj.gold.boj2109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		//주의: 뒤에서부터 셀 것임
		PriorityQueue<Lecture> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1.date == o2.date) { //날짜가 같다면
				return o1.pay - o2.pay; //가격 오름차순
			} 
			return o2.date - o1.date; //그렇지 않다면 날짜 내림차순
		});
		
		String[] temp;
		for(int i = 0; i < N; i++) {
			temp = br.readLine().split(" ");
			
			int pay = Integer.parseInt(temp[0]);
			int date = Integer.parseInt(temp[1]);
			
			pq.add(new Lecture(pay, date));
		}
		
		int answer = 0;
		int currPay = 0;
		while(!pq.isEmpty()) {
			Lecture l = pq.poll();
			
			if(l.pay > currPay) {
				answer += l.pay;
			}
			currPay = l.pay;
		}
		
		System.out.println(answer);
	}
}

class Lecture {
	int pay;
	int date;

	public Lecture(int pay, int date) {
		this.pay = pay;
		this.date = date;
	}
}