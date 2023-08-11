import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;

		String[] commands;
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
			//원본 암호문 길이
			int N = Integer.parseInt(br.readLine());

			//원본 암호문
			LinkedList<Integer> list = new LinkedList<>();
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}

			//명령어의 수
			int K = Integer.parseInt(br.readLine());

			commands = br.readLine().split(" ");
			for(int i = 0; i < commands.length;) {
				int x = Integer.parseInt(commands[1 + i]);
				int y = Integer.parseInt(commands[2 + i]);

				LinkedList<Integer> subList = new LinkedList<>();
				for(int j = 3 + i; j < (3 + i) + y; j++) {
					int element = Integer.parseInt(commands[j]);
					subList.add(element);
				}
				list.addAll(x, subList);
				i += (y + 3);
			}

			sb.append("#").append(test_case).append(" ");
			for(int i = 0; i < 10; i++) {
				sb.append(list.get(i)).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

}