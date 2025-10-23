import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//DP
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] dp = new int[K + 1][N + 1];  //경우의 수(행=더해지는 수의 개수, 열=합)
        Arrays.fill(dp[1], 1);

        for (int i = 2; i < K + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                if (j > 0) {
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1000000000;
                } else dp[i][j] = dp[i - 1][j];
            }
        }
        System.out.println(dp[K][N] % 1000000000);
    }
}
