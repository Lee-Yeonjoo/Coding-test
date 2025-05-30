import java.io.*;
import java.util.*;

//DP
public class Main {
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);  //큰 값으로 채우기
        dp[1] = 0;
        int[] answer = new int[N + 1];
        for (int i = 2; i < N + 1; i++) {
            if (i % 3 == 0) {
                if (dp[i / 3] < dp[i]) {
                    dp[i] = dp[i / 3];
                    answer[i] = i / 3;
                }
            } 
            /*else*/if (i % 2 == 0) {  //else if로 하면 6처럼 3과 2 모두 나누어지는 수에서 3에 대해서만 연산한다 주의..ㅜㅜ
                if (dp[i / 2] < dp[i]) {
                    dp[i] = dp[i / 2];
                    answer[i] = i / 2;  //더 작으면 갱신
                }
            }
            if (dp[i - 1] < dp[i]) {
                dp[i] = dp[i - 1];
                answer[i] = i - 1;
            }
            dp[i]++;
        }

        System.out.println(dp[N]);
        //각 값으로 인덱스를 따라가며 출력
        StringBuilder sb = new StringBuilder();
        sb.append(N).append(" ");
        int idx = N;
        while (idx > 1) {
            sb.append(answer[idx]).append(" ");
            idx = answer[idx];
        }
        System.out.println(sb);
    }
}
