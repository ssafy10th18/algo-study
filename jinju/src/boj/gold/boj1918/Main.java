package boj.gold.boj1918;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	private static String operator = "+-*/";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String formula = br.readLine();

		Stack<String> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < formula.length(); i++) {
			String c = "" + formula.charAt(i);

			if (operator.contains(c)) { // 연산자라면
				if (c.equals("*") || c.equals("/")) {
					if (stack.isEmpty()) {
						stack.push(c);
						continue;
					}

					while(!stack.isEmpty() && "*/".contains(stack.peek())) {
						sb.append(stack.pop());
					}
					
					stack.push(c);
				}

				if (c.equals("+") || c.equals("-")) {
					if (stack.isEmpty()) {
						stack.push(c);
						continue;
					}

					while(!stack.isEmpty() && operator.contains(stack.peek())) {
						sb.append(stack.pop());
					}	
					stack.push(c);
				}
			} else if (c.charAt(0) == '(') {
				stack.push(c);
			} else if(c.charAt(0) == ')') {
				while (!stack.peek().equals("(")) {
					sb.append(stack.pop());
				}
				stack.pop(); //여는 괄호 제거
			} else if (c.charAt(0) >= 'A' && c.charAt(0) <= 'Z') { // 피연산자라면
				sb.append(c);
			}
		}

		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}

		System.out.print(sb);
	}

}
