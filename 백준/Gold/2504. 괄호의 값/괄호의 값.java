import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//스택, 구현
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String parentheses = br.readLine();
        Stack<Character> stack = new Stack<>();

        int temp = 1;  //임시로 계산 중간값을 저장하는 변수
        boolean isClosedFirst = false; //열린 괄호 이후로 가장 먼저 닫히는 괄호인지 여부 표시
        int answer = 0;  //답. 최종 괄호값

        //입력받은 괄호열을 한 문자씩 순차적으로 탐색
        for (int i = 0; i < parentheses.length(); i++) {
            char x = parentheses.charAt(i);

            //열린 괄호라면 스택에 push하고, temp에 2나 3 곱한다. 이후 오는 닫힌 괄호는 처음이니까 isClosedFirst를 true로 변경
            if (x == '(') {
                stack.push(x);
                temp *= 2;
                isClosedFirst = true;

            } else if (x == '[') {
                stack.push(x);
                temp *= 3;
                isClosedFirst = true;

            } //닫힌 괄호라면, stack의 peek값이 짝이 맞는 열린 괄호라면, pop한 후, 만약 처음 닫힌 괄호면 answer에 temp값 더하기. 이후 오는 닫힌 괄호는 처음이 아니기에 false로 변경
            else if (x == ')') {
                if (!stack.isEmpty() && stack.peek().equals('(')) {
                    stack.pop();
                    if (isClosedFirst) {
                        answer += temp;
                        isClosedFirst = false;
                    }
                    temp /= 2;  //괄호가 닫혔기 때문에 temp값에서 2나 3을 나눠주어야 한다. 분배 법칙처럼 계산하기 위함
                } else { //stack의 peek값이 올바르지 않기 때문에 괄호열 성립x
                    System.out.println(0);
                    return;
                }

            } else if (x == ']') {
                if (!stack.isEmpty() && stack.peek().equals('[')) {
                    stack.pop();
                    if (isClosedFirst) {
                        answer += temp;
                        isClosedFirst = false;
                    }
                    temp /= 3;
                } else {
                    System.out.println(0);
                    return;
                }
            }
        }

        //스택이 비어있어야 올바른 괄호열
        if (stack.isEmpty()) {
            System.out.println(answer);
        }
        else System.out.println(0);
    }
}
