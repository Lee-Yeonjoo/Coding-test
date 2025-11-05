import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//DP
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] price = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 1];  //dp[i]는 i개의 카드일 때, 최소 비용
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = price[1];
        for (int i = 2; i < N + 1; i++) {  //O(N^2)
            for (int j = i; j >= 1; j--) {
                dp[i] = Math.min(dp[i], price[j] + dp[i - j]);
            }
        }
        System.out.println(dp[N]);
    }
}
