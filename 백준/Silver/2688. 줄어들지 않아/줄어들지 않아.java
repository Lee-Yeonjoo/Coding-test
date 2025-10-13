import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//DP
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[][] dp = new long[65][10];
        Arrays.fill(dp[1], 1);  //초기화
        for (int i = 2; i <= 64; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k <= j; k++) {
                    dp[i][k] += dp[i-1][j];
                }
            }
        }

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            long sum = 0;
            for (int j = 0; j <= 9; j++) {
                sum += dp[n][j];
            }
            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }
}
