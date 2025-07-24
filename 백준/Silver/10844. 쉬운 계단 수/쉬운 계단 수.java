import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//DP
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N + 1][10];  //개수가 21억을 넘을 수 있으므로 long타입
        Arrays.fill(dp[1], 1L);

        //dp[i][j] = 길이가 i이고, 앞자리 수가 j인 계단수의 개수
        for (int i = 2; i <= N; i++) {
            //0번째, 9번째는 사이드라 따로 진행
            dp[i][0] = dp[i - 1][1];
            dp[i][9] = dp[i - 1][8];
            for (int j = 1; j <= 8; j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1000000000;  //오버플로우 때문에 나눠줘야 함
            }
        }

        //계단수 개수 세기
        long sum = 0;
        for (int i = 1; i <= 9; i++) {
            sum += dp[N][i];  //이건 long타입이라 담을 수 있다. 오버플로우x
        }
        System.out.println(sum % 1000000000);  //마지막 답에서 또 나눠줘야 답이 제대로 나오무ㅜㅜ
        br.close();
    }
}
