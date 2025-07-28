import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//구현, 브루트 포스 - 중복순열
public class Main {
    static int N;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            dfs(1, "1", 2, 0);
            sb.append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    static void dfs(int value, String str, int depth, int symbol) {
        //식이 완성된 경우
        if (depth == N + 1) {
            if (value == 0) {  //결과가 0이면 출력 후 리턴
                sb.append(str).append("\n");
                return;
            } else return;
        }

        //0=공백, 1=+, 2=- -> 아스키 순서대로 탐색
        StringBuilder sb2;
        int temp = value;
        for (int i = 0; i < 3; i++) {
            sb2 = new StringBuilder(str);
            if (i == 0) {
                if (symbol == 0) {
                    value = temp * 10 + depth;
                } else if (symbol == 1) {  //이전 계산이 + -> 다시 빼서 복구하고, depth-1에 depth 붙여서 더하기
                    value = (temp - (depth - 1)) + ((depth - 1) * 10 + depth);
                } else {  //이전 계산이 - -> 더해서 복구하고, depth-1에 depth 붙여서 빼기
                    value = (temp + (depth - 1)) - ((depth - 1) * 10 + depth);
                }
                sb2.append(" ").append(depth);
            } else if (i == 1) {
                value = temp + depth;
                sb2.append("+").append(depth);
            } else {
                value = temp - depth;
                sb2.append("-").append(depth);
            }

            dfs(value, sb2.toString(), depth + 1, i);
        }
    }
}
