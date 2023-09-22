import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, preIdx;
	static int[] inOrder, postOrder, preOrder;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		inOrder = new int[N];
		postOrder = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			inOrder[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			postOrder[i] = Integer.parseInt(st.nextToken());
		}

		preOrder = new int[N];
		preIdx = 0;
		makePre(0, N - 1, 0, N - 1);

		for(int i : preOrder) {
			System.out.print(i + " ");
		}
	}

	private static void makePre(int inStart, int inEnd, int postStart, int postEnd) {
		if (inStart > inEnd || postStart > postEnd)
			return;
		
		preOrder[preIdx++] = postOrder[postEnd];

		int inRoot = inStart;
		for (int i = inStart; i <= inEnd; i++) {
			if (postOrder[postEnd] == inOrder[i]) {
				inRoot = i;
				break;
			}
		}

		makePre(inStart, inRoot - 1, postStart, postStart + inRoot - inStart - 1);
		makePre(inRoot + 1, inEnd, postStart + inRoot - inStart, postEnd - 1);
	}
}