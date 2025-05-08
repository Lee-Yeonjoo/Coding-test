import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] sequence = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        //0열은 가장 긴 증가하는 부분 수열의 크기를 저장, 1열은 가장 긴 감소(or 증가했다 감소 중)하는 부분 수열의 크기를 저장
        int[][] dp = new int[N][2];
        //자기 자신을 포함하는 경우를 저장하기 때문에 최소 길이는 1 -> 1로 초기화
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], 1);
        }

        int maxLength = 1;  //최소 1은 보장되므로 1로 초기화
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                //증가 중인 경우
                if (sequence[i] > sequence[j]) {
                    dp[i][0] = Math.max(dp[i][0], dp[j][0] + 1);
                } else if (sequence[i] < sequence[j]) {  //증가했다 이제 감소하는 경우 vs 감소 중인 경우
                    dp[i][1] = Math.max(dp[i][1], Math.max(dp[j][0], dp[j][1]) + 1);
                }

                maxLength = Math.max(maxLength, Math.max(dp[i][0], dp[i][1]));
            }
        }

        System.out.println(maxLength);
    }
}
