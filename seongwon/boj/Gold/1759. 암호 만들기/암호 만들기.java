import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int L, C;
	static char[] chars;

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        String str = br.readLine().replace(" ", "");
        chars = str.toCharArray();

        Arrays.sort(chars);
        
        int[] mask = new int[C];
        for(int i = C - L; i < C; i++) {
        	mask[i] = 1;
        }
        List<String> list = new ArrayList<String>();
        do{
        	String line = "";
        	int v = 0, c = 0;
        	for(int i = 0; i < C; i++) {
        		if(mask[i] == 1) {
        			line += chars[i];
        			if(chars[i] == 'a' || chars[i] == 'e'||chars[i] == 'i' || chars[i] == 'o' || chars[i] == 'u')
        				v++;
        			else c++;
        		}
        	}
        	if(v >= 1 && c >= 2)
        		list.add(line);
        }while(np(mask));
        
        Collections.sort(list);
        
        for(String s : list) {
        	System.out.println(s);
        }
    }

	private static boolean np(int[] p) {
		// 1. 꼭지점을 탐색
		int N = p.length;
		int i = N - 1;
		while (i > 0 && p[i - 1] >= p[i])
			i--;

		if (i == 0)
			return false; // 꼭지점이 첫번째인 경우 더이상의 경우의 수 없음

		// 2. 꼭지점전 값과 바꿀 한단계 높은 값을 탐색
		int j = N - 1;
		while (p[i - 1] >= p[j])
			j--;

		// 3. 교체
		swap(p, i - 1, j);

		// 4. 꼭지점 이후의 값을 오름차순 정렬
		int k = N - 1;
		while (i < k) {
			swap(p, i++, k--);
		}

		return true;
	}

	private static void swap(int[] mask, int i, int j) {
		int temp = mask[i];
		mask[i] = mask[j];
		mask[j] = temp;
	}
}