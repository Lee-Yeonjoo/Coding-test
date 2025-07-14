import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//DP 답 봄
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int result = Integer.parseInt(st.nextToken());

        long[][] dp = new long[N-2][21];
        //초기화 - 첫번째 연산. 범위 체크 꼭!!
        if (nums[0] + nums[1] <= 20) {
            dp[0][nums[0] + nums[1]] += 1;  //1로 지정하는게 아닌 더해야함!!@!@
        }
        if (nums[0] - nums[1] >= 0) {
            dp[0][nums[0] - nums[1]] += 1;
        }

        //두번째 연산부터
        for (int i = 1; i < N - 2; i++) {
            //0~20 중 나올 수 있는 수의 개수 더하기
            for (int j = 0; j <= 20; j++) {
                if (dp[i - 1][j] != 0) {  //연산 전의 값이 존재하는 것만 구하면 됨
                    //j값에 더하고 뺀 수가 0~20 범위 내라면 경우의 수 더하기
                    int x = j + nums[i + 1];
                    int y = j - nums[i + 1];

                    if (x <= 20) {
                        dp[i][x] += dp[i - 1][j];
                    }
                    if (y >= 0) {
                        dp[i][y] += dp[i - 1][j];
                    }
                }
            }
        }
        System.out.println(dp[N - 3][result]);
    }
}
