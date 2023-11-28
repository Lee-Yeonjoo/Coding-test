import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Character> stack = new Stack<>();
        String input;
        int N;
        int cnt = 0;

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            input = br.readLine();  //단어 입력받기
            for (int j = 0; j < input.length(); j++) { //좋은 단어인지 확인하기 위한 반복문.
                char ch = input.charAt(j);      //단어에서 한글자씩 떼서 ch에 담기
                if(stack.isEmpty())
                    stack.push(ch);
                else{
                    if(stack.peek().equals(ch)){
                        stack.pop();
                    }
                    else {
                        stack.push(ch);
                    }
                }
            }

            if(stack.isEmpty()) //좋은 단어인 경우 위의 작업이 끝나면 스택이 빈다.
                cnt++;

            stack.clear();  //단어 한개가 끝나면 꼭 스택 비우기
        }

        bw.write(""+cnt);
        bw.close();
        br.close();
    }
}