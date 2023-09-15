import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 강연 요청 수
		
		Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
			if(o1[0] == o2[0]) return Integer.compare(o1[1], o2[1]); // limit는 오름차순
			return Integer.compare(o2[0], o1[0]); // pay는 내림차순
		});
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int pay = Integer.parseInt(st.nextToken()); // 강연료
			int timeLimit = Integer.parseInt(st.nextToken()); // 기한
			
			queue.add(new int[] {pay, timeLimit});
		}
		
		boolean[] schedule = new boolean[10001]; // 스케쥴 체크
		int answer = 0; // 최대로 벌 수 있는 금액
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			int time = cur[1];
			while(time > 0) { // 각 강연은 최대한 마지막 기한에 할 수 있도록 함
				if(!schedule[time]) {
					schedule[time] = true;
					break;
				}
				else time--;
			}
			if(time > 0)answer += cur[0]; // 강연을 할 수 있다면 합
		}
		System.out.println(answer);
	}
}
