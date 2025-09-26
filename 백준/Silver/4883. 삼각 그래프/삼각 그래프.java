import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//DP
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = 1;
        while (N != 0) {
            int[][] dp = new int[N + 1][3];
            StringTokenizer st;
            for (int i = 1; i < N + 1; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 3; j++) {
                    dp[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            //초기화
            dp[1][2] += dp[1][1];
            dp[2][0] += dp[1][1];
            int temp = Math.min(dp[1][1], dp[1][2]);
            dp[2][1] += Math.min(dp[2][0], temp);
            dp[2][2] += Math.min(dp[2][1], temp);

            //dp
            for (int i = 3; i <= N; i++) {
                dp[i][0] += Math.min(dp[i - 1][0], dp[i - 1][1]);
                int minValue = Integer.MAX_VALUE;
                for (int j = 0; j < 3; j++) {
                    minValue = Math.min(minValue, dp[i - 1][j]);
                }
                dp[i][1] += Math.min(minValue, dp[i][0]);
                dp[i][2] += Math.min(dp[i][1], Math.min(dp[i - 1][1], dp[i - 1][2]));
            }

            sb.append(T).append(". ").append(dp[N][1]).append("\n");
            N = Integer.parseInt(br.readLine());
            T++;
        }
        System.out.println(sb);
    }
}
