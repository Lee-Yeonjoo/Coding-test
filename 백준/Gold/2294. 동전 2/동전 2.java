import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(coins);  //동전의 가치를 오름차순 정렬

        int[] dp = new int[k+1];
        Arrays.fill(dp, 10001);  //최소값으로 갱신해야 하므로 큰 값으로 초기화
        dp[0] = 0;  //0원이 되려면 동전의 개수는 0개

        //바텀업 방식
        for (int i = 1; i < k + 1; i++) {
            //가장 작은 가치의 동전보다 작은 가치는 만들 수 없으므로 continue
            if (i < coins[0]) {
                continue;
            }

            //각 동전의 가치들에 대해 탐색
            for (int coin : coins) {
                //i가 동전의 가치보다 작으므로 break
                if (i < coin) {
                    break;
                }

                //i가 동전의 가치보다 크므로 최소값 구하기
                dp[i] = Math.min(dp[i], dp[i - coin]);
            }

            dp[i] += 1;
        }

        if (dp[k] >= 10001) {
            System.out.println(-1);
        }
        else System.out.println(dp[k]);
    }
}
