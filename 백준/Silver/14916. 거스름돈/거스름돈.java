import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//DP
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = -1;  //거스름 불가능한 경우
        if (N >= 3) {
            dp[3] = -1;  //불가능한 경우
        }

        for (int i = 2; i < N + 1; i++) {
            //이미 -1인 경우
            if (dp[i] == -1) continue;

            //2원을 추가하는 경우
            if (dp[i - 2] != -1) {
                dp[i] = Math.min(dp[i], dp[i - 2] + 1);
            }

            //5원을 추가하는 경우
            if (i - 5 >= 0 && dp[i - 5] != -1) {
                dp[i] = Math.min(dp[i], dp[i - 5] + 1);
            }
        }
        System.out.println(dp[N]);
    }
}
