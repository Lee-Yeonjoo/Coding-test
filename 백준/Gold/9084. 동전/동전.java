import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//DP 답 봄
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] coins = new int[N];
            for (int j = 0; j < N; j++) {
                coins[j] = Integer.parseInt(st.nextToken());
            }
            int M = Integer.parseInt(br.readLine());
            int[] dp = new int[M + 1];  //j원이 되는 경우의 수를 기록

            //동전 한 종류씩 추가하면서 j원에 대한 경우의 수 구하기 
            for (int coin : coins) {
                for (int j = coin; j < M + 1; j++) {
                    if (j - coin == 0) {  //j원이 그 동전의 값과 같으면 경우의 수 추가
                        dp[j] += 1;
                    } else {  //같지 않다면, 동전의 값을 뺐을 때의 경우의 수를 더해줌
                        dp[j] += dp[j - coin];
                    }
                }
            }
            System.out.println(dp[M]);
        }
    }
}
