import java.io.*;
import java.util.*;

public class Main {
	
	static int[] inorder;
	static int[] postorder;
	static int[] preorder;
	static int idx;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		inorder = new int[n];
		postorder = new int[n];
		preorder = new int[n];
		
		st = new StringTokenizer(br.readLine());
		int i = 0;
		while (st.hasMoreTokens()) {
			inorder[i++] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		i = 0;
		while (st.hasMoreTokens()) {
			postorder[i++] = Integer.parseInt(st.nextToken());
		}
		
		recursion(0, n-1, 0, n-1);
		for (int pre : preorder) System.out.print(pre+" ");
	}
	
	// 분할정복
	static void recursion(int inLeft, int inRight, int postLeft, int postRight) {
		if (inLeft > inRight) return;
		int root = postorder[postRight--];
		preorder[idx++] = root;
		if (inRight == inLeft) return;
		for (int i=inLeft; i<=inRight; i++) {
			if (inorder[i] == root) {
				recursion(inLeft, i-1, postLeft, postLeft+(i-inLeft)-1);
				recursion(i+1, inRight,  postLeft+(i-inLeft), postRight);
			}
		}
	}

}
