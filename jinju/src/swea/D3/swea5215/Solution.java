package swea.D3.swea5215;

import java.io.*;

class Solution
{
    private static int[] weights;
    private static int[] cals;
    
    private static int[][] DP;
    
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
			String[] temp = br.readLine().split(" ");
            int N = Integer.parseInt(temp[0]);
            int L = Integer.parseInt(temp[1]);

            weights = new int[N + 1]; //0은 사용하지 않음
            cals = new int[N + 1]; //0은 사용하지 않음
            DP = new int[N + 1][L + 1]; //최대 칼로리가 주어졌으므로 인덱스로 사용할 수 있다.
            
            for(int i = 1; i <= N; i++) {
                String[] temp2 = br.readLine().split(" ");
                int weight = Integer.parseInt(temp2[0]);
                int cal = Integer.parseInt(temp2[1]);
                
                weights[i] = weight;
                cals[i] = cal;
            }
            
            for(int i = 1; i <= N; i++) { //아이템의 개수 만큼 순회
            	int currWeight = weights[i];
                int currCal = cals[i];
                
                for(int cal = 1; cal <= L; cal++) { //칼로리를 늘려가며 순회
                	if(currCal <= cal) { //아직 최대 칼로리에 도달하지 못했다면 더해간다. (ON)
                        DP[i][cal] = Math.max(DP[i - 1][cal - currCal] + currWeight, DP[i - 1][cal]);
                        //이전 배열값을 쓸지, 이전 배열 값에 현재의 재료를 더할지 결정한다.
                    }
                    else { //현재 칼로리가 더 크면 이전 배열 값을 이어서 쓴다. (OFF)
                        DP[i][cal] = DP[i - 1][cal];
                    }
                }
            }
            
            int answer = DP[N][L];
            
            sb.append("#" + test_case + " ").append(answer).append("\n");
		}
        
        System.out.print(sb);
	}
}
