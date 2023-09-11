import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 박스의 개수
		
		int[] boxArr = new int[N]; // 박스 배열
		for(int i = 0; i < N; i++) {
			boxArr[i] = Integer.parseInt(br.readLine()); // 박스 크기 입력
		}
		
		Arrays.sort(boxArr); // 박스 정렬
		int inBox = 0; // 담을 수 있는 박스의 수
		for(int i = N/2 - 1, j = N - 1; i >= 0; i--) { // i = 정리후 최소개가 될 수 있는 박스의 수 이하부터 검사, j = 최대 크기의 박스 부터 검사
			if(boxArr[i] * 2 <= boxArr[j]) { // 담을 수 있다면 cnt
				inBox++;
				j--;
			}
		}
		System.out.println(N - inBox); // 총 박스의 수 - 담을 수 있는 박스의 수
	}
}
