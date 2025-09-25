import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//DP
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int D = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[D + 1][2];  //방정식의 계수를 저장
        dp[1][0] = 1;
        dp[2][1] = 1;
        for (int i = 3; i <= D; i++) {
            dp[i][0] = dp[i - 2][0] + dp[i - 1][0];
            dp[i][1] = dp[i - 2][1] + dp[i - 1][1];
        }

        //식이 성립하는 A, B를 구하기
        for (int a = 1; a <= 500000; a++) {
            if ((K - (dp[D][0] * a)) % dp[D][1] == 0) {
                System.out.println(a);
                System.out.println((K - (dp[D][0] * a)) / dp[D][1]);
                break;
            }
        }
    }
}
