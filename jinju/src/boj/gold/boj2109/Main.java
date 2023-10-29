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
			if (o1.pay == o2.pay) { //비용이 같다면
				return o2.date - o1.date; //날짜 내림차순
			} 
			return o2.pay - o1.pay; //그렇지 않다면 비용 내림차순
		});
		
		String[] temp;
		int maxDate = 0;
		for(int i = 0; i < N; i++) {
			temp = br.readLine().split(" ");
			
			int pay = Integer.parseInt(temp[0]);
			int date = Integer.parseInt(temp[1]);
			
			maxDate = Math.max(maxDate, date);
			
			pq.add(new Lecture(pay, date));
		}
		
		boolean[] checked = new boolean[maxDate + 1]; 
		//i번째 날짜에 유망한 강의를 이미 선택했는지를 체크한다.
		
		int answer = 0;
		while(!pq.isEmpty()) {
			Lecture l = pq.poll();
			
			for(int day = l.date; day > 0; day--) {
				if(!checked[day]) { //해당 날짜에 아직 예약이 되지 않았다면
					checked[day] = true;
					answer += l.pay;
					break;
				}
			}
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