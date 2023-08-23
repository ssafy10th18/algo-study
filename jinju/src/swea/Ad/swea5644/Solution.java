package swea.Ad.swea5644;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	private static final int STOP = 0;
	private static final int UP = 1;
	private static final int RIGHT = 2;
	private static final int DOWN = 3;
	private static final int LEFT = 4;
	
	private static int[] dr = {0, -1, 0, 1, 0};
	private static int[] dc = {0, 0, 1, 0, -1};
	
	@SuppressWarnings("unchecked")
	private static List<BatteryCharger>[][] field = new ArrayList[10][10];
	private static int[][] moves;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			String[] temp = br.readLine().split(" ");

			int M = Integer.parseInt(temp[0]); // 이동 수
			int A = Integer.parseInt(temp[1]); // 배터리 충전소의 개수

			moves = new int[2][M + 1]; // A & B의 M + 1개의 이동 경로 (0초 포함)

			moves[0][0] = 0;
			moves[1][0] = 0;
			
			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 1; j <= M; j++) {
					moves[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					field[i][j] = new ArrayList<>();
				}
			}

			for (int i = 0; i < A; i++) {
				temp = br.readLine().split(" ");

				int x = Integer.parseInt(temp[0]);
				int y = Integer.parseInt(temp[1]);
				int c = Integer.parseInt(temp[2]);
				int p = Integer.parseInt(temp[3]);

				//주의: x & y 뒤바뀜
				init(new BatteryCharger(x - 1, y - 1, 0, c, p));
			}
			
			
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 10; j++) {
					System.out.print(field[i][j].size() + " ");
				}
				System.out.println();
			}
						
			Customer a = new Customer(0, 0, 0);
			Customer b = new Customer(9, 9, 0);
			
			for(int t = 0; t <= M; t++) {
				int aMove = moves[0][t]; //t초의 이동 정보
				int bMove = moves[1][t];
				
				 a.row = a.row + dr[aMove];
				 a.col = a.col + dc[aMove];
				 
				 b.row = b.row + dr[bMove];
				 b.col = b.col + dc[bMove];
				 
				 checkBatteryCharger(a, b);
				 
				 System.out.println( "시간: " + t + " | " + "A: " + a.charge + " | " + "B: " + b.charge);
			}

			System.out.println(a.charge + ", " + b.charge);
			
			int answer = a.charge + b.charge;
			
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}

		System.out.println(sb);
	}

	private static void checkBatteryCharger(Customer a, Customer b) {
		boolean isAOnCharger = isCharger(a.row, a.col);
		boolean isBOnCharger = isCharger(b.row, b.col);
		
		//서있는 곳이 bc가 아니라면 리턴한다.
		if(!isAOnCharger && !isBOnCharger) {
			return;
		}
		
		if(isAOnCharger && !isBOnCharger) {
			charge(a);
		} 

		if(!isAOnCharger && isBOnCharger) {
			charge(b);
		}

		if(isAOnCharger && isBOnCharger) {
			List<BatteryCharger> chargersA = field[a.row][a.col];
			List<BatteryCharger> chargersB = field[b.row][b.col];
			
			int maxP = 0;
			BatteryCharger maxA = null;
			for(BatteryCharger bc : chargersA) {
				if(bc.p > maxP) {
					maxP = bc.p;
					maxA = bc;
				}
			}
			
			maxP = 0;
			BatteryCharger maxB = null;
			for(BatteryCharger bc : chargersB) {
				if(bc.p > maxP) {
					maxP = bc.p;
					maxB = bc;
				}
			}
			
			//3.두 명이 서로 다른 bc에 서있음 => 각자 더함
			//TODO: 여기 p값 비교가 너무 하드코딩 아닌지?
			if(maxA.p == maxB.p && maxA != maxB) { //리스트에 담긴 최대 bc의 레퍼런스 값이 다르다
				a.charge += maxA.p / 2;
				b.charge += maxB.p / 2;
			} else { //4.두 명이 한 bc에 서있다면 (레퍼런스 값 동일)
				
				//4-1. 레퍼런스가 같지 않으면서 충전량이 최대인 서로 다른 충전기를 찾는다.
				int currCharge = maxA.p / 2 + maxB.p / 2; //유망한 충전량
				
				boolean found = false;
				for(int i = 0; i < chargersA.size(); i++) {
					for(int j = 0; j < chargersB.size(); j++) {
						BatteryCharger newA = chargersA.get(i);
						BatteryCharger newB = chargersB.get(j);
						if(newA != newB &&
								currCharge < newA.p + newB.p) {
							found = true;
							maxA = newA;
							maxB = newB;
						}
					}
				}
				
				if(found) { //새로 찾은 최대 충전량을 더해준다.
					a.charge += maxA.p;
					b.charge += maxB.p;
				} else {
					//4-2. 그게 없다면 1/2 충전을 한다.
					a.charge += maxA.p / 2;
					b.charge += maxB.p / 2;
				}
			}
		}
	}

	private static void charge(Customer c) {
		List<BatteryCharger> chargers = field[c.row][c.col];
		
		//1.사용자가 한 bc에만 서있음 => 그냥 계산
		if(chargers.size() == 1) {
			c.charge += chargers.get(0).p;
		}
		else { //2.사용자가 두 가지 이상 bc에 서있음 => 가장 충전량 높은 bc 채택
			int maxP = 0;
			for(BatteryCharger bc : chargers) {
				maxP = Math.max(bc.p, maxP);
			}
			c.charge += maxP;
		}
	}

	private static boolean isCharger(int row, int col) {
		return (field[row][col].size() != 0);
	}

	private static void init(BatteryCharger bc) {
		int xStart = bc.col - bc.scope; //주의: x & y 뒤바뀜
		int xEnd = bc.col + bc.scope;
		int k = bc.scope;
		
		for (int i = xStart; i <= xEnd; i++) {
			int yStart = bc.row - bc.scope + k;
			int yEnd = bc.row + bc.scope - k;
			
			for (int j = yStart; j <= yEnd; j++) {
				if (i >= 0 && j >= 0 && i < 10 && j < 10)
					field[i][j].add(bc);
			}
			
			if (i < (xStart + xEnd) / 2)
				k--;
			else
				k++;
		}
	}
}

class Customer {
	int row;
	int col;
	int charge;

	public Customer(int row, int col, int charge) {
		this.row = row;
		this.col = col;
		this.charge = charge;
	}
}

class BatteryCharger {
	int row;
	int col;
	int status;
	int scope;
	int p;

	public BatteryCharger(int row, int col, int status, int scope, int p) {
		this.row = row;
		this.col = col;
		this.status = status;
		this.scope = scope;
		this.p = p;
	}
}