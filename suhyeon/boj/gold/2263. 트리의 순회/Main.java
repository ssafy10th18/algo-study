import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /*
     * 인오더 : 왼쪽 서브트리 -> 루트 -> 오른쪽 서브트리
     * 포스트오더 : 왼쪽 서브트리 -> 오른쪽 서브트리 -> 루트
     */

    static int[] inOrder;
    static int[] inOrderIndex;
    static int[] postOrder;
    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        inOrder = new int[n];
        postOrder = new int[n];
        inOrderIndex = new int[n + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i++) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < n; i++) {
            inOrderIndex[inOrder[i]] = i;
        }

        getPreOrder(0, n-1, 0, n-1);
        System.out.println(sb);
    }

    public static void getPreOrder(int inStart, int inEnd, int postStart, int postEnd) {

        if(inStart > inEnd || postStart > postEnd) {
            return;
        }

        // 1. 루트 노드 (포스트오더의 제일 마지막 값)를 구한다.
        int root = postOrder[postEnd];
        sb.append(root).append(" ");

        // 2. 루트 노드의 인덱스 값을 구하여 왼쪽 서브트리와 오른쪽 서브트리를 나눈다.
        int rootIndex = inOrderIndex[root];
        int leftNodeLength = rootIndex - inStart;

        // 3. 나눈 트리에서 루트노드를 찾는다.
        getPreOrder(inStart, rootIndex-1, postStart, postStart+leftNodeLength-1);

        getPreOrder(rootIndex+1, inEnd, postStart+leftNodeLength, postEnd-1);

    }
}
