import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] schedule = new int[N][2];
        //뒤에서부터 탐색할거라서 뒤 스케줄부터 저장
        for (int i = N-1; i >= 0; i--) {
            st = new StringTokenizer(br.readLine());
            schedule[i][0] = Integer.parseInt(st.nextToken());
            schedule[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        //1보다 크면 그날 상담을 끝낼 수 없다.
        if (schedule[0][0] <= 1) {
            dp[0] = schedule[0][1];
        }
        for (int i = 1; i < N; i++) {
            //-1보다 작을 경우, 해당 상담은 N일을 넘기기 때문에 포함 불가
            if (i - schedule[i][0] < -1) {
                dp[i] = dp[i - 1];
            } else {
                //-1이면 해당 상담이 딱 N일까지 가능한 경우이다.
                if (i - schedule[i][0] == -1) {
                    dp[i] = Math.max(dp[i - 1], schedule[i][1]);
                } else dp[i] = Math.max(dp[i - 1], dp[i - schedule[i][0]] + schedule[i][1]);
            }
        }

        System.out.println(dp[N-1]);
    }
}
