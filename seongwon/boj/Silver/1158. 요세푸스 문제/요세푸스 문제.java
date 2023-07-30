import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i = 1; i <= N; i++)queue.add(i);
		
		StringBuilder sb = new StringBuilder("<");
		int rotate = 1;
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			if(rotate % K == 0) {
				sb.append(cur).append(", ");
			}
			else queue.add(cur);
			rotate++;
		}
		
		sb.delete(sb.length()-2, sb.length());
		sb.append(">");
		
		System.out.println(sb.toString());
	}

}
