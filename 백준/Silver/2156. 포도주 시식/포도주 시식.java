import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//DP
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] wine = new int[n];
        for (int i = 0; i < n; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[n];
        dp[0] = wine[0];
        if (n >= 2) {
            dp[1] = wine[0] + wine[1];
        }
        if (n >= 3) {
            dp[2] = Math.max(dp[1], Math.max(wine[0] + wine[2], wine[1] + wine[2]));
        }
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1]);  //i번째 포도주를 안 마신 경우

            //i번째 포도주 마시고, i-1번째 포도주는 안 마신 경우
            dp[i] = Math.max(dp[i], wine[i] + dp[i - 2]);

            //i번째 포도주 마시고, i-1번째 포도주도 마시는 경우(i-2번째 포도주를 안마셔야함)
            if (i >= 3) {
                dp[i] = Math.max(dp[i], wine[i] + wine[i - 1] + dp[i - 3]);
            }
        }
        System.out.println(dp[n - 1]);
    }
}
