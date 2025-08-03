import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//DP
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[][] dp = new long[32][32];  //행은 남은 w알약의 개수, 열은 남은 H알약의 개수, dp[i][j]는 가능한 문자열의 개수
        StringBuilder sb = new StringBuilder();

        //dp 초기화
        Arrays.fill(dp[0], 1);

        //먼저 다 구하기
        for (int i = 1; i <= 30; i++) {
            for (int j = 0; j <= 30; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i-1][j + 1];
                } else dp[i][j] = dp[i - 1][j + 1] + dp[i][j - 1];
            }
        }

        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            sb.append(dp[N][0]).append("\n");
        }
        System.out.println(sb);
    }
}
