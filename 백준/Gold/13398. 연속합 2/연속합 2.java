import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//DP
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int max = nums[0];  //0으로 초기화하면 안됨! n이 1이고, 음수일 때 그 수가 max값인데, 0이 출력되기 때문
        int[][] dp = new int[2][n];
        dp[0][0] = nums[0];
        dp[1][0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = Math.max(dp[0][i - 1] + nums[i], nums[i]);  //제거 안했을 때의 최대 연속합
            dp[1][i] = Math.max(dp[0][i - 1], dp[1][i - 1] + nums[i]);  //제거 했을 때의 최대 연속합
            max = Math.max(max, Math.max(dp[0][i], dp[1][i]));
        }
        System.out.println(max);
    }
}
