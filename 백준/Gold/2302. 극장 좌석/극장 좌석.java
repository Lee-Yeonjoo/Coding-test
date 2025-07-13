import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//DP
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        boolean[] isVip = new boolean[N + 1];
        //vip 번호 표시
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(br.readLine());
            isVip[num] = true;
        }

        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < N + 1; i++) {
            //i번째 좌석이 vip거나 그 앞자리가 vip인 경우 -> 자리를 못 바꿈
            if (isVip[i] || isVip[i - 1]) {
                dp[i] = dp[i - 1];
            } else {  //그 외에는 자리를 바꿀 수 있음
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }

        System.out.println(dp[N]);
    }
}