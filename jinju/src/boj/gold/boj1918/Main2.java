package boj.gold.boj1918;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main2 {

	private static String operator = "+-*/";
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String formula = br.readLine();
		
		Stack<String> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
		boolean isBracket = false;
		for(int i = 0; i < formula.length(); i++) {
			String c = "" + formula.charAt(i);
			
			if(operator.contains(c)) { //연산자라면
				if(c.equals("*") || c.equals("/")) {	
					if(stack.isEmpty()) {
						stack.push(c);
						continue;
					}
					
					String curr = stack.peek(); //우선순위가 같다면 비운다. 괄호를 닫았어도 비운다.
					if(curr.equals("*") || curr.equals("/") || isBracket) {
						while(!stack.isEmpty()) {
							String p = stack.peek();
							if(p.equals("+") || p.equals("-")) { 
								break; //괄호 밖 & 우선순위가 낮으면 멈춘다.
							}
							sb.append(stack.pop());
						}
						isBracket = false;
					}
					stack.push(c);
				}

				if(c.equals("+") || c.equals("-")) {
					if(stack.isEmpty()) {
						stack.push(c);
						continue;
					}
					
					if(isBracket) { //괄호가 나타난 적 있다면
						isBracket = false;
						stack.push(c); //우선순위를 무시하고 넣는다.
						continue;
					}
					
					while(!stack.isEmpty()) {
						sb.append(stack.pop());
					}
					stack.push(c);
				}
			} else if(c.charAt(0) >= 'A' && c.charAt(0) <= 'Z') { //피연산자라면
				sb.append(c);
			} else if(c.charAt(0) == '(' || c.charAt(0) == ')'){ //괄호가 나타났다면
				isBracket = true;
			}
		}
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		System.out.print(sb);
	}

}
