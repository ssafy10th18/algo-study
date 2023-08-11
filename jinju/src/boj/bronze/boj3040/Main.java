import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static final int SIZE = 9;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = new int[SIZE];

		int sum = 0;
		for(int i = 0; i < SIZE; i++) {
			input[i] = Integer.parseInt(br.readLine());
			sum += input[i];
		}

		all : for(int p = 0; p < SIZE; p++) {
			for(int q = p + 1; q < SIZE; q++) {
				if(sum - (input[p] + input[q]) == 100) {
					input[p] = -1;
					input[q] = -1;
					break all;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int n : input) {
			if(n != -1) sb.append(n).append("\n");
		}

		System.out.println(sb);
	}

}