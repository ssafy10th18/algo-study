package boj.gold.boj17135;

import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {

	private static final int NUM = 3;

	private static int N;
	private static int M;
	private static int D;
	
	private static int result = 0;
	private static int remain = 0;

	private static int[] combinations;
	private static List<Point> archers;
	private static List<Point> enemies;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");

		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		D = Integer.parseInt(temp[2]);

		combinations = new int[M];

		enemies = new ArrayList<>(); // 원본 리스트
		
		List<Point> copied = new ArrayList<>(); // 매 시도마다의 적 정보를 복사할 리스트

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				int next = Integer.parseInt(st.nextToken());
				if (next == 1) {
					enemies.add(new Point(j, i));
					result += 1;
				}
			}
		}

		//성벽에 가까운 순으로 정렬
		Collections.sort(enemies, (o1, o2) -> (Integer.compare(o2.y, o1.y)));
		
		// 1.궁수 3명 배치 => np
		for (int i = M - 1; i > M - 1 - NUM; i--) {
			combinations[i] = 1;
		}

		int answer = 0;
		do {
			remain = 0;
			initArchers();
			
			for (Point e : enemies) {
				copied.add((Point) e.clone());
			}
			
			// 2.배치된 궁수마다 격퇴할 수 있는 적 세기
			while(!isAllRemoved(copied)) { //모든 적이 사라질 때까지
				// 3.공격받은 적 제거 
				removeEnemies(copied);
	
				// 4.턴 종료 후 적 이동 (배열 row 조작)
				copied = move(copied);
			}
			
			copied.clear();
			answer = Math.max(answer, result - remain);
		} while (nextPermutation(combinations));

		System.out.println(answer);
	}

	private static void initArchers() {
		archers = new ArrayList<>();
		
		for(int i = 0; i < combinations.length; i++) {
			if(combinations[i] == 1) {
				archers.add(new Point(i, N - 1));
			}
		}
	}

	private static boolean isAllRemoved(List<Point> copied) {
		return copied.size() == 0;
	}

	private static void removeEnemies(List<Point> enemies) { //TODO: 이 모듈만 수정
		ArrayList<Point> target = new ArrayList<>();
		
		for(Point a : archers) {
			Point minEnemy = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
			int minDist = Integer.MAX_VALUE;
			for(Point e : enemies) {
				int dist = Math.abs(a.x - e.x) + Math.abs(a.y - e.y);
				
				if(dist <= D && dist < minDist) { //거리가 D 이하이면서도
					minEnemy = e; //거리가 '최소'인 적을 기록
					minDist = dist; //최소 거리 갱신
				} else if(dist == minDist) { //만약 최소 거리가 같다면
					minEnemy = (e.x < minEnemy.x) ? e : minEnemy; //더 왼쪽에 있는 것을 선택
				}
			}

			if(minEnemy.x != Integer.MAX_VALUE && minEnemy.y != Integer.MAX_VALUE) {
				target.add(minEnemy); //가장 왼쪽의 적 후보 
			}
		}
		
		for(Point t : target) {
			enemies.remove(t); //후보로 담긴 적을 모두 삭제
		}
	}
	
	private static List<Point> move(List<Point> enemies) {
		List<Point> newEnemies = new ArrayList<>();
		
		for (Point e : enemies) {
			if(e.y + 1 < N) { //한 보 전진 가능하다면
				newEnemies.add(new Point(e.x, e.y + 1));
			} else remain += 1; //성벽을 넘었다면 제외시킨다.
		}
		
		return newEnemies;
	}

	private static boolean nextPermutation(int[] arr) {
		int swapIndex = -1;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i - 1] < arr[i])
				swapIndex = i - 1;
		}

		if (swapIndex == -1)
			return false;

		int largerIndex = -1;
		for (int j = 1; j < arr.length; j++) {
			if (arr[swapIndex] < arr[j])
				largerIndex = j;
		}

		swap(arr, swapIndex, largerIndex);

		int q = arr.length - 1;
		for (int p = swapIndex + 1; p < q; p++) {
			swap(arr, p, q);
			q--;
		}

		return true;
	}

	private static void swap(int[] arr, int p, int q) {
		int temp = arr[p];
		arr[p] = arr[q];
		arr[q] = temp;
	}

}