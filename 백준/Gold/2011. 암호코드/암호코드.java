import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//DP
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String code = br.readLine();
        int[] dp = new int[code.length()];  //i번째 수일 때 해석되는 가짓수
        if (code.charAt(0) != '0') {
            dp[0] = 1;  //초기화 - 첫번째 수가 0이 아니라면 가짓수는 무조건 1가지
        } else {
            System.out.println(0);
            return;
        }

        for (int i = 1; i < code.length(); i++) {
            if (code.charAt(i) != '0') {  //i번째 수가 0이 아니라면, dp(i-1)값 더하기
                dp[i] += dp[i - 1] % 1000000;
            }

            //i-1번째 수가 0이 아니고, i-1번째 수와 i번째 수를 합쳤을 때, 0보다 크고, 26이하인 수
            if (code.charAt(i - 1) != '0' && (code.charAt(i - 1) - '0') * 10 + (code.charAt(i) - '0') <= 26) {
                if (i >= 2) {
                    dp[i] += dp[i - 2] % 1000000;
                } else {  //i=1일 땐, dp[i-2]값이 없으므로 +1
                    dp[i]++;
                }
            } else {  //위에 해당이 안되는 경우
                if (code.charAt(i) == '0') {  //0이 나오면 i-1번째 수와 합쳤을 때, 26이하여야 함
                    System.out.println(0);
                    return;
                }
            }
        }
        System.out.println(dp[code.length() - 1] % 1000000);
    }
}
