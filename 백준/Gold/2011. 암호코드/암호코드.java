import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String str = st.nextToken();

        long[] dp = new long[str.length() + 1];

        if (str.charAt(0) == '0') {
            System.out.println(0);
            return;
        } else {
            dp[0] = 1;
            dp[1] = 1;

            for (int i = 2; i <= str.length(); i++) {
                if (str.charAt(i - 1) == '0') {
                    if (str.charAt(i - 2) == '1' || str.charAt(i - 2) == '2') {
                        dp[i] = dp[i - 2] % 1000000;
                    } else break;
                } else {
                    int result = Integer.parseInt(str.substring(i - 2, i));
                    if (result < 27 && result > 9) {
                        dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000;
                    } else {
                        dp[i] = dp[i-1] % 1000000;
                    } 
                } 
            }
        }

        System.out.println(dp[str.length()] % 1000000);
    }
}
