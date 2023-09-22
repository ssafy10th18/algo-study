import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static Stack<Character> stack;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        String s = br.readLine();

        stack = new Stack<Character>();

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if(c == '<') {
                emptyStack();
                stack.push(c);
                sb.append('<');
            }
            else if(c == '>') {
                stack.pop();
                sb.append('>');
            }
            else {
                if(!stack.isEmpty() && stack.peek() == '<') {
                    sb.append(c);
                }
                else if(c == ' ') {
                    emptyStack();
                    sb.append(' ');
                }
                else {
                    stack.push(c);
                }
            }
        }

        emptyStack();
        System.out.println(sb);

    }

    public static void emptyStack() {
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
    }
}
