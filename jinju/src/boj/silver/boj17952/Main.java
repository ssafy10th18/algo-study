package boj.silver.boj17952;

import java.io.*;
import java.util.*;

public class Main {

private static final int NO_TASK = -1;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		List<Task> tasks = new ArrayList<>();
		String[] temp;
		for(int i = 0; i < T; i++) {
			temp = br.readLine().split(" ");
			
			if(temp[0].equals("0")) { //새 업무가 들어오지 않았다면
				tasks.add(new Task()); //time = -1으로 초기화한 빈 객체를 넣는다
			} else {
				int a = Integer.parseInt(temp[1]);
				int t = Integer.parseInt(temp[2]);
				
				tasks.add(new Task(i, a, t)); //새 업무 정보를 넣는다.
			}
		}
		
		int totalScore = 0;
		Stack<Task> stack = new Stack<>();
		for(Task task : tasks) {
			if(task.time == NO_TASK) {
				Task currTask;
				
				if(!stack.isEmpty()) {
					currTask = stack.pop();
				} else continue;
				
				currTask.time -= 1;
				
				if(currTask.time == 0) {
					totalScore += currTask.score;
				} else {
					stack.add(currTask);
				}
			} 
			else {
				task.time -= 1;
				
				if(task.time == 0) {
					totalScore += task.score;
				} else {
					stack.add(task);
				}
			}
			
		}
		
		System.out.println(totalScore);
	}
}

class Task {
	int num;
	int score;
	int time;
	
	public Task() {
		this.time = -1;
	}
	
	public Task(int num, int score, int time) {
		this.num = num;
		this.score = score;
		this.time = time;
	}
}